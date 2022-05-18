package Exception;

public class SelectADriverException extends Exception {
    private Exception dataException;

    public SelectADriverException(Exception dataException) {
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche du pilote. (erreur détaillée : "+dataException+")";
    }
}
