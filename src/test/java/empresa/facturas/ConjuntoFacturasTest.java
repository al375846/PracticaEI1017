package empresa.facturas;

import empresa.excepcion.FacturaNotFound;
import empresa.tarifas.Tarifa;
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
        factura = new Factura(86.38, "factura1", new Tarifa(31), fecha_inicio, fecha_fin);
        otra_factura = new Factura(96.03, "factura2", new Tarifa(5), fecha_inicio, fecha_fin);
    }

    @Test
    public void añadirFactura() throws FacturaNotFound {

        boolean prueba = conjuntoFacturas.contieneFactura(factura);
        assertEquals(false, prueba);

        prueba = conjuntoFacturas.contieneFactura(otra_factura);
        assertEquals(false, prueba);

        conjuntoFacturas.añadirFactura(factura);
        prueba = conjuntoFacturas.contieneFactura(factura);
        assertEquals(true, prueba);

        conjuntoFacturas.añadirFactura(otra_factura);
        prueba = conjuntoFacturas.contieneFactura(otra_factura);
        assertEquals(true, prueba);

    }

    @Test
    public void obtenerFactura() throws FacturaNotFound{

        try{
            conjuntoFacturas.obtenerFactura(otra_factura.getCodigo());
        } catch (FacturaNotFound e){
            assertEquals(true, true);
        }

        conjuntoFacturas.añadirFactura(factura);
        assertEquals(factura, conjuntoFacturas.obtenerFactura(factura.getCodigo()));

        conjuntoFacturas.añadirFactura(otra_factura);
        assertEquals(otra_factura, conjuntoFacturas.obtenerFactura(otra_factura.getCodigo()));

    }

    @Test
    public void listaFacturas() throws FacturaNotFound{
        conjuntoFacturas.añadirFactura(factura);
        conjuntoFacturas.añadirFactura(otra_factura);
        HashMap<String, Factura> total_facturas = new HashMap<>();
        total_facturas.put(factura.getCodigo(), factura);
        total_facturas.put(otra_factura.getCodigo(), otra_factura);
        assertEquals(total_facturas, conjuntoFacturas.listaFacturas());
    }
}