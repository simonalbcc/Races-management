package Model;

import java.util.Date;

public class Race {
    private Integer serialNumber;
    private Circuit circuit;
    private Integer departureHour;
    private Date date;
    private Integer nbRounds;

    public Race(Integer serialNumber, Circuit circuit, Integer departureHour, Date date, Integer nbRounds){
        this.serialNumber = serialNumber;
        this.circuit = circuit;
        this.departureHour = departureHour;
        this.date = date;
        this.nbRounds = nbRounds;
    }


}
