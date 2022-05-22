package Exception;

public class RaceException extends Exception{

    public RaceException() {}

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de la course selectionnée dans la base de données.";
    }

}
