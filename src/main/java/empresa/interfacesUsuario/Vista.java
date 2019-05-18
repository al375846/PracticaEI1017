package empresa.interfacesUsuario;

import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

public interface Vista {
    ClienteParticular getClientePart();
    ClienteEmpresa getClienteEmp();
    void setModelLista();
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
}
