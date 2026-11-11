package parcial.ii.estructuras.estructuras;

/**
 * Nodo de un árbol binario de búsqueda. Guarda un código entero y las
 * referencias a sus hijos: en el izquierdo van los códigos menores y en el
 * derecho los mayores.
 */
public class NodoABB {

    /** Código (clave) del equipo; no cambia una vez creado el nodo. */
    private final int codigo;

    /** Hijo con códigos menores, o {@code null}. */
    private NodoABB izquierdo;

    /** Hijo con códigos mayores, o {@code null}. */
    private NodoABB derecho;

    /**
     * Crea un nodo hoja (sin hijos).
     *
     * @param codigo clave del nodo
     */
    public NodoABB(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return la clave del nodo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @return el hijo izquierdo, o {@code null} si no tiene
     */
    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    /**
     * @param izquierdo nuevo hijo izquierdo
     */
    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    /**
     * @return el hijo derecho, o {@code null} si no tiene
     */
    public NodoABB getDerecho() {
        return derecho;
    }

    /**
     * @param derecho nuevo hijo derecho
     */
    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}
