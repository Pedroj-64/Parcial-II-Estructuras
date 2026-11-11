package parcial.ii.estructuras.modelo;

/** Tipo de entrega de un {@link Paquete}. Los urgentes se consideran primero. */
public enum TipoEntrega {
    /** Se selecciona antes que los normales. */
    URGENTE,
    /** Se selecciona después de los urgentes. */
    NORMAL
}
