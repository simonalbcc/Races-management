package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
    private Boolean improved;// voir dossier !!
    private String name;


    public Car(int number, double averageConsumption, double power, Team membership, boolean improved, String name) {
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
}
