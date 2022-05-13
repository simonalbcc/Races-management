package Utility;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import Exception.DateException;

public class DateUtils {

    public static void dateIsCorrect(JSpinner startSpinner, JSpinner endSpinner) throws DateException {
        boolean correct = false;
        StringBuilder errorDate = new StringBuilder("Erreur : ");
        GregorianCalendar start, end, current;
        start = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(startSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(startSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(startSpinner.getValue())));
        end = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(endSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(endSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(endSpinner.getValue())));
        current = new GregorianCalendar();

        if(start.getTime().compareTo(current.getTime()) > 0){
            errorDate.append(" la date de début est après la date de ce jour");
        } else if(end.compareTo(current) > 0){
            errorDate.append(" la date de fin est après la date de ce jour");
        } else if(start.getTime().compareTo(end.getTime()) > 0){
            errorDate.append(" la date de début se situe après celle de fin");
        } else {
            correct = true;
        }
        throw new DateException(errorDate.toString());
    }


}
