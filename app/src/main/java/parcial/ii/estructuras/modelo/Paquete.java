package parcial.ii.estructuras.modelo;

/**
 * Paquete pendiente de entrega del ejercicio 3.
 *
 * @param codigo código único del paquete (por ejemplo {@code "P2"})
 * @param tipo   tipo de entrega: urgente o normal
 * @param peso   peso entero positivo, en kilogramos
 */
public record Paquete(String codigo, TipoEntrega tipo, int peso) {

    /**
     * Valida que el peso sea positivo.
     *
     * @throws IllegalArgumentException si {@code peso} es menor o igual a cero
     */
    public Paquete {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser un entero positivo");
        }
    }

    /**
     * @return el texto {@code codigo(TIPO, pesokg)}, por ejemplo {@code P2(URGENTE, 7kg)}
     */
    @Override
    public String toString() {
        return codigo + "(" + tipo + ", " + peso + "kg)";
    }
}
