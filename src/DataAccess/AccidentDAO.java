//region packages & imports
package DataAccess;

import Model.Accident;

import java.util.ArrayList;
import java.util.Date;
//endregion

public interface AccidentDAO {

    public ArrayList<Accident> getAccidentedDrivers(Date startDate, Date endDate) throws Exception;

}
