package DataAccess;

import Model.Accident;

import java.util.ArrayList;
import java.util.Date;

public interface AccidentDAO {
    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate);
}
