
package View;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;

public class AccidentsPanel extends JPanel {
    private JLabel subtitleStart, subtitleEnd;
    private ButtonsPanel buttonsPanel;
    private JPanel spinnerPanel;
    private SpinnerPanel startSpinner, endSpinner;
    private GridBagConstraints gc;
    private static final String[] months = {"December", "November", "October", "September", "Augustus", "July", "June", "May", "April", "March", "Februari", "Januari"};
    private  StateChangeWindow stateFirstWindow, stateSecondWindow;
    private  StateChangeWindow currentState;

    public AccidentsPanel() {
        // create layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);

        // create buttons panel
        buttonsPanel = new ButtonsPanel();

        // create main panel that has 2 spinners
        spinnerPanel = new JPanel();

        // create state for windows
        stateFirstWindow = new StateNewWindow();
        stateSecondWindow = new StateNewWindow();

        // set specific layout for the spinnerPanel
        spinnerPanel.setLayout(new GridLayout(2,2, 200,10));

        // create left & right spinners
        startSpinner = new SpinnerPanel();
        endSpinner = new SpinnerPanel();

        // create title for each spinner
        subtitleStart = new JLabel("<html> <h4> <u> Select the starting date of the accident : </u> </h4> </html>");
        subtitleStart.setHorizontalAlignment(SwingConstants.CENTER);

        subtitleEnd = new JLabel("<html> <h4> <u> Select the ending date of the accident : </u> </h4> </html>");
        subtitleEnd.setHorizontalAlignment(SwingConstants.CENTER);

        // add spinners to spinner panel
        spinnerPanel.add(subtitleStart);
        spinnerPanel.add(subtitleEnd);
        spinnerPanel.add(startSpinner);
        spinnerPanel.add(endSpinner);

        // add both panels
        gc.gridy = 1;
        this.add(spinnerPanel, gc);
        gc.gridy = 2;
        this.add(buttonsPanel,gc);

        // state the state of the current window as the first one
        currentState = stateFirstWindow;

    }
    private class SpinnerPanel extends JPanel{
        private JSpinner day, month, year;
        private JLabel dayLabel, monthLabel, yearLabel;
        public SpinnerPanel() {

            this.setLayout(new GridLayout(3,2, 10, 10));

            day = new JSpinner(new SpinnerNumberModel(1,1,31,1));
            month = new JSpinner(new SpinnerListModel(months));
            month.setValue(months[11]);
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
        private JButton back, ok;
        public ButtonsPanel(){

            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonListener());

            ok = new JButton("<html> <u>O</u>k <html>");
            ok.addActionListener(new ButtonListener());

            this.add(back);
            this.add(ok);
        }
        private class ButtonListener implements ActionListener{
            private ButtonsPanel buttonPanelTable;
            @Override
            public void actionPerformed(ActionEvent e) {
                AccidentsPanel.this.removeAll();
                if(e.getSource() == back ){
                    if(currentState.equals(stateFirstWindow)){
                        currentState.backWindow(AccidentsPanel.this, new WelcomeJPanel());
                    } else {
                        currentState.backWindow(AccidentsPanel.this, new RankingPanel());
                    }
                }
                if(e.getSource() == ok ){
                    if(currentState.equals(stateFirstWindow)){
                        currentState.nextWindow(AccidentsPanel.this, gc, new AccidentsJTable());
                        currentState = stateSecondWindow;
                    } else {
                        currentState.nextWindow(AccidentsPanel.this, gc, new RankingPanel());
                    }
                }
                AccidentsPanel.this.repaint();
                AccidentsPanel.this.validate();
            }
        }
    }
    private class AccidentsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public AccidentsJTable (){
            this.setLayout(new GridBagLayout());

            title = new JLabel("Drivers list");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));


                 String[] headColumns = new String[]{"Date", "Name", "Address", "Locality", "Team"};

                 Object[][] data = new Object[][] {
                    {"01/02/2003", "Thomas", "Address 1", "Paris", 1},
                    {"02/02/2003", "Jean", "Address 2", "Marseille", 2},
                    {"03/02/2003", "Yvon", "Address 3", "Lyon", 3},
                    {"04/02/2003", "Yohan", "Address 4","Nice", 4},
                    {"05/02/2003", "Merlin","Address 5","Dublin", 5}
            };
            jTable = new JTable(data, headColumns);

            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));
            jTable.setFillsViewportHeight(true);


            this.add(sp, gc);
            gc.gridy = 1;
            this.add(title, gc);
        }

    }
}
