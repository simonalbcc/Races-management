//region packages & imports
package Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;
//endregion

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
        setLastNameFirstName(lastNameFirstName);
        setPhoneNumber(phoneNumber);
        setStreetAndNumber(streetAndNumber);
        setNationality(nationality);
        setTeam(team);
        setHasRenewedCommitmentContract(hasRenewedCommitmentContract);
        setBirthdate(birthdate);
        setHome(home);
    }
    public Driver(String lastNameFirstName){
        this.lastNameFirstName = lastNameFirstName;
    }
    public Driver(Integer number){
        setNumber(number);
    }

    //region getters & setters
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        if(number != null) {
            if (number < 1000 && number > -1) {
                this.number = number;
            }
        } else {
            this.number = number;
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
        if(phoneNumber == null){
            return "";
        } else {
            return phoneNumber;
        }
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team stable) {
        this.team = stable;
    }

    public Boolean getHasRenewedCommitmentContract() {
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
    //endregion

    public int getAge(){
        return Period.between(LocalDate.now(), birthdate.toZonedDateTime().toLocalDate()).getYears();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Pilote : "+getLastNameFirstName()+"\n");
        output.append("- Âge : "+getAge()+"\n");
        output.append("- Adresse : "+getStreetAndNumber()+", "+ getHome().getCity()+" "+getHome().getPostalCode()+" "+getHome().getCountry()+"\n");
        output.append("- Nationalité : "+getNationality()+"\n");
        output.append("- Equipe : "+getTeam().getName()+"\n");
        output.append("- Numéro : "+getNumber()+"\n");
        output.append("- Numéro de téléphone : "+getPhoneNumber()+"\n");
        if(rankings != null && rankings.length > 0 ){
            output.append("apparaît dans"+(rankings.length > 1 ?"le classement":"les classements")+" suivant+\n");
            for (Ranking ranking:rankings) {
                output.append("- "+ranking.getRace().getCircuit()+"\n");
            }
        }
        if(accidents != null && accidents.length > 0){
            output.append("a eu "+(rankings.length > 1 ?"plusieurs accidents aux dates suivantes":"un accident à la date suivante")+" :\n");
            for (Accident accident:accidents) {
                output.append("- "+accident.getDate()+"\n");
            }
        }

        return output.toString();
    }
}
