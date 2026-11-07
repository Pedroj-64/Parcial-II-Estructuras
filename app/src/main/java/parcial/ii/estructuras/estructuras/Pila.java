package parcial.ii.estructuras.estructuras;

import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Pila propia (LIFO) implementada con nodos enlazados.
 * Todas las operaciones básicas son O(1).
 *
 * @param <T> tipo de los elementos
 */
public class Pila<T> {

    private Nodo<T> cima;
    private int tamanio;

    /** Agrega un elemento en la cima. */
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    /** Retira y retorna el elemento de la cima. */
    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

    /** Retorna el elemento de la cima sin retirarlo. */
    public T verCima() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        return cima.getDato();
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Representación desde la cima hasta el fondo, por ejemplo "A6 → A5 → A4".
     * Se construye de forma recursiva recorriendo los nodos; no modifica la estructura.
     */
    public String mostrar(Function<T, String> formato) {
        if (estaVacia()) {
            return "(vacía)";
        }
        return mostrar(cima, formato);
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
