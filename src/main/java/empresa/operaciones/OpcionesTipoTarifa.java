package empresa.operaciones;

public enum OpcionesTipoTarifa {


    TARIFA_BASICA("Cambiar la tarifa básica."),
    TARIFA_DIARIA("Cambiar la tarifa diaria."),
    TARIFA_HORARIA("Cambiar la tarifa horaria.");

    private String descripcion;

    OpcionesTipoTarifa(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesTipoTarifa obtenerOpcion(int posicion) {
        return values()[posicion - 1];
    }

    public static String getTipoTarifa() {
        StringBuilder ab = new StringBuilder();
        for (OpcionesTipoTarifa opcion : OpcionesTipoTarifa.values()) {
            ab.append(opcion.ordinal() + 1);
            ab.append(".- ");
            ab.append(opcion.getDescripcion());
            ab.append("\n");
        }
        return ab.toString();
    }
}
