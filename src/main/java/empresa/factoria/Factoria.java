package empresa.factoria;

import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

public class Factoria implements Fabrica{

    @Override
    public ClienteParticular crearClienteParticular() {
        return new ClienteParticular();
    }

    @Override
    public ClienteEmpresa crearClienteEmpresa() {
        return new ClienteEmpresa();
    }

    @Override
    public Tarifa crearTarifa(/*double precioBasica, double precioDiaria, double precioHoraria*/) {
        return new TarifaBasica(0.15);
    }

}
