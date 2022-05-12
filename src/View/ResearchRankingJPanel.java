package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ResearchRankingJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private JLabel datesLabel;
    private JPanel currentPanel;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox, datesCombobox;
    private Integer iPanel;
    private Controller controller;

    public ResearchRankingJPanel(Container mainContainer){

        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel("Précédent", "Suivant");
        buttonsPanel.addActionListener(new ButtonListener());

        controller = new Controller();

        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));

        iPanel = 1;

        setCurrentPanel();
        updatePanel();
    }

    public void updatePanel(){
        this.removeAll();
        gc.gridy = 1;
        this.add(currentPanel, gc);
        if(iPanel > 0 && iPanel < 4){
            gc.gridy = 2;
            this.add(buttonsPanel, gc);
        }
    }

    public void setCurrentPanel() {
        if(iPanel == 0){
            currentPanel = new WelcomeJPanel();
        } else if (iPanel == 1){
            currentPanel = new CircuitsPanel();
        } else if (iPanel == 2){
            currentPanel = new DatePanel();
        } else if (iPanel == 3){
            currentPanel = new RankingTable();
        } else if (iPanel == 4){
            currentPanel = new FinaleJPanel(mainContainer, new ResearchRankingJPanel(mainContainer));
        }
    }

    private class CircuitsPanel extends JPanel{
            public CircuitsPanel(){
                circuitsLabel = new JLabel("Choisissez un circuit");
                circuitsCombobox = new JComboBox(controller.getAllCircuitsNames().toArray());
                circuitsCombobox.setPreferredSize(new Dimension(100,30));

                this.add(circuitsLabel);
                this.add(circuitsCombobox);
            }
    }
    private class DatePanel extends JPanel{
        public DatePanel(){
            datesLabel = new JLabel("Choisissez une date : ");
            datesCombobox = new JComboBox(controller.getRaceDatesOfACircuit(circuitsCombobox.getSelectedItem().toString()).toArray());
            datesCombobox.setPreferredSize(new Dimension(100,30));
            this.add(datesLabel);
            this.add(datesCombobox);
        }
    }
    private class RankingTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        private GridBagConstraints gcTable;
        public RankingTable (){

            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();


            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

            jTable = new JTable(new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), datesCombobox.getSelectedItem().toString())));

            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));

            jTable.getTableHeader().setReorderingAllowed(false);
            sp.setPreferredSize(new Dimension(900,250));

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
                iPanel--;
            }

            if(e.getSource() == buttonsPanel.getNext()){
                iPanel++;
            }
            setCurrentPanel();
            updatePanel();

            if(iPanel > 0){
                mainContainer.add(ResearchRankingJPanel.this);
            }
            mainContainer.repaint();
            mainContainer.validate();
        }
    }


}