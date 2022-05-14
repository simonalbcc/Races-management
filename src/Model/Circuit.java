package Model;

import java.util.Date;

public class Circuit {
    private String name;
    private Double distance;
    private Integer nbChicanesPerRound;
    private Date renewalDate;
    private Race[] races;

    public Circuit(String name, Double distance, Integer nbChicanesPerRound, Date renewalDate, Race[] races) {
        this.name = name;
        this.distance = distance;
        this.nbChicanesPerRound = nbChicanesPerRound;
        this.renewalDate = renewalDate;
        this.races = races;
    }
    public Circuit(String name, Double distance, Integer nbChicanesPerRound) {
        this.name = name;
        this.distance = distance;
        this.nbChicanesPerRound = nbChicanesPerRound;
    }
}
