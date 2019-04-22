package empresa.tarifas;

import empresa.llamadas.Llamada;

public class TarifaBasica extends Tarifa {

    static double precioBasica = 0.15;

   public TarifaBasica() {
        super(precioBasica);
    }

   public TarifaBasica(double precio) {
       super(precio);
   }

    public double getPrecioLlamada(Llamada llamada) {
        return super.getPrecioLlamada(llamada);
    }

    public static void cambiarPrecio(double precio) {
       precioBasica = precio;
    }
}
