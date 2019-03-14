package empresa.fecha;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Fecha implements Serializable {
    private Calendar fecha;

    public Fecha() {

    }

    public <T extends Fecha> HashSet<T> extraerEnPeriodo(Collection<T> datos, Calendar fecha_inicio, Calendar fecha_fin) {
        Collection<T> resultado = new HashSet<>();
        for(T dato: datos) {
            if (fecha_inicio.before(dato.getFecha()) && fecha_fin.after(dato.getFecha())) {
                resultado.add(dato);
                System.out.println(dato.toString());
            }
        }
        return (HashSet<T>) resultado;
    }

    public Calendar getFecha() {
        return this.fecha;
    }
}
