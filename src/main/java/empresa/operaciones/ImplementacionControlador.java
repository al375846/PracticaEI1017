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

    public void baja() {
        String baja = vista.getClienteBaja();
        modelo.baja(baja);
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


    public void modificarTarifaBasica() {
        Double precio = vista.getNueva_tarifaBasica();
        modelo.cambiarTarifaBasica(precio);
    }


    public void modificarTarifaDiaria() {
        Double precio = vista.getNueva_tarifaDiaria();
        int dia = vista.getNuevo_dia();
        modelo.cambiarTarifaDiaria(precio, dia);
    }


    public void modificarTarifaHoraria() {
        Double precio = vista.getNueva_tarifaHoraria();
        int inicio = vista.getNueva_horaInicio();
        int fin = vista.getNueva_horaFin();
        modelo.cambiarTarifaHoraria(precio, inicio, fin);
    }

    public void setTodo(Interfaz vista, ImplementacionModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }
}
