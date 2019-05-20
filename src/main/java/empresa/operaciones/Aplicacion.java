package empresa.operaciones;

import empresa.clientes.ClienteParticular;
import empresa.clientes.Direccion;
import empresa.factoria.Factory;
import empresa.interfacesUsuario.Interfaz;

import javax.swing.*;

public class Aplicacion {
    public static void main(String[] args) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        Interfaz vista = new Interfaz();
        ImplementacionModelo modelo = new ImplementacionModelo();
        modelo.setVista(vista);
        controlador.setTodo(vista, modelo);
        vista.setTodo(modelo, controlador);
        vista.principal();
    }
}
