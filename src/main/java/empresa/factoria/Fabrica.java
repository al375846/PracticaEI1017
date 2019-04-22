package empresa.factoria;

import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.tarifas.Tarifa;

public interface Fabrica {

    ClienteParticular crearClienteParticular();
    ClienteEmpresa crearClienteEmpresa();
    Tarifa crearTarifa();
}
