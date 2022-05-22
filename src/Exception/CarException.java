//region packages & imports
package Exception;
//endregion

public class CarException extends Exception{

    public CarException() {}


    @Override
    public String getMessage() {
        return "Erreur, il n'y a pas de voitures correspondant aux critères demandés dans la base de données.";
    }

}
