package Exception;

public class CircuitException extends Exception{
    private Exception sqlException; // à changer

    public CircuitException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche du circuit dans la base de données (erreur détaillée : "+sqlException.getMessage()+").";
    }
}
