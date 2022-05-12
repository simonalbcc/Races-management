package Model;

public class Company {
    private String name;
    private String streetAndNumber;
    private String lastNameFirstNameMarketingManager;
    private Locality location;
    private Team[] teams;

    public Company(String name, String streetAndNumber, String lastNameFirstNameMarketingManager, Locality location, Team[] teams) {
        this.name = name;
        this.streetAndNumber = streetAndNumber;
        this.lastNameFirstNameMarketingManager = lastNameFirstNameMarketingManager;
        this.location = location;
        this.teams = teams;
    }
}
