package parcial.ii.estructuras.estructuras;

import java.util.NoSuchElementException;
import java.util.function.Function;

public class Pila<T> {

    private Nodo<T> cima;
    private int tamanio;

    public void apilar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tamanio++;
    }

    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía");
        }
        T dato = cima.getDato();
        cima = cima.getSiguiente();
        tamanio--;
        return dato;
    }

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
