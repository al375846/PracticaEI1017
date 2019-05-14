package empresa.operaciones;

import empresa.clientes.*;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;

public class ImplementacionControlador implements Controlador{
    private Vista vista;
    private Modelo modelo;

    public void altaClienteParticular() {
        modelo.altaCliente(vista.getClientePart());
    }

    public void setTodo(Interfaz vista, ImplementacionModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
