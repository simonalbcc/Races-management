package Exception;

public class LocalityException extends Exception{
    private Exception dataException; // à changer

    public LocalityException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur la création de la localité dans la base de données (erreur détaillée : "+dataException.getMessage()+")."; // à changer
    }
}
