package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Pila;

public final class Ejercicio2Historial {

    private Ejercicio2Historial() {
    }

    public static <T> Pila<T> obtenerSegmento(Pila<T> original, int inicio, int fin) {
        Pila<T> segmento = new Pila<>();
        int total = original.tamanio();
        if (inicio < 1 || inicio > fin || fin > total) {
            return segmento;
        }
        Pila<T> aux = new Pila<>();
        while (!original.estaVacia()) {
            aux.apilar(original.desapilar());
        }
        int posicion = total;
        while (!aux.estaVacia()) {
            T accion = aux.desapilar();
            original.apilar(accion);
            if (posicion >= inicio && posicion <= fin) {
                segmento.apilar(accion);
            }
            posicion--;
        }
        return segmento;
    }
}
