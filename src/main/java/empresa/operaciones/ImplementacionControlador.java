package empresa.operaciones;

import empresa.clientes.*;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;

public class ImplementacionControlador implements Controlador{
    private Vista vista;
    private Modelo modelo;

    public ImplementacionControlador() {

    }

    public void altaClienteParticular() {
        Cliente cliente = vista.getClientePart();
        modelo.altaCliente(cliente);
    }

    public void setTodo(Interfaz vista, ImplementacionModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
