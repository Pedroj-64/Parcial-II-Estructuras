package parcial.ii.estructuras.ejercicios;

import java.util.Map;
import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.estructuras.Pila;

public final class EvaluadorExpresiones {

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

    public static Cola<String> infijaAPostfija(String expresion) {
        Cola<String> salida = new Cola<>();
        Pila<String> operadores = new Pila<>();
        Cola<String> tokens = tokenizar(expresion);
        while (!tokens.estaVacia()) {
            String t = tokens.desencolar();
            if (t.equals("(")) {
                operadores.apilar(t);
            } else if (t.equals(")")) {
                while (!operadores.estaVacia() && !operadores.verCima().equals("(")) {
                    salida.encolar(operadores.desapilar());
                }
                if (operadores.estaVacia()) {
                    throw new IllegalArgumentException("Paréntesis desbalanceados");
                }
                operadores.desapilar();
            } else if (esOperador(t)) {
                while (!operadores.estaVacia() && esOperador(operadores.verCima())
                        && (precedencia(operadores.verCima()) > precedencia(t)
                        || (precedencia(operadores.verCima()) == precedencia(t) && !asociativoDerecha(t)))) {
                    salida.encolar(operadores.desapilar());
                }
                operadores.apilar(t);
            } else {
                salida.encolar(t);
            }
        }
        while (!operadores.estaVacia()) {
            String op = operadores.desapilar();
            if (op.equals("(")) {
                throw new IllegalArgumentException("Paréntesis desbalanceados");
            }
            salida.encolar(op);
        }
        return salida;
    }

    public static double evaluarPostfija(Cola<String> postfija, Map<String, Double> variables) {
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
            if (esOperador(t)) {
                double b = pila.desapilar();
                double a = pila.desapilar();
                pila.apilar(aplicar(t, a, b));
            } else {
                double valor = variables.containsKey(t) ? variables.get(t) : Double.parseDouble(t);
                pila.apilar(valor);
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
}
