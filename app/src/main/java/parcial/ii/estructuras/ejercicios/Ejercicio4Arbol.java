package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.NodoABB;

/** Ejercicio 4a: consulta de equipos por rango en un árbol binario de búsqueda. */
public final class Ejercicio4Arbol {

    private Ejercicio4Arbol() {
    }

    /**
     * Cuenta los equipos cuyo código está en el rango {@code [inferior, superior]}
     * (extremos incluidos).
     *
     * <p>Aprovecha el orden del ABB para omitir subárboles que no pueden
     * aportar: si el código del nodo es menor que el límite inferior, todo el
     * subárbol izquierdo es menor y solo se visita el derecho; si es mayor que
     * el límite superior, solo se visita el izquierdo. Si está dentro del
     * rango, cuenta 1 y revisa ambos lados.</p>
     *
     * @param raiz     raíz del árbol (o subárbol) a consultar
     * @param inferior límite inferior del rango
     * @param superior límite superior del rango
     * @return cuántos códigos están en el rango; 0 si el árbol está vacío o
     *         {@code inferior > superior}
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
