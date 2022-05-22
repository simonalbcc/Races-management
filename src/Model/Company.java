//region packages & imports
package Model;
//endregion

public class Company {
    private String name;
    private String streetAndNumber;
    private String lastNameFirstNameMarketingManager;
    private Locality location;
    private Team[] teams;

    public Company(String name, String streetAndNumber, String lastNameFirstNameMarketingManager, Locality location, Team[] teams) {
        setName(name);
        setStreetAndNumber(streetAndNumber);
        setLastNameFirstNameMarketingManager(lastNameFirstNameMarketingManager);
        setLocation(location);
        setTeams(teams);
    }

    public Company(String name){
        setName(name);
    }

    //region getters & setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public void setLastNameFirstNameMarketingManager(String lastNameFirstNameMarketingManager) {
        this.lastNameFirstNameMarketingManager = lastNameFirstNameMarketingManager;
    }

    public void setLocation(Locality location) {
        this.location = location;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }
    //endregion

}
