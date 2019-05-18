package empresa.operaciones;
import empresa.clientes.*;
import empresa.excepcion.ClientNotFound;
import empresa.excepcion.InvoiceNotFound;
import empresa.excepcion.IllegalPeriodException;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;
import empresa.facturas.Factura;

import java.util.HashSet;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws  UnexpectedAnswerException {
        ConjuntoFacturas conjunto_facturas  = new ConjuntoFacturas();
        CarteraClientes cartera_clientes = new CarteraClientes();
        OpcionesMenu opcionMenu;
        System.out.println("Bienvenido a su gestor de facturación");
        String load = MemoryCard.load();
        cartera_clientes = MemoryCard.loading(cartera_clientes, load);
        conjunto_facturas = MemoryCard.loading(conjunto_facturas, load);
        System.out.println();
        do {

            System.out.println(OpcionesMenu.getMenu());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Elige una opción: ");
            byte opcion = scanner.nextByte();
            System.out.print("\n");
            opcionMenu = OpcionesMenu.obtenerOpcion(opcion);

            switch (opcionMenu) {
                case ALTA_CLIENTE:
                    System.out.println(OpcionesSubMenuClientes.getSubMenuClientes());
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Elige tipo del cliente: ");
                    byte opcionCliente = scan.nextByte();
                    System.out.print("\n");
                    OpcionesSubMenuClientes opcionClientes = OpcionesSubMenuClientes.obtenerOpcion(opcionCliente);
                    switch (opcionClientes) {
                        case ALTA_CLIENTE_EMPRESA:
                            System.out.println(OpcionesCrearTarifa.getCrearTarifa());
                            Scanner scr = new Scanner(System.in);
                            System.out.println("Elige el tipo de tarifa del cliente");
                            byte opcion_tipo_tarifa = scr.nextByte();
                            System.out.println("\n");
                            OpcionesCrearTarifa opcionCrear = OpcionesCrearTarifa.obtenerOpcion(opcion_tipo_tarifa);
                            switch (opcionCrear) {
                                case TARIFA_GENERAL:
                                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteEmpresa());
                                    break;
                                case TARIFA_PERSONALIZA:
                                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteEmpresaPersonalizado());
                                    break;
                            }
                            break;
                        case ALTA_CLIENTE_PARTICULAR:
                            System.out.println(OpcionesCrearTarifa.getCrearTarifa());
                            Scanner scr2 = new Scanner(System.in);
                            System.out.println("Elige el tipo de tarifa del cliente");
                            byte opcion_tipo_tarifa2 = scr2.nextByte();
                            System.out.println("\n");
                            OpcionesCrearTarifa opcionCrear2 = OpcionesCrearTarifa.obtenerOpcion(opcion_tipo_tarifa2);
                            switch (opcionCrear2) {
                                case TARIFA_GENERAL:
                                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteParticular());
                                    break;
                                case TARIFA_PERSONALIZA:
                                    cartera_clientes.altaCliente(CentroInformacion.nuevoClienteParticularPersonalizado());
                                    break;
                            }
                            break;
                        case CANCELAR:
                            break;
                    }
                    break;
                case BAJA_CLIENTE:
                    cartera_clientes.bajaCliente(CentroInformacion.codigoCliente());
                    System.out.print(cartera_clientes.toString());
                    break;
                case CAMBIAR_TARIFA:
                    System.out.println(OpcionesCambiarTarifa.getCambiarTarifa());
                    Scanner sc = new Scanner(System.in);
                    System.out.print("Elige el tipo de la tarifa a cambiar: ");
                    byte valor = sc.nextByte();
                    System.out.print("\n");
                    OpcionesCambiarTarifa opcionTarifa = OpcionesCambiarTarifa.obtenerOpcion(valor);
                    switch (opcionTarifa) {
                        case TARIFA_GENERAL:
                            System.out.println(OpcionesTipoTarifa.getTipoTarifa());
                            Scanner sca = new Scanner(System.in);
                            System.out.print("Elige el tipo de la tarifa a cambiar: ");
                            byte tipovalor = sca.nextByte();
                            System.out.print("\n");
                            OpcionesTipoTarifa tipoopcion = OpcionesTipoTarifa.obtenerOpcion(tipovalor);
                            switch (tipoopcion) {
                                case TARIFA_BASICA:
                                    CentroInformacion.tarifaBasica();
                                    break;
                                case TARIFA_DIARIA:
                                    CentroInformacion.tarifaDiaria();
                                    break;
                                case TARIFA_HORARIA:
                                    CentroInformacion.tarifaHoraria();
                                    break;
                            }
                            break;
                        case TARIFA_CLIENTE:
                            cartera_clientes.cambiarTarifa(CentroInformacion.codigoCliente(), CentroInformacion.tarifaPersonalizada());
                            break;
                    }
                    break;
                case DATOS_CLIENTE:
                    System.out.print(cartera_clientes.datosCliente(CentroInformacion.codigoCliente()).toString());
                    break;
                case LISTA_CLIENTES:
                    cartera_clientes.listaClientes();
                    System.out.print(cartera_clientes.toString());
                    break;
                case ALTA_LLAMADA:
                    cartera_clientes.altaLlamada(CentroInformacion.codigoCliente(), CentroInformacion.nuevaLlamada());
                    break;
                case LISTA_LLAMADAS:
                    String codigo_llamadas = CentroInformacion.codigoCliente();
                    cartera_clientes.listaLlamadas(codigo_llamadas);
                    System.out.print(cartera_clientes.datosCliente(codigo_llamadas).toStringLlamadas());
                    break;
                case EMITIR_FACTURA:
                    Cliente cliente = cartera_clientes.datosCliente(CentroInformacion.codigoCliente());
                    Factura factura = Factura.emitirFactura(cliente, CentroInformacion.codigoFactura(), CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    cartera_clientes.addFactura(factura, cliente);
                    conjunto_facturas.addFactura(factura);
                    break;
                case DATOS_FACTURA:
                    Factura datos_factura = conjunto_facturas.obtenerFactura(CentroInformacion.codigoFactura());
                    System.out.print(datos_factura.toString());
                    break;
                case FACTURAS_CLIENTE:
                    String codigo_facturas = CentroInformacion.codigoCliente();
                    cartera_clientes.listaFacturas(codigo_facturas);
                    System.out.print(cartera_clientes.datosCliente(codigo_facturas).toStringFacturas());
                    break;
                case CLIENTES_PERIODO:
                    HashSet<Cliente> clientes = cartera_clientes.clientesEnPeriodo(CentroInformacion.fechaInicio(), CentroInformacion.fechaFinal());
                    System.out.print(cartera_clientes.toStringConjunto(clientes));
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
                    MemoryCard.save(conjunto_facturas, cartera_clientes, "Y");
                    break;
            }
        }while (opcionMenu != OpcionesMenu.SALIR);
    }
}
