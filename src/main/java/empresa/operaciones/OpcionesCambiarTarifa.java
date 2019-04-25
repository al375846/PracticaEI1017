package empresa.operaciones;

public enum OpcionesCambiarTarifa {

    TARIFA_GENERAL("Cambiar la tarifa general."),
    TARIFA_CLIENTE("Cambiar la tarifa de un cliente.");

    private String descripcion;

    OpcionesCambiarTarifa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesCambiarTarifa obtenerOpcion(int posicion) {
        return values()[posicion - 1];
    }

    public static String getCambiarTarifa() {
        StringBuilder ab = new StringBuilder();
        for (OpcionesCambiarTarifa opcion : OpcionesCambiarTarifa.values()) {
            ab.append(opcion.ordinal() + 1);
            ab.append(".- ");
            ab.append(opcion.getDescripcion());
            ab.append("\n");
        }
        return ab.toString();
    }
}
