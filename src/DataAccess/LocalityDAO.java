//region packages & imports
package DataAccess;

import Model.Locality;
import Exception.DataException;

import java.util.ArrayList;
import java.util.HashMap;
//endregion

public interface LocalityDAO {

    public Integer getNumberLocality(Locality locality)throws Exception;
    public HashMap getLocalitiesName() throws Exception;
    public ArrayList<String> getCountries() throws Exception;

}
