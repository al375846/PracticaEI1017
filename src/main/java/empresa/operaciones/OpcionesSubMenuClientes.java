package empresa.operaciones;

public enum OpcionesSubMenuClientes {

    ALTA_CLIENTE_PARTICULAR("Dar de alta a un cliente particular."),
    ALTA_CLIENTE_EMPRESA("Dar de alta a un cliente empresa."),
    CANCELAR("Volver al gestor de facturación");

    private String descripcion;

    OpcionesSubMenuClientes(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesSubMenuClientes obtenerOpcion(int posicion) {
        return values()[posicion - 1];
    }

    public static String getSubMenuClientes() {
        StringBuilder ab = new StringBuilder();
        for (OpcionesSubMenuClientes opcion : OpcionesSubMenuClientes.values()) {
            ab.append(opcion.ordinal() + 1);
            ab.append(".- ");
            ab.append(opcion.getDescripcion());
            ab.append("\n");
        }
        return ab.toString();
    }
}
