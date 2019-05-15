package empresa.llamadas;

import empresa.fecha.Fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Llamada extends Fecha implements Serializable {

    private static final long serialVersionUID = 5631L;

    private String num_llamo;
    private Calendar fecha_efectuo;
    private double duracion;

    public Llamada() {
        Llamada llamada = new Llamada();
    }

    public Llamada(String num_llamo, double duracion, Calendar fehcha_efectuo) {
        this.num_llamo = num_llamo;
        this.fecha_efectuo = fehcha_efectuo;
        this.duracion = duracion;
    }

    public Calendar getFecha() {
        return fecha_efectuo;
    }
    public String impFecha(){
        return (this.fecha_efectuo.get(Calendar.DAY_OF_MONTH) + "/" + (this.fecha_efectuo.get(Calendar.MONTH) + 1)+ "/" + this.fecha_efectuo.get(Calendar.YEAR));
    }

    public String getNum_llamo() {
        return num_llamo;
    }

    public double getDuracion() {
        return duracion;
    }

    public int getDia() {

        return this.fecha_efectuo.get(Calendar.DAY_OF_WEEK);
    }
    public int getHora() {
        return this.fecha_efectuo.get(Calendar.HOUR_OF_DAY);
    }
    public String impHora() {
        return this.fecha_efectuo.get(Calendar.HOUR_OF_DAY) + ":" + this.fecha_efectuo.get(Calendar.MINUTE);
    }

    public String toString() {
        String fecha = (this.fecha_efectuo.get(Calendar.DAY_OF_MONTH) + "/" + (this.fecha_efectuo.get(Calendar.MONTH) + 1)+ "/" + this.fecha_efectuo.get(Calendar.YEAR));
        //String hora = (this.fecha_efectuo.get(Calendar.HOUR_OF_DAY) + ":" + (this.fecha_efectuo.get(Calendar.MINUTE)));
        StringBuilder llamada = new StringBuilder();
        llamada.append("Número al que llamó: " + this.num_llamo);
        llamada.append("\n");
        llamada.append(" Fecha en la que se efectuó: " + fecha);
        llamada.append("\n");
        llamada.append(" Hora en la que se efectuó: " + impHora());
        llamada.append("\n");
        llamada.append(" Duración: " + this.duracion);
        llamada.append("\n");
        return llamada.toString();
    }
}
