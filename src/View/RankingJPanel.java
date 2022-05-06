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
    private GridBagConstraints gc;

    public RankingJPanel(Container mainContainer){
        // init container, buttonPanel
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel("Précédent", "Suivant");
        buttonsPanel.addActionListener(new ButtonListener());

        // set layout & constraints
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        // set black borders & size
        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));

        //init panels & current panel
        this.panels = new JPanel[]{new WelcomeJPanel(), new CircuitsPanel(), new DatePanel(), new RankingTable()};
        iPosition = 1;
        currentPanel = panels[iPosition];

        updateWindow();
    }

    void updateWindow(){
        this.removeAll();
        gc.gridy = 1;
        this.add(currentPanel, gc);
        if(iPosition > 0 && iPosition < 4){
            gc.gridy = 2;
            this.add(buttonsPanel, gc);
        }
    }

    void nextPanel(){
        iPosition++;
        if(iPosition < panels.length){
            currentPanel = panels[iPosition];
        } else {
            currentPanel = new FinaleResearchJPanel(mainContainer, new RankingJPanel(mainContainer));
        }
    }
    void previousPanel(){
        iPosition--;
        if(iPosition == 0){
            mainContainer.add(new WelcomeJPanel());
        } else {
            currentPanel = panels[iPosition];
        }
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
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            mainContainer.removeAll();

            if(e.getSource() == buttonsPanel.getBack()){
                previousPanel();
            }

            if(e.getSource() == buttonsPanel.getNext()){
                nextPanel();
            }

            updateWindow();
            if(iPosition > 0){
                mainContainer.add(RankingJPanel.this);
            }
            mainContainer.repaint();
            mainContainer.validate();
        }
    }



}