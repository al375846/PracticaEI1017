package empresa.tarifas;

import empresa.llamadas.Llamada;

import java.io.Serializable;

public class FranjaHoraria extends CasosEspecialesTarifa implements Serializable {

    private static final long serialVersionUID = 5631L;

    static int hora_inicio = 20;
    static int hora_fin = 23;

    static double precioHoraria = 0.12;

    public FranjaHoraria(Tarifa tarifa) {} //Para inicializar una tarifa general

    public FranjaHoraria(Tarifa tarifa, double precio_extra, int inicio, int fin) { //Para inicializar una tarifa presonalizada

        super(tarifa, precio_extra);
        hora_inicio = inicio;
        hora_fin = fin;
    }

    public double getPrecio() {
        return precioHoraria;
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

    public static void modificarHoraria(double precio, int inicio, int fin) {
        precioHoraria = precio;
        hora_inicio = inicio;
        hora_fin = fin;
    }

    public String descripcion() {
        return super.descripcion() + "Horaria";
    }

    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append(descripcion() + " " + getPrecio());
        System.out.println(tarifa);
        tarifa.append("\n");
        tarifa.append(getTarifaLlamada().toString());
        System.out.println(tarifa);
        return tarifa.toString();
    }
}
