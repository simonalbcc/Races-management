//region packages and imports
package View;

import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//endregion

public class ResearchCarJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private CircuitsPanel circuitsPanel;
    private GridBagConstraints gc;
    private Controller controller;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox;
    private int iNumPanel;

    public ResearchCarJPanel(Container mainContainer) throws Exception {
            this.setLayout(new GridBagLayout());
            this.gc = new GridBagConstraints();
            this.mainContainer = mainContainer;
            this.iNumPanel = 0;
            this.controller = new Controller();

            this.buttonsPanel = new ButtonsPanel("Précédent", "Suivant");
            this.buttonsPanel.addActionListener(new ButtonListener());
            this.circuitsPanel = new CircuitsPanel();

            gc.gridy = 1;
            this.add(circuitsPanel, gc);
            gc.gridy = 2;
            this.add(buttonsPanel, gc);

    }

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

        public CarsJTable() {
            this.setLayout(new GridBagLayout());
            this.gc = new GridBagConstraints();

            title = new JLabel("Liste de voitures et de leurs sponsors");
            title.setFont(new Font("Arial", Font.TRUETYPE_FONT, 20));

            jTable = new JTable(new SponsorModel(controller.getWinningSponsorsOfACircuit(circuitsCombobox.getSelectedItem().toString())));

            jTable.getColumnModel( ).getColumn(0).setPreferredWidth(40);
            for(int iCell = 1; iCell < jTable.getColumnCount()-1; iCell++){
                jTable.getColumnModel( ).getColumn(iCell).setPreferredWidth(132);
            }
            jTable.getColumnModel( ).getColumn(jTable.getColumnCount()-1).setPreferredWidth(101);

            jTable.setRowHeight(40);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for(int cell=0; cell < jTable.getColumnCount();cell++){
                if(cell != 6){
                    jTable.getColumnModel().getColumn(cell).setCellRenderer(centerRenderer);
                }
            }

            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(900, 250));
            jTable.setFillsViewportHeight(true);

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

            mainContainer.removeAll();

            if(e.getSource() == buttonsPanel.getBack()){
                if(iNumPanel == 1){
                    try {
                        mainContainer.add(new CircuitsPanel()); ////// A CHANGERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    iNumPanel--;
                }else{
                    mainContainer.add(new WelcomeJPanel());
                }
            }

            if(e.getSource() == buttonsPanel.getNext()){
                if(iNumPanel == 0){
                    mainContainer.add(new CarsJTable());
                    iNumPanel++;
                }else{
                    mainContainer.add(new WelcomeJPanel());
                }
            }

            mainContainer.repaint();
            mainContainer.validate();
        }
    }
}
