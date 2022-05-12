package Model;

import java.util.GregorianCalendar;

public class Driver {
    private Integer serialNumber;
    private String lastNameFirstName;
    private String streetAndNumber;
    private String nationality;
    private String phoneNumber;
    private Team team;
    private Boolean hasRenewedCommitmentContract;
    private GregorianCalendar birthdate;
    private Locality home;

    public Driver(Integer serialNumber, String lastNameFirstName, String phoneNumber, String streetAndNumber, String nationality, Team team, Boolean hasRenewedCommitmentContract, GregorianCalendar birthdate, Locality home) {
        setSerialNumber(serialNumber);
        this.lastNameFirstName = lastNameFirstName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.nationality = nationality;
        this.team = team;
        this.hasRenewedCommitmentContract = hasRenewedCommitmentContract;
        this.birthdate = birthdate;
        this.home = home;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
            if(serialNumber != null){
                this.serialNumber = serialNumber;
            }
    }

    public String getLastNameFirstName() {
        return lastNameFirstName;
    }
    public void setLastNameFirstName(String lastNameFirstName) {
        this.lastNameFirstName = lastNameFirstName;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }
    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    public void setHasRenewedCommitmentContract(Boolean hasRenewedCommitmentContract) {
        this.hasRenewedCommitmentContract = hasRenewedCommitmentContract;
    }

    public GregorianCalendar getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(GregorianCalendar birthdate) {
        this.birthdate = birthdate;
    }

    public Locality getHome() {
        return home;
    }
    public void setHome(Locality home) {
        this.home = home;
    }
}
