package parcial.ii.estructuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import parcial.ii.estructuras.ejercicios.Ejercicio1Cajas;
import parcial.ii.estructuras.estructuras.Pila;
import parcial.ii.estructuras.modelo.Caja;

class Ejercicio1Test {

    private static String texto(Pila<?> p) {
        return p.mostrar(String::valueOf);
    }

    private static Pila<Caja> pilaDelEnunciado() {
        Pila<Caja> pila = new Pila<>();
        int[] valores = {2, 4, 6, 8, 3, 9, 5};
        for (int i = 0; i < valores.length; i++) {
            pila.apilar(new Caja("C" + (i + 1), valores[i]));
        }
        return pila;
    }

    @Test
    void retirarCajaSecuenciaDelEnunciado() {
        Pila<Caja> pila = pilaDelEnunciado();

        assertTrue(Ejercicio1Cajas.retirarCaja(pila, "C3"));
        assertEquals("C7:5 → C5:3 → C2:4 → C6:9 → C4:8 → C1:2", texto(pila));

        assertTrue(Ejercicio1Cajas.retirarCaja(pila, "C4"));
        assertEquals("C7:5 → C2:4 → C1:2 → C5:3 → C6:9", texto(pila));

        assertTrue(Ejercicio1Cajas.retirarCaja(pila, "C7"));
        assertEquals("C1:2 → C6:9 → C2:4 → C5:3", texto(pila));
    }

    @Test
    void retirarCajaInexistenteOVacia() {
        Pila<Caja> pila = pilaDelEnunciado();
        String antes = texto(pila);

        assertFalse(Ejercicio1Cajas.retirarCaja(pila, "XX"));
        assertEquals(antes, texto(pila));
        assertEquals(7, pila.tamanio());

        Pila<Caja> vacia = new Pila<>();
        assertFalse(Ejercicio1Cajas.retirarCaja(vacia, "C1"));
        assertTrue(vacia.estaVacia());
    }

    @Test
    void retirarUnicaCaja() {
        Pila<Caja> pila = new Pila<>();
        pila.apilar(new Caja("C1", 1));

        assertTrue(Ejercicio1Cajas.retirarCaja(pila, "C1"));
        assertTrue(pila.estaVacia());
    }
}
