package empresa.operaciones;

import empresa.clientes.*;
import empresa.factoria.Factoria;
import empresa.llamadas.Llamada;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CentroInformacion {


    public CentroInformacion() {
    }

    public static Cliente nuevoClienteEmpresa() {

        System.out.print("Inserte datos de la empresa:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique el nombre de la empresa: ");
        String nombre = scanner.next();
        System.out.print("Indique el codigo de la empresa: ");
        String codigo = scanner.next();
        System.out.print("Indique el correo de la empresa: ");
        String correo = scanner.next();
        System.out.print("Indique el codigo postal de la empresa: ");
        String cp = scanner.next();
        System.out.print("Indique la provincia de la empresa: ");
        String provincia = scanner.next();
        System.out.print("Indique la población de la empresa: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Factoria factory = new Factoria();
        Cliente nuevoCliente = factory.crearClienteEmpresa(nombre, correo, codigo, direccion);
        return nuevoCliente;
    }

    public static Cliente nuevoClienteEmpresaPersonalizado() {

        System.out.print("Inserte datos de la empresa:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Indique el nombre de la empresa: ");
        String nombre = sc.next();
        System.out.print("Indique el codigo de la empresa: ");
        String codigo = sc.next();
        Tarifa tarifa = tarifaPersonalizada();
        System.out.print("Indique el correo de la empresa: ");
        String correo = sc.next();
        System.out.print("Indique el codigo postal de la empresa: ");
        String cp = sc.next();
        System.out.print("Indique la provincia de la empresa: ");
        String provincia = sc.next();
        System.out.print("Indique la población de la empresa: ");
        String poblacion = sc.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Factoria factory = new Factoria();
        Cliente nuevoCliente = factory.crearClienteEmpresaPersonalizado(nombre, tarifa, correo, codigo, direccion);
        return nuevoCliente;
    }

    public static Cliente nuevoClienteParticular() {

        System.out.println("Inserte datos del Cliente:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Indique los apellidos del cliente: ");
        String apellidos = scanner.next();
        System.out.print("Indique el correo del cliente: ");
        String correo = scanner.next();
        System.out.print("Indique el nif del clientes: ");
        String codigo = scanner.next();
        System.out.print("Indique el codigo postal del cliente: ");
        String cp = scanner.next();
        System.out.print("Indique la provincia del cliente: ");
        String provincia = scanner.next();
        System.out.print("Indique la población del cliente: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Factoria factory = new Factoria();
        Cliente nuevoCliente = factory.crearClienteParticular(nombre, apellidos, correo, codigo, direccion);
        return nuevoCliente;
    }

    public static Cliente nuevoClienteParticularPersonalizado() {

        System.out.println("Inserte datos del Cliente:");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indique el nombre del cliente: ");
        String nombre = scanner.next();
        System.out.print("Indique los apellidos del cliente: ");
        String apellidos = scanner.next();
        Tarifa tarifa = tarifaPersonalizada();
        System.out.print("Indique el correo del cliente: ");
        String correo = scanner.next();
        System.out.print("Indique el nif del clientes: ");
        String codigo = scanner.next();
        System.out.print("Indique el codigo postal del cliente: ");
        String cp = scanner.next();
        System.out.print("Indique la provincia del cliente: ");
        String provincia = scanner.next();
        System.out.print("Indique la población del cliente: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Factoria factory = new Factoria();
        Cliente nuevoCliente = factory.crearClienteParticularPersonalizado(nombre, apellidos, tarifa, correo, codigo, direccion);
        return nuevoCliente;
    }


    public static String codigoCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el codigo del cliente: ");
        String codigo = scanner.next();
        return codigo;
    }

    public static Tarifa tarifaPersonalizada(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el precio de la tarifa básica: ");
        double precioBasica = scanner.nextDouble();
        System.out.println("Inserte el precio de la tarifa diaria: ");
        double precioDiaria = scanner.nextDouble();
        System.out.println("Inserte el dia que aplicará la tarifa (Domingo: 1, Lunes: 2 ...): ");
        int dia = scanner.nextInt();
        System.out.print("Inserte el precio de la tarifa horaria: ");
        double precioHoraria = scanner.nextDouble();
        System.out.println("Inserte la hora inicial de la tarifa horaria: ");
        int hora_inicio = scanner.nextInt();
        System.out.println("Inserte la hora final de la tarifa horaria: ");
        int hora_fin = scanner.nextInt();
        Factoria factory = new Factoria();
        Tarifa tarifa = factory.tarifaPersonalizada(precioBasica, precioDiaria, dia, precioHoraria, hora_inicio, hora_fin);
        return tarifa;
    }

    public static void tarifaBasica() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el precio de la tarifa básica: ");
        double precioBasica = scanner.nextDouble();
        TarifaBasica.cambiarPrecio(precioBasica);
    }

    public static void tarifaDiaria() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserte el precio de la tarifa diaria: ");
        double precioDiaria = scanner.nextDouble();
        System.out.println("Inserte el dia que aplicará la tarifa (Domingo: 1, Lunes: 2 ...): ");
        int dia = scanner.nextInt();
        Diaria.modificarDiaria(precioDiaria, dia);
    }
    public static void tarifaHoraria() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el precio de la tarifa horaria: ");
        double precioHoraria = scanner.nextDouble();
        System.out.println("Inserte la hora inicial de la tarifa horaria: ");
        int hora_inicio = scanner.nextInt();
        System.out.println("Inserte la hora final de la tarifa horaria: ");
        int hora_fin = scanner.nextInt();
        FranjaHoraria.modificarHoraria(precioHoraria, hora_inicio, hora_fin);
    }


    public static Llamada nuevaLlamada(){
        System.out.print("Inserte los datos de la llamada:");
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el numero al que llamó: ");
        String numero = scanner.next();
        System.out.print("Inserte la duración de la llamada (Ej: 0,0): ");
        double duracion = scanner.nextDouble();
        System.out.print("Inserte el dia en que se efectuó la llamada: ");
        int dia = scanner.nextInt();
        System.out.print("Inserte el mes en que se efectuó la llamada: ");
        int mes = scanner.nextInt();
        System.out.print("Inserte el año en que se efectuó la llamada: ");
        int year = scanner.nextInt();
        Llamada llamada = new Llamada(numero, duracion, new GregorianCalendar(year, mes -1, dia));
        return llamada;
    }
    public static String codigoFactura(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el codigo de la factura: ");
        String codigo_factura = scanner.next();
        return codigo_factura;
    }

    public static Calendar fechaInicio(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el dia de inicio del periodo de facturacion: ");
        int day_start = scanner.nextInt();
        System.out.print("Inserte el mes de inicio del periodo de facturacion: ");
        int month_start = scanner.nextInt();
        System.out.print("Inserte el año de inicio del periodo de facturacion: ");
        int year_start = scanner.nextInt();
        Calendar inicio_periodo = new GregorianCalendar(year_start, month_start -1, day_start);
        return inicio_periodo;
    }
    public static Calendar fechaFinal(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Inserte el dia de fin del periodo de facturacion: ");
        int day_end = scanner.nextInt();
        System.out.print("Inserte el mes de fin del periodo de facturacion: ");
        int month_end = scanner.nextInt();
        System.out.print("Inserte el año de fin del periodo de facturacion: ");
        int year_end = scanner.nextInt();
        Calendar fin_periodo = new GregorianCalendar(year_end, month_end - 1, day_end);
        return fin_periodo;
    }

}
