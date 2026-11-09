package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Pila;
import parcial.ii.estructuras.modelo.Caja;

/** Ejercicio 1: retiro de cajas y reorganización de la pila según la paridad del valor retirado. */
public final class Ejercicio1Cajas {

    private Ejercicio1Cajas() {
    }

    /**
     * Retira la caja con el código dado y reorganiza las restantes.
     * <ul>
     *   <li>Valor retirado par: posiciones impares arriba, luego las pares.</li>
     *   <li>Valor retirado impar: posiciones pares arriba, luego las impares.</li>
     * </ul>
     * Las posiciones se cuentan desde la cima (1) una vez retirada la caja. En cada grupo se
     * conserva el orden relativo. Si el código no existe o la pila está vacía retorna false y la
     * pila queda igual.
     */
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila.estaVacia()) {
            return false;
        }
        // 1) Buscar la caja guardando en 'aux' las que están encima de ella.
        Pila<Caja> aux = new Pila<>();
        Caja retirada = null;
        while (!pila.estaVacia() && retirada == null) {
            Caja c = pila.desapilar();
            if (c.codigo().equals(codigo)) {
                retirada = c;
            } else {
                aux.apilar(c);
            }
        }
        // 2) Restaurar el orden original de las que estaban encima.
        while (!aux.estaVacia()) {
            pila.apilar(aux.desapilar());
        }
        if (retirada == null) {
            return false; // la pila ya quedó idéntica
        }

        // 3) Separar por posición (desde la cima, empezando en 1) en dos pilas auxiliares.
        //    Al apilar, el último de cada grupo queda en la cima de su pila.
        Pila<Caja> impares = new Pila<>();
        Pila<Caja> pares = new Pila<>();
        int posicion = 1;
        while (!pila.estaVacia()) {
            Caja c = pila.desapilar();
            if (posicion % 2 == 1) {
                impares.apilar(c);
            } else {
                pares.apilar(c);
            }
            posicion++;
        }

        // 4) Reconstruir: primero (más al fondo) el grupo que queda abajo, luego el que queda arriba.
        //    Desapilar un grupo entrega primero su último elemento, que es justo el que va más al fondo.
        boolean valorPar = retirada.valor() % 2 == 0;
        Pila<Caja> arriba = valorPar ? impares : pares;
        Pila<Caja> abajo = valorPar ? pares : impares;
        while (!abajo.estaVacia()) {
            pila.apilar(abajo.desapilar());
        }
        while (!arriba.estaVacia()) {
            pila.apilar(arriba.desapilar());
        }
        return true;
    }
}
