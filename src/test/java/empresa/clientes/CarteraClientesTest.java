package empresa.clientes;

import empresa.excepcion.ClienteNotFound;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CarteraClientesTest {

    CarteraClientes carteraClientes;
    GeneradorDatosINE datos;
    Cliente clienteEmpresa;
    Cliente clienteParticular;
    Llamada nueva_llamada;
    Llamada otra_llamada;
    Factura factura;
    Factura otra_factura;
    Calendar fecha_inicio;
    Calendar fecha_fin;

    @Before
    public void setUp() throws Exception {
        carteraClientes = new CarteraClientes();
        datos = new GeneradorDatosINE();
        String nombre_empresa = datos.getNombre();
        String nif_empresa = datos.getNIF();
        Tarifa tarifa_empresa = new Tarifa(27);
        String provincia_empresa = datos.getProvincia();
        Direccion direccion_empresa = new Direccion("288", provincia_empresa, datos.getPoblacion(provincia_empresa));
        String correo_empresa = nombre_empresa.toLowerCase().replace(" ", "" ) + "@gmail.com";
        clienteEmpresa = new ClienteEmpresa(tarifa_empresa, nombre_empresa, correo_empresa, nif_empresa, direccion_empresa);
        datos = new GeneradorDatosINE();
        String nombre_particular = datos.getNombre();
        String nif_particular = datos.getNIF();
        Tarifa tarifa_particular = new Tarifa(13);
        String apellidos_particular = datos.getApellido();
        String provincia_particular = datos.getProvincia();
        Direccion direccion_particular = new Direccion("353", provincia_particular, datos.getPoblacion(provincia_particular));
        String correo_particular = nombre_particular.toLowerCase().replace(" ", "" ) + "@gmail.com";
        clienteParticular = new ClienteParticular(tarifa_particular, nombre_particular, apellidos_particular, correo_particular, nif_particular, direccion_particular);
        nueva_llamada = new Llamada("694578245", 8.56, new GregorianCalendar());
        otra_llamada = new Llamada("674668915", 10.67, new GregorianCalendar());
        fecha_inicio = new GregorianCalendar();
        fecha_inicio.set(2019,0,25);
        fecha_fin = new GregorianCalendar();
        fecha_fin.set(2019,1,25);
        factura = new Factura(86.38, "factura1", new Tarifa(31), fecha_inicio, fecha_fin);
        otra_factura = new Factura(96.03, "factura2", new Tarifa(5), fecha_inicio, fecha_fin);
    }

    @Test
    public void altaCliente() throws ClienteNotFound {
        carteraClientes.altaCliente(clienteEmpresa);
        boolean prueba = carteraClientes.contieneCliente(clienteEmpresa);
        assertEquals(true, prueba);

        carteraClientes.altaCliente(clienteParticular);
        prueba = carteraClientes.contieneCliente(clienteParticular);
        assertEquals(true, prueba);

        prueba = carteraClientes.contieneCliente(new ClienteEmpresa());
        assertEquals(false, prueba);

    }

    @Test
    public void bajaCliente() throws ClienteNotFound {

        carteraClientes.altaCliente(clienteEmpresa);
        boolean prueba = carteraClientes.contieneCliente(clienteEmpresa);
        assertEquals(true, prueba);
        carteraClientes.bajaCliente(clienteEmpresa.getCodigo());
        prueba = carteraClientes.contieneCliente(new ClienteEmpresa());
        assertEquals(false, prueba);

        carteraClientes.altaCliente(clienteParticular);
        prueba = carteraClientes.contieneCliente(clienteParticular);
        assertEquals(true, prueba);
        carteraClientes.bajaCliente(clienteParticular.getCodigo());
        prueba = carteraClientes.contieneCliente(new ClienteEmpresa());
        assertEquals(false, prueba);

    }

    @Test
    public void cambiarTarifa() {

        Tarifa nuevaTarifa = new Tarifa(15);
        assertNotEquals(nuevaTarifa, clienteEmpresa.getTarifa());
        clienteEmpresa.cambiarTarifa(nuevaTarifa);
        assertEquals(nuevaTarifa, clienteEmpresa.getTarifa());

    }

    @Test
    public void altaLlamada() throws ClienteNotFound {

        carteraClientes.altaCliente(clienteParticular);
        carteraClientes.altaLlamada(clienteParticular.getCodigo(), nueva_llamada);
        boolean prueba = carteraClientes.contieneLlamada(clienteParticular, nueva_llamada);
        assertEquals(true, prueba);
        prueba = carteraClientes.contieneLlamada(clienteParticular, otra_llamada);
        assertEquals(false, prueba);

    }

    @Test
    public void datosCliente() throws  ClienteNotFound{
        carteraClientes.altaCliente(clienteParticular);
        assertEquals(clienteParticular, carteraClientes.datosCliente(clienteParticular.getCodigo()));
        carteraClientes.altaCliente(clienteEmpresa);
        assertEquals(clienteEmpresa, carteraClientes.datosCliente(clienteEmpresa.getCodigo()));
    }

    @Test
    public void listaClientes() throws ClienteNotFound{

        CarteraClientes carteraEnB = new CarteraClientes();
        carteraEnB.altaCliente(clienteEmpresa);
        carteraEnB.altaCliente(clienteParticular);
        HashMap<String, Cliente> clientes = new HashMap<>();
        clientes.put(clienteEmpresa.getCodigo(), clienteEmpresa);
        clientes.put(clienteParticular.getCodigo(), clienteParticular);
        assertEquals(clientes, carteraEnB.listaClientes());

    }

    @Test
    public void listaLlamadas() throws ClienteNotFound{

        carteraClientes.altaCliente(clienteEmpresa);
        List<Llamada> registroLlamadas = new ArrayList<>();
        registroLlamadas.add(nueva_llamada);
        registroLlamadas.add(otra_llamada);
        assertNotEquals(registroLlamadas, carteraClientes.listaLlamadas(clienteEmpresa.getCodigo()));

        carteraClientes.altaLlamada(clienteEmpresa.getCodigo(), nueva_llamada);
        carteraClientes.altaLlamada(clienteEmpresa.getCodigo(), otra_llamada);
        assertEquals(registroLlamadas, carteraClientes.listaLlamadas(clienteEmpresa.getCodigo()));

    }

    @Test
    public void addFactura() throws ClienteNotFound{

        carteraClientes.altaCliente(clienteEmpresa);
        boolean prueba = carteraClientes.contieneFactura(clienteEmpresa, factura);
        assertEquals(false, prueba);

        carteraClientes.addFactura(factura, clienteEmpresa);
        prueba = carteraClientes.contieneFactura(clienteEmpresa, factura);
        assertEquals(true, prueba);
    }

    @Test
    public void listaFacturas() throws ClienteNotFound{

        carteraClientes.altaCliente(clienteParticular);
        List<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        facturas.add(otra_factura);
        assertNotEquals(facturas, carteraClientes.listaFacturas(clienteParticular.getCodigo()));

        carteraClientes.addFactura(factura, clienteParticular);
        carteraClientes.addFactura(otra_factura, clienteParticular);
        assertEquals(facturas, carteraClientes.listaFacturas(clienteParticular.getCodigo()));

    }
}