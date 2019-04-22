package empresa.tarifas;

import empresa.llamadas.Llamada;

public abstract class CasosEspecialesTarifa extends Tarifa {

    private Tarifa tarifa;

    public CasosEspecialesTarifa() {
    }

    public CasosEspecialesTarifa(Tarifa tarifa, double precio) {
        super(precio);
        this.tarifa = tarifa;
    }

    public double getPrecioLlamada(Llamada llamada) {
        if(super.getPrecio() > tarifa.getPrecioLlamada(llamada))
            return tarifa.getPrecioLlamada(llamada);
        return super.getPrecio();
    }

    public Tarifa getTarifaLlamada() {
        return tarifa;
    }

}
