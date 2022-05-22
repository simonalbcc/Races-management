//region packages & imports
package Model;
//endregion

public class Ranking {
    private Car car;
    private Race race;
    private Integer position;
    private Integer nbStopsPits;
    private Integer abandonmentRoundNumber;
    private Double record;
    private Driver driver;

    public Ranking(Car car, Race race, Integer position, Integer nbStopsPits, Integer abandonmentRoundNumber, Double record, Driver driver) {
        setCar(car);
        setRace(race);
        setPosition(position);
        setNbStopsPits(nbStopsPits);
        setAbandonmentRoundNumber(abandonmentRoundNumber);
        setRecord(record);
        setDriver(driver);
    }

    public Ranking(Integer carNumber, Integer raceNumber, Integer nbStopsPits, Integer position , Integer driverNumber, Double record){
        setCar(new Car(carNumber,null));
        setRace(new Race(raceNumber,null,null,null,null,null));
        setPosition(position);
        setDriver(new Driver(driverNumber));
        setNbStopsPits(nbStopsPits);
        setRecord(record);
    }
    public Ranking(Car car){
        setCar(car);
    }

    //region getters & setters
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getNbStopsPits() {
        return nbStopsPits;
    }
    public void setNbStopsPits(Integer nbStopsPits) {
        this.nbStopsPits = nbStopsPits;
    }

    public Integer getAbandonmentRoundNumber() {
        return abandonmentRoundNumber;
    }
    public void setAbandonmentRoundNumber(Integer abandonmentRoundNumber) {
        this.abandonmentRoundNumber = abandonmentRoundNumber;
    }

    public Double getRecord() {
        return record;
    }
    public void setRecord(Double record) {
        this.record = record;
    }

    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    //endregion

}
