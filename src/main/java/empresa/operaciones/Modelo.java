package empresa.operaciones;

import empresa.clientes.Cliente;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.Calendar;
import java.util.Set;

public interface Modelo {
    void altaCliente(Cliente cliente, JFrame ventana);
    Cliente datosCliente(String codigo);
    DefaultListModel getClientes();
    void altaLlamada(String cliente, Llamada llamada);
    DefaultTableModel getLlamadas(Cliente cliente);
    void cambiarTarifaCliente(String codigo, Tarifa tarifa);
    void altaFactura(Cliente cliente, Factura factura);
    boolean facturaExistente(Factura factura);
    DefaultListModel getFacturas();
    DefaultTableModel getFacturasCliente(Cliente cliente);
    Factura datosFactura(String codigo);
    void cambiarTarifaBasica(double precio);
    void cambiarTarifaDiaria(double precio, int dia);
    void cambiarTarifaHoraria(double precio, int inicio, int fin);
    void baja(String codigo, JFrame ventana);
    void load(File fichero);
    void save(File fichero);
    void actualizar();
    Set<String> totalClientes();
    DefaultTableModel getClientesBusqueda(String codigo);
    DefaultTableModel getFacturasBusqueda(String codigo);
    DefaultTableModel getFacturasPeriodoCliente(String codigo, Calendar fecha_inicio, Calendar fecha_fin);
    DefaultTableModel getLlamadasPeriodoCliente(String codigo, Calendar fecha_inicio, Calendar fecha_fin);
    DefaultTableModel getClientesPeriodo(Calendar fecha_inicio,Calendar fecha_fin);
    DefaultTableModel getFacturasPeriodo(Calendar fecha_inicio,Calendar fecha_fin);
}
