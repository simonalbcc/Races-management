package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
<<<<<<< Updated upstream
    private Boolean improved; // voir dossier !!
=======
    private Boolean improved;// voir dossier !!
    private String name;
>>>>>>> Stashed changes

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
