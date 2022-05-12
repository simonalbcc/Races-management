package DataAccess;

import Model.Locality;

public interface LocalityDAO {

    public Integer getNumberLocality(Locality locality)throws Exception;
    public void createLocality(Locality locality)throws Exception;



}
