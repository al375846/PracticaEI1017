package empresa.clientes;

import java.io.Serializable;

public class Direccion implements Serializable {
    private String cp;
    private String provincia;
    private String poblacion;

    public Direccion() {
    }
    public Direccion(String cp, String provincia, String poblacion) {
        this.cp = cp;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public String getCp() {
        return cp;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String toString() {
        StringBuilder direccion = new StringBuilder();
        direccion.append("Direccion -> ");
        direccion.append("Codigo postal: " + this.cp);
        direccion.append(", Provincia: " + this.provincia);
        direccion.append(", Población: " + this.poblacion);
        direccion.append("\n");
        return direccion.toString();
    }
}
