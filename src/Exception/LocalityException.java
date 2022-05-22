package Exception;

public class LocalityException extends Exception{

    public LocalityException() {}

    @Override
    public String getMessage() {
        return "Erreur, la localité sélectionnée n'existe pas dans la base de données.";
    }

}
