package empresa.excepcion;

public class IllegalPeriodException extends Exception {
    public IllegalPeriodException(){
        super("Periodo de fechas no válido.");
    }
}
