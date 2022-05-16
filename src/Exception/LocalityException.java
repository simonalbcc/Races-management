package Exception;

public class LocalityException extends Exception{
    private Exception sqlException; // à changer

    public LocalityException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur la création de la localité dans la base de données (erreur détaillée : "+sqlException.getMessage()+")."; // à changer
    }
}
