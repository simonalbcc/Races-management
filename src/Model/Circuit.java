//region packages & imports
package Model;
import java.util.Date;
//endregion

public class Circuit {
    private String name;
    private Double distance;
    private Integer nbChicanesPerRound;
    private Date renewalDate;
    private Race[] races;

    public Circuit(String name, Double distance, Integer nbChicanesPerRound, Date renewalDate, Race[] races) {
        setName(name);
        setDistance(distance);
        setNbChicanesPerRound(nbChicanesPerRound);
        setRenewalDate(renewalDate);
        setRaces(races);
    }
    public Circuit(String name, Double distance, Integer nbChicanesPerRound) {
        setName(name);
        setDistance(distance);
        setNbChicanesPerRound(nbChicanesPerRound);
    }

    //region setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setNbChicanesPerRound(Integer nbChicanesPerRound) {
        this.nbChicanesPerRound = nbChicanesPerRound;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public void setRaces(Race[] races) {
        this.races = races;
    }
    //endregion

    @Override
    public String toString() {
        return name+" ("+distance+"km)";
    }
}
