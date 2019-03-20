package empresa.facturas;

import empresa.excepcion.FacturaNotFound;
import empresa.fecha.Fecha;

import java.io.Serializable;
import java.util.HashMap;

public class ConjuntoFacturas extends Fecha implements Serializable {
    private HashMap<String, Factura> facturas;

    public ConjuntoFacturas() {
        this.facturas = new HashMap<>();
    }

    public void addFactura(Factura factura) throws FacturaNotFound {
        if(facturas.containsKey(factura.getCodigo())){
            throw new FacturaNotFound();
        }
        facturas.put(factura.getCodigo(), factura);
    }

    public Factura obtenerFactura (String codigo) throws FacturaNotFound {
        if(!facturas.containsKey(codigo)){
            throw new FacturaNotFound();
        }
        return facturas.get(codigo);
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
