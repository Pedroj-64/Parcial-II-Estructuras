package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.modelo.Paquete;
import parcial.ii.estructuras.modelo.TipoEntrega;

public final class Ejercicio3Paquetes {

    private Ejercicio3Paquetes() {
    }

    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        return seleccionarPaquetes(pendientes, new int[] {capacidad});
    }

    /** capacidad[0] entra con la capacidad inicial y sale con la que queda libre. */
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int[] capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (capacidad[0] <= 0) {
            return seleccionados;
        }
        pasada(pendientes, seleccionados, TipoEntrega.URGENTE, capacidad, pendientes.tamanio());
        pasada(pendientes, seleccionados, TipoEntrega.NORMAL, capacidad, pendientes.tamanio());
        return seleccionados;
    }

    // Recorre n paquetes: los que no se seleccionan vuelven al final, conservando su orden.
    private static void pasada(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
            TipoEntrega tipo, int[] capacidad, int n) {
        if (n == 0) {
            return;
        }
        Paquete p = pendientes.desencolar();
        if (p.tipo() == tipo && p.peso() <= capacidad[0]) {
            seleccionados.encolar(p);
            capacidad[0] -= p.peso();
        } else {
            pendientes.encolar(p);
        }
        pasada(pendientes, seleccionados, tipo, capacidad, n - 1);
    }
}
