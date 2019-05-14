package empresa.operaciones;

import empresa.clientes.*;
import empresa.facturas.ConjuntoFacturas;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;

import javax.swing.*;
import java.util.Iterator;
import java.util.Set;

public class ImplementacionModelo implements Modelo {
    private Vista vista;
    private CarteraClientes carteraClientes;
    private ConjuntoFacturas conjuntoFacturas;

    public ImplementacionModelo() {
        carteraClientes = new CarteraClientes();
        conjuntoFacturas = new ConjuntoFacturas();
    }

    public void altaCliente(Cliente cliente) {
        carteraClientes.altaCliente(cliente);
        vista.setModelLista();
    }
    public void setVista(Interfaz vista) {
        this.vista = vista;
    }

    public Cliente datosCliente(String codigo) {
        return carteraClientes.datosCliente(codigo);
    }

    public DefaultListModel getClientes(){
        Set<String> listado= carteraClientes.listaClientes().keySet();
        Iterator<String> iter = listado.iterator();
        DefaultListModel nuevo = new DefaultListModel();
        while(iter.hasNext()) {
            nuevo.addElement(iter.next());
        }
        return nuevo;
    }


}
