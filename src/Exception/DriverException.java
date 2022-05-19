package Exception;

public class DriverException extends Exception{
    @Override
    public String getMessage() {
        return "Ce pilote n'existe pas";
    }
}
