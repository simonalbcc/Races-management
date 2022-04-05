package Model;

public class Ranking {
    private Car car;
    private Race race;
    private int position;
    private int nbStopsPits;
    private int abandonmentRoundNumber;
    private double record;
    private Driver driver;

    public Ranking(Car car, Race race, int position, int nbStopsPits, int abandonmentRoundNumber, double record, Driver driver) {
        this.car = car;
        this.race = race;
        this.position = position;
        this.nbStopsPits = nbStopsPits;
        this.abandonmentRoundNumber = abandonmentRoundNumber;
        this.record = record;
        this.driver = driver;
    }

    public Ranking(Car car, Race race, int position, int nbStopsPits, double record, Driver driver) {
        this.car = car;
        this.race = race;
        this.position = position;
        this.nbStopsPits = nbStopsPits;
        this.record = record;
        this.driver = driver;
    }
}
