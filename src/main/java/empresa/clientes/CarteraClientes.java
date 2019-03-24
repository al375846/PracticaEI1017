package empresa.clientes;

import empresa.excepcion.ClientAlreadyExistentException;
import empresa.excepcion.ClientNotFound;
import empresa.excepcion.IllegalPeriodException;
import empresa.facturas.Factura;
import empresa.fecha.Fecha;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import java.io.Serializable;
import java.util.*;

public class CarteraClientes extends Fecha implements Serializable {
    private HashMap<String, Cliente> cartera_clientes;

    public CarteraClientes() {
        this.cartera_clientes = new HashMap<>();
    }

    public void altaCliente(Cliente cliente){
        try {
            this.contieneClienteExcepcion(cliente);
            cartera_clientes.put(cliente.getCodigo(), cliente);
        }
        catch (ClientAlreadyExistentException c) {
            System.out.println("El cliente ya existe");
        }

    }
    public void contieneClienteExcepcion(Cliente cliente) throws ClientAlreadyExistentException {
        if (cartera_clientes.containsKey(cliente.getCodigo()))
            throw new ClientAlreadyExistentException();
    }

    public void bajaCliente(String codigo){
        try {
            this.noContieneClienteExcepcion(codigo);
            cartera_clientes.remove(codigo);
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
        }
    }
    public void noContieneClienteExcepcion(String codigo) throws ClientNotFound {
        if (!cartera_clientes.containsKey(codigo))
            throw new ClientNotFound();
    }

    public void cambiarTarifa(Tarifa tarifa, String codigo) {
        try {
            this.noContieneClienteExcepcion(codigo);
            cartera_clientes.get(codigo).cambiarTarifa(tarifa);
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
        }
    }

    public void altaLlamada(String codigo, Llamada llamada){
        try {
            this.noContieneClienteExcepcion(codigo);
            cartera_clientes.get(codigo).addLlamada(llamada);
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
        }
    }

    public Cliente datosCliente(String codigo) {
        try {
            this.noContieneClienteExcepcion(codigo);
            if (this.contieneCodigo(codigo))
                return cartera_clientes.get(codigo);
            else return new ClienteParticular();
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
            return new ClienteParticular();
        }
    }

    public HashMap<String, Cliente> listaClientes() {
        return cartera_clientes;
    }

    public List<Llamada> listaLlamadas(String codigo) {
        try {
            this.noContieneClienteExcepcion(codigo);
            return cartera_clientes.get(codigo).getLlamadas();
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
            return new ArrayList<>();
        }
    }

    public void addFactura(Factura factura, Cliente cliente) {
        if (factura != null)
            cartera_clientes.get(cliente.getCodigo()).addFactura(factura);
    }

    public List<Factura> listaFacturas(String codigo){
        try {
            this.noContieneClienteExcepcion(codigo);
            return cartera_clientes.get(codigo).getFacturas();
        }
        catch (ClientNotFound c) {
            System.out.println("El cliente no existe");
            return new ArrayList<>();
        }
    }

    public String toString(){
        StringBuilder clientes = new StringBuilder();
        for (Cliente cliente: this.cartera_clientes.values())
            clientes.append(cliente.toString());
        return clientes.toString();
    }

    public boolean contieneCliente(Cliente cliente) {
        if (!cartera_clientes.containsKey(cliente.getCodigo()))
            return false;
        return true;
    }

    public boolean contieneCodigo(String codigo) {
        if (!cartera_clientes.containsKey(codigo))
            return false;
        return true;
    }

    public boolean contieneLlamada(Cliente cliente, Llamada llamada) {
        return cliente.contieneLlamada(cliente, llamada);
    }

    public boolean contieneFactura(Cliente cliente, Factura factura) {
        return  cliente.contieneFactura(cliente, factura);
    }

    public HashSet<Cliente> clientesEnPeriodo(Calendar fecha_inicio, Calendar fecha_fin) {
        try {
            return extraerEnPeriodo(cartera_clientes.values(), fecha_inicio, fecha_fin);
        }
        catch (IllegalPeriodException e) {
            System.out.println("Periodo de fechas no válido");
        }
        return new HashSet<>();
    }
    public HashSet<Llamada> llamadasEnPeriodo(String codigo, Calendar fecha_inicio, Calendar fecha_fin){
        try {
            return extraerEnPeriodo(this.datosCliente(codigo).getLlamadas(), fecha_inicio, fecha_fin);
        }
        catch (IllegalPeriodException p) {
            System.out.println("Periodo de fechas no válido");
        }
        return new HashSet<>();
    }

    public HashSet<Factura> facturasEnPeriodo(String codigo, Calendar fecha_inicio, Calendar fecha_fin) {
        try {
            return extraerEnPeriodo(this.datosCliente(codigo).getFacturas(), fecha_inicio, fecha_fin);
        }
        catch (IllegalPeriodException p) {
            System.out.println("Periodo de fechas no válido");
        }
        return new HashSet<>();
    }
}
