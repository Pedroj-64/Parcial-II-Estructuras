package parcial.ii.estructuras.modelo;

public record Paquete(String codigo, TipoEntrega tipo, int peso) {

    public Paquete {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser un entero positivo");
        }
    }

    @Override
    public String toString() {
        return codigo + "(" + tipo + ", " + peso + "kg)";
    }
}
