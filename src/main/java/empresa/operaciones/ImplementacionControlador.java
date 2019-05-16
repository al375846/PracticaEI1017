package empresa.operaciones;

import empresa.clientes.*;
import empresa.facturas.Factura;
import empresa.interfacesUsuario.Interfaz;
import empresa.interfacesUsuario.Vista;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

public class ImplementacionControlador implements Controlador{
    private Vista vista;
    private Modelo modelo;

    public ImplementacionControlador() {

    }

    public void altaClienteParticular() {
        Cliente cliente = vista.getClientePart();
        modelo.altaCliente(cliente);
    }
    public void altaClienteEmpresa() {
        Cliente cliente = vista.getClienteEmp();
        modelo.altaCliente(cliente);
    }

    public void altaLlamada() {
        Cliente cliente = vista.getClienteAdd();
        Llamada llamada = vista.getLlamadaAdd();
        modelo.altaLlamada(cliente.getCodigo(), llamada);

    }

    public void modificarTarifa(){
        String codigo = vista.getClienteAdd().getCodigo();
        Tarifa tarifa = vista.getTarifaNueva();
        modelo.cambiarTarifaCliente(codigo, tarifa);
    }

    public void altaFactura() {
        Cliente cliente = vista.getClienteAdd();
        Factura factura = vista.getFacturaAdd();
        modelo.altaFactura(cliente, factura);
    }

    public void setTodo(Interfaz vista, ImplementacionModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
