package empresa.excepcion;

public class ClientAlreadyExistentException extends Exception {
    public ClientAlreadyExistentException(){
        super("El cliente ya existe.");
    }
}
