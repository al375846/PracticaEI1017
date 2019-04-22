package empresa.tarifas;

import empresa.llamadas.Llamada;

public class Diaria extends CasosEspecialesTarifa {

    private int dia_aplicable = 7;      //sabado


    public Diaria(){

    }

    public Diaria(Tarifa tarifa, double precio_extra) {
        super(tarifa, precio_extra);
        this.dia_aplicable = dia_aplicable;
    }

    public boolean aplicable(int dia) {
        if (dia == this.dia_aplicable)
            return true;
        return false;
    }

    public double getPrecioLlamada(Llamada llamada){
        boolean aplicar = this.aplicable(llamada.getDia());
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
