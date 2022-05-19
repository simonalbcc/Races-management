package Exception;

import java.sql.SQLException;

public class AddCarException extends Exception{
    private SQLException exception;
    public AddCarException(SQLException exception) {
        this.exception = exception;
    }

    @Override
    public String getMessage() {
        return "Cette voiture existe déjà (erreur détaillée : "+exception.getMessage()+")";
    }
}
