package Model;

import java.util.GregorianCalendar;

public class Driver {
    private Integer number;
    private String lastNameFirstName;
    private String streetAndNumber;
    private String nationality;
    private String phoneNumber;
    private Team team;
    private Boolean hasRenewedCommitmentContract;
    private GregorianCalendar birthdate;
    private Locality home;
    private Ranking[] rankings;
    private Accident[] accidents;

    public Driver(Integer serialNumber, String lastNameFirstName, String phoneNumber, String streetAndNumber, String nationality, Team team, Boolean hasRenewedCommitmentContract, GregorianCalendar birthdate, Locality home) {
        setNumber(serialNumber);
        this.lastNameFirstName = lastNameFirstName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.nationality = nationality;
        this.team = team;
        this.hasRenewedCommitmentContract = hasRenewedCommitmentContract;
        this.birthdate = birthdate;
        this.home = home;
    }

    public Driver(String lastNameFirstName){
        this.lastNameFirstName = lastNameFirstName;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
            if(number != null){
                this.number = number;
            }
    }

    public String getLastNameFirstName() {
        return lastNameFirstName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team stable) {
        this.team = stable;
    }

    public Boolean isHasRenewedCommitmentContract() {
        return hasRenewedCommitmentContract;
    }

    public GregorianCalendar getBirthdate() {
        return birthdate;
    }

    public Locality getHome() {
        return home;
    }
}
