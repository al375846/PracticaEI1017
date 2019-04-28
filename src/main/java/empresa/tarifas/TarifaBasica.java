package empresa.tarifas;

import empresa.llamadas.Llamada;

import java.io.Serializable;

public class TarifaBasica extends Tarifa implements Serializable {

    private static final long serialVersionUID = 5631L;

    static double precioBasica = 0.15;

   public TarifaBasica() {
        super(precioBasica);
    } //Para inicializar una tarifa general

   public TarifaBasica(double precio) {
       super(precio);
   } //Para inicializar una tarifa presonalizada

    public double getPrecioLlamada(Llamada llamada) {
        return super.getPrecioLlamada(llamada);
    }

    public static void cambiarPrecio(double precio) {
       precioBasica = precio;
    }

    public String descripcion() {
        return super.descripcion() + " Basica";
    }

    public double getPrecio() {
       return precioBasica;
    }

    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append(descripcion() + " " + getPrecio());
        tarifa.append("\n");
        return tarifa.toString();
    }
}
