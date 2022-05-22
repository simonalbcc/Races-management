//region packages & imports
package Exception;
//endregion

public class TeamException extends Exception{

    public TeamException() {}


    @Override
    public String getMessage() {
        return "Erreur, il n'y pas d'équipes dans la base de données.";
    }

}
