package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResearchRankingJPanel extends JPanel {
    private Container mainContainer;
    private JPanel[] panels;
    private JPanel currentPanel;
    private int iPosition;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox, datesCombobox;
    private String circuitName;
    private JLabel datesLabel;


    public ResearchRankingJPanel(Container mainContainer){
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
    public void setCurrentCircuit(String circuitName){
        this.circuitName = circuitName;
    }
    public void updateWindow(){
        this.removeAll();
        gc.gridy = 1;
        this.add(currentPanel, gc);
        if(iPosition > 0 && iPosition < 4){
            gc.gridy = 2;
            this.add(buttonsPanel, gc);
        }
    }
    public void nextPanel(){
        iPosition++;
        if(iPosition < panels.length){
            currentPanel = panels[iPosition];
        } else {
            currentPanel = new FinaleJPanel(mainContainer, new ResearchRankingJPanel(mainContainer));
        }
    }
    public void previousPanel(){
        iPosition--;
        if(iPosition == 0){
            mainContainer.add(new WelcomeJPanel());
        } else {
            currentPanel = panels[iPosition];
        }
    }

    private class CircuitsPanel extends JPanel{
            public CircuitsPanel(){
                circuitsLabel = new JLabel("Choisissez un circuit");
                circuitsCombobox = new JComboBox(new Controller().getAllCircuitsNames().toArray());
                circuitsCombobox.setPreferredSize(new Dimension(100,30));

                this.add(circuitsLabel);
                this.add(circuitsCombobox);
            }
    }
    private class DatePanel extends JPanel{
        public DatePanel(){
            datesLabel = new JLabel("Choisissez une date : ");
            setCurrentCircuit(circuitsCombobox.getSelectedItem().toString());
            datesCombobox = new JComboBox(new Controller().getRaceDatesOfACircuit(circuitName).toArray());
            datesCombobox.setPreferredSize(new Dimension(100,30));
            this.add(datesLabel);
            this.add(datesCombobox);
        }
    }
    private class RankingTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        private GridBagConstraints gcTable;
        private Controller controller;
        public RankingTable (){
            // init layout
            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();

            // init title
            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

            this.controller = new Controller();
            jTable = new JTable(new RankingModel(controller.getARaceRanking(circuitName,datesCombobox.getSelectedItem().toString())));

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
                setCurrentCircuit(circuitsCombobox.getSelectedItem().toString());
                panels[2] = new DatePanel();
                panels[3] = new RankingTable();
                nextPanel();
            }

            updateWindow();
            if(iPosition > 0){
                mainContainer.add(ResearchRankingJPanel.this);
            }
            mainContainer.repaint();
            mainContainer.validate();
        }
    }
}