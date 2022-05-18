package Exception;

public class DeleteDriverException extends Exception{
    private Exception dataException; // à supprimer

    public DeleteDriverException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote sélectionné n'existe pas dans la base de données (erreur détaillée : "+dataException.getMessage()+").";
    }
}
