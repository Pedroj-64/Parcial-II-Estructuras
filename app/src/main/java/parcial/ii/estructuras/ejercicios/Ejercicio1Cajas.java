package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Pila;
import parcial.ii.estructuras.modelo.Caja;

public final class Ejercicio1Cajas {

    private Ejercicio1Cajas() {
    }

    public static boolean retirarCaja(Pila<Caja> pila, String codigo) {
        if (pila.estaVacia()) {
            return false;
        }

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

        while (!aux.estaVacia()) {
            pila.apilar(aux.desapilar());
        }

        if (retirada == null) {
            return false;
        }

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
