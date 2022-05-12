package Exception;

public class DeleteDriverException extends Exception{
    private Exception sqlException; // à supprimer

    public DeleteDriverException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote sélectionné n'existe pas dans la base de données (erreur détaillée : "+sqlException.getMessage()+").";
    }
}
