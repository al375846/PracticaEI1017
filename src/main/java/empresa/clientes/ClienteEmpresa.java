package empresa.clientes;

import empresa.tarifas.Tarifa;

import java.io.Serializable;

public class ClienteEmpresa extends Cliente implements Serializable {

    private static final long serialVersionUID = 5631L;

    public ClienteEmpresa() {
        super();
    }

    public ClienteEmpresa(String nombre, Tarifa tarifa, String correo, String codigo, Direccion direccion) {
        super(nombre, tarifa, correo, codigo, direccion);
    }
    @Override
    public void establecerCliente(String nombre, Tarifa tarifa, String correo, String codigo, Direccion direccion ) {
        super.establecerCliente(nombre, tarifa, correo, codigo, direccion);
    }

    public boolean isParticular() {
        return false;
    }
}
