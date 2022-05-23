package Exception;

public class NumberCarException extends Exception{

    public NumberCarException() {
    }

    @Override
    public String getMessage() {
        return "Le numéro est invalide.";
    }
}
