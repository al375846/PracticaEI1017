package empresa.tarifas;

import empresa.llamadas.Llamada;

import java.io.Serializable;

public abstract class CasosEspecialesTarifa extends Tarifa implements Serializable {

    private static final long serialVersionUID = 5631L;

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

    public String descripcion() {
        return super.descripcion() + " especial: ";
    }

    public Tarifa getTarifaLlamada() {
        return tarifa;
    }

}
