package empresa.facturas;

import empresa.clientes.CarteraClientes;
import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.Direccion;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;
import es.uji.www.GeneradorDatosINE;
import org.junit.Before;
import org.junit.Test;


import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class FacturaTest {
    GeneradorDatosINE datos;
    Cliente clienteEmpresa;
    Llamada nueva_llamada;
    Llamada otra_llamada;
    Factura factura;
    Calendar fecha_emision;
    Calendar fecha_nuevallamada;
    Calendar fecha_otrallamada;
    Calendar fecha_inicio;
    Calendar fecha_fin;
    String codigo;
    Factura factura_prueba;
    Tarifa tarifa_empresa;

    @Before
    public void setUp() {
        datos = new GeneradorDatosINE();
        String nombre_empresa = datos.getNombre();
        String nif_empresa = datos.getNIF();
        tarifa_empresa = new TarifaBasica(0.15);
        String provincia_empresa = datos.getProvincia();
        Direccion direccion_empresa = new Direccion("288", provincia_empresa, datos.getPoblacion(provincia_empresa));
        String correo_empresa = nombre_empresa.toLowerCase().replace(" ", "" ) + "@gmail.com";
        clienteEmpresa = new ClienteEmpresa(nombre_empresa, tarifa_empresa, correo_empresa, nif_empresa, direccion_empresa);
        fecha_nuevallamada = new GregorianCalendar();
        fecha_nuevallamada.set(2019,0,31);
        nueva_llamada = new Llamada("694578245", 8.56, fecha_nuevallamada);
        fecha_otrallamada = new GregorianCalendar();
        fecha_otrallamada.set(2019,1,14);
        otra_llamada = new Llamada("674668915", 10.67, fecha_otrallamada);
        fecha_inicio = new GregorianCalendar();
        fecha_inicio.set(2019,0,25);
        fecha_fin = new GregorianCalendar();
        fecha_fin.set(2019,1,25);
        fecha_emision = new GregorianCalendar();
        fecha_emision.set(2019,2,4);
        codigo = "factura1";
        factura = new Factura(519.21, codigo, tarifa_empresa, fecha_inicio, fecha_fin);
        factura.setFecha_emision(fecha_emision);
    }
    @Test
    public void emitirFactura() {
        clienteEmpresa.addLlamada(nueva_llamada);
        clienteEmpresa.addLlamada(otra_llamada);
        factura_prueba = Factura.emitirFactura(clienteEmpresa, codigo, fecha_inicio, fecha_fin);
        factura_prueba.setFecha_emision(fecha_emision);
        boolean prueba = factura.compare(factura_prueba);
        assertEquals(true, prueba);


    }
}