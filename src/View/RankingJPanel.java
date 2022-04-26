package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingJPanel extends JPanel {
    private Container mainContainer;
    private JPanel[] panels;
    private JPanel currentPanel;
    private int iPosition;
    private ButtonsPanel buttonsPanel;

    public RankingJPanel(Container mainContainer){
        // init container & buttonPanel
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel("Back", "Next");
        buttonsPanel.addActionListener(new ButtonListener());

        // set black borders
        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));

        //init panels & current
        this.panels = new JPanel[]{new WelcomeJPanel(mainContainer), new CircuitsPanel(), new DatePanel(), new RankingTable(), new FinalePanel()};
        iPosition = 1;
        setCurrentPanel();

        //update window for the first one
        updateWindow();

    }

    public void setCurrentPanel(){
        this.currentPanel = panels[iPosition];
    }

    public void updateWindow(){
        mainContainer.removeAll();
        setCurrentPanel();
        if(iPosition == 0 || iPosition == panels.length-1){
            mainContainer.add(currentPanel);
        } else {
            mainContainer.add(new BasicPanel(currentPanel, buttonsPanel));
        }
        mainContainer.repaint();
        mainContainer.validate();
    }
    public void nextWindow(){
        iPosition++;
        if(iPosition > panels.length){
            iPosition = 0;
        }
    }
    public void previousWindow(){
        iPosition--;
    }

    private class CircuitsPanel extends JPanel{
        private JLabel circuitsLabel;
        private JComboBox circuitsCombobox;
            public CircuitsPanel(){
                circuitsLabel = new JLabel("Choisissez un circuit");
                circuitsCombobox = new JComboBox(new String[]{"test circuit ", "test circuit"});
                circuitsCombobox.setPreferredSize(new Dimension(100,30));

                this.add(circuitsLabel);
                this.add(circuitsCombobox);

                this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));
            }
    }
    private class DatePanel extends JPanel{
        private JLabel datesLabel;
        private JComboBox datesCombobox;
        public DatePanel(){
            datesLabel = new JLabel("Choisissez une date : ");
            datesCombobox = new JComboBox(new String[]{"test", "test"});
            datesCombobox.setPreferredSize(new Dimension(100,30));

            this.add(datesLabel);
            this.add(datesCombobox);

            this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));
        }
    }
    private class RankingTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        private GridBagConstraints gcTable;
        public RankingTable (){
            // init layout
            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();

            // init title
            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));


            // init of headers
            String[] headColumns = new String[]{"Position", "Numéro de voiture", "Puissance de la voiture", "Nom du pilote", "Meilleur temps"};

            // init fictive data
            Object[][] data = new Object[][] {
                    {"1", "42", "puissance", "Paris", 1},
                    {"2", "35", "puissance", "Marseille", 2},
                    {"3", "63", "puissance", "Lyon", 3},
                    {"4", "23", "puissance","Nice", 4},
                    {"5", "5","puissance","Dublin", 5}
            };
            jTable = new JTable(data, headColumns);

            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));
            jTable.setFillsViewportHeight(true);

            this.add(title,gcTable);
            gcTable.gridy = 1;
            this.add(sp, gcTable);
        }

    }
    private class FinalePanel extends  JPanel{
        private JLabel title;
        private ButtonsPanel buttonsFinalePanel;
        private GridBagConstraints gcFinalePanel;
        public FinalePanel(){
            // set title
            title = new JLabel("<html> <u> Que voulez-vous faire? </u> </html>");
            title.setHorizontalAlignment(SwingConstants.CENTER);

            // set layout
            this.setLayout(new GridBagLayout());
            gcFinalePanel = new GridBagConstraints();

            // set buttons panel
            buttonsFinalePanel = new ButtonsPanel("Retour au menu principal", "Recommencer une recherche");
            buttonsFinalePanel.addActionListener(new FinaleButtonsListener());

            // add components
            this.add(title, gcFinalePanel);
            gcFinalePanel.gridy = 1;
            this.add(buttonsFinalePanel, gcFinalePanel);

        }private class FinaleButtonsListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainContainer.removeAll();
                if(e.getSource() == buttonsFinalePanel.getBack()){
                    mainContainer.add(new WelcomeJPanel(mainContainer));
                }
                if(e.getSource() == buttonsFinalePanel.getNext()){
                    mainContainer.add(new RankingJPanel(mainContainer));
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }
    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getBack()){
                previousWindow();
            }
            if(e.getSource() == buttonsPanel.getNext()){
                nextWindow();
            }
            updateWindow();
        }
    }
}