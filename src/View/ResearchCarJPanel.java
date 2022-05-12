//region packages and imports
package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//endregion

public class ResearchCarJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private CircuitsPanel circuitsPanel;
    private GridBagConstraints gc;
    private int iNumPanel;

    public ResearchCarJPanel(Container mainContainer){
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();
        this.mainContainer = mainContainer;
        this.iNumPanel = 0;

        this.buttonsPanel = new ButtonsPanel("Précédent","Suivant");
        this.buttonsPanel.addActionListener(new ButtonListener());
        this.circuitsPanel = new CircuitsPanel();

        gc.gridy = 1;
        this.add(circuitsPanel,gc);
        gc.gridy = 2;
        this.add(buttonsPanel,gc);
    }

    private class CircuitsPanel extends JPanel {
        private JLabel circuitsLabel;
        private JComboBox circuitsCombobox;

        public CircuitsPanel() {
            circuitsLabel = new JLabel("Choisissez un circuit");
            circuitsCombobox = new JComboBox(new String[]{"test circuit ", "test circuit"});
            circuitsCombobox.setPreferredSize(new Dimension(100, 30));
            this.add(circuitsLabel);
            this.add(circuitsCombobox);

        }
    }

    private class CarsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        private GridBagConstraints gc;

        public CarsJTable (){
            this.setLayout(new GridBagLayout());
            this.gc = new GridBagConstraints();

            title = new JLabel("Liste de voitures et de leurs sponsors");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));


            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));
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
                if(iNumPanel == 0){
                    mainContainer.add(new WelcomeJPanel());
                } else {
                    mainContainer.add(new ResearchCarJPanel(mainContainer));
                }
                iNumPanel--;
            }
            if(e.getSource() == buttonsPanel.getNext()){
                if(iNumPanel == 1){
                    mainContainer.add(new FinaleJPanel(mainContainer, new ResearchCarJPanel(mainContainer)));
                } else {
                    mainContainer.add(new CarsJTable());
                }
                iNumPanel++;
            }
            mainContainer.repaint();
            mainContainer.validate();
        }
    }

}
