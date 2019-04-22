package empresa.facturas;

import empresa.excepcion.InvoiceNotFound;
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
    public void decorador() {
        Tarifa tarifa = new TarifaBasica(0.15);
        tarifa = new Diaria(tarifa, 0.09);
        tarifa = new FranjaHoraria(tarifa, 0.1);
        GregorianCalendar fecha = new GregorianCalendar(2019, 3, 13);
        fecha.set(2019, 3,13,21,30);
        Llamada llamada = new Llamada("1", 1, fecha);
        TarifaBasica.cambiarPrecio(0.08);
        assertEquals(0.09, tarifa.getPrecioLlamada(llamada), 0);
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
}