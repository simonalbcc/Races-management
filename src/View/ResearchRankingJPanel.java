//region packages & imports
package View;

import Controller.Controller;
import View.Utility.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
//endregion

public class ResearchRankingJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsJPanel buttonsPanel;
    private GridBagConstraints gc;
    private JLabel datesLabel;
    private JPanel currentPanel;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox, datesCombobox;
    private Integer iPanel;
    private Controller controller;

    public ResearchRankingJPanel(Container mainContainer) throws Exception {
        // init main container, buttons panel, controller & lauout
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsJPanel("Précédent", "Suivant");
        buttonsPanel.addActionListener(new ButtonListener());

        controller = new Controller();

        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();

        //add all to the main panel of the research
        iPanel = 1;
        this.add(new CircuitsPanel(), gc);
        gc.gridy = 1;
        this.add(buttonsPanel, gc);
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
        try{
            if(iPanel == 0){
                Utils.addToMainContainer(mainContainer, new WelcomeJPanel());
            }else{
                switch (iPanel){
                    case 1 :
                        currentPanel = new CircuitsPanel();
                        break;
                    case 2 :
                        currentPanel = new DatePanel();
                        break;
                    case 3 :
                        currentPanel = new RankingTable();
                        break;
                    case 4 :
                        currentPanel = new FinalePanel(mainContainer, new ResearchRankingJPanel(mainContainer));
                        break;
                }
                updatePanel();
                Utils.addToMainContainer(mainContainer, ResearchRankingJPanel.this);
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    //region inner classes
    private class CircuitsPanel extends JPanel{
            public CircuitsPanel() throws Exception {
                circuitsLabel = new JLabel("Choisissez un circuit");
                circuitsCombobox = new JComboBox(controller.getAllCircuitsNames().toArray());
                circuitsCombobox.setPreferredSize(new Dimension(100,30));

                this.add(circuitsLabel);
                this.add(circuitsCombobox);
            }
    }
    private class DatePanel extends JPanel{
        public DatePanel() throws Exception {
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
        public RankingTable () throws Exception {

            this.setLayout(new GridBagLayout());
            gcTable = new GridBagConstraints();


            title = new JLabel("Classement");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

            jTable = new JTable(new RankingModel(controller.getARaceRanking(circuitsCombobox.getSelectedItem().toString(), (Date)datesCombobox.getSelectedItem())));

            JScrollPane sp = Utils.centerTableData(jTable);

            this.add(title,gcTable);
            gcTable.gridy = 1;
            this.add(sp, gcTable);
        }

    }
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getBack()){
                iPanel--;
            }

            if(e.getSource() == buttonsPanel.getNext()){
                iPanel++;
            }
            setCurrentPanel();
        }
    }
    //endregion


}