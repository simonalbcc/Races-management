//region package & imports
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
//endregion
public class ShowAccidents extends JPanel {
    private JLabel subtitle;
    private ButtonsPanel buttonsPanel;
    private SpinnerPanel spinnerPanel;

    // mettre en constante ?
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
//region methods
    public void setSubtitle(String subtitle){
        this.subtitle.setText(subtitle);
    }
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }
//endregion
//region inner classes
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

            back = new JButton("<html> <u>B</u>ack <html>");
            back.addActionListener(new ButtonListener());

            next = new JButton("<html> <u>N</u>ext </html>");
            next.addActionListener(new ButtonListener());

            ok = new JButton("<html> <u>O</u>k <html>");
            ok.addActionListener(new ButtonListener());

            this.add(back);
            this.add(next);

        }
        private class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                ShowAccidents.this.removeAll();
                if(e.getSource() == next){
                    ShowAccidents showAccidents = new ShowAccidents();
                    showAccidents.setSubtitle("<html> <h3> Select the ending date of the accident : </h3> </html>");
                    showAccidents.buttonsPanel.next.setVisible(false);
                    showAccidents.buttonsPanel.add(ok);

                    ShowAccidents.this.add(showAccidents);
                }
                if(e.getSource() == back){
                    ShowAccidents.this.add(new ShowAccidents());
                }
                if(e.getSource() == ok){
                    ShowAccidents.this.add(new AccidentsJTable());
                }
            repaint();
            validate();
            }
        }
    }
    private class AccidentsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public AccidentsJTable (){
            // init layout
            this.setLayout(new BorderLayout());

            // init title
            title = new JLabel("List drivers");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));

            // init of the table (numRows will change)

                // init of headers
                 String[] headColumns = new String[]{"Date", "Name", "Address", "Locality", "Team"};

                 // init fictive data
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


            this.add(title, BorderLayout.PAGE_START);
            this.add(sp, BorderLayout.CENTER);
        }

    }
//endregion
}
