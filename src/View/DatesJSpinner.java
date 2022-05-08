package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class DatesJSpinner extends JPanel{
    private JSpinner day, month, year;
    private GregorianCalendar dateSelectedCal;
    private static final String[] months = {"Décembre", "Novembre", "Octobre", "Septembre", "Août", "Juillet", "Juin", "Mai", "Avril", "Mars", "Février", "Janvier"};
    public DatesJSpinner(){


        day = new JSpinner(new SpinnerNumberModel(1,1,31,1));
        month = new JSpinner(new SpinnerListModel(months));
        month.setValue(months[11]);
        year = new JSpinner(new SpinnerNumberModel(2000,1950, LocalDate.now().getYear(),1));
        year.setEditor(new JSpinner.NumberEditor(year, "#")); // remove comma

        day.setPreferredSize(new Dimension(75,30));
        month.setPreferredSize(new Dimension(75,30));
        year.setPreferredSize(new Dimension(75,30));

        day.addChangeListener(new SpinnerListenerChange());
        month.addChangeListener(new SpinnerListenerChange());
        year.addChangeListener(new SpinnerListenerChange());


        dateSelectedCal = new GregorianCalendar(Integer.parseInt(year.getValue().toString()), findIndexMonth(month.getValue().toString()), Integer.parseInt(day.getValue().toString()));

        year.setToolTipText("Selectionnez l'année");
        month.setToolTipText("Selectionnez le mois");
        day.setToolTipText("Selectionnez le jour");

        this.add(year);
        this.add(month);
        this.add(day);
    }
    public int findIndexMonth(String monthRead){
        int iMonth = 0;
        while(iMonth != months.length && months[iMonth] != monthRead){
            iMonth++;
        }
        return (months.length-iMonth);
    }
    public int findLengthMonth(String monthRead){
        int iMonth = findIndexMonth(monthRead);
        return YearMonth.of((int)(year.getValue()), iMonth).lengthOfMonth();
    }

    public GregorianCalendar getDateSelectedCal() {
        return dateSelectedCal;
    }

    private class SpinnerListenerChange implements ChangeListener {
        private SpinnerNumberModel spinnerModel;
        private int oldValue;
        private int lengthMonth;
        @Override
        public void stateChanged(ChangeEvent e) {
            oldValue = (int)(day.getValue());
            lengthMonth = findLengthMonth(month.getValue().toString());
            if(e.getSource() == year || e.getSource() == month){
                spinnerModel = new SpinnerNumberModel(1,1,lengthMonth,1);
                day.setModel(spinnerModel);
                if (oldValue < lengthMonth) {
                    day.setValue(oldValue);
                } else {
                    day.setValue(lengthMonth);
                }
            }
            dateSelectedCal = new GregorianCalendar((int) year.getValue(), findIndexMonth(month.getValue().toString()),(int)day.getValue());
        }
    }
}
