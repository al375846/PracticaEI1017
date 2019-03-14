package empresa.clientes;

import empresa.excepcion.ClienteNotFound;
import empresa.facturas.Factura;
import empresa.fecha.Fecha;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.io.Serializable;
import java.util.*;

public class CarteraClientes extends Fecha implements Serializable {
    private HashMap<String, Cliente> cartera_clientes;

    public CarteraClientes() {
        this.cartera_clientes = new HashMap<>();
    }

    public void altaCliente(Cliente cliente) throws ClienteNotFound{
        if (cartera_clientes.containsKey(cliente.getCodigo()))
            throw new ClienteNotFound();
        cartera_clientes.put(cliente.getCodigo(), cliente);
    }

    public void bajaCliente(String codigo) throws ClienteNotFound{
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        cartera_clientes.remove(codigo);
    }

    public void cambiarTarifa(Tarifa tarifa, String codigo) throws ClienteNotFound{
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        cartera_clientes.get(codigo).cambiarTarifa(tarifa);
    }

    public void altaLlamada(String codigo, Llamada llamada) throws ClienteNotFound{
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        cartera_clientes.get(codigo).addLlamada(llamada);
    }

    public Cliente datosCliente(String codigo) throws ClienteNotFound {
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        return cartera_clientes.get(codigo);
    }

    public HashMap<String, Cliente> listaClientes() {
        return cartera_clientes;
    }

    public List<Llamada> listaLlamadas(String codigo) throws ClienteNotFound {
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        return cartera_clientes.get(codigo).getLlamadas();
    }

    public void añadirFactura(Factura factura, Cliente cliente) {
        cartera_clientes.get(cliente.getCodigo()).addFactura(factura);
    }

    public List<Factura> listaFacturas(String codigo) throws ClienteNotFound{
        if (!cartera_clientes.containsKey(codigo))
            throw new ClienteNotFound();
        return cartera_clientes.get(codigo).getFacturas();
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

    public boolean contieneLlamada(Cliente cliente, Llamada llamada) {
        return cliente.contieneLlamada(cliente, llamada);
    }

    public boolean contieneFactura(Cliente cliente, Factura factura) {
        return  cliente.contieneFactura(cliente, factura);
    }

}
