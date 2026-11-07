package parcial.ii.estructuras.estructuras;

/**
 * ArbolBB es un arbol binario de busqueda
 */
public class ArbolBB {

    private NodoABB raiz;
    private int tamanio;
    private String ultimaEliminacion = "";

    public NodoABB getRaiz() {
        return raiz;
    }

    public int tamanio() {
        return tamanio;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Inserta un nuevo nodo con la clave especificada en el árbol binario de
     * búsqueda
     * 
     * @param clave lo que se va meter
     * @return booleano que representa si se metio o no
     */
    public boolean insertar(int clave) {
        if (raiz == null) {
            raiz = new NodoABB(clave);
            tamanio++;
            return true;
        }
        boolean insertado = insertar(raiz, clave);
        if (insertado) {
            tamanio++;
        }
        return insertado;
    }

    /**
     * Metodo auxiliar y recursivo en caso de que el arbol ya tenga elementos
     * 
     * @param nodo  el nodo raiz del arbol
     * @param clave el valor a meter
     * @return booleano que representa si se metio o no
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

    /**
     * Cuenta la cantidad de equipos en un rango específico
     *
     * @param raiz     el nodo raiz del arbol
     * @param inferior el límite inferior
     * @param superior el límite superior
     * @return la cantidad de equipos en el rango dado
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

    /**
     * Cuenta la cantidad de equipos en un rango específico desde la raiz
     * 
     * @param inferior el límite inferior
     * @param superior el límite superior
     * @return la cantidad de equipos en el rango dado
     */
    public int contarEquiposEnRango(int inferior, int superior) {
        return contarEquiposEnRango(raiz, inferior, superior);
    }

    public Cola<Integer> inorden() {
        Cola<Integer> salida = new Cola<>();
        inorden(raiz, salida);
        return salida;
    }

    public Cola<Integer> preorden() {
        Cola<Integer> salida = new Cola<>();
        preorden(raiz, salida);
        return salida;
    }

    public Cola<Integer> postorden() {
        Cola<Integer> salida = new Cola<>();
        postorden(raiz, salida);
        return salida;
    }

    private void inorden(NodoABB nodo, Cola<Integer> salida) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), salida);
        salida.encolar(nodo.getCodigo());
        inorden(nodo.getDerecho(), salida);
    }

    private void preorden(NodoABB nodo, Cola<Integer> salida) {
        if (nodo == null) {
            return;
        }
        salida.encolar(nodo.getCodigo());
        preorden(nodo.getIzquierdo(), salida);
        preorden(nodo.getDerecho(), salida);
    }

     private void postorden(NodoABB nodo, Cola<Integer> salida) {
        if (nodo == null) {
            return;
        }
        postorden(nodo.getIzquierdo(), salida);
        postorden(nodo.getDerecho(), salida);
        salida.encolar(nodo.getCodigo());
    }


}
