package Exception;

public class RaceException extends Exception{
    private Exception sqlException; // à changer

    public RaceException(Exception sqlException) {
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de la course selectionnée dans la base de données (erreur détailée : "+sqlException.getMessage()+").";
    }
}
