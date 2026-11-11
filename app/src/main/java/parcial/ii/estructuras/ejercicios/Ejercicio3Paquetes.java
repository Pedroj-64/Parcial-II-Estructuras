package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.modelo.Paquete;
import parcial.ii.estructuras.modelo.TipoEntrega;

/**
 * Ejercicio 3: selección de paquetes para una jornada. Todo el procesamiento
 * se hace con recursividad, sin ciclos.
 */
public final class Ejercicio3Paquetes {

    private Ejercicio3Paquetes() {
    }

    /**
     * Selecciona los paquetes que caben en el vehículo y los retira de la cola
     * de pendientes.
     *
     * <p>Se hacen dos pasadas sobre los pendientes: primero los urgentes y
     * después los normales, cada una en orden de registro. Un paquete se
     * selecciona si su peso es menor o igual a la capacidad restante (y se
     * descuenta); si no cabe, sigue pendiente y se evalúa el siguiente.</p>
     *
     * <p>La cola retornada conserva el orden de selección (urgentes y luego
     * normales) y los pendientes conservan su orden de registro. Si la
     * capacidad es menor o igual a cero se retorna una cola vacía y los
     * pendientes no cambian.</p>
     *
     * @param pendientes cola de paquetes pendientes (se modifica)
     * @param capacidad  capacidad máxima de carga, en kilogramos
     * @return una cola nueva con los paquetes seleccionados
     */
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (capacidad <= 0) {
            return seleccionados;
        }
        // La capacidad que sobra de la pasada de urgentes es la que tiene
        // disponible la pasada de normales.
        capacidad = pasada(pendientes, seleccionados, TipoEntrega.URGENTE, capacidad, pendientes.tamanio());
        pasada(pendientes, seleccionados, TipoEntrega.NORMAL, capacidad, pendientes.tamanio());
        return seleccionados;
    }

    /**
     * Recorre recursivamente {@code n} paquetes de la cola. Cada paquete sale
     * del frente: si es del tipo buscado y cabe, pasa a seleccionados; en otro
     * caso vuelve al final de pendientes. Al procesar exactamente {@code n}
     * elementos, los que no se seleccionan quedan en su orden original.
     *
     * @param pendientes    cola de pendientes
     * @param seleccionados cola donde se agregan los elegidos
     * @param tipo          tipo de entrega que se está considerando
     * @param capacidad     capacidad restante antes de esta llamada
     * @param n             cantidad de paquetes que faltan por evaluar
     * @return la capacidad restante después de evaluar los {@code n} paquetes
     */
    private static int pasada(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
            TipoEntrega tipo, int capacidad, int n) {
        if (n == 0) {
            return capacidad;
        }
        Paquete p = pendientes.desencolar();
        if (p.tipo() == tipo && p.peso() <= capacidad) {
            seleccionados.encolar(p);
            capacidad -= p.peso();
        } else {
            pendientes.encolar(p);
        }
        return pasada(pendientes, seleccionados, tipo, capacidad, n - 1);
    }

    /**
     * Suma el peso de todos los paquetes de una cola sin alterarla. Sirve para
     * calcular la capacidad que queda libre tras la selección.
     *
     * @param cola cola de paquetes
     * @return la suma de los pesos, en kilogramos
     */
    public static int pesoTotal(Cola<Paquete> cola) {
        return pesoTotal(cola, cola.tamanio());
    }

    /** Suma recursivamente {@code n} pesos, rotando la cola para dejarla igual. */
    private static int pesoTotal(Cola<Paquete> cola, int n) {
        if (n == 0) {
            return 0;
        }
        Paquete p = cola.desencolar();
        cola.encolar(p);
        return p.peso() + pesoTotal(cola, n - 1);
    }
}
