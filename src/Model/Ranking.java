package Model;

public class Ranking {
    private Car car;
    private Race race;
    private Integer position;
    private Integer nbStopsPits;
    private Integer abandonmentRoundNumber;
    private Double record;
    private Driver driver;

    public Ranking(Car car, Race race, Integer position, Integer nbStopsPits, Integer abandonmentRoundNumber, Double record, Driver driver) {
        this.car = car;
        this.race = race;
        this.position = position;
        this.nbStopsPits = nbStopsPits;
        this.abandonmentRoundNumber = abandonmentRoundNumber;
        this.record = record;
        this.driver = driver;
    }
    public Ranking(Integer carNumber, Integer raceNumber, Integer nbStopsPits, Integer position , Integer driverNumber, Double record){

        this.car = new Car(carNumber,null);
        this.race = new Race(raceNumber,null,null,null,null,null);
        this.position = position;
        this.driver = new Driver(driverNumber);
        this.nbStopsPits = nbStopsPits;
        this.record = record;
    }
    public Ranking(Car car){
        this.car = car;
    }


    public Integer getPosition() {
        return position;
    }
    public Double getRecord() {
        return record;
    }
    public Driver getDriver() {
        return driver;
    }
    public Car getCar() {
        return car;
    }
    public Race getRace() {
        return race;
    }

    public Integer getNbStopsPits() {
        return nbStopsPits;
    }
}
