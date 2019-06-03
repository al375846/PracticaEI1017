package empresa.operaciones;

import com.toedter.calendar.JCalendar;
import empresa.clientes.*;
import empresa.excepcion.ClientAlreadyExistentException;
import empresa.excepcion.IllegalPeriodException;
import empresa.excepcion.InvoiceAlreadyExistentException;
import empresa.excepcion.UnexpectedAnswerException;
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
import java.io.File;
import java.util.*;

public class ImplementacionModelo implements Modelo {
    private Vista vista;
    private CarteraClientes carteraClientes;
    private ConjuntoFacturas conjuntoFacturas;

    public ImplementacionModelo() {
        carteraClientes = new CarteraClientes();
        conjuntoFacturas = new ConjuntoFacturas();
    }

    public void load(File fichero) {
        try {
            Object[] carga = MemoryCard.loadingTodo(carteraClientes, conjuntoFacturas, fichero);
            carteraClientes = (CarteraClientes) carga[0];
            conjuntoFacturas = (ConjuntoFacturas) carga[1];
        } catch (UnexpectedAnswerException e) {
            e.printStackTrace();
        }
    }

    public void save(File fichero) {
        try {
            MemoryCard.saveTodo(conjuntoFacturas, carteraClientes, fichero);
        } catch (UnexpectedAnswerException e) {
            e.printStackTrace();
        }
    }

    public Set<String> totalClientes() {
        return carteraClientes.listaClientes().keySet();
    }


    public void actualizar() {
        vista.setModelIniciar();
    }

    public void altaCliente(Cliente cliente, JFrame ventana) {

            carteraClientes.altaCliente(cliente, ventana);
            vista.setModelLista();

    }

    public void baja(String cliente, JFrame ventana) {
        carteraClientes.bajaCliente(cliente, ventana);
        vista.setModelBaja();
    }

    public void altaLlamada(String cliente, Llamada llamada) {
        carteraClientes.altaLlamada(cliente, llamada);
        vista.setModelLlamadas();
    }

