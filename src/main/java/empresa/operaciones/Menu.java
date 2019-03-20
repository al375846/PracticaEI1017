package empresa.operaciones;
import empresa.clientes.*;
import empresa.excepcion.ClienteNotFound;
import empresa.excepcion.FacturaNotFound;
import empresa.excepcion.IllegalPeriodException;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws ClienteNotFound, FacturaNotFound, UnexpectedAnswerException, IllegalPeriodException {

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
                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteParticular());
                    System.out.println(cartera_clientes.toString());
                    break;
                case ALTA_CLIENTE_EMPRESA:
                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteEmpresa());
                    System.out.println(cartera_clientes.toString());
                    break;
                case BAJA_CLIENTE:
                    cartera_clientes.bajaCliente(CentroInformacion.codigoCliente());
                    System.out.println(cartera_clientes.toString());
                    break;
                case CAMBIAR_TARIFA:
                    cartera_clientes.cambiarTarifa(CentroInformacion.tarifaCliente(), CentroInformacion.codigoCliente());
                    System.out.println(cartera_clientes.toString());
                    break;
                case DATOS_CLIENTE:
                    System.out.println(cartera_clientes.datosCliente(CentroInformacion.codigoCliente()).toString());
                    break;
                case LISTA_CLIENTES:
                    cartera_clientes.listaClientes();
                    System.out.println(cartera_clientes.toString());
                    break;
                case ALTA_LLAMADA:
                    cartera_clientes.altaLlamada(CentroInformacion.codigoCliente(), CentroInformacion.nuevaLlamada());
                    break;
                case LISTA_LLAMADAS:
                    String codigo_llamadas = CentroInformacion.codigoCliente();
                    cartera_clientes.listaLlamadas(codigo_llamadas);
                    System.out.println(cartera_clientes.datosCliente(codigo_llamadas).toStringLlamadas());
                    break;
                case EMITIR_FACTURA:
                    Cliente cliente = cartera_clientes.datosCliente(CentroInformacion.codigoCliente());
                    Factura factura = Factura.emitirFactura(cliente, CentroInformacion.codigoFactura(), CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    cartera_clientes.addFactura(factura, cliente);
                    totalFacturas.addFactura(factura);
                    break;
                case DATOS_FACTURA:
                    Factura datos_factura = totalFacturas.obtenerFactura(CentroInformacion.codigoFactura());
                    System.out.println(datos_factura.toString());
                    break;
                case FACTURAS_CLIENTE:
                    String codigo_facturas = CentroInformacion.codigoCliente();
                    cartera_clientes.listaFacturas(codigo_facturas);
                    System.out.println(cartera_clientes.datosCliente(codigo_facturas).toStringFacturas());
                    break;
                case CLIENTES_PERIODO:
                    cartera_clientes.clientesEnPeriodo(CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    break;
                case LLAMADAS_PERIODO:
                    String cliente_llamadas = CentroInformacion.codigoCliente();
                    cartera_clientes.llamadasEnPeriodo(cliente_llamadas, CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    break;
                case FACTURAS_PERIODO:
                    String cliente_facturas = CentroInformacion.codigoCliente();
                    cartera_clientes.facturasEnPeriodo(cliente_facturas, CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    break;
                case SALIR:
                    MemoryCard.save(totalFacturas, cartera_clientes);
                    break;
            }
        }while (opcionMenu != OpcionesMenu.SALIR);
    }
}
