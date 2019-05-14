package empresa.operaciones;

import empresa.clientes.*;
import empresa.facturas.ConjuntoFacturas;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;

public class ImplementacionModelo implements Modelo {
    private Vista vista;
    private CarteraClientes carteraClientes;
    private ConjuntoFacturas conjuntoFacturas;

    public void altaCliente(Cliente cliente) {
        carteraClientes.altaCliente(cliente);
    }
    public void setVista(Interfaz vista) {
        this.vista = vista;
    }


}
