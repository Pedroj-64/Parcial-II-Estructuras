package parcial.ii.estructuras.estructuras;

public class NodoABB {
    private final int codigo;
    private NodoABB izquierdo;
    private NodoABB derecho;

    public NodoABB(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
}
