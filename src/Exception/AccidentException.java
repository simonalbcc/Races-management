package Exception;

public class AccidentException extends Exception{
    private Exception dataException; // à changer

    public AccidentException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de l'accident dans la base de données (erreur détaillées : "+dataException.getMessage()+").";
    }
}
