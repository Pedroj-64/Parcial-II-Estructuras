package parcial.ii.estructuras;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import parcial.ii.estructuras.ejercicios.Ejercicio3Paquetes;
import parcial.ii.estructuras.ejercicios.Ejercicio4Arbol;
import parcial.ii.estructuras.estructuras.ArbolBB;
import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.modelo.Paquete;
import parcial.ii.estructuras.modelo.TipoEntrega;

class Ejercicio3y4Test {

    private static Cola<Paquete> cola() {
        Cola<Paquete> c = new Cola<>();
        Object[][] d = {{"P1", 'N', 4}, {"P2", 'U', 7}, {"P3", 'N', 3}, {"P4", 'U', 5},
                {"P5", 'N', 6}, {"P6", 'U', 4}, {"P7", 'N', 2}};
        for (Object[] f : d) {
            c.encolar(new Paquete((String) f[0], (char) f[1] == 'U' ? TipoEntrega.URGENTE : TipoEntrega.NORMAL, (int) f[2]));
        }
        return c;
    }

    @Test
    void seleccionDelEnunciado() {
        Cola<Paquete> pend = cola();
        int cap = 14;
        Cola<Paquete> sel = Ejercicio3Paquetes.seleccionarPaquetes(pend, cap);
        assertEquals("P2(URGENTE, 7kg) → P4(URGENTE, 5kg) → P7(NORMAL, 2kg)", sel.toString());
        assertEquals("P1(NORMAL, 4kg) → P3(NORMAL, 3kg) → P5(NORMAL, 6kg) → P6(URGENTE, 4kg)", pend.toString());
        assertEquals(0, cap - Ejercicio3Paquetes.pesoTotal(sel));
    }

    @Test
    void capacidadCeroNoCambiaNada() {
        Cola<Paquete> pend = cola();
        assertEquals(0, Ejercicio3Paquetes.seleccionarPaquetes(pend, 0).tamanio());
        assertEquals(7, pend.tamanio());
    }

    @Test
    void conteoPorRango() {
        ArbolBB a = new ArbolBB();
        for (int c : new int[] {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 43, 44}) {
            a.insertar(c);
        }
        assertEquals(7, Ejercicio4Arbol.contarEquiposEnRango(a.getRaiz(), 20, 50));
        assertEquals(13, Ejercicio4Arbol.contarEquiposEnRango(a.getRaiz(), 0, 100));
        assertEquals(0, Ejercicio4Arbol.contarEquiposEnRango(a.getRaiz(), 50, 20));
        assertEquals(0, Ejercicio4Arbol.contarEquiposEnRango(null, 0, 10));
    }
}
