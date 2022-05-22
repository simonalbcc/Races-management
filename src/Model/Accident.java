//region packages & imports
package Model;

import java.util.Date;
//endregion

public class Accident {
    private Date date;
    private Driver driver;

    public Accident(Date date, Driver driver) {
        setDate(date);
        setDriver(driver);
    }

    //region getters & setters
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    //endregion

}
