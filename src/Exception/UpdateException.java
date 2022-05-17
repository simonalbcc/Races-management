package Exception;

import Model.Driver;

public class UpdateException extends Exception {
    private Driver driver;
    private Exception sqlException;
    public UpdateException(Exception sqlException, Driver driver) {
        this.driver = driver;
        this.sqlException = sqlException;
    }

    @Override
    public String getMessage() {
        return "Erreur lors de la mise à jour du pilote '"+driver.getLastNameFirstName()+"' (erreur détaillée : "+sqlException.getMessage()+")";
    }
}
