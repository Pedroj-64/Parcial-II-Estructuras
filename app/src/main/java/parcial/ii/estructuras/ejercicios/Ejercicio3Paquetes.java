package parcial.ii.estructuras.ejercicios;

import parcial.ii.estructuras.estructuras.Cola;
import parcial.ii.estructuras.modelo.Paquete;
import parcial.ii.estructuras.modelo.TipoEntrega;

public final class Ejercicio3Paquetes {

    private Ejercicio3Paquetes() {
    }

    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();
        if (capacidad <= 0) {
            return seleccionados;
        }
        capacidad = pasada(pendientes, seleccionados, TipoEntrega.URGENTE, capacidad, pendientes.tamanio());
        pasada(pendientes, seleccionados, TipoEntrega.NORMAL, capacidad, pendientes.tamanio());
        return seleccionados;
    }

    // Recorre n paquetes y retorna la capacidad que queda. Los no seleccionados vuelven al final en su orden.
    private static int pasada(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
            TipoEntrega tipo, int capacidad, int porRecorrer) {
        if (porRecorrer == 0) {
            return capacidad;
        }
        Paquete p = pendientes.desencolar();
        if (p.tipo() == tipo && p.peso() <= capacidad) {
            seleccionados.encolar(p);
            capacidad -= p.peso();
        } else {
            pendientes.encolar(p);
        }
        return pasada(pendientes, seleccionados, tipo, capacidad, porRecorrer - 1);
    }

    /** Suma los pesos de la cola sin alterarla. */
    public static int pesoTotal(Cola<Paquete> cola) {
        return pesoTotal(cola, cola.tamanio());
    }

    private static int pesoTotal(Cola<Paquete> cola, int n) {
        if (n == 0) {
            return 0;
        }
        Paquete p = cola.desencolar();
        cola.encolar(p);
        return p.peso() + pesoTotal(cola, n - 1);
    }
}
