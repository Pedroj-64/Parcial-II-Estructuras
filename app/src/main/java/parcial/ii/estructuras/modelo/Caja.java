package parcial.ii.estructuras.modelo;

public record Caja(String codigo, int valor) {

    @Override
    public String toString() {
        return codigo + ":" + valor;
    }
}
