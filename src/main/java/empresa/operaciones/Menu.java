package empresa.operaciones;
import empresa.clientes.*;
import empresa.excepcion.ClienteNotFound;
import empresa.excepcion.FacturaNotFound;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws ClienteNotFound, FacturaNotFound, UnexpectedAnswerException {

        ConjuntoFacturas totalFacturas  = new ConjuntoFacturas();
        CarteraClientes cartera_clientes = new CarteraClientes();
        OpcionesMenu opcionMenu;
        System.out.println("Bienvenido a su gestor de facturación");
        String load = MemoryCard.load();
        cartera_clientes = MemoryCard.loading(cartera_clientes, load);
        totalFacturas = MemoryCard.loading(totalFacturas, load);

        do {
            System.out.println();
            System.out.println(OpcionesMenu.getMenu());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Elige una opción:");
            byte opcion = scanner.nextByte();
            opcionMenu = OpcionesMenu.obtenerOpcion(opcion);

            switch (opcionMenu) {
                case ALTA_CLIENTE_PARTICULAR:
                    cartera_clientes.altaCliente(RecopilacionInformacion.nuevoClienteParticular());
                    System.out.println(cartera_clientes.toString());
                    break;
                case ALTA_CLIENTE_EMPRESA:
                    cartera_clientes.altaCliente(RecopilacionInformacion.nuevoClienteEmpresa());
                    System.out.println(cartera_clientes.toString());
                    System.out.println("Hola");
                    break;
                case BAJA_CLIENTE:
                    cartera_clientes.bajaCliente(RecopilacionInformacion.codigoCliente());
                    System.out.println(cartera_clientes.toString());
                    break;
                case CAMBIAR_TARIFA:
                    cartera_clientes.cambiarTarifa(RecopilacionInformacion.tarifaCliente(), RecopilacionInformacion.codigoCliente());
                    System.out.println(cartera_clientes.toString());
                    break;
                case DATOS_CLIENTE:
                    System.out.println(cartera_clientes.datosCliente(RecopilacionInformacion.codigoCliente()).toString());
                    break;
                case LISTA_CLIENTES:
                    cartera_clientes.listaClientes();
                    System.out.println(cartera_clientes.toString());
                    break;
                case ALTA_LLAMADA:
                    cartera_clientes.altaLlamada(RecopilacionInformacion.codigoCliente(), RecopilacionInformacion.nuevaLlamada());
                    break;
                case LISTA_LLAMADAS:
                    String codigo_llamadas = RecopilacionInformacion.codigoCliente();
                    cartera_clientes.listaLlamadas(codigo_llamadas);
                    System.out.println(cartera_clientes.datosCliente(codigo_llamadas).toStringLlamadas());
                    break;
                case EMITIR_FACTURA:
                    Cliente cliente = cartera_clientes.datosCliente(RecopilacionInformacion.codigoCliente());
                    Factura factura = Factura.emitirFactura(cliente, RecopilacionInformacion.codigoFactura(), RecopilacionInformacion.fechaInicio(), RecopilacionInformacion.fechaFinal());
                    cartera_clientes.añadirFactura(factura, cliente);
                    totalFacturas.añadirFactura(factura);
                    break;
                case DATOS_FACTURA:
                    Factura datos_factura = totalFacturas.obtenerFactura(RecopilacionInformacion.codigoFactura());
                    System.out.println(datos_factura.toString());
                    break;
                case FACTURAS_CLIENTE:
                    //totalFacturas.listaFacturas();
                    String codigo_facturas = RecopilacionInformacion.codigoCliente();
                    cartera_clientes.listaFacturas(codigo_facturas);
                    System.out.println(cartera_clientes.datosCliente(codigo_facturas).toStringFacturas());
                    break;
                case CLIENTES_PERIODO:
                    cartera_clientes.extraerEnPeriodo(cartera_clientes.listaClientes().values(), RecopilacionInformacion.fechaInicio(), RecopilacionInformacion.fechaFinal());
                    break;
                case LLAMADAS_PERIODO:
                    Cliente cliente_llamadas = cartera_clientes.datosCliente(RecopilacionInformacion.codigoCliente());
                    cliente_llamadas.extraerEnPeriodo(cliente_llamadas.getLlamadas(), RecopilacionInformacion.fechaInicio(), RecopilacionInformacion.fechaFinal());
                    break;
                case FACTURAS_PERIODO:
                    Cliente cliente_facturas = cartera_clientes.datosCliente(RecopilacionInformacion.codigoCliente());
                    cliente_facturas.extraerEnPeriodo(cliente_facturas.getFacturas(), RecopilacionInformacion.fechaInicio(), RecopilacionInformacion.fechaFinal());
                    break;
                case SALIR:
                    MemoryCard.save(totalFacturas, cartera_clientes);
                    break;
            }
        }while (opcionMenu != OpcionesMenu.SALIR);
    }
}
