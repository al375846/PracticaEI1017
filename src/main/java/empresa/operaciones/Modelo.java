package empresa.operaciones;

import empresa.clientes.Cliente;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface Modelo {
    void altaCliente(Cliente cliente);
    Cliente datosCliente(String codigo);
    DefaultListModel getClientes();
    void altaLlamada(String cliente, Llamada llamada);
    DefaultTableModel getLlamadas(Cliente cliente);
    void cambiarTarifaCliente(String codigo, Tarifa tarifa);
    void altaFactura(Cliente cliente, Factura factura);
    DefaultListModel getFacturas();
    DefaultTableModel getFacturasCliente(Cliente cliente);
}
