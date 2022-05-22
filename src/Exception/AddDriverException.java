package Exception;

import Model.Driver;

public class AddDriverException extends Exception {
    private Driver driver;
    public AddDriverException(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote '"+driver.getLastNameFirstName()+"' existe déjà. ";
    }
}
