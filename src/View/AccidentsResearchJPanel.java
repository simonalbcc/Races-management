//region packages & imports
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class AccidentsResearchJPanel extends JPanel{
    private GridBagConstraints gc;
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private int iNumPanel;

    public AccidentsResearchJPanel(Container mainContainer) {
        // init container & buttonsPanel
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel("Back", "Next");
        buttonsPanel.addActionListener(new ButtonListener());

        // create layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);

        // set num window
        iNumPanel = 0;

        // create & add main panel and button panel to the main panel
        gc.gridy = 1;
        this.add( new MainSpinnerPanel(), gc);
        gc.gridy = 2;
        this.add(buttonsPanel,gc);

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
            subtitleStart = new JLabel("<html> <h4> <u> Choisissez une date de début : </u> </h4> </html>");
            subtitleStart.setHorizontalAlignment(SwingConstants.CENTER);

            subtitleEnd = new JLabel("<html> <h4> <u> Choisissez une date de fin : </u> </h4> </html>");
            subtitleEnd.setHorizontalAlignment(SwingConstants.CENTER);

            // add spinners to the main spinner panel
            this.add(subtitleStart);
            this.add(subtitleEnd);
            this.add(start);
            this.add(end);
        }
    }
    private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                if(e.getSource() == buttonsPanel.getBack()){
                    if(iNumPanel == 0){
                        mainContainer.add(new WelcomeJPanel());
                    } else {
                        mainContainer.add(new AccidentsResearchJPanel(mainContainer));
                    }
                    iNumPanel--;
                }
                if(e.getSource() == buttonsPanel.getNext()){
                    if(iNumPanel == 1){
                        mainContainer.add(new FinaleResearchJPanel(mainContainer, new AccidentsResearchJPanel(mainContainer)));
                    } else {
                        mainContainer.add(new AccidentsJTable());
                    }
                    iNumPanel++;
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }
    private class AccidentsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public AccidentsJTable (){
            this.setLayout(new GridBagLayout());


            title = new JLabel("Liste de pilotes");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));


                 String[] headColumns = new String[]{"Date", "Nom", "Adresse", "Localité", "Equipe"};

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

            gc.gridy = 1;
            this.add(title, gc);
            gc.gridy = 2;
            this.add(sp, gc);
            gc.gridy = 3;
            this.add(buttonsPanel, gc);
        }
    }

}
