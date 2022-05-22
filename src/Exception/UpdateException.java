package Exception;

import Model.Driver;

public class UpdateException extends Exception {
    private Driver driver;
    public UpdateException(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la mise à jour du pilote '"+driver.getLastNameFirstName()+"'.";
    }
}
