package Model;

import java.util.Date;

public class Accident {
    private Date date;
    private Driver driver;

    public Accident(Date date, Driver driver) {
        this.date = date;
        this.driver = driver;
    }
}
