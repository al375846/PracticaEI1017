package empresa.excepcion;

public class InvoiceAlreadyExistentException extends Exception{
    public InvoiceAlreadyExistentException(){
        super("La factura ya existe");
    }
}
