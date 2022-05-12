package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
    private Boolean improved;
    private String name;



    public Car(Integer number, Double averageConsumption, Double power, Team membership, Boolean improved, String name) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improved = improved;
        this.name = name;
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
