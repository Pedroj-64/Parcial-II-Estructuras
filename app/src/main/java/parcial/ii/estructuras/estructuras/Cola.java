package parcial.ii.estructuras.estructuras;

import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Cola propia (estructura FIFO: el primero en entrar es el primero en salir),
 * implementada con nodos enlazados. Se agrega por el final y se retira por el
 * frente.
 *
 * @param <T> tipo de los elementos de la cola
 */
public class Cola<T> {

    /** Primer nodo (el próximo en salir), o {@code null} si está vacía. */
    private Nodo<T> frente;

    /** Último nodo (el más recientemente agregado). */
    private Nodo<T> fin;

    /** Cantidad de elementos almacenados. */
    private int tamanio;

    /**
     * Agrega un elemento al final de la cola.
     *
     * @param dato elemento a encolar
     */
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

    /**
     * Quita y retorna el elemento del frente.
     *
     * @return el elemento que estaba al frente
     * @throws NoSuchElementException si la cola está vacía
     */
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

    /**
     * Retorna el elemento del frente sin quitarlo.
     *
     * @return el elemento del frente
     * @throws NoSuchElementException si la cola está vacía
     */
    public T verFrente() {
        if (estaVacia()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return frente.getDato();
    }

    /**
     * @return {@code true} si la cola no tiene elementos
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * @return la cantidad de elementos de la cola
     */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Construye un texto con el contenido de la cola, desde el frente hasta el
     * final, separado por flechas. No modifica la cola.
     *
     * @param formato función que convierte cada elemento en texto
     * @return el contenido en forma {@code a → b → c}, o {@code (vacía)}
     */
    public String mostrar(Function<T, String> formato) {
        if (estaVacia()) {
            return "(vacía)";
        }
        return mostrar(frente, formato);
    }

    /** Recorre recursivamente los nodos desde {@code actual} hasta el final. */
    private String mostrar(Nodo<T> actual, Function<T, String> formato) {
        String texto = formato.apply(actual.getDato());
        if (actual.getSiguiente() == null) {
            return texto;
        }
        return texto + " → " + mostrar(actual.getSiguiente(), formato);
    }

    /**
     * @return el contenido de la cola, del frente al final
     */
    @Override
    public String toString() {
        return mostrar(String::valueOf);
    }
}
