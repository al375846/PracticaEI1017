package empresa.clientes;

import empresa.tarifas.Tarifa;

import java.io.Serializable;

public class ClienteParticular extends Cliente implements Serializable {
    private String apellidos;
    public ClienteParticular() {
        super();
    }
    public ClienteParticular(Tarifa tarifa, String nombre, String apellidos, String correo, String codigo, Direccion direccion) {
        super(tarifa, nombre, correo, codigo, direccion);
        this.apellidos = apellidos;
    }

    @Override
   /* public void datos() {
        super.datos();
        System.out.println("Apellidos: " + this.getApellidos());
    }*/

    public String toString() {
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
