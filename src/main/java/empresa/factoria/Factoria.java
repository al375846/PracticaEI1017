package empresa.factoria;

import empresa.clientes.ClienteEmpresa;
import empresa.clientes.ClienteParticular;
import empresa.clientes.Direccion;
import empresa.tarifas.Diaria;
import empresa.tarifas.FranjaHoraria;
import empresa.tarifas.Tarifa;
import empresa.tarifas.TarifaBasica;

public class Factoria{

    public ClienteParticular crearClienteParticular(String nombre, String apellidos, String correo, String codigo, Direccion direccion) {
        return new ClienteParticular(nombre, crearTarifa(), apellidos, correo, codigo, direccion);
    }

    public ClienteParticular crearClienteParticularPersonalizado(String nombre, String apellidos, Tarifa tarifa, String correo, String codigo, Direccion direccion) {
        return new ClienteParticular(nombre, tarifa, apellidos, correo, codigo, direccion);
    }

    public ClienteEmpresa crearClienteEmpresa(String nombre, String correo, String codigo, Direccion direccion) {
        return new ClienteEmpresa(nombre, crearTarifa(), correo, codigo, direccion);
    }

    public ClienteEmpresa crearClienteEmpresaPersonalizado(String nombre, Tarifa tarifa, String correo, String codigo, Direccion direccion) {
        return new ClienteEmpresa(nombre, tarifa, correo, codigo, direccion);
    }

    public Tarifa crearTarifa() {
        Tarifa tarifa = new TarifaBasica();
        System.out.println(tarifa.toString());
        tarifa = new Diaria(tarifa);
        System.out.println(tarifa.toString());
        tarifa = new FranjaHoraria(tarifa);
        System.out.println(tarifa.toString());
        return tarifa;
    }

    public Tarifa tarifaPersonalizada(double precioBasica, double precioDiaria,int diaDiaria, double precioHoraria, int hora_inicio, int hora_fin) {
        Tarifa tarifa = new TarifaBasica(precioBasica);
        tarifa = new Diaria(tarifa, precioDiaria, diaDiaria);
        tarifa = new FranjaHoraria(tarifa, precioHoraria, hora_inicio, hora_fin);
        return tarifa;
    }
}
