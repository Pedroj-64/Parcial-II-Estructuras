package parcial.ii.estructuras.modelo;

/**
 * Caja del depósito del ejercicio 1.
 *
 * @param codigo código único de la caja (por ejemplo {@code "C3"})
 * @param valor  valor numérico entero asociado; su paridad decide cómo se
 *               reorganiza la pila al retirarla
 */
public record Caja(String codigo, int valor) {

    /**
     * @return el texto {@code codigo:valor}, por ejemplo {@code C3:6}
     */
    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}
