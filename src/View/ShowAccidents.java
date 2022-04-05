package View;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;


public class ShowAccidents extends JPanel {
    private JLabel subtitle;
    private ButtonsPanel buttonsPanel;
    private SpinnerPanel spinnerPanel;
    String[] months = {"Januari", "Februari", "March", "April", "May", "June", "July", "Augustus", "September", "October", "November", "December"};


    public ShowAccidents() {

        buttonsPanel = new ButtonsPanel();
        spinnerPanel = new SpinnerPanel();



        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);


        subtitle = new JLabel("<html> <h3> Select the starting date of the accident : </h3> </html>");
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        subtitle.setBorder(new BorderUIResource.TitledBorderUIResource(""));


        this.add(subtitle, gc);
        gc.gridy = 1;
        this.add(spinnerPanel, gc);
        gc.gridy = 2;
        this.add(buttonsPanel,gc);

    }

    public void setSubtitle(String subtitle){
        this.subtitle.setText(subtitle);
    }

    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    private class SpinnerPanel extends JPanel{
        private JSpinner day, month, year;
        private JLabel dayLabel, monthLabel, yearLabel;
        public SpinnerPanel() {

            this.setLayout(new GridLayout(3,2));


            day = new JSpinner(new SpinnerNumberModel(1,1,31,1));
            month = new JSpinner(new SpinnerListModel(months));
            year = new JSpinner(new SpinnerNumberModel(2000,1950, LocalDate.now().getYear(),1));
            year.setEditor(new JSpinner.NumberEditor(year, "#")); // remove comma


            year.addChangeListener(new SpinnerListenerChange());
            month.addChangeListener(new SpinnerListenerChange());


            dayLabel = new JLabel("Select the day : ");
            monthLabel = new JLabel("Select the month : ");
            yearLabel = new JLabel("Select the year : ");


            this.add(yearLabel);
            this.add(year);
            this.add(monthLabel);
            this.add(month);
            this.add(dayLabel);
            this.add(day);
        }
        public int findIndexMonth(String monthRead){
            int iMonth = 0;
            while(iMonth != months.length && months[iMonth] != monthRead){
                iMonth++;
            }
            return iMonth+1;
        }
        public int findLengthMonth(String monthRead){
            int iMonth = findIndexMonth(monthRead);
            return YearMonth.of(Integer.parseInt(year.getValue().toString()), iMonth).lengthOfMonth();
        }

        private class SpinnerListenerChange implements ChangeListener{

            @Override
            public void stateChanged(ChangeEvent e) {
                day.setModel(new SpinnerNumberModel(1,1,findLengthMonth(month.getValue().toString()),1));
            }
        }

    }
    private class ButtonsPanel extends JPanel{
        private JButton next, back, ok;
        public ButtonsPanel(){
            // to comeback to the first panel or menu
            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonListener());

            // To enter in the second panel
            next = new JButton("<html> <u>N</u>ext </html>");
            next.addActionListener(new ButtonListener());

            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == next){
                    ShowAccidents.this.removeAll();

                    repaint();
                }
                if(e.getSource() == back){
                    removeAll();
                    add(new ShowAccidents());
                    repaint();
                    validate();
                }
                if(e.getSource() == ok){

                }
                repaint();
            validate();
            }
        }
    }


    //endregion
}