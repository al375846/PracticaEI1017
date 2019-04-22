package empresa.tarifas;

import empresa.llamadas.Llamada;

public class FranjaHoraria extends CasosEspecialesTarifa {

    private int hora_inicio = 20;
    private int hora_fin = 23;

    public FranjaHoraria() {}

    public FranjaHoraria(Tarifa tarifa, double precio_extra) {
        super(tarifa, precio_extra);
    }

    public boolean aplicable(int hora) {
        if(hora >= this.hora_inicio && hora <= hora_fin)
            return true;
        return true;
    }

    public double getPrecioLlamada(Llamada llamada) {
        boolean aplicar = this.aplicable(llamada.getHora());
        if(aplicar == true) {
            if (super.getPrecioLlamada(llamada) > getTarifaLlamada().getPrecioLlamada(llamada))
                return getTarifaLlamada().getPrecioLlamada(llamada);
            else
                return super.getPrecioLlamada(llamada);
        }
        else
            return super.getTarifaLlamada().getPrecioLlamada(llamada);
    }
}
