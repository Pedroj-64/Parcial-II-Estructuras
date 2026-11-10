package parcial.ii.estructuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import parcial.ii.estructuras.ejercicios.Ejercicio2Historial;
import parcial.ii.estructuras.estructuras.Pila;

class Ejercicio2Test {

    private static String texto(Pila<?> p) {
        return p.mostrar(String::valueOf);
    }

    private static Pila<String> historial() {
        Pila<String> p = new Pila<>();
        for (int i = 1; i <= 6; i++) {
            p.apilar("A" + i);
        }
        return p;
    }

    @Test
    void segmentoDelEnunciado() {
        Pila<String> p = historial();
        assertEquals("A5 → A4 → A3", texto(Ejercicio2Historial.obtenerSegmento(p, 2, 4)));
        assertEquals("A6 → A5 → A4 → A3 → A2 → A1", texto(p));
    }

    @Test
    void segmentoUnicoYExtremos() {
        Pila<String> p = historial();
        assertEquals("A4", texto(Ejercicio2Historial.obtenerSegmento(p, 3, 3)));
        assertEquals("A6 → A5 → A4 → A3 → A2 → A1", texto(Ejercicio2Historial.obtenerSegmento(p, 1, 6)));
        assertEquals("A1", texto(Ejercicio2Historial.obtenerSegmento(p, 6, 6)));
    }

    @Test
    void segmentoInvalidoRetornaVacia() {
        Pila<String> p = historial();
        assertTrue(Ejercicio2Historial.obtenerSegmento(p, 0, 3).estaVacia());
        assertTrue(Ejercicio2Historial.obtenerSegmento(p, 4, 2).estaVacia());
        assertTrue(Ejercicio2Historial.obtenerSegmento(p, 2, 7).estaVacia());
        assertTrue(Ejercicio2Historial.obtenerSegmento(new Pila<String>(), 1, 1).estaVacia());
        assertEquals(6, p.tamanio());
    }
}
