package parcial.ii.estructuras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import org.junit.jupiter.api.Test;
import parcial.ii.estructuras.ejercicios.Ejercicio1Cajas;
import parcial.ii.estructuras.ejercicios.EvaluadorExpresiones;
import parcial.ii.estructuras.estructuras.Cola;
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

    @Test
    void valoresDelEnunciadoParaLasVariables() {
        Pila<Caja> pila = pilaDelEnunciado();
        Ejercicio1Cajas.retirarCaja(pila, "C3");
        Ejercicio1Cajas.retirarCaja(pila, "C4");
        Ejercicio1Cajas.retirarCaja(pila, "C7");
        assertEquals("C1:2 → C6:9 → C2:4 → C5:3", texto(pila));

        double a = 2;
        double b = 9 / a;
        double c = 4 + a;
        double d = (3 + c) * a;
        assertEquals(2, a, 1e-9);
        assertEquals(4.5, b, 1e-9);
        assertEquals(6, c, 1e-9);
        assertEquals(18, d, 1e-9);
    }

    @Test
    void evaluadorInfijoYPostfijo() {
        Cola<String> post = EvaluadorExpresiones.infijaAPostfija("( A + B ) * C - D / 2");
        assertEquals("A B + C * D 2 / -", post.mostrar(s -> s).replace(" → ", " "));

        double resultado = EvaluadorExpresiones.evaluarPostfija(
                post, Map.of("A", 2.0, "B", 4.5, "C", 6.0, "D", 18.0));
        assertEquals((2 + 4.5) * 6 - 18 / 2.0, resultado, 1e-9);
    }

    @Test
    void expresionDelEnunciado() {
        Cola<String> post = EvaluadorExpresiones.infijaAPostfija("(A + B) * C - (A - B) * D");
        assertEquals("A B + C * A B - D * -", post.mostrar(s -> s).replace(" → ", " "));

        double resultado = EvaluadorExpresiones.evaluarPostfija(
                post, Map.of("A", 2.0, "B", 4.5, "C", 6.0, "D", 18.0));
        assertEquals(84, resultado, 1e-9);
    }

    @Test
    void precedenciaYPotencia() {
        Cola<String> post = EvaluadorExpresiones.infijaAPostfija("2 ^ 3 ^ 2");
        assertEquals("2 3 2 ^ ^", post.mostrar(s -> s).replace(" → ", " "));

        double resultado = EvaluadorExpresiones.evaluarPostfija(post, Map.of());
        assertEquals(512, resultado, 1e-9);
    }
}
