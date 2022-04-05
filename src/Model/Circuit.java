package Model;

import java.util.Date;

public class Circuit {
    private String name;
    private double distance;
    private int nbChicanesPerRound;
    private Date renewalDate;

    public Circuit(String name, double distance, int nbChicanesPerRound, Date renewalDate) {
        this.name = name;
        this.distance = distance;
        this.nbChicanesPerRound = nbChicanesPerRound;
        this.renewalDate = renewalDate;
    }
    public Circuit(String name, double distance, int nbChicanesPerRound) {
        this.name = name;
        this.distance = distance;
        this.nbChicanesPerRound = nbChicanesPerRound;
    }
}
