package parcial.ii.estructuras.estructuras;

/**
 * Árbol binario de búsqueda (ABB) con claves enteras únicas. Para cada nodo,
 * todos los códigos de su subárbol izquierdo son menores y todos los de su
 * subárbol derecho son mayores.
 */
public class ArbolBB {

    /** Raíz del árbol, o {@code null} si está vacío. */
    private NodoABB raiz;

    /**
     * @return la raíz del árbol, o {@code null} si está vacío
     */
    public NodoABB getRaiz() {
        return raiz;
    }

    /**
     * Inserta una clave en el árbol. Si el árbol está vacío la clave queda
     * como raíz; en otro caso se delega en el método recursivo.
     *
     * @param clave código a insertar
     * @return {@code true} si se insertó, {@code false} si ya existía
     */
    public boolean insertar(int clave) {
        if (raiz == null) {
            raiz = new NodoABB(clave);
            return true;
        }
        return insertar(raiz, clave);
    }

    /**
     * Baja por el árbol comparando la clave con cada nodo: a la izquierda si
     * es menor, a la derecha si es mayor, y se detiene si es igual (repetida).
     * Al encontrar un hueco crea ahí el nodo nuevo.
     *
     * @param nodo  nodo actual del recorrido
     * @param clave código a insertar
     * @return {@code true} si se insertó, {@code false} si ya existía
     */
    private boolean insertar(NodoABB nodo, int clave) {
        if (clave == nodo.getCodigo()) {
            return false;
        }
        if (clave < nodo.getCodigo()) {
            if (nodo.getIzquierdo() == null) {
                nodo.setIzquierdo(new NodoABB(clave));
                return true;
            }
            return insertar(nodo.getIzquierdo(), clave);
        }
        if (nodo.getDerecho() == null) {
            nodo.setDerecho(new NodoABB(clave));
            return true;
        }
        return insertar(nodo.getDerecho(), clave);
    }
}
