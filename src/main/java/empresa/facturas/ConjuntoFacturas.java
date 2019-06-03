package empresa.facturas;

import empresa.excepcion.InvoiceNotFound;
import empresa.excepcion.InvoiceAlreadyExistentException;
import empresa.fecha.Fecha;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public class ConjuntoFacturas extends Fecha implements Serializable {

    private static final long serialVersionUID = 5631L;

    private HashMap<String, Factura> facturas;

    public ConjuntoFacturas() {
        this.facturas = new HashMap<>();
    }

    public void addFactura(Factura factura) {
        if (factura != null) {
            try {
                contieneFacturaExcepcion(factura.getCodigo());
                facturas.put(factura.getCodigo(), factura);
            }
            catch (InvoiceAlreadyExistentException i) {
                i.printStackTrace();
            }
        }
    }

    public void contieneFacturaExcepcion(String codigo) throws InvoiceAlreadyExistentException {
        if (facturas.containsKey(codigo))
            throw new InvoiceAlreadyExistentException();
    }

    public void noContieneFacturaExcepcion(String codigo) throws InvoiceNotFound {
        if (!facturas.containsKey(codigo))
            throw new InvoiceNotFound();
    }
    public Factura obtenerFactura (String codigo) {
        try {
            noContieneFacturaExcepcion(codigo);
            return facturas.get(codigo);
        }
        catch (InvoiceNotFound i) {
            i.printStackTrace();
        }
        return new Factura();
    }

    public HashMap<String, Factura> listaFacturas() {
        return facturas;
    }

    public boolean contieneFactura(Factura factura) {
        return facturas.containsKey(factura.getCodigo());
    }

    public String toString(){
        StringBuilder facturas = new StringBuilder();
        for (Factura factura: this.facturas.values())
            facturas.append(factura.toString());
        return facturas.toString();
    }

}
