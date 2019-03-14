package empresa.operaciones;

public enum OpcionesMenu {
    ALTA_CLIENTE_PARTICULAR("Dar de alta a un cliente particular."),
    ALTA_CLIENTE_EMPRESA("Dar de alta a un cliente empresa."),
    BAJA_CLIENTE("Dar de baja un cliente"),
    CAMBIAR_TARIFA("Cambiar tarifa de un cliente"),
    DATOS_CLIENTE("Obtener datos del cliente"),
    LISTA_CLIENTES("Obtener lista de clientes"),
    ALTA_LLAMADA("Dar de alta una llamada del cliente"),
    LISTA_LLAMADAS("Obtener lista de llamadas del cliente"),
    EMITIR_FACTURA("Emitir una factura"),
    DATOS_FACTURA("Obtener datos de una factura"),
    FACTURAS_CLIENTE("Obtener todas las facturas de un cliente"),
    CLIENTES_PERIODO("Obtener el conjunto de clientes dados de alta en un periodo"),
    LLAMADAS_PERIODO("Obtener el conjunto de llamadas de un cliente en un periodo"),
    FACTURAS_PERIODO("Obtener el conjunto de facturas de un cliente en un periodo"),
    SALIR("Salir del gestor de facturación");

    private String descripcion;

    OpcionesMenu(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesMenu obtenerOpcion(int posicion) {
        return values()[posicion - 1];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for (OpcionesMenu opcion : OpcionesMenu.values()) {
            sb.append(opcion.ordinal() + 1);
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
}
