package Model;

import java.util.GregorianCalendar;

public class Driver {
    private Integer serialNumber;
    private String lastNameFirstName;
    private String streetAndNumber;
    private String nationality;
    private Integer phoneNumber;
    private Team stable;
    private Boolean hasRenewedCommitmentContract;
    private GregorianCalendar birthdate;
    private Locality home;

    public Driver(Integer serialNumber, String lastNameFirstName, Integer phoneNumber, String streetAndNumber, String nationality, Team stable, boolean hasRenewedCommitmentContract, GregorianCalendar birthdate, Locality home) {
        this.lastNameFirstName = lastNameFirstName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.nationality = nationality;
        this.stable = stable;
        this.hasRenewedCommitmentContract = hasRenewedCommitmentContract;
        this.birthdate = birthdate;
        this.home = home;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Team getTeam() {
        return stable;
    }
    public void setTeam(Team stable) {
        this.stable = stable;
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
