package Exception;

public class CircuitException extends Exception{
    private Exception dataException; // à changer

    public CircuitException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche du circuit dans la base de données (erreur détaillée : "+dataException.getMessage()+").";
    }
}
