//region packages & imports
package Exception;
//endregion

public class AddDriverToRankingException extends Exception{

    public AddDriverToRankingException() {}


    @Override
    public String getMessage() {
        return "Erreur, cette voiture existe déjà dans ce classement.";
    }

}
