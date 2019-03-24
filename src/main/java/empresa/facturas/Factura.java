package empresa.facturas;

import empresa.excepcion.IllegalPeriodException;
import empresa.fecha.Fecha;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import empresa.clientes.Cliente;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashSet;

public class Factura extends Fecha implements Serializable {

    private static final long serialVersionUID = 5631L;

    private double importe;
    private String codigo;
    private Tarifa tarifa;
    private Calendar fecha_emision;
    private Calendar inicio_periodo;
    private Calendar fin_periodo;

    public Factura() { }

    public Factura(double importe, String codigo, Tarifa tarifa, Calendar inicio_facturacion, Calendar fin_facturacion) {
        this.importe = importe;
        this.codigo = codigo;
        this.tarifa = tarifa;
        this.fecha_emision = new GregorianCalendar();
        this.inicio_periodo = inicio_facturacion;
        this.fin_periodo = fin_facturacion;
    }

    public static Factura emitirFactura(Cliente cliente, String codigo, Calendar inicio, Calendar fin) {
        try {
            HashSet<Llamada> llamadas = cliente.extraerEnPeriodo(cliente.getLlamadas(), inicio, fin);
            double totalMinutos = 0;
            for (Llamada llamada : llamadas)
                totalMinutos += llamada.getDuracion();
            double importe = totalMinutos * cliente.getTarifa().getPrecio();
            Factura factura = new Factura(importe, codigo, cliente.getTarifa(), inicio, fin);
            return factura;
        }
        catch (IllegalPeriodException p) {
            System.out.println("Periodo de fechas no válido");
        }
        return null;
    }

    public Calendar getFecha() {
        return fecha_emision;
    }

    public double getImporte() {
        return importe;
    }

    public String getCodigo() {
        return codigo;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Calendar getInicio_periodo() {
        return inicio_periodo;
    }

    public Calendar getFin_periodo() {
        return fin_periodo;
    }

    public void setFecha_emision(Calendar fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public boolean compare(Factura otra_factura) {
        if (this.codigo.compareTo(otra_factura.getCodigo()) == 0)
            if (this.importe == otra_factura.getImporte())
                if (this.tarifa.toString().compareTo(otra_factura.getTarifa().toString()) == 0)
                    if (this.fecha_emision.toString().compareTo(otra_factura.getFecha().toString()) == 0)
                        if (this.inicio_periodo.toString().compareTo(otra_factura.getInicio_periodo().toString()) == 0)
                            if (this.fin_periodo.toString().compareTo(otra_factura.getFin_periodo().toString()) == 0)
                                return true;
        return false;
    }

    public String toString() {
        String fecha = (this.fecha_emision.get(Calendar.DAY_OF_MONTH) + "/" + (this.fecha_emision.get(Calendar.MONTH) + 1)+ "/" + this.fecha_emision.get(Calendar.YEAR));
        StringBuilder factura = new StringBuilder();
        factura.append("Codigo: " + this.codigo);
        factura.append("\n");
        factura.append("Importe: " + this.getImporte());
        factura.append("\n");
        factura.append(this.tarifa.toString());
        factura.append("Fecha de emision: " + fecha);
        factura.append("\n");
        fecha = (this.inicio_periodo.get(Calendar.DAY_OF_MONTH) + "/" + (this.inicio_periodo.get(Calendar.MONTH) + 1) + "/" + this.inicio_periodo.get(Calendar.YEAR));
        factura.append("Inicio del periodo de facturacion: " + fecha);
        factura.append("\n");
        fecha = (this.fin_periodo.get(Calendar.DAY_OF_MONTH) + "/" + (this.fin_periodo.get(Calendar.MONTH) + 1) + "/" + this.fin_periodo.get(Calendar.YEAR));
        factura.append("Fin del periodo de facturacion: " + fecha);
        factura.append("\n");

        return factura.toString();
    }
}
