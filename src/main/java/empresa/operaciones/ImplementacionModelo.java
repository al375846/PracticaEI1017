package empresa.operaciones;

import empresa.clientes.*;
import empresa.facturas.ConjuntoFacturas;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.List;
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

    public void altaLlamada(String cliente, Llamada llamada) {
        carteraClientes.altaLlamada(cliente, llamada);
        vista.setModelLlamadas();
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

    public DefaultTableModel getLlamadas(Cliente cliente){
        List<Llamada> llamadas = carteraClientes.listaLlamadas(cliente.getCodigo());
        DefaultTableModel nuevo = new DefaultTableModel();
        String[] columnas = {"Número", "Duración", "Fecha", "Hora"};
        for(int i = 0; i < columnas.length; i++){
            nuevo.addColumn(columnas[i]);
        }
        Iterator<Llamada> iter = llamadas.iterator();
        while(iter.hasNext()) {
            Llamada llamada = iter.next();
            Object[] fila = {llamada.getNum_llamo(), llamada.getDuracion(), llamada.impFecha(), llamada.impHora()};
            nuevo.addRow(fila);
        }
        return nuevo;

    }

    public void cambiarTarifaCliente(String codigo, Tarifa tarifa){
        carteraClientes.cambiarTarifa(codigo, tarifa);
    }

}
