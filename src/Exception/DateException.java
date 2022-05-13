package Exception;

public class DateException extends Exception{

    private String exceptionMessage;

    public DateException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String getMessage() {
        return exceptionMessage;
    }
}
