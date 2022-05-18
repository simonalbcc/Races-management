package Exception;

public class DataException extends Exception{
    private Exception exception;

    public DataException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return "Erreur, la base de donnée n'est pas connectée ou vide( "+exception.getMessage()+" )";
    }
}
