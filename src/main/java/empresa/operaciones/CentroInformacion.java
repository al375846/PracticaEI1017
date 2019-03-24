package empresa.operaciones;

import empresa.clientes.*;
import empresa.llamadas.Llamada;
import empresa.tarifas.Tarifa;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CentroInformacion {


    public CentroInformacion() {
    }

    public static Cliente nuevoClienteEmpresa() {
        System.out.print("Inserte datos de la empresa");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique el nombre de la empresa:");
        String nombre = scanner.next();
        System.out.print("Indique el codigo de la empresa:");
        String codigo = scanner.next();
        System.out.print("Indique la tarifa que se aplicará:");
        int precio = scanner.nextInt();
        Tarifa tarifa = new Tarifa(precio);
        System.out.print("Indique el correo de la empresa:");
        String correo = scanner.next();
        System.out.print("Indique el codigo postal de la empresa:");
        String cp = scanner.next();
        System.out.print("Indique la provincia de la empresa:");
        String provincia = scanner.next();
        System.out.print("Indique la población de la empresa:");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente nuevoCliente = new ClienteEmpresa(tarifa, nombre, correo, codigo, direccion);
        return nuevoCliente;
    }

    public static Cliente nuevoClienteParticular() {
        System.out.println("Inserte datos del Cliente");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique el nombre del cliente:");
        String nombre = scanner.next();
        System.out.print("Indique los apellidos del cliente:");
        String apellidos = scanner.next();
        System.out.print("Indique la tarifa que se aplicará al cliente:");
        int precio = scanner.nextInt();
        Tarifa tarifa = new Tarifa(precio);
        System.out.print("Indique el correo del cliente:");
        String correo = scanner.next();
        System.out.print("Indique el nif del clientes:");
        String codigo = scanner.next();
        System.out.print("Indique el codigo postal del cliente:");
        String cp = scanner.next();
        System.out.print("Indique la provincia del cliente:");
        String provincia = scanner.next();
        System.out.print("Indique la población del cliente:");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente nuevoCliente = new ClienteParticular(tarifa, nombre, apellidos, correo, codigo, direccion);
        return nuevoCliente;
    }
    public static String codigoCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el codigo del cliente: ");
        String codigo = scanner.next();
        return codigo;
    }
    public static Tarifa tarifaCliente(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte la nueva tarifa");
        int precio = scanner.nextInt();
        Tarifa tarifa = new Tarifa(precio);
        return tarifa;
    }
    public static Llamada nuevaLlamada(){
        System.out.print("Inserte los datos de la llamada...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el numero al que llamó:");
        String numero = scanner.next();
        System.out.print("Inserte la duración de la llamada:");
        double duracion = scanner.nextDouble();
        Llamada llamada = new Llamada(numero, duracion, new GregorianCalendar());
        return llamada;
    }
    public static String codigoFactura(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el codigo de la factura:");
        String codigo_factura = scanner.next();
        return codigo_factura;
    }

    public static Calendar fechaInicio(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el dia de inicio del periodo de facturacion:");
        int dia_inicio = scanner.nextInt();
        System.out.print("Inserte el mes de inicio del periodo de facturacion:");
        int mes_inicio = scanner.nextInt();
        System.out.print("Inserte el año de inicio del periodo de facturacion:");
        int año_inicio = scanner.nextInt();
        Calendar inicio_periodo = new GregorianCalendar(año_inicio, mes_inicio, dia_inicio);
        //inicio_periodo.set(año_inicio, mes_inicio, dia_inicio);
        return inicio_periodo;
    }
    public static Calendar fechaFinal(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el dia de fin del periodo de facturacion:");
        int dia_fin = scanner.nextInt();
        System.out.print("Inserte el mes de fin del periodo de facturacion:");
        int mes_fin = scanner.nextInt();
        System.out.print("Inserte el año de fin del periodo de facturacion:");
        int año_fin = scanner.nextInt();
        Calendar fin_periodo = new GregorianCalendar(año_fin, mes_fin - 1, dia_fin);
        //fin_periodo.set(año_fin, mes_fin, dia_fin);
        return fin_periodo;
    }

}
