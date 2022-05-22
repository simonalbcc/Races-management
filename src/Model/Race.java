//region packages & imports
package Model;

import java.util.ArrayList;
import java.util.Date;
//endregion

public class Race {
    private Integer serialNumber;
    private Circuit circuit;
    private Integer departureHour;
    private Date date;
    private Integer nbRounds;
    private Ranking[] rankings;
    private Integer nbRankings;

    public Race(Integer serialNumber, Circuit circuit, Integer departureHour, Date date, Integer nbRounds,Ranking[] rankings){
        setSerialNumber(serialNumber);
        setCircuit(circuit);
        setDepartureHour(departureHour);
        setDate(date);
        setNbRounds(nbRounds);
        setRankings(rankings);
    }
    public Race(Date date, Ranking ranking){
        setDate(date);
        nbRankings = 0;
        addRanking(ranking);
    }

    //region getters & setters
    public Circuit getCircuit(){return circuit;}
    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public void setDepartureHour(Integer departureHour) {
        this.departureHour = departureHour;
    }

    public void setNbRounds(Integer nbRounds) {
        this.nbRounds = nbRounds;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Ranking[] getRankings() {
        return rankings;
    }
    public void setRankings(Ranking[] rankings) {
        this.rankings = rankings;
        if(rankings != null){
            nbRankings = this.rankings.length;
        }
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }
    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }
    //endregion

    public void addRanking(Ranking ranking){
        this.rankings[nbRankings] = ranking;
        nbRankings++;
    }
}
