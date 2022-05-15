package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
    private Car improvedFrom;
    private String name;
    private Ranking[] rankings;


    public Car(Integer number, Double averageConsumption, Double power, Team membership, Car improvedFrom, String name, Ranking[] rankings) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improvedFrom = improvedFrom;
        this.name = name;
        this.rankings = rankings;
    }

    public Car(Integer number, Double power){
        this.number = number;
        this.power = power;
    }

    public Car(Team membership){
        this.membership = membership;
    }

    public Integer getNumber() {
        return number;
    }
    public Double getPower() {
        return power;
    }
    public Team getMembership() {
        return membership;
    }
}
