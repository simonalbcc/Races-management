//region packages and imports
package View;
import Controller.Controller;
import View.Utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class ResearchCarJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsJPanel buttonsPanel;
    private CircuitsPanel circuitsPanel;
    private GridBagConstraints gc;
    private Controller controller;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox;
    private int iNumPanel;
    private JPanel currentPanel;

    public ResearchCarJPanel(Container mainContainer) throws Exception {
            this.setLayout(new GridBagLayout());
            this.gc = new GridBagConstraints();
            this.mainContainer = mainContainer;
            this.iNumPanel = 0;
            this.controller = new Controller();

            this.buttonsPanel = new ButtonsJPanel("Précédent", "Suivant");
            this.buttonsPanel.addActionListener(new ButtonListener());
            this.circuitsPanel = new CircuitsPanel();

            gc.gridy = 1;
            this.add(circuitsPanel, gc);
            gc.gridy = 2;
            this.add(buttonsPanel, gc);

    }

    //region inner classes
    private class CircuitsPanel extends JPanel {
        public CircuitsPanel() throws Exception {
            circuitsLabel = new JLabel("Choisissez un circuit");
            circuitsCombobox = new JComboBox(controller.getAllCircuitsNames().toArray());
            circuitsCombobox.setPreferredSize(new Dimension(100, 30));
            this.add(circuitsLabel);
            this.add(circuitsCombobox);

        }
    }
    private class CarsJTable extends JPanel {
        private JTable jTable;
        private JLabel title;
        private GridBagConstraints gc;

        public CarsJTable() throws Exception {
            this.setLayout(new GridBagLayout());
            this.gc = new GridBagConstraints();

            title = new JLabel("Liste de voitures et de leurs sponsors");
            title.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));

            jTable = new JTable(new SponsorModel(controller.getWinningSponsorsOfACircuit(circuitsCombobox.getSelectedItem().toString())));

            JScrollPane sp = Utils.centerTableData(jTable);
            
            this.add(title, gc);
            gc.gridy = 1;
            this.add(sp, gc);
            gc.gridy = 2;
            this.add(buttonsPanel, gc);

        }
    }
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonsPanel.getBack()){
                if(iNumPanel == 1){
                    try {
                        currentPanel = new ResearchCarJPanel(mainContainer);
                    } catch (Exception exception) {
                       Utils.showErrorMessage(exception.getMessage());
                    }
                }else{
                    currentPanel = new WelcomeJPanel();
                }
                iNumPanel--;
            }

            if(e.getSource() == buttonsPanel.getNext()){
                try {
                    if(iNumPanel == 0){
                        currentPanel = new CarsJTable();
                    }else{
                        int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.", "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                        if(result == 0){
                            currentPanel = new FinalePanel(mainContainer, new ResearchCarJPanel(mainContainer));
                        }
                    }
                } catch (Exception exception) {
                   Utils.showErrorMessage(exception.getMessage());
                }
                iNumPanel++;
            }
            Utils.addToMainContainer(mainContainer, currentPanel);
        }
    }
    //endregion

}
