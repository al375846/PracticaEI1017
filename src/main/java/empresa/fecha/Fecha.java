package empresa.fecha;

import empresa.excepcion.IllegalPeriodException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Fecha implements Serializable {

    private static final long serialVersionUID = 5631L;

    private Calendar fecha;

    public Fecha() {

    }

    public <T extends Fecha> HashSet<T> extraerEnPeriodo(Collection<T> datos, Calendar fecha_inicio, Calendar fecha_fin) throws IllegalPeriodException {
        if(fecha_inicio.after(fecha_fin))
            throw new IllegalPeriodException();

        Collection<T> resultado = new HashSet<>();
        for(T dato: datos) {
            if (fecha_inicio.before(dato.getFecha()) && fecha_fin.after(dato.getFecha())) {
                resultado.add(dato);
            }
        }
        return (HashSet<T>) resultado;
    }

    public <T extends Fecha> String toStringConjunto(HashSet<T> lista) {
        StringBuilder listado = new StringBuilder();
        listado.append("\n");
        for(T dato: lista)
            listado.append(dato.toString());
        return listado.toString();
    }
    public Calendar getFecha() {
        return this.fecha;
    }

}
