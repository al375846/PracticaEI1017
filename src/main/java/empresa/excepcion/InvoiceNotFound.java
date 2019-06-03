package empresa.excepcion;

public class InvoiceNotFound extends Exception {
    public InvoiceNotFound(){
        super("La factura no existe");
    }
}
