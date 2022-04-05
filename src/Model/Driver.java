package Model;

import java.util.Date;

public class Driver {
    private int serialNumber;
    private String lastNameFirstName;
    private String streetAndNumber;
    private String nationality;
    private int phoneNumber;
    private Team stable;
    private boolean hasRenewedCommitmentContract;
    private Date birthdate;
    private Locality home;

    public Driver(int serialNumber, String lastNameFirstName, int phoneNumber, String streetAndNumber, String nationality, Team stable, boolean hasRenewedCommitmentContract, Date birthdate, Locality home) {
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
}
