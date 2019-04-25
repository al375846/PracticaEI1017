package empresa.operaciones;

public enum OpcionesCrearTarifa {


    TARIFA_GENERAL("Usar la tarifa general."),
    TARIFA_PERSONALIZA("Usar una tarifa personalizada.");

    private String descripcion;

    OpcionesCrearTarifa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesCrearTarifa obtenerOpcion(int posicion) {
        return values()[posicion - 1];
    }

    public static String getCrearTarifa() {
        StringBuilder ab = new StringBuilder();
        for (OpcionesCrearTarifa opcion : OpcionesCrearTarifa.values()) {
            ab.append(opcion.ordinal() + 1);
            ab.append(".- ");
            ab.append(opcion.getDescripcion());
            ab.append("\n");
        }
        return ab.toString();
    }
}
