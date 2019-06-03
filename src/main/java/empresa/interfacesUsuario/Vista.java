package empresa.interfacesUsuario;

import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import javax.swing.*;
import java.util.Calendar;

public interface Vista {
    ClienteParticular getClientePart();
    ClienteEmpresa getClienteEmp();
    void setModelLista();
    JFrame getFrameAltaCliente();
    Cliente getClienteAdd();
    Llamada getLlamadaAdd();
    void setModelLlamadas();
    Tarifa getTarifaNueva();
    Factura getFacturaAdd();
    void setModelFacturas();
    Double getNueva_tarifaBasica();
    Double getNueva_tarifaDiaria();
    int getNuevo_dia();
    Double getNueva_tarifaHoraria();
    int getNueva_horaInicio();
    int getNueva_horaFin();
    String getClienteBaja();
    void setModelIniciar();
    void setModelBaja();
    JFrame getVentana();
    JFrame getFrameFactura();

}
