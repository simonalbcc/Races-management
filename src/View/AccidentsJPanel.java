//region packages & imports
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class AccidentsJPanel extends JPanel{
    private GridBagConstraints gc;
    private Container mainContainer;
    private int numWindow;
    private static final String[] months = {"December", "November", "October", "September", "Augustus", "July", "June", "May", "April", "March", "Februari", "Januari"};

    public AccidentsJPanel(Container mainContainer) {
        // init container
        this.mainContainer = mainContainer;

        // init number of the window/page
        numWindow = 0;

        // create layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);

        // create & add main panel and button panel to the main panel
        gc.gridy = 1;
        this.add( new MainSpinnerPanel(), gc);
        gc.gridy = 2;
        this.add(new ButtonsPanel(),gc);

    }
    private class MainSpinnerPanel extends JPanel{
        private JLabel subtitleStart, subtitleEnd, startLabel, endLabel;
        private DatesJSpinner start, end;
        public MainSpinnerPanel(){
            // set specific layout for the 2 spinnerPanels
            this.setLayout(new GridLayout(2,2, 150,10));

            // set start & end spinnerpanel
            start = new DatesJSpinner();
            end = new DatesJSpinner();

            // create title for each spinnerPanel
            subtitleStart = new JLabel("<html> <h4> <u> Select the starting date of the accident : </u> </h4> </html>");
            subtitleStart.setHorizontalAlignment(SwingConstants.CENTER);

            subtitleEnd = new JLabel("<html> <h4> <u> Select the ending date of the accident : </u> </h4> </html>");
            subtitleEnd.setHorizontalAlignment(SwingConstants.CENTER);

            // create subtitles


            // add spinners to the main spinner panel
            this.add(subtitleStart);
            this.add(subtitleEnd);
            this.add(start);
            this.add(end);
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
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                if(e.getSource() == back){
                    switch(numWindow){
                        case 0:

                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                    }
                    numWindow++;
                }
                if(e.getSource() == ok){
                    switch(numWindow){
                        case 0:

                            break;
                        case 1:

                            break;
                        case 2:

                            break;
                    }
                    numWindow--;
                }
                mainContainer.repaint();
                mainContainer.validate();
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
