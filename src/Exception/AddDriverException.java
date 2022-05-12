package Exception;

import Model.Driver;

public class AddDriverException extends Exception {
    private Driver driver;
    private Exception sqlException;
    public AddDriverException(Driver driver, Exception sqlException) {
        this.driver = driver;
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur, le pilote '"+driver.getLastNameFirstName()+"' existe déjà (erreur détaillée : "+sqlException.getMessage()+")";
    }
}
