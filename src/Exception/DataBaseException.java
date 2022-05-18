package Exception;

public class DataBaseException extends Exception{
    private Exception exception;

    public DataBaseException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return "Erreur, la base de donnée n'est pas connectée ou vide( "+exception.getMessage()+" )";
    }
}
