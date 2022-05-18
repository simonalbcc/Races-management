package Exception;

public class RaceException extends Exception{
    private Exception dataException; // à changer

    public RaceException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de la course selectionnée dans la base de données (erreur détailée : "+dataException.getMessage()+").";
    }
}