    public void altaFactura(Cliente cliente, Factura factura) {

        conjuntoFacturas.addFactura(factura);
        carteraClientes.addFactura(factura, cliente);
        vista.setModelFacturas();

    }
    public boolean facturaExistente(Factura factura){
            return conjuntoFacturas.contieneFactura(factura);
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
            String codigo = iter.next();
            nuevo.addElement(codigo);
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

    public DefaultTableModel getFacturasBusqueda(String codigo) {
        Set<String> facturas = conjuntoFacturas.listaFacturas().keySet();
        DefaultTableModel nuevo = new DefaultTableModel();
        String[] columnas = {"Código", "Importe", "Emisión", "Inicio", "Fin"};
        for(int i = 0; i < columnas.length; i++){
            nuevo.addColumn(columnas[i]);
        }
        for (String code: facturas) {
            Factura factura = conjuntoFacturas.obtenerFactura(code);
            if (factura.getCodigo().contains(codigo)) {
                Object[] fila = {factura.getCodigo(), factura.getImporte(), factura.impFecha(), factura.impFechaInicio(), factura.impFechaFin()};
                nuevo.addRow(fila);
            }
        }
        return nuevo;
    }

    public DefaultTableModel getClientesBusqueda(String codigo) {
        Set<String> clientes = carteraClientes.listaClientes().keySet();
        DefaultTableModel nuevo = new DefaultTableModel();
        String[] columnas = {"Código", "Nombre", "Alta", "Correo"};
        for(int i = 0; i < columnas.length; i++){
            nuevo.addColumn(columnas[i]);
        }
        for (String code: clientes) {
            Cliente client = carteraClientes.datosCliente(code);
            if (client.getCodigo().contains(codigo)) {
                Object[] fila = {client.getCodigo(), client.getNombre(), client.impFecha(), client.getCorreo()};
                nuevo.addRow(fila);
            }
        }
        return nuevo;
    }


    public Factura datosFactura(String codigo) {
        return conjuntoFacturas.obtenerFactura(codigo);
    }


    public void cambiarTarifaBasica(double precio) {
        TarifaBasica.cambiarPrecio(precio);
    }


    public void cambiarTarifaDiaria(double precio, int dia) {
        Diaria.modificarDiaria(precio, dia);
    }


    public void cambiarTarifaHoraria(double precio, int inicio, int fin) {
        FranjaHoraria.modificarHoraria(precio, inicio, fin);
    }

    public void cambiarTarifaCliente(String codigo, Tarifa tarifa)  {
        carteraClientes.cambiarTarifa(codigo, tarifa);
    }

    public DefaultTableModel getFacturasPeriodoCliente(String codigo, Calendar fecha_inicio, Calendar fecha_fin) {
        try {
            Set<Factura> facturas = carteraClientes.datosCliente(codigo).extraerEnPeriodo(carteraClientes.datosCliente(codigo).getFacturas(), fecha_inicio, fecha_fin);
            //Set<Factura> facturas = carteraClientes.facturasEnPeriodo(codigo, fecha_inicio, fecha_fin, );

            DefaultTableModel nuevo = new DefaultTableModel();
            String[] columnas = {"Código", "Importe", "Emisión", "Inicio", "Fin"};
            for (int i = 0; i < columnas.length; i++) {
                nuevo.addColumn(columnas[i]);
            }
            for (Factura factura : facturas) {

                Object[] fila = {factura.getCodigo(), factura.getImporte(), factura.impFecha(), factura.impFechaInicio(), factura.impFechaFin()};
                nuevo.addRow(fila);

            }
            return nuevo;
        } catch (IllegalPeriodException e) {
            e.printStackTrace();
            return new DefaultTableModel();
        }

    }

    public DefaultTableModel getLlamadasPeriodoCliente(String codigo, Calendar fecha_inicio, Calendar fecha_fin) {
        try {
            Set<Llamada> llamadas = carteraClientes.datosCliente(codigo).extraerEnPeriodo(carteraClientes.datosCliente(codigo).getLlamadas(), fecha_inicio, fecha_fin);
            DefaultTableModel nuevo = new DefaultTableModel();
            String[] columnas = {"Número", "Duración", "Fecha", "Hora"};
            for(int i = 0; i < columnas.length; i++){
                nuevo.addColumn(columnas[i]);
            }
            for (Llamada llamada: llamadas) {
                Object[] fila = {llamada.getNum_llamo(), llamada.getDuracion(), llamada.impFecha(), llamada.impHora()};
                nuevo.addRow(fila);
            }
            return nuevo;
        } catch (IllegalPeriodException e) {
            System.out.println("Periodo de fechas no válido.");
            return new DefaultTableModel();
        }

    }


    public DefaultTableModel getFacturasPeriodo(Calendar fecha_inicio,Calendar fecha_fin) {
        try {
            Set<Factura> facturas = conjuntoFacturas.extraerEnPeriodo(conjuntoFacturas.listaFacturas().values(), fecha_inicio, fecha_fin);
            DefaultTableModel nuevo = new DefaultTableModel();
            String[] columnas = {"Código", "Importe", "Emisión", "Inicio", "Fin"};
            for (int i = 0; i < columnas.length; i++) {
                nuevo.addColumn(columnas[i]);
            }
            for (Factura factura : facturas) {
                Object[] fila = {factura.getCodigo(), factura.getImporte(), factura.impFecha(), factura.impFechaInicio(), factura.impFechaFin()};
                nuevo.addRow(fila);
            }
            return nuevo;
        } catch (IllegalPeriodException e) {
            System.out.println("Periodo de fechas no válido.");
            return new DefaultTableModel();
        }

    }

    public DefaultTableModel getClientesPeriodo(Calendar fecha_inicio,Calendar fecha_fin) {
        try {
            Set<Cliente> clientes = carteraClientes.extraerEnPeriodo(carteraClientes.listaClientes().values(), fecha_inicio, fecha_fin);
            DefaultTableModel nuevo = new DefaultTableModel();
            String[] columnas = {"Código", "Nombre", "Alta", "Correo"};
            for (int i = 0; i < columnas.length; i++) {
                nuevo.addColumn(columnas[i]);
            }
            for (Cliente cliente : clientes) {
                Object[] fila = {cliente.getCodigo(), cliente.getNombre(), cliente.impFecha(), cliente.getCorreo()};
                nuevo.addRow(fila);
            }
            return nuevo;
        } catch (IllegalPeriodException e) {
            System.out.println("Periodo de fechas no válido.");
            return new DefaultTableModel();
        }
    }


}
