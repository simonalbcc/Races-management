package Model;

public class Car {
    private Integer number;
    private Double averageConsumption;
    private Double power;
    private Team membership;
    private Boolean improved; // voir dossier !!
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
    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPower() {
        return power;
    }
    public void setPower(Double power) {
        this.power = power;
    }

    public Double getAverageConsumption() {
        return averageConsumption;
    }
    public void setAverageConsumption(Double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Team getMembership() {
        return membership;
    }
    public void setMembership(Team membership) {
        this.membership = membership;
    }

    public Boolean getImproved() {
        return improved;
    }
    public void setImproved(Boolean improved) {
        this.improved = improved;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
