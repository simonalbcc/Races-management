package Exception;

import Model.Driver;

public class AddDriverException extends Exception {
    private Driver driver;
    private Exception dataException;
    public AddDriverException(Driver driver, Exception dataException) {
        this.driver = driver;
        this.dataException = dataException;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote '"+driver.getLastNameFirstName()+"' existe déjà (erreur détaillée : "+dataException.getMessage()+")";
    }
}
