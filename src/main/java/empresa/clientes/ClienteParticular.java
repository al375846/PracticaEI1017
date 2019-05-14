package empresa.clientes;

import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

import java.io.Serializable;

public class ClienteParticular extends Cliente implements Serializable {

    private static final long serialVersionUID = 5631L;

    private String apellidos;
    public ClienteParticular() {
        super();
        this.apellidos = null;
    }
    public ClienteParticular( String nombre, Tarifa tarifa, String apellidos, String correo, String codigo, Direccion direccion) {
        super( nombre, tarifa, correo, codigo, direccion);
        this.apellidos = apellidos;
    }

    public boolean isParticular() {
            return true;
    }

    @Override
    public String toString() {
        if (this.apellidos == null)
            return "";
        String cliente = super.toString();
        StringBuilder clientePar = new StringBuilder();
        clientePar.append(cliente);
        clientePar.append("Apellidos: " + this.getApellidos());
        clientePar.append("\n");
        clientePar.append("\n");
        return clientePar.toString();
    }

    public String getApellidos() {
        return apellidos;
    }
}
