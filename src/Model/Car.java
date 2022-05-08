package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
    private Boolean improved;// voir dossier !!

    public Car(int number, double averageConsumption, double power, Team membership, boolean improved) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improved = improved;
    }
}
