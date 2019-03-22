package empresa.clientes;

import empresa.excepcion.IllegalPeriodException;
import empresa.facturas.Factura;
import empresa.fecha.Fecha;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public abstract class Cliente extends Fecha implements Serializable {
    private Tarifa tarifa; //euros por minuto
    private Calendar fecha_alta;
    private List<Llamada> llamadas;
    private List<Factura> facturas;
    private String nombre;
    private String correo;
    private String codigo;
    private Direccion direccion;

    public Cliente() {

    }

    public Cliente(Tarifa tarifa, String nombre, String correo, String codigo, Direccion direccion) {
        this.tarifa = tarifa;
        this.nombre = nombre;
        this.correo = correo;
        this.codigo = codigo;
        this.direccion = direccion;
        this.fecha_alta = new GregorianCalendar();
        this.llamadas = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public Calendar getFecha() {
        return this.fecha_alta;
    }

    public Tarifa getTarifa() {
        return this.tarifa;
    }

    public List<Llamada> getLlamadas() {
        return llamadas;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void cambiarTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public String toString() {
        String fecha = (this.fecha_alta.get(Calendar.DAY_OF_MONTH) + "/" + (this.fecha_alta.get(Calendar.MONTH) + 1)+ "/" + this.fecha_alta.get(Calendar.YEAR));
        StringBuilder cliente = new StringBuilder();
        cliente.append("Nombre: " + this.nombre);
        cliente.append("\n");
        cliente.append("NIF: " + this.codigo);
        cliente.append("\n");
        cliente.append("Correo: " + this.correo);
        cliente.append("\n");
        cliente.append("Fecha: " + fecha);
        cliente.append("\n");
        cliente.append(this.tarifa.toString());
        cliente.append(this.direccion.toString());
        return cliente.toString();
    }

    public String toStringLlamadas() {
        StringBuilder to_llamadas = new StringBuilder();
        for(Llamada llamada: this.llamadas) {
           to_llamadas.append(llamada.toString());
        }
        return to_llamadas.toString();
    }

    public String toStringFacturas() {
        StringBuilder to_facturas = new StringBuilder();
        for(Factura factura: this.facturas) {
            to_facturas.append(factura.toString());
        }
        return to_facturas.toString();
    }

    public void addLlamada(Llamada llamada) {
        this.llamadas.add(llamada);
    }

    public void addFactura(Factura factura) {this.facturas.add(factura); }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public boolean contieneLlamada(Cliente cliente, Llamada llamada) {
        if (cliente.llamadas.contains(llamada))
            return true;
        return false;
    }

    public boolean contieneFactura(Cliente cliente, Factura factura) {
        if(cliente.facturas.contains(factura))
            return true;
        return false;
    }

}
