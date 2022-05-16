package Exception;

public class DataBaseException extends Exception{

    @Override
    public String getMessage() {
        return "Erreur, la base de donnée n'est pas connectée ou vide";
    }
}
