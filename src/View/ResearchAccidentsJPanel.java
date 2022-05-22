//region packages & imports
package View;

import Controller.Controller;
import Utility.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
//endregion

public class ResearchAccidentsJPanel extends JPanel {
    private GridBagConstraints gc;
    private Container mainContainer;
    private ButtonsJPanel buttonsPanel;
    private Integer iNumPanel;
    private JLabel subtitleStart, subtitleEnd;
    private JSpinner startSpinner, endSpinner;
    private JPanel currentPanel;


    public ResearchAccidentsJPanel(Container mainContainer) {
        // init container & buttonsPanel
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsJPanel();
        buttonsPanel.addActionListener(new ButtonListener());

        // create layout
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.insets = new Insets(5,5,5,5);

        // set num window
        iNumPanel = 0;

        // create & add main panel and button panel to the main panel
        gc.gridy = 1;
        this.add( new MainSpinnerPanel(), gc);
        gc.gridy = 2;
        this.add(buttonsPanel,gc);

    }
    private class MainSpinnerPanel extends JPanel{
        public MainSpinnerPanel(){
            this.setLayout(new GridLayout(2,2, 20,30));

            startSpinner = new JSpinner(new SpinnerDateModel());
            startSpinner.setEditor(new JSpinner.DateEditor(startSpinner, "dd-MM-yyyy"));
            startSpinner.setPreferredSize(new Dimension(150,50));

            endSpinner = new JSpinner(new SpinnerDateModel());
            endSpinner.setEditor(new JSpinner.DateEditor(endSpinner, "dd-MM-yyyy"));
            endSpinner.setPreferredSize(new Dimension(150,50));
            subtitleStart = new JLabel("Choisissez une date de début : ");
            subtitleStart.setHorizontalAlignment(SwingConstants.CENTER);

            subtitleEnd = new JLabel("Choisissez une date de fin : ");
            subtitleEnd.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(subtitleStart);
            this.add(startSpinner);
            this.add(subtitleEnd);
            this.add(endSpinner);
        }
    }
    private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == buttonsPanel.getBack()){
                    if(iNumPanel == 0){
                        currentPanel = new WelcomeJPanel();
                    } else {
                        currentPanel = new ResearchAccidentsJPanel(mainContainer);
                    }
                    iNumPanel--;
                }
                if(e.getSource() == buttonsPanel.getNext()){
                    if(iNumPanel == 0 && Utils.dateIsCorrect(startSpinner, endSpinner)){
                        try {
                            currentPanel = new AccidentsJTable();
                            iNumPanel++;
                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    } else if (iNumPanel == 1){
                        int result = JOptionPane.showConfirmDialog(null, "Êtes-vous sûrs de vouloir continuer? Les données seront perdues.", "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                        if(result == 0){
                            currentPanel = new FinalePanel(mainContainer, new ResearchAccidentsJPanel(mainContainer));
                        } else {
                            iNumPanel = 0;
                        }
                    }
                }
                Utils.addToMainContainer(mainContainer, currentPanel);
            }
        }
    private class AccidentsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public AccidentsJTable () throws Exception {
            this.setLayout(new GridBagLayout());

            title = new JLabel("Liste des accidents");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
            jTable = new JTable(new AccidentModel(new Controller().getAccidentedDrivers((Date)startSpinner.getValue(),(Date)endSpinner.getValue())));

            JScrollPane sp = Utils.centerTableData(jTable);

            gc.gridy = 1;
            this.add(title, gc);
            gc.gridy = 2;
            this.add(sp, gc);
            gc.gridy = 3;
            this.add(buttonsPanel, gc);
            this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        }
    }

}
