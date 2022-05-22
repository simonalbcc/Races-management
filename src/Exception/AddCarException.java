//region packages
package Exception;
//endregion

public class AddCarException extends Exception{

    public AddCarException() {}

    @Override
    public String getMessage() {
        return "Erreur, cette voiture existe déjà.";
    }

}
