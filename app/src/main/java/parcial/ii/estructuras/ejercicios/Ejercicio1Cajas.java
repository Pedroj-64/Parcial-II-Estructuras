package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Pila;
import parcial.ii.estructuras.modelo.Caja;

/** Ejercicio 1: retiro de cajas de una pila y reorganización de las restantes. */
public final class Ejercicio1Cajas {

    private Ejercicio1Cajas() {
    }

    /**
     * Retira la caja con el código dado y reorganiza las demás según la
     * paridad del valor de la caja retirada.
     *
     * <p>Una vez retirada la caja, las restantes se numeran desde la cima
     * (posición 1, 2, 3, ...) y se separan en dos grupos por posición:
     * impares y pares.</p>
     * <ul>
     *   <li>Valor retirado <b>par</b>: quedan arriba las posiciones impares y
     *       debajo las pares.</li>
     *   <li>Valor retirado <b>impar</b>: quedan arriba las posiciones pares y
     *       debajo las impares.</li>
     * </ul>
     * <p>Dentro de cada grupo se conserva el orden relativo original. Si el
     * código no existe o la pila está vacía, la pila queda intacta.</p>
     *
     * @param pila   pila de cajas (se modifica)
     * @param codigo código de la caja a retirar
     * @return {@code true} si la caja existía y se retiró; {@code false} en
     *         caso contrario
     */
    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila.estaVacia()) {
            return false;
        }

        // Paso 1: buscar la caja. Las que están encima se guardan en una pila
        // auxiliar para poder devolverlas después.
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

        // Paso 2: devolver las cajas guardadas; la pila recupera su orden original.
        while (!aux.estaVacia()) {
            pila.apilar(aux.desapilar());
        }

        if (retirada == null) {
            return false;
        }

        // Paso 3: separar por posición (1 = cima). Cada grupo queda invertido
        // en su pila auxiliar, pero se restaura al volver a apilar.
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

        // Paso 4: el valor de la caja retirada decide qué grupo queda arriba.
        // Se apila primero el grupo de abajo y luego el de arriba.
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
