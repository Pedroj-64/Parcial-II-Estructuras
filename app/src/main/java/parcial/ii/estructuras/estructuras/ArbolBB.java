package parcial.ii.estructuras.estructuras;

/**
 * ArbolBB es un arbol binario de busqueda
 */
public class ArbolBB {

    private NodoABB raiz;
    private int tamanio;

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
     * Cuenta la cantidad de equipos en un rango específico. Aprovecha el orden
     * del árbol: si el código es menor que el límite inferior solo se visita el
     * subárbol derecho, y si es mayor que el superior solo el izquierdo.
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

    /**
     * Elimina la clave del árbol. Si el nodo tiene dos hijos se reemplaza por
     * el mayor valor de su subárbol izquierdo.
     *
     * @param clave el código a eliminar
     * @return true si existía y se eliminó
     */
    public boolean eliminar(int clave) {
        int antes = tamanio;
        raiz = eliminar(raiz, clave);
        return tamanio < antes;
    }

    private NodoABB eliminar(NodoABB nodo, int clave) {
        if (nodo == null) {
            return null;
        }
        if (clave < nodo.getCodigo()) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), clave));
            return nodo;
        }
        if (clave > nodo.getCodigo()) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), clave));
            return nodo;
        }
        // nodo encontrado
        if (nodo.getIzquierdo() == null) {
            tamanio--;
            return nodo.getDerecho();
        }
        if (nodo.getDerecho() == null) {
            tamanio--;
            return nodo.getIzquierdo();
        }
        NodoABB mayor = nodo.getIzquierdo();
        while (mayor.getDerecho() != null) {
            mayor = mayor.getDerecho();
        }
        nodo.setCodigo(mayor.getCodigo());
        nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), mayor.getCodigo()));
        return nodo;
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

    
    public Cola<Integer> amplitud() {
        Cola<Integer> salida = new Cola<>();
        if (raiz == null) {
            return salida;
        }
        Cola<NodoABB> pendientes = new Cola<>();
        pendientes.encolar(raiz);
        while (!pendientes.estaVacia()) {
            NodoABB actual = pendientes.desencolar();
            salida.encolar(actual.getCodigo());
            if (actual.getIzquierdo() != null) {
                pendientes.encolar(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                pendientes.encolar(actual.getDerecho());
            }
        }
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