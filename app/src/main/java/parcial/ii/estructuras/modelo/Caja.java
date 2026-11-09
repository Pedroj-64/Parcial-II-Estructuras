package parcial.ii.estructuras.modelo;

/** Caja del depósito: código único y valor entero. */
public record Caja(String codigo, int valor) {

    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}
