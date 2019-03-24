package empresa.clientes;

import empresa.tarifas.Tarifa;

import java.io.Serializable;

public class ClienteEmpresa extends Cliente implements Serializable {

    private static final long serialVersionUID = 5631L;

    public ClienteEmpresa() {
        super();
    }

    public ClienteEmpresa(Tarifa tarifa, String nombre, String correo, String codigo, Direccion direccion) {
        super(tarifa, nombre, correo, codigo, direccion);
    }

}
