package Exception;

public class CircuitException extends Exception{

    public CircuitException() {}

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche du circuit dans la base de données.";
    }
}
