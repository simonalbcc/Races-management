package Model;

public class Car {
    private int number;
    private double averageConsumption;
    private double power;
    private Team membership;
    private boolean improved;// voir dossier !!

    public Car(int number, double averageConsumption, double power, Team membership, boolean improved) {
        this.number = number;
        this.averageConsumption = averageConsumption;
        this.power = power;
        this.membership = membership;
        this.improved = improved;
    }
}
