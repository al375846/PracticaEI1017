package empresa.operaciones;

import empresa.clientes.CarteraClientes;
import empresa.clientes.Cliente;
import empresa.clientes.ClienteEmpresa;
import empresa.clientes.Direccion;
import empresa.excepcion.ClienteNotFound;
import empresa.excepcion.FacturaNotFound;
import empresa.excepcion.IllegalPeriodException;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) throws ClienteNotFound, FacturaNotFound, UnexpectedAnswerException, IllegalPeriodException {

        ConjuntoFacturas conjunto_facturas = new ConjuntoFacturas();
        CarteraClientes cartera_clientes = new CarteraClientes();
        Cliente cliente = new ClienteEmpresa(new Tarifa(7), "empresa", "@correo", "1", new Direccion("12", "CS", "Cs"));
        System.out.printf(cliente.toString());
        cartera_clientes.altaCliente(cliente);
        Calendar fecha_1 = new GregorianCalendar(2019, 2, 10);

        Llamada llamada_1 = new Llamada("111",1.1, fecha_1);
        System.out.printf(llamada_1.toString());
        Calendar fecha_2 = new GregorianCalendar(2019, 2, 13);
        Llamada llamada_2 = new Llamada("222",2.9, fecha_2);
        System.out.printf(llamada_2.toString());
        Calendar fecha_3 = new GregorianCalendar(2019, 2, 14);
        Llamada llamada_3 = new Llamada("333",3.0, fecha_3);
        System.out.printf(llamada_3.toString());
        Calendar fecha_4 = new GregorianCalendar(2019, 2, 16);
        Llamada llamada_4 = new Llamada("444",4.0, fecha_4);
        System.out.printf(llamada_4.toString());
        Calendar fecha_5 = new GregorianCalendar();
        fecha_5.set(2019, 2, 9);
        Llamada llamada_5 = new Llamada("555",4.0, fecha_5);
        System.out.printf(llamada_5.toString());
        cartera_clientes.altaLlamada("1", llamada_1);
        cartera_clientes.altaLlamada("1", llamada_2);

        Factura factura = Factura.emitirFactura(cliente, "f1", new GregorianCalendar(2019, 1, 15), new GregorianCalendar(2019, 2, 15));
        System.out.printf(factura.toString());
        cartera_clientes.addFactura(factura, cliente);
        System.out.println(cartera_clientes.datosCliente("1").toStringFacturas());
        conjunto_facturas.addFactura(factura);

        Factura factura_2 = Factura.emitirFactura(cliente, "f2", new GregorianCalendar(2019, 2, 12), new GregorianCalendar(2019, 3, 12));
        System.out.printf(factura_2.toString());
        cartera_clientes.addFactura(factura_2, cliente);
        System.out.println(cartera_clientes.datosCliente("1").toStringFacturas());
        conjunto_facturas.addFactura(factura_2);

    }
}
