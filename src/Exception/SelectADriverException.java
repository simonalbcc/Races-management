package Exception;

public class SelectADriverException extends Exception {
    private Exception exceptionSQL;

    public SelectADriverException(Exception exceptionSQL) {
        this.exceptionSQL = exceptionSQL;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche du pilote. (erreur détaillée : "+exceptionSQL+")";
    }
}
