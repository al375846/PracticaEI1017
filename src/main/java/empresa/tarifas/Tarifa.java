package empresa.tarifas;

import empresa.llamadas.Llamada;

import java.io.Serializable;

public abstract class Tarifa implements Serializable {

    private static final long serialVersionUID = 5631L;

    private double precio; //euros por minuto

    public Tarifa() {}

    public Tarifa(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPrecioLlamada(Llamada llamada) {
        return precio;
    }

    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append("Tarifa: " + this.precio);
        tarifa.append("\n");
        return tarifa.toString();
    }
}
