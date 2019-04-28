package empresa.facturas;

import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.clientes.Direccion;
import empresa.excepcion.InvoiceNotFound;
import empresa.factoria.Factory;
import empresa.llamadas.Llamada;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ConjuntoFacturasTest {

    ConjuntoFacturas conjuntoFacturas;
    Factura factura;
    Factura otra_factura;
    Calendar fecha_inicio;
    Calendar fecha_fin;

    @Before
    public void setUp() throws Exception {
        conjuntoFacturas = new ConjuntoFacturas();
        fecha_inicio = new GregorianCalendar();
        fecha_inicio.set(2019,0,25);
        fecha_fin = new GregorianCalendar();
        fecha_fin.set(2019,1,25);
        factura = new Factura(86.38, "factura1", new TarifaBasica(0.15), fecha_inicio, fecha_fin);
        otra_factura = new Factura(96.03, "factura2", new TarifaBasica(0.15), fecha_inicio, fecha_fin);
    }


    @Test
    public void addFactura() throws InvoiceNotFound {

        boolean prueba = conjuntoFacturas.contieneFactura(factura);
        assertEquals(false, prueba);

        prueba = conjuntoFacturas.contieneFactura(otra_factura);
        assertEquals(false, prueba);

        conjuntoFacturas.addFactura(factura);
        prueba = conjuntoFacturas.contieneFactura(factura);
        assertEquals(true, prueba);

        conjuntoFacturas.addFactura(otra_factura);
        prueba = conjuntoFacturas.contieneFactura(otra_factura);
        assertEquals(true, prueba);

    }

    @Test
    public void obtenerFactura() throws InvoiceNotFound {

        boolean prueba = conjuntoFacturas.contieneFactura(otra_factura);
        assertEquals(false, prueba);

        conjuntoFacturas.addFactura(factura);
        assertEquals(factura, conjuntoFacturas.obtenerFactura(factura.getCodigo()));

        conjuntoFacturas.addFactura(otra_factura);
        assertEquals(otra_factura, conjuntoFacturas.obtenerFactura(otra_factura.getCodigo()));

    }

    @Test
    public void listaFacturas() throws InvoiceNotFound {
        conjuntoFacturas.addFactura(factura);
        conjuntoFacturas.addFactura(otra_factura);
        HashMap<String, Factura> total_facturas = new HashMap<>();
        total_facturas.put(factura.getCodigo(), factura);
        total_facturas.put(otra_factura.getCodigo(), otra_factura);
        assertEquals(total_facturas, conjuntoFacturas.listaFacturas());
    }

    @Test
    public void decorador() {
        Tarifa tarifa = new TarifaBasica(0.15);
        tarifa = new Diaria(tarifa, 0.09, 7);
        tarifa = new FranjaHoraria(tarifa, 0.1, 20, 23);
        GregorianCalendar fecha = new GregorianCalendar(2019, 3, 13);
        fecha.set(2019, 3,13,21,30);
        Llamada llamada = new Llamada("1", 1, fecha);
        TarifaBasica.cambiarPrecio(0.08);
        assertEquals(0.09, tarifa.getPrecioLlamada(llamada), 0);
    }

    @Test
    public void fabrica() {

        Tarifa tarifa = new TarifaBasica(0.25);
        tarifa = new Diaria(tarifa, 0.09, 5);
        tarifa = new FranjaHoraria(tarifa, 0.1, 20, 22);
        Factory fabrica = new Factory();
        Tarifa tarifa_prueba = fabrica.tarifaPersonalizada(0.25, 0.09, 5, 0.1, 20, 22);
        assertEquals(tarifa.toString(), tarifa_prueba.toString());

        Tarifa tarifa_2 = new TarifaBasica(0.15);
        tarifa_2 = new Diaria(tarifa_2, 0.1, 7);
        tarifa_2 = new FranjaHoraria(tarifa_2, 0.12, 20, 23);
        Tarifa tarifa_prueba2 = fabrica.crearTarifa();
        assertEquals(tarifa_2.toString(), tarifa_prueba2.toString());

        Cliente cliente_1 = new ClienteParticular("cliente_1", tarifa, "apellidos", "correo", "codigo", new Direccion("cp", "provincia", "poblacion"));
        Cliente cliente_prueba1 = fabrica.crearClienteParticularPersonalizado("cliente_1", "apellidos", tarifa, "correo", "codigo", new Direccion("cp", "provincia", "poblacion"));
        assertEquals(cliente_1.toString(), cliente_prueba1.toString());

        Cliente cliente_2 = new ClienteEmpresa("cliente_2", tarifa_2, "correo_empresa", "nif", new Direccion("cp_e", "provincia_e", "poblacion_e"));
        Cliente cliente_prueba2 = fabrica.crearClienteEmpresa("cliente_2", "correo_empresa","nif", new Direccion("cp_e", "provincia_e", "poblacion_e"));
        assertEquals(cliente_2.toString(), cliente_prueba2.toString());

    }

}