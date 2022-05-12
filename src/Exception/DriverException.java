package Exception;

import Model.Driver;

public class DriverException extends java.lang.Exception {
    private Driver driver;

    public DriverException(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote '"+driver.getLastNameFirstName()+"' existe déjà";
    }
}
