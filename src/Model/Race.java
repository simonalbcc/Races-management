package Model;

import java.util.Date;

public class Race {
    private Integer serialNumber;
    private Circuit circuit;
    private Integer departureHour;
    private Date date;
    private Integer nbRounds;
    private Ranking[] rankings;

    public Race(Integer serialNumber, Circuit circuit, Integer departureHour, Date date, Integer nbRounds,Ranking[] rankings){
        this.serialNumber = serialNumber;
        this.circuit = circuit;
        this.departureHour = departureHour;
        this.date = date;
        this.nbRounds = nbRounds;
        this.rankings = rankings;
    }

    public Date getDate() {
        return date;
    }

    public Ranking[] getRankings() {
        return rankings;
    }
}
