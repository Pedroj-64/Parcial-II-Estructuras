package parcial.ii.estructuras.estructuras;

import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Cola propia (FIFO) implementada con nodos enlazados (frente y final).
 * Todas las operaciones básicas son O(1).
 *
 * @param <T> tipo de los elementos
 */
public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    /** Agrega un elemento al final de la cola. */
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.setSiguiente(nuevo);
        }
        fin = nuevo;
        tamanio++;
    }

    /** Retira y retorna el elemento del frente. */
    public T desencolar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        T dato = frente.getDato();
        frente = frente.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    /** Retorna el elemento del frente sin retirarlo. */
    public T verFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return frente.getDato();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Representación desde el frente hasta el final, por ejemplo "A6 → A5 → A4".
     * Se construye de forma recursiva recorriendo los nodos; no modifica la estructura.
     */
    public String mostrar(Function<T, String> formato) {
        if (estaVacia()) {
            return "(vacía)";
        }
        return mostrar(frente, formato);
    }

    private String mostrar(Nodo<T> actual, Function<T, String> formato) {
        String texto = formato.apply(actual.getDato());
        if (actual.getSiguiente() == null) {
            return texto;
        }
        return texto + " → " + mostrar(actual.getSiguiente(), formato);
    }

    @Override
    public String toString() {
        return mostrar(String::valueOf);
    }
}
