package empresa.tarifas;

import empresa.llamadas.Llamada;

import java.io.Serializable;

public class Diaria extends CasosEspecialesTarifa implements Serializable {

    private static final long serialVersionUID = 5631L;

    static int dia_aplicable = 7;      //sabado

    static double precioDiaria = 0.1;

    public Diaria(Tarifa tarifa){ //Para inicializar una tarifa general
        super(tarifa, precioDiaria);
    }

    public Diaria(Tarifa tarifa, double precio_extra, int dia_aplicable) { //Para inicializar una tarifa presonalizada
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

    public static void modificarDiaria(double precio, int dia) {
        precioDiaria = precio;
        dia_aplicable = dia;
    }

    public String descripcion() {
        return super.descripcion() + "Diaria";
    }

    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append(descripcion() + " " + getPrecio());
        tarifa.append("\n");
        tarifa.append(getTarifaLlamada().toString());
        return tarifa.toString();
    }
}
