package Exception;

public class AccidentException extends Exception{
    private Exception sqlException; // à changer

    public AccidentException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de l'accident dans la base de données (erreur détaillées : "+sqlException.getMessage()+").";
    }
}
