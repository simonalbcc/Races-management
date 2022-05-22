package Exception;

public class DeleteDriverException extends Exception{

    public DeleteDriverException() {}

    @Override
    public String getMessage() {
        return "Erreur, la suppression du pilote sélectionné ne peut être effectuée.";
    }
}
