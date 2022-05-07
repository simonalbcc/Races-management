package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarsResearchJPanel extends JPanel {
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private GridBagConstraints gc;
    private int iNumPanel;

    public CarsResearchJPanel(Container mainContainer){
        this.mainContainer = mainContainer;
        this.buttonsPanel = new ButtonsPanel("Précédent","Suivant");
        this.buttonsPanel.addActionListener(new ButtonListener());
        this.gc = new GridBagConstraints();
        this.iNumPanel = 0;

        this.add(buttonsPanel);
    }

    private class carsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public carsJTable (){
            this.setLayout(new GridBagLayout());

            title = new JLabel("Liste de voitures et de leurs sponsors");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));

            String[] headColumns = new String[]{"Modele", "Sponsor"};
            Object[][] data = new Object[][] {
                    {"Simon","L eau chaude"},
                    {"test","toste"}
            };

            jTable = new JTable(data, headColumns);
            JScrollPane sp = new JScrollPane(jTable);
            sp.setPreferredSize(new Dimension(300, 250));
            jTable.setFillsViewportHeight(true);

            this.add(title, gc);
            gc.gridy = 1;
            this.add(sp, gc);
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
                    mainContainer.add(new CarsResearchJPanel(mainContainer));
                }
                iNumPanel--;
            }
            if(e.getSource() == buttonsPanel.getNext()){
                if(iNumPanel == 1){
                    mainContainer.add(new FinaleJPanel(mainContainer, new CarsResearchJPanel(mainContainer)));
                } else {
                    mainContainer.add(new BasicPanel(new CarsResearchJPanel.carsJTable(), buttonsPanel));
                }
                iNumPanel++;
            }
            mainContainer.repaint();
            mainContainer.validate();
        }
    }

}

