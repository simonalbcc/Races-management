package Exception;

import Model.Driver;

public class UpdateException extends Exception {
    private Driver driver;
    private Exception dataException;
    public UpdateException(Exception dataException, Driver driver) {
        this.driver = driver;
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la mise à jour du pilote '"+driver.getLastNameFirstName()+"' (erreur détaillée : "+dataException.getMessage()+")";
    }
}
