package parcial.ii.estructuras.estructuras;

/**
 * Nodo de una lista simplemente enlazada. Es la pieza base sobre la que se
 * construyen {@link Pila} y {@link Cola}.
 *
 * @param <T> tipo del dato que guarda el nodo
 */
public class Nodo<T> {

    /** Dato almacenado en el nodo. */
    private T dato;

    /** Nodo que sigue en la estructura, o {@code null} si es el último. */
    private Nodo<T> siguiente;

    /**
     * Crea un nodo sin siguiente.
     *
     * @param dato dato que guardará el nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
    }

    /**
     * @return el dato almacenado
     */
    public T getDato() {
        return dato;
    }

    /**
     * @param dato nuevo dato a almacenar
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * @return el nodo siguiente, o {@code null} si no hay
     */
    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente nodo que quedará enlazado a continuación de este
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
