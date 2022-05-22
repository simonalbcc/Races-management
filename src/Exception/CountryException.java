//region packages & imports
package Exception;
//endregion

public class CountryException extends Exception{

    public CountryException() {}


    @Override
    public String getMessage() {
        return "Erreur, il n'y a aucun pays enregistré dans la base de données";
    }

}
