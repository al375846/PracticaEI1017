package empresa.operaciones;

import empresa.clientes.Cliente;

import javax.swing.*;

public interface Modelo {
    void altaCliente(Cliente cliente);
    Cliente datosCliente(String codigo);
    DefaultListModel getClientes();
}
