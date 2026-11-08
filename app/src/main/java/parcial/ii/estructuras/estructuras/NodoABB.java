package parcial.ii.estructuras.estructuras;

/**
 * NodoABB es el nodo de arbol binario de búsqueda (ABB) que contiene una clave y referencias a sus hijos izquierdo y derecho.
 * La clase proporciona métodos para acceder y modificar la clave y los hijos.
 */
public class NodoABB {
    private int codigo;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}
