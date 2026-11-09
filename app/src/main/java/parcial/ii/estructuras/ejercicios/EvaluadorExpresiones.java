package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.estructuras.Pila;

import java.util.Map;

/**
 * Conversión de expresiones infijas a postfijas (algoritmo de la pila de operadores) y
 * evaluación postfija con una pila, dejando la tabla paso a paso.
 */
public final class EvaluadorExpresiones {

    /** Una fila de la tabla: elemento procesado, operación y contenido de la pila (fondo → cima). */
    public record Fila(String elemento, String operacion, String pila) {
    }

    private EvaluadorExpresiones() {
    }

    private static boolean esOperador(String t) {
        return t.length() == 1 && "+-*/^".contains(t);
    }

    private static int precedencia(String op) {
        return switch (op) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }

    private static boolean asociativoDerecha(String op) {
        return op.equals("^");
    }

    /** Separa la expresión en tokens: números/variables, operadores y paréntesis. */
    public static Cola<String> tokenizar(String expresion) {
        Cola<String> tokens = new Cola<>();
        StringBuilder actual = new StringBuilder();
        for (char ch : expresion.toCharArray()) {
            if (Character.isWhitespace(ch)) {
                vaciar(actual, tokens);
            } else if ("+-*/^()".indexOf(ch) >= 0) {
                vaciar(actual, tokens);
                tokens.encolar(String.valueOf(ch));
            } else {
                actual.append(ch);
            }
        }
        vaciar(actual, tokens);
        return tokens;
    }

    private static void vaciar(StringBuilder sb, Cola<String> tokens) {
        if (sb.length() > 0) {
            tokens.encolar(sb.toString());
            sb.setLength(0);
        }
    }

    /**
     * Convierte infijo → postfijo. Si 'filas' no es null, se agrega por cada token una fila con
     * la acción y el contenido de la pila de operadores.
     */
    public static Cola<String> infijaAPostfija(String expresion, Cola<Fila> filas) {
        Cola<String> salida = new Cola<>();
        Pila<String> operadores = new Pila<>();
        Cola<String> tokens = tokenizar(expresion);
        while (!tokens.estaVacia()) {
            String t = tokens.desencolar();
            String accion;
            if (t.equals("(")) {
                operadores.apilar(t);
                accion = "Apilar '('";
            } else if (t.equals(")")) {
                StringBuilder sacados = new StringBuilder();
                while (!operadores.estaVacia() && !operadores.verCima().equals("(")) {
                    String op = operadores.desapilar();
                    salida.encolar(op);
                    sacados.append(op).append(' ');
                }
                if (operadores.estaVacia()) {
                    throw new IllegalArgumentException("Paréntesis desbalanceados");
                }
                operadores.desapilar();
                accion = "Desapilar hasta '(' → salida: " + (sacados.length() == 0 ? "nada" : sacados.toString().trim());
            } else if (esOperador(t)) {
                StringBuilder sacados = new StringBuilder();
                while (!operadores.estaVacia() && esOperador(operadores.verCima())
                        && (precedencia(operadores.verCima()) > precedencia(t)
                        || (precedencia(operadores.verCima()) == precedencia(t) && !asociativoDerecha(t)))) {
                    String op = operadores.desapilar();
                    salida.encolar(op);
                    sacados.append(op).append(' ');
                }
                operadores.apilar(t);
                accion = (sacados.length() == 0 ? "" : "Pasar a salida: " + sacados.toString().trim() + "; ")
                        + "Apilar '" + t + "'";
            } else {
                salida.encolar(t);
                accion = "Operando → a la salida";
            }
            if (filas != null) {
                filas.encolar(new Fila(t, accion + " | salida: " + salida.mostrar(s -> s).replace(" → ", " ")
                        .replace("(vacía)", ""), operadoresComoTexto(operadores)));
            }
        }
        while (!operadores.estaVacia()) {
            String op = operadores.desapilar();
            if (op.equals("(")) {
                throw new IllegalArgumentException("Paréntesis desbalanceados");
            }
            salida.encolar(op);
            if (filas != null) {
                filas.encolar(new Fila("(fin)", "Pasar '" + op + "' a la salida | salida: "
                        + salida.mostrar(s -> s).replace(" → ", " "), operadoresComoTexto(operadores)));
            }
        }
        return salida;
    }

    private static String operadoresComoTexto(Pila<String> pila) {
        return pilaFondoACima(pila);
    }

    /** Texto "fondo ... cima" de una pila, sin alterarla. */
    private static <T> String pilaFondoACima(Pila<T> pila) {
        if (pila.estaVacia()) {
            return "[ ]";
        }
        Pila<T> aux = new Pila<>();
        while (!pila.estaVacia()) {
            T x = pila.desapilar();
            aux.apilar(x);
        }
        StringBuilder sb = new StringBuilder("[ ");
        while (!aux.estaVacia()) {
            T x = aux.desapilar();
            sb.append(x).append(' ');
            pila.apilar(x);
        }
        return sb.append("]").toString();
    }

    private static String pilaNumericaFondoACima(Pila<Double> pila) {
        Pila<String> texto = new Pila<>();
        Pila<Double> aux = new Pila<>();
        while (!pila.estaVacia()) {
            aux.apilar(pila.desapilar());
        }
        while (!aux.estaVacia()) {
            double x = aux.desapilar();
            pila.apilar(x);
            texto.apilar(formato(x));
        }
        return pilaFondoACima(texto);
    }

    /**
     * Evalúa una expresión postfija con los valores de las variables dados.
     * Cada fila indica el elemento procesado, la operación realizada y la pila resultante.
     */
    public static double evaluarPostfija(Cola<String> postfija, Map<String, Double> variables, Cola<Fila> filas) {
        Pila<Double> pila = new Pila<>();
        Cola<String> copia = new Cola<>();
        int n = postfija.tamanio();
        for (int i = 0; i < n; i++) {
            String t = postfija.desencolar();
            postfija.encolar(t);
            copia.encolar(t);
        }
        while (!copia.estaVacia()) {
            String t = copia.desencolar();
            String operacion;
            if (esOperador(t)) {
                double b = pila.desapilar();
                double a = pila.desapilar();
                double r = aplicar(t, a, b);
                pila.apilar(r);
                operacion = "Desapilar " + formato(b) + " y " + formato(a) + " → " + formato(a) + " " + t + " " + formato(b)
                        + " = " + formato(r) + "; apilar " + formato(r);
            } else {
                double valor = variables.containsKey(t) ? variables.get(t) : Double.parseDouble(t);
                pila.apilar(valor);
                operacion = "Operando: apilar " + formato(valor);
            }
            if (filas != null) {
                filas.encolar(new Fila(t, operacion, pilaNumericaFondoACima(pila)));
            }
        }
        if (pila.tamanio() != 1) {
            throw new IllegalArgumentException("Expresión inválida");
        }
        return pila.desapilar();
    }

    private static double aplicar(String op, double a, double b) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("División por cero");
                }
                yield a / b;
            }
            case "^" -> Math.pow(a, b);
            default -> throw new IllegalArgumentException("Operador desconocido: " + op);
        };
    }

    /** Formatea con hasta 4 decimales, sin ceros de más. */
    public static String formato(double d) {
        if (d == Math.rint(d)) {
            return String.valueOf((long) d);
        }
        return String.valueOf(Math.round(d * 10000.0) / 10000.0);
    }
}
