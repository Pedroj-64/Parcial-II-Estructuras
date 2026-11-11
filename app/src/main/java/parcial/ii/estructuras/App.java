package parcial.ii.estructuras;

import parcial.ii.estructuras.ejercicios.Ejercicio1Cajas;
import parcial.ii.estructuras.ejercicios.Ejercicio2Historial;
import parcial.ii.estructuras.ejercicios.Ejercicio3Paquetes;
import parcial.ii.estructuras.ejercicios.Ejercicio4Arbol;
import parcial.ii.estructuras.estructuras.ArbolBB;
import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.estructuras.Pila;
import parcial.ii.estructuras.modelo.Caja;
import parcial.ii.estructuras.modelo.Paquete;
import parcial.ii.estructuras.modelo.TipoEntrega;

/**
 * Punto de entrada con las pruebas de escritorio de los ejercicios del parcial.
 * Se ejecuta con {@code ./gradlew run}.
 */
public class App {

    /**
     * Ejecuta en orden las pruebas de los ejercicios 1 a 4a.
     *
     * @param args no se usan
     */
    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();
    }

    /** Ejercicio 1: pila de 7 cajas y retiro de C3, C4 y C7 sobre la misma pila. */
    private static void ejercicio1() {
        System.out.println("== Ejercicio 1: retirarCaja ==");
        Pila<Caja> pila = new Pila<>();
        int[] valores = {2, 4, 6, 8, 3, 9, 5};
        for (int i = 0; i < valores.length; i++) {
            pila.apilar(new Caja("C" + (i + 1), valores[i]));
        }
        System.out.println("Inicial: " + pila);
        for (String codigo : new String[] {"C3", "C4", "C7"}) {
            boolean ok = Ejercicio1Cajas.retirarCaja(pila, codigo);
            System.out.println("retirar " + codigo + " -> " + ok + " | " + pila);
        }
        System.out.println("Final: " + pila);
    }

    /** Ejercicio 2: segmentos de un historial de 6 acciones, válidos e inválidos. */
    private static void ejercicio2() {
        System.out.println("\n== Ejercicio 2: obtenerSegmento ==");
        Pila<String> historial = new Pila<>();
        for (int i = 1; i <= 6; i++) {
            historial.apilar("A" + i);
        }
        System.out.println("Original: " + historial);
        System.out.println("(2,4): " + Ejercicio2Historial.obtenerSegmento(historial, 2, 4));
        System.out.println("(3,3): " + Ejercicio2Historial.obtenerSegmento(historial, 3, 3));
        System.out.println("(0,2): " + Ejercicio2Historial.obtenerSegmento(historial, 0, 2));
        System.out.println("(4,9): " + Ejercicio2Historial.obtenerSegmento(historial, 4, 9));
        System.out.println("Original tras las llamadas: " + historial);
    }

    /** Ejercicio 3: cola de 7 paquetes con capacidad de 14 kg. */
    private static void ejercicio3() {
        System.out.println("\n== Ejercicio 3: seleccionarPaquetes ==");
        Cola<Paquete> cola = new Cola<>();
        cola.encolar(new Paquete("P1", TipoEntrega.NORMAL, 4));
        cola.encolar(new Paquete("P2", TipoEntrega.URGENTE, 7));
        cola.encolar(new Paquete("P3", TipoEntrega.NORMAL, 3));
        cola.encolar(new Paquete("P4", TipoEntrega.URGENTE, 5));
        cola.encolar(new Paquete("P5", TipoEntrega.NORMAL, 6));
        cola.encolar(new Paquete("P6", TipoEntrega.URGENTE, 4));
        cola.encolar(new Paquete("P7", TipoEntrega.NORMAL, 2));
        System.out.println("Inicial: " + cola);
        int capacidad = 14;
        Cola<Paquete> seleccionados = Ejercicio3Paquetes.seleccionarPaquetes(cola, capacidad);
        System.out.println("Seleccionados: " + seleccionados);
        System.out.println("Pendientes: " + cola);
        System.out.println("Capacidad libre: " + (capacidad - Ejercicio3Paquetes.pesoTotal(seleccionados)) + " kg");
    }

    /** Ejercicio 4a: conteo por rango sobre el ABB de los códigos del enunciado. */
    private static void ejercicio4() {
        System.out.println("\n== Ejercicio 4a: contarEquiposEnRango ==");
        ArbolBB arbol = new ArbolBB();
        for (int c : new int[] {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 43, 44}) {
            arbol.insertar(c);
        }
        System.out.println("[20, 50]: " + Ejercicio4Arbol.contarEquiposEnRango(arbol.getRaiz(), 20, 50));
        System.out.println("[0, 100]: " + Ejercicio4Arbol.contarEquiposEnRango(arbol.getRaiz(), 0, 100));
        System.out.println("[70, 80]: " + Ejercicio4Arbol.contarEquiposEnRango(arbol.getRaiz(), 70, 80));
        System.out.println("[50, 20] (invertido): " + Ejercicio4Arbol.contarEquiposEnRango(arbol.getRaiz(), 50, 20));
    }
}
