package empresa.operaciones;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import empresa.clientes.CarteraClientes;
import empresa.excepcion.UnexpectedAnswerException;
import empresa.facturas.ConjuntoFacturas;

import java.io.*;
import java.util.Scanner;

public class MemoryCard {

    public static String load() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Desea cargar la información guardada?(Y/N): ");
        return scanner.next();
    }

    public static Object[] loadingTodo(CarteraClientes cartera_clientes, ConjuntoFacturas total_facturas, File fichero) throws UnexpectedAnswerException {
            try {
                FileInputStream file = new FileInputStream(fichero);
                ObjectInputStream ois = new ObjectInputStream(file);
                cartera_clientes = (CarteraClientes) ois.readObject();
                total_facturas = (ConjuntoFacturas) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero");
            } catch (ClassNotFoundException c) {
                System.out.println("Error al cargar el fichero");
            }
        Object[] vuelta = {cartera_clientes, total_facturas};
        return vuelta;
    }

    public static void saveTodo(ConjuntoFacturas total_facturas, CarteraClientes cartera_clientes, File fichero) throws UnexpectedAnswerException {
            try {
                FileOutputStream filecli = new FileOutputStream(fichero);
                ObjectOutputStream save = new ObjectOutputStream(filecli);
                save.writeObject(cartera_clientes);
                save.writeObject(total_facturas);
                save.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero");
            }
    }

    public static CarteraClientes loading(CarteraClientes cartera_clientes, String respuesta) throws UnexpectedAnswerException {
        if (respuesta.compareTo("Y") == 0 || respuesta.compareTo("y") == 0) {
            try {

                FileInputStream file = new FileInputStream("clientesFinal.bin");
                ObjectInputStream ois = new ObjectInputStream(file);
                cartera_clientes = (CarteraClientes) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero");
                loading(cartera_clientes, respuesta);
            } catch (ClassNotFoundException c) {
                System.out.println("Error al cargar el fichero");
                loading(cartera_clientes, respuesta);
            }
        }
        else
        if (respuesta.compareTo("Y") != 0 && respuesta.compareTo("y") != 0 && respuesta.compareTo("N") != 0 && respuesta.compareTo("n") != 0)
            throw new UnexpectedAnswerException();
        return cartera_clientes;
    }

    public static ConjuntoFacturas loading(ConjuntoFacturas total_facturas, String respuesta) throws UnexpectedAnswerException {
        if (respuesta.compareTo("Y") == 0 || respuesta.compareTo("y") == 0) {
            try {

                FileInputStream file = new FileInputStream("facturasFinal.bin");
                ObjectInputStream ois = new ObjectInputStream(file);
                total_facturas = (ConjuntoFacturas) ois.readObject();
                ois.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero");
                loading(total_facturas, respuesta);
            } catch (ClassNotFoundException c) {
                System.out.println("Error al cargar el fichero");
                loading(total_facturas, respuesta);
            }
        }
        else
        if (respuesta.compareTo("Y") != 0 && respuesta.compareTo("y") != 0 && respuesta.compareTo("N") != 0 && respuesta.compareTo("n") != 0)
            throw new UnexpectedAnswerException();
        return total_facturas;
    }

    public static void save(ConjuntoFacturas total_facturas, CarteraClientes cartera_clientes, String respuesta) throws UnexpectedAnswerException {
        if (respuesta.compareTo("Y") == 0 || respuesta.compareTo("y") == 0) {
            try {
                FileOutputStream filecli = new FileOutputStream("clientesFinal.bin");
                ObjectOutputStream savecli = new ObjectOutputStream(filecli);
                savecli.writeObject(cartera_clientes);
                savecli.close();
                FileOutputStream filefac = new FileOutputStream("facturasFinal.bin");
                ObjectOutputStream savefac = new ObjectOutputStream(filefac);
                savefac.writeObject(total_facturas);
                savefac.close();
            } catch (IOException e) {
                System.out.println("Error al leer el fichero");
                save(total_facturas, cartera_clientes, respuesta);
            }
        }
        else
        if (respuesta.compareTo("Y") != 0 && respuesta.compareTo("y") != 0 && respuesta.compareTo("N") != 0 && respuesta.compareTo("n") != 0)
            throw new UnexpectedAnswerException();
    }
}
