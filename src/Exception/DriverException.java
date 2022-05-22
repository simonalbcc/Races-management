package Exception;

public class DriverException extends Exception{

    public DriverException() {}

    @Override
    public String getMessage() {
        return "Erreur, le pilote recherché n'existe pas dans la base de données.";
    }

}
