package empresa.operaciones;

import empresa.clientes.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface Modelo {
    void altaCliente(Cliente cliente);
    Cliente datosCliente(String codigo);
    DefaultListModel getClientes();
    DefaultTableModel getLlamadas(Cliente cliente);
}
