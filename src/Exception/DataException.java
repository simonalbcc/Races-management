package Exception;

public class DataException extends Exception{

    public DataException() {}

    @Override
    public String getMessage() {
        return "Erreur, la base de donnée n'est pas connectée ou vide";
    }
}
