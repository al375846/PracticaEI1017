package empresa.interfacesUsuario;

import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
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
}
