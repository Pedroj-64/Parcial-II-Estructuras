package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.NodoABB;

public class Ejercicio4Arbol {
    /**
     * Cuenta los codigos en [inferior, superior], omitiendo subarboles fuera de
     * rango.
     */
    public static int contarEquiposEnRango(NodoABB raiz, int inferior, int superior) {
        if (raiz == null || inferior > superior) {
            return 0;
        }
        if (raiz.getCodigo() < inferior) {
            return contarEquiposEnRango(raiz.getDerecho(), inferior, superior);
        }
        if (raiz.getCodigo() > superior) {
            return contarEquiposEnRango(raiz.getIzquierdo(), inferior, superior);
        }
        return 1 + contarEquiposEnRango(raiz.getIzquierdo(), inferior, superior)
                + contarEquiposEnRango(raiz.getDerecho(), inferior, superior);
    }
}
