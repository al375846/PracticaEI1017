package empresa.tarifas;

import java.io.Serializable;

public class Tarifa implements Serializable {
    private int precio; //euros por minuto

    public Tarifa() {
        Tarifa tarifa = new Tarifa();
    }

    public Tarifa(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }

    public String toString() {
        StringBuilder tarifa = new StringBuilder();
        tarifa.append("Tarifa: " + this.precio);
        tarifa.append("\n");
        return tarifa.toString();
    }
}
