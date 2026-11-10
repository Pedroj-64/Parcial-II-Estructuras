package parcial.ii.estructuras.estructuras;

/**
 * Arbol binario de busqueda con claves enteras unicas.
 */
public class ArbolBB {

    private NodoABB raiz;

    public NodoABB getRaiz() {
        return raiz;
    }

    public boolean insertar(int clave) {
        if (raiz == null) {
            raiz = new NodoABB(clave);
            return true;
        }
        return insertar(raiz, clave);
    }

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
