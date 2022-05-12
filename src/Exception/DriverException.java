package Exception;

import Model.Driver;

public class DriverException extends Exception {
    private Driver driver;
    private Exception exception;
    public DriverException(Driver driver, Exception exception) {
        this.driver = driver;
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote '"+driver.getLastNameFirstName()+"' existe déjà (code erreur : "+exception.getMessage()+")";
    }
}
