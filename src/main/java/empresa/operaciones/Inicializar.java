package empresa.operaciones;

import empresa.clientes.CarteraClientes;
import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.Direccion;
import empresa.excepcion.ClientNotFound;
import empresa.excepcion.InvoiceNotFound;
import empresa.excepcion.IllegalPeriodException;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;
import es.uji.www.GeneradorDatosINE;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Inicializar {

    public static void datosIniciales(CarteraClientes cartera_clientes, ConjuntoFacturas conjunto_facturas, int cantidad_clientes){

        GeneradorDatosINE datos;

        if (cartera_clientes.listaClientes().keySet().isEmpty()){

            Cliente cliente_0 = new ClienteEmpresa( "empresa_0", new TarifaBasica(0.15), "empresa_0@correo", "1", new Direccion("12", "CS", "Cs"));
            cliente_0.getFecha().set(2019, 0, 1);
            cartera_clientes.altaCliente(cliente_0);
            Calendar fecha_1 = new GregorianCalendar(2019, 1, 10);
            Llamada llamada_1 = new Llamada("111",1.1, fecha_1);
            Calendar fecha_2 = new GregorianCalendar(2019, 1, 13);
            Llamada llamada_2 = new Llamada("222",2.9, fecha_2);
            Calendar fecha_3 = new GregorianCalendar(2019, 2, 14);
            Llamada llamada_3 = new Llamada("333",3.0, fecha_3);
            Calendar fecha_4 = new GregorianCalendar(2019, 2, 16);
            Llamada llamada_4 = new Llamada("444",4.0, fecha_4);
            Calendar fecha_5 = new GregorianCalendar(2019, 2, 18);
            Llamada llamada_5 = new Llamada("555",4.0, fecha_5);
            cartera_clientes.altaLlamada("1", llamada_1);
            cartera_clientes.altaLlamada("1", llamada_2);
            cartera_clientes.altaLlamada("1", llamada_3);
            cartera_clientes.altaLlamada("1", llamada_4);
            cartera_clientes.altaLlamada("1", llamada_5);

            Factura factura_1 = Factura.emitirFactura(cliente_0, "f1", new GregorianCalendar(2019, 0, 15), new GregorianCalendar(2019, 1, 15));
            Factura factura_2 = Factura.emitirFactura(cliente_0, "f2", new GregorianCalendar(2019, 1, 15), new GregorianCalendar(2019, 2, 15));
            Factura factura_3 = Factura.emitirFactura(cliente_0, "f3", new GregorianCalendar(2019, 2, 15), new GregorianCalendar(2019, 3, 15));
            cartera_clientes.addFactura(factura_1, cliente_0);
            conjunto_facturas.addFactura(factura_1);
            cartera_clientes.addFactura(factura_2, cliente_0);
            conjunto_facturas.addFactura(factura_2);
            cartera_clientes.addFactura(factura_3, cliente_0);
            conjunto_facturas.addFactura(factura_3);

            for (int i = 0; i<cantidad_clientes; i++){
                datos = new GeneradorDatosINE();
                String nombre_empresa = datos.getNombre();
                String nif_empresa = datos.getNIF();
                Tarifa tarifa_empresa = new TarifaBasica(0.15);
                String provincia_empresa = datos.getProvincia();
                Direccion direccion_empresa = new Direccion("288", provincia_empresa, datos.getPoblacion(provincia_empresa));
                String correo_empresa = nombre_empresa.toLowerCase().replace(" ", "" ) + "@gmail.com";
                Cliente clienteEmpresa = new ClienteEmpresa(nombre_empresa, tarifa_empresa, correo_empresa, nif_empresa, direccion_empresa);
                cartera_clientes.altaCliente(clienteEmpresa);
            }
        }
    }
}
