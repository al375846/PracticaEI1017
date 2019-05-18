package empresa.operaciones;

import empresa.clientes.*;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;
import empresa.llamadas.Llamada;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

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

    public void altaFactura(Cliente cliente, Factura factura) {
        carteraClientes.addFactura(factura, cliente);
        conjuntoFacturas.addFactura(factura);
        vista.setModelFacturas();
    }

    public void setVista(Interfaz vista) {
        this.vista = vista;
    }

    public Cliente datosCliente(String codigo) {
        return carteraClientes.datosCliente(codigo);
    }

    public DefaultListModel getClientes(){
        Set<String> listado = carteraClientes.listaClientes().keySet();
        Iterator<String> iter = listado.iterator();
        DefaultListModel nuevo = new DefaultListModel();
        while(iter.hasNext()) {
            nuevo.addElement(iter.next());
        }
        return nuevo;
    }

    public DefaultListModel getFacturas(){
        Set<String> listado = conjuntoFacturas.listaFacturas().keySet();
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

    public DefaultTableModel getFacturasCliente(Cliente cliente){
        List<Factura> facturas = carteraClientes.listaFacturas(cliente.getCodigo());
        DefaultTableModel nuevo = new DefaultTableModel();
        String[] columnas = {"Código", "Importe", "Emisión", "Inicio", "Fin"};
        for(int i = 0; i < columnas.length; i++){
            nuevo.addColumn(columnas[i]);
        }
        Iterator<Factura> iter = facturas.iterator();
        while(iter.hasNext()) {
            Factura factura = iter.next();
            Object[] fila = {factura.getCodigo(), factura.getImporte(), factura.impFecha(), factura.impFechaInicio(), factura.impFechaFin()};
            nuevo.addRow(fila);
        }
        return nuevo;

    }

    @Override
    public Factura datosFactura(String codigo) {
        return conjuntoFacturas.obtenerFactura(codigo);
    }

    @Override
    public void cambiarTarifaBasica(double precio) {
        TarifaBasica.cambiarPrecio(precio);
    }

    @Override
    public void cambiarTarifaDiaria(double precio, int dia) {
        Diaria.modificarDiaria(precio, dia);
    }

    @Override
    public void cambiarTarifaHoraria(double precio, int inicio, int fin) {
        FranjaHoraria.modificarHoraria(precio, inicio, fin);
    }

    public void cambiarTarifaCliente(String codigo, Tarifa tarifa){
        carteraClientes.cambiarTarifa(codigo, tarifa);
    }

}
