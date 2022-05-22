//region packages & imports
package Model;
//endregion

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Integer power;
    private Team membership;
    private Car improvedFrom;
    private String name;
    private Ranking[] rankings;

    public Car(Integer number, Double averageConsumption, Integer power, Team membership, String name) {
        setNumber(number);
        setAverageConsumption(averageConsumption);
        setPower(power);
        setMembership(membership);
        setImprovedFrom(improvedFrom);
        setName(name);
        setRankings(rankings);
    }
    public Car(Integer number, Integer power){
        setNumber(number);
        setPower(power);
    }
    public Car(Team membership){
        setMembership(membership);
    }

    //region getters & setters
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPower() {
        return power;
    }
    public void setPower(Integer power) {
        this.power = power;
    }

    public Team getMembership() {
        return membership;
    }
    public void setMembership(Team membership) {
        this.membership = membership;
    }

    public Double getAverageConsumption() {
        return averageConsumption;
    }
    public void setAverageConsumption(Double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Car getImprovedFrom() {
        return improvedFrom;
    }
    public void setImprovedFrom(Car improvedFrom) {
        this.improvedFrom = improvedFrom;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setRankings(Ranking[] rankings) {
        this.rankings = rankings;
    }
    //endregion

}