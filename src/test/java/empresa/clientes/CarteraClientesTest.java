package empresa.clientes;

import empresa.excepcion.ClientNotFound;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;
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
    Llamada llamada_1;
    Llamada llamada_2;
    Llamada llamada_3;
    Llamada llamada_4;
    Llamada llamada_5;

    @Before
    public void setUp() throws Exception {
        carteraClientes = new CarteraClientes();
        datos = new GeneradorDatosINE();
        String nombre_empresa = datos.getNombre();
        String nif_empresa = datos.getNIF();
        Tarifa tarifa_empresa = new TarifaBasica(0.15);
        String provincia_empresa = datos.getProvincia();
        Direccion direccion_empresa = new Direccion("288", provincia_empresa, datos.getPoblacion(provincia_empresa));
        String correo_empresa = nombre_empresa.toLowerCase().replace(" ", "" ) + "@gmail.com";
        clienteEmpresa = new ClienteEmpresa(nombre_empresa, tarifa_empresa, correo_empresa, nif_empresa, direccion_empresa);
        datos = new GeneradorDatosINE();
        String nombre_particular = datos.getNombre();
        String nif_particular = datos.getNIF();
        Tarifa tarifa_particular = new TarifaBasica(0.15);
        String apellidos_particular = datos.getApellido();
        String provincia_particular = datos.getProvincia();
        Direccion direccion_particular = new Direccion("353", provincia_particular, datos.getPoblacion(provincia_particular));
        String correo_particular = nombre_particular.toLowerCase().replace(" ", "" ) + "@gmail.com";
        clienteParticular = new ClienteParticular(nombre_particular, tarifa_particular, apellidos_particular, correo_particular, nif_particular, direccion_particular);
        Calendar fecha_1 = new GregorianCalendar(2019, 2, 10);
        llamada_1 = new Llamada("111",1.1, fecha_1);
        Calendar fecha_2 = new GregorianCalendar(2019, 2, 13);
        llamada_2 = new Llamada("222",2.9, fecha_2);
        Calendar fecha_3 = new GregorianCalendar(2019, 2, 14);
        llamada_3 = new Llamada("333",3.0, fecha_3);
        Calendar fecha_4 = new GregorianCalendar(2019, 2, 16);
        llamada_4 = new Llamada("444",4.0, fecha_4);
        Calendar fecha_5 = new GregorianCalendar(2019, 2, 18);
        llamada_5 = new Llamada("555",4.0, fecha_5);
        nueva_llamada = new Llamada("694578245", 8.56, new GregorianCalendar());
        otra_llamada = new Llamada("674668915", 10.67, new GregorianCalendar());
        fecha_inicio = new GregorianCalendar(2019,0,25);
        fecha_fin = new GregorianCalendar(2019,1,25);
        factura = new Factura(86.38, "factura1", new TarifaBasica(0.15), fecha_inicio, fecha_fin);
        otra_factura = new Factura(96.03, "factura2", new TarifaBasica(0.15), fecha_inicio, fecha_fin);
    }

    @Test
    public void altaCliente() throws ClientNotFound {
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
    public void bajaCliente() throws ClientNotFound {

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

        Tarifa nuevaTarifa = new TarifaBasica(0.15);
        assertNotEquals(nuevaTarifa, clienteEmpresa.getTarifa());
        clienteEmpresa.cambiarTarifa(nuevaTarifa);
        assertEquals(nuevaTarifa, clienteEmpresa.getTarifa());

    }

    @Test
    public void altaLlamada() throws ClientNotFound {

        carteraClientes.altaCliente(clienteParticular);
        carteraClientes.altaLlamada(clienteParticular.getCodigo(), nueva_llamada);
        boolean prueba = carteraClientes.contieneLlamada(clienteParticular, nueva_llamada);
        assertEquals(true, prueba);
        prueba = carteraClientes.contieneLlamada(clienteParticular, otra_llamada);
        assertEquals(false, prueba);

    }

    @Test
    public void datosCliente() throws ClientNotFound {
        carteraClientes.altaCliente(clienteParticular);
        assertEquals(clienteParticular, carteraClientes.datosCliente(clienteParticular.getCodigo()));
        carteraClientes.altaCliente(clienteEmpresa);
        assertEquals(clienteEmpresa, carteraClientes.datosCliente(clienteEmpresa.getCodigo()));
    }

    @Test
    public void listaClientes() throws ClientNotFound {

        CarteraClientes carteraEnB = new CarteraClientes();
        carteraEnB.altaCliente(clienteEmpresa);
        carteraEnB.altaCliente(clienteParticular);
        HashMap<String, Cliente> clientes = new HashMap<>();
        clientes.put(clienteEmpresa.getCodigo(), clienteEmpresa);
        clientes.put(clienteParticular.getCodigo(), clienteParticular);
        assertEquals(clientes, carteraEnB.listaClientes());

    }

    @Test
    public void listaLlamadas() throws ClientNotFound {

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
    public void addFactura() throws ClientNotFound {

        carteraClientes.altaCliente(clienteEmpresa);
        boolean prueba = carteraClientes.contieneFactura(clienteEmpresa, factura);
        assertEquals(false, prueba);

        carteraClientes.addFactura(factura, clienteEmpresa);
        prueba = carteraClientes.contieneFactura(clienteEmpresa, factura);
        assertEquals(true, prueba);
    }

    @Test
    public void listaFacturas() throws ClientNotFound {

        carteraClientes.altaCliente(clienteParticular);
        List<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        facturas.add(otra_factura);
        assertNotEquals(facturas, carteraClientes.listaFacturas(clienteParticular.getCodigo()));

        carteraClientes.addFactura(factura, clienteParticular);
        carteraClientes.addFactura(otra_factura, clienteParticular);
        assertEquals(facturas, carteraClientes.listaFacturas(clienteParticular.getCodigo()));

    }

    @Test
    public void extraerPeriodo() {
        clienteEmpresa.getFecha().set(2019, 2,1);
        carteraClientes.altaCliente(clienteEmpresa);
        HashSet<Cliente> clientes = new HashSet<>();
        assertEquals(clientes, carteraClientes.clientesEnPeriodo(new GregorianCalendar(2021,1,1), new GregorianCalendar(2022,1,1)));
        clientes.add(clienteEmpresa);
        assertEquals(clientes, carteraClientes.clientesEnPeriodo(new GregorianCalendar(2019, 1, 1), new GregorianCalendar(2020,1,1)));
        clienteEmpresa.addLlamada(llamada_1);
        clienteEmpresa.addLlamada(llamada_2);
        clienteEmpresa.addLlamada(llamada_3);
        clienteEmpresa.addLlamada(llamada_4);
        clienteEmpresa.addLlamada(llamada_5);
        HashSet<Llamada> llamadas = new HashSet<>();
        llamadas.add(llamada_1);
        llamadas.add(llamada_2);
        assertEquals(llamadas, carteraClientes.llamadasEnPeriodo(clienteEmpresa.getCodigo(), new GregorianCalendar(2019, 1, 1), new GregorianCalendar(2019, 2, 14)));
        llamadas.clear();
        llamadas.add(llamada_3);
        llamadas.add(llamada_4);
        llamadas.add(llamada_5);
        assertEquals(llamadas, carteraClientes.llamadasEnPeriodo(clienteEmpresa.getCodigo(), new GregorianCalendar(2019, 2, 13), new GregorianCalendar(2019, 2, 19)));
        HashSet<Factura> facturas = new HashSet<>();
        assertEquals(facturas, carteraClientes.facturasEnPeriodo(clienteEmpresa.getCodigo(), new GregorianCalendar(2019,1,26), new GregorianCalendar(2019,2,26)));
        factura.getFecha().set(2019, 2, 15);
        otra_factura.getFecha().set(2019, 5, 15);
        clienteEmpresa.addFactura(factura);
        clienteEmpresa.addFactura(otra_factura);
        facturas.add(factura);
        facturas.add(otra_factura);
        assertEquals(facturas, carteraClientes.facturasEnPeriodo(clienteEmpresa.getCodigo(), new GregorianCalendar(2019,0,15), new GregorianCalendar(2019,6,26)));
        facturas.remove(otra_factura);
        assertEquals(facturas, carteraClientes.facturasEnPeriodo(clienteEmpresa.getCodigo(), new GregorianCalendar(2019,1,15), new GregorianCalendar(2019,3,15)));
    }
}