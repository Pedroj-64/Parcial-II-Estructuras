package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Pila;

/** Ejercicio 2: obtener un segmento del historial de acciones de un editor. */
public final class Ejercicio2Historial {

    private Ejercicio2Historial() {
    }

    /**
     * Retorna una nueva pila con las acciones entre las posiciones
     * {@code inicio} y {@code fin} (ambas incluidas). Las posiciones se cuentan
     * desde la cima empezando en 1, y la acción de la posición {@code inicio}
     * queda en la cima del resultado.
     *
     * <p>Ejemplo: con {@code A6 → A5 → A4 → A3 → A2 → A1} (cima a la izquierda),
     * inicio = 2 y fin = 4 el resultado es {@code A5 → A4 → A3}.</p>
     *
     * <p>La pila original queda con los mismos elementos y en el mismo orden.
     * Si {@code inicio < 1}, {@code inicio > fin} o {@code fin} supera la
     * cantidad de elementos, se retorna una pila vacía.</p>
     *
     * @param original pila de acciones (se vacía y se restaura)
     * @param inicio   primera posición del segmento (1 = cima)
     * @param fin      última posición del segmento
     * @param <T>      tipo de las acciones
     * @return una pila nueva con el segmento, o vacía si el rango no es válido
     */
    public static <T> Pila<T> obtenerSegmento(Pila<T> original, int inicio, int fin) {
        Pila<T> segmento = new Pila<>();
        int total = original.tamanio();
        if (inicio < 1 || inicio > fin || fin > total) {
            return segmento;
        }

        // Paso 1: pasar todo a una pila auxiliar. Queda invertida: la cima de
        // aux es la acción más antigua (posición = total).
        Pila<T> aux = new Pila<>();
        while (!original.estaVacia()) {
            aux.apilar(original.desapilar());
        }

        // Paso 2: devolver cada acción a la original (restaurando el orden) y,
        // si su posición cae en [inicio, fin], copiarla al segmento. Como se
        // procesa de la más antigua a la más reciente, la de posición
        // 'inicio' se apila al final y queda en la cima del segmento.
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
