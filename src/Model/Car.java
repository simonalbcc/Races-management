package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Integer power;
    private Team membership;
    private Car improvedFrom;
    private String name;
    private Ranking[] rankings;


    public Car(Integer number, Double averageConsumption, Integer power, Team membership, Car improvedFrom, String name, Ranking[] rankings) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improvedFrom = improvedFrom;
        this.name = name;
        this.rankings = rankings;
    }

    public Car(Integer number, Double averageConsumption, Integer power, Team membership, String name) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improvedFrom = improvedFrom;
        this.name = name;
        this.rankings = rankings;
    }

    public Car(Integer number, Integer power){
        this.number = number;
        this.power = power;
    }
    public Car(Integer number){
        this.number = number;
    }

    public Car(Team membership){
        this.membership = membership;
    }

    public Integer getNumber() {
        return number;
    }
    public Integer getPower() {
        return power;
    }
    public Team getMembership() {
        return membership;
    }
    public Double getAverageConsumption() {
        return averageConsumption;
    }
    public Car getImprovedFrom() {
        return improvedFrom;
    }
    public String getName() {
        return name;
    }
}