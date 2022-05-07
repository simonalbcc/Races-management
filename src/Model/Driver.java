package Model;

import java.sql.Date;
import java.util.GregorianCalendar;

public class Driver {
    private int serialNumber;
    private String lastNameFirstName;
    private String streetAndNumber;
    private String nationality;
    private int phoneNumber;
    private Team stable;
    private boolean hasRenewedCommitmentContract;
    private GregorianCalendar birthdate;
    private Locality home;

    public Driver(int serialNumber, String lastNameFirstName, int phoneNumber, String streetAndNumber, String nationality, Team stable, boolean hasRenewedCommitmentContract, GregorianCalendar birthdate, Locality home) {
        this.serialNumber = serialNumber;
        this.lastNameFirstName = lastNameFirstName;
        this.phoneNumber = phoneNumber;
        this.streetAndNumber = streetAndNumber;
        this.nationality = nationality;
        this.stable = stable;
        this.hasRenewedCommitmentContract = hasRenewedCommitmentContract;
        this.birthdate = birthdate;
        this.home = home;
    }

    public int getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(int serialNumber) {
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

    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Team getTeam() {
        return stable;
    }
    public void setTeam(Team stable) {
        this.stable = stable;
    }

    public boolean isHasRenewedCommitmentContract() {
        return hasRenewedCommitmentContract;
    }
    public void setHasRenewedCommitmentContract(boolean hasRenewedCommitmentContract) {
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
