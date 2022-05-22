//region packages
package Exception;
//endregion

public class AccidentException extends Exception{

    public AccidentException(){}

    @Override
    public String getMessage() {
        return "Erreur lors de la recherche de l'accident dans la base de données. L'accident sélectionné n'existe pas.";
    }

}
