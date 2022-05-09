package DataAccess;

import Model.*;

import java.util.ArrayList;
import java.util.Date;

public interface DAO {

    public int addDriver(Model.Driver driver);
    public void updateDriver();
    public void deleteDriver();
    public ArrayList<Team> getAllTeams();
    public ArrayList<Driver> getAllDrivers();
<<<<<<< HEAD
    public Locality checkLocality(Locality locality);
=======

    public ArrayList<String> getAllCircuitsNames();
    public ArrayList<Date> getRaceDatesOfACircuit(String circuitName);

<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
>>>>>>> 4c5b31be510f19fd55532ae96d20b11816263a78
>>>>>>> Stashed changes
=======
>>>>>>> 4c5b31be510f19fd55532ae96d20b11816263a78
>>>>>>> Stashed changes

}
