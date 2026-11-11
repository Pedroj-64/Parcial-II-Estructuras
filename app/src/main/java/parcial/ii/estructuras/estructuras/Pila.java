package parcial.ii.estructuras.estructuras;

import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Pila propia (estructura LIFO: el último en entrar es el primero en salir),
 * implementada con nodos enlazados. Solo se puede acceder a la cima.
 *
 * @param <T> tipo de los elementos de la pila
 */
public class Pila<T> {

    /** Nodo de la cima, o {@code null} si la pila está vacía. */
    private Nodo<T> cima;

    /** Cantidad de elementos almacenados. */
    private int tamanio;

    /**
     * Agrega un elemento en la cima.
     *
     * @param dato elemento a apilar
     */
    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    /**
     * Quita y retorna el elemento de la cima.
     *
     * @return el elemento que estaba en la cima
     * @throws NoSuchElementException si la pila está vacía
     */
    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

    /**
     * Retorna el elemento de la cima sin quitarlo.
     *
     * @return el elemento de la cima
     * @throws NoSuchElementException si la pila está vacía
     */
    public T verCima() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        return cima.getDato();
    }

    /**
     * @return {@code true} si la pila no tiene elementos
     */
    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * @return la cantidad de elementos de la pila
     */
    public int tamanio() {
        return tamanio;
    }

    /**
     * Construye un texto con el contenido de la pila, desde la cima hasta el
     * fondo, separado por flechas. No modifica la pila.
     *
     * @param formato función que convierte cada elemento en texto
     * @return el contenido en forma {@code a → b → c}, o {@code (vacía)}
     */
    public String mostrar(Function<T, String> formato) {
        if (estaVacia()) {
            return "(vacía)";
        }
        return mostrar(cima, formato);
    }

    /** Recorre recursivamente los nodos desde {@code actual} hasta el fondo. */
    private String mostrar(Nodo<T> actual, Function<T, String> formato) {
        String texto = formato.apply(actual.getDato());
        if (actual.getSiguiente() == null) {
            return texto;
        }
        return texto + " → " + mostrar(actual.getSiguiente(), formato);
    }

    /**
     * @return el contenido de la pila, de la cima al fondo
     */
    @Override
    public String toString() {
        return mostrar(String::valueOf);
    }
}
