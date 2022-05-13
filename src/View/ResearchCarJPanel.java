//region packages and imports
package View;

import Controller.Controller;
import Utility.AddUtils;
import Utility.FinaleJPanel;
import Utility.SponsorModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//endregion

public class ResearchCarJPanel extends JPanel {
    //region private attributes & constructor
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private CircuitsPanel circuitsPanel;
    private GridBagConstraints gc;
    private Controller controller;
    private JLabel circuitsLabel;
    private JComboBox circuitsCombobox;
    private int iNumPanel;
    private JPanel currentPanel;

    public ResearchCarJPanel(Container mainContainer) {
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
    //endregion

    //region inner classes
    private class CircuitsPanel extends JPanel {
        public CircuitsPanel()  {
            circuitsLabel = new JLabel("Choisissez un circuit");
            try {
                circuitsCombobox = new JComboBox(controller.getAllCircuitsNames().toArray());
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); // peut être fait là car dans la view.
            }
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
            if(e.getSource() == buttonsPanel.getBack()){
                if(iNumPanel == 1){
                    currentPanel = new ResearchCarJPanel(mainContainer);
                }else{
                    currentPanel = new WelcomeJPanel();
                }
                iNumPanel--;
            }

            if(e.getSource() == buttonsPanel.getNext()){
                if(iNumPanel == 0){
                    currentPanel = new CarsJTable();
                }else{
                    int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.", "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                    if(result == 0){
                        currentPanel = new FinaleJPanel(mainContainer, new ResearchCarJPanel(mainContainer));
                    }
                 }
                iNumPanel++;
            }
            AddUtils.addToMainContainer(mainContainer, currentPanel);
        }
    }
    //endregion
}
