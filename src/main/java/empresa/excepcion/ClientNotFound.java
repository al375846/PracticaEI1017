package empresa.excepcion;

public class ClientNotFound extends Exception {
    public ClientNotFound(){
        super("El cliente no existe.");
    }
}
