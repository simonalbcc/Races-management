package Exception;

public class TeamException extends Exception{
    private Exception sqlException; // à changer

    public TeamException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    public Exception getSqlException() {
        return sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de l'équipe selectionnée dans la base de données (erreur détaillée : "+sqlException.getMessage()+").";
    }
}
