//region packages & imports
package View;

import Controller.Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

//endregion

public class ResearchAccidentsJPanel extends JPanel{
    private GridBagConstraints gc;
    private Container mainContainer;
    private ButtonsPanel buttonsPanel;
    private int iNumPanel;
    private JLabel subtitleStart, subtitleEnd;
    private JSpinner startSpinner, endSpinner;
    private StringBuilder errorDate;

    public ResearchAccidentsJPanel(Container mainContainer) {
        // init container & buttonsPanel
        this.mainContainer = mainContainer;
        buttonsPanel = new ButtonsPanel();
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
    public boolean dateIsCorrect(){
        boolean correct = false;
        GregorianCalendar start, end,current;

        start = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(startSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(startSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(startSpinner.getValue())));
        end = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(endSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(endSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(endSpinner.getValue())));
        current = new GregorianCalendar();

        errorDate = new StringBuilder("Erreur : ");

        if(start.getTime().compareTo(current.getTime()) > 0){
            errorDate.append(" la date de début est après la date de ce jour");
        } else if(end.compareTo(current) > 0){
            errorDate.append(" la date de fin est après la date de ce jour");
        } else if(start.getTime().compareTo(end.getTime()) > 0){
            errorDate.append(" la date de début se situe après celle de fin");
        } else {
            correct = true;
        }
        return correct;
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
            subtitleStart.setFont(new Font("SansSerif",Font.TRUETYPE_FONT,15));
            subtitleStart.setHorizontalAlignment(SwingConstants.CENTER);

            subtitleEnd = new JLabel("Choisissez une date de fin : ");
            subtitleEnd.setHorizontalAlignment(SwingConstants.CENTER);
            subtitleEnd.setFont(new Font("SansSerif",Font.TRUETYPE_FONT,15));

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
                    mainContainer.removeAll();
                    if(iNumPanel == 0){
                        mainContainer.add(new WelcomeJPanel());
                    } else {
                        mainContainer.add(new ResearchAccidentsJPanel(mainContainer));
                    }
                    iNumPanel--;
                }
                if(e.getSource() == buttonsPanel.getNext()){
                    if(iNumPanel == 1){
                        mainContainer.removeAll();
                        mainContainer.add(new FinaleJPanel(mainContainer, new ResearchAccidentsJPanel(mainContainer)));
                    } else {
                        if(dateIsCorrect()){
                            mainContainer.removeAll();
                            try {
                                mainContainer.add(new AccidentsJTable());
                            } catch (Exception exception) {
                                JOptionPane.showMessageDialog(null, exception.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            iNumPanel++;
                        } else {
                            JOptionPane.showMessageDialog(null, errorDate.toString());
                        }
                    }
                }
                mainContainer.repaint();
                mainContainer.validate();
            }
        }
    private class AccidentsJTable extends JPanel{
        private JTable jTable;
        private JLabel title;
        public AccidentsJTable () throws Exception {
            this.setLayout(new GridBagLayout());

            title = new JLabel("Liste des accidents");
            title.setFont(new Font("Arial",Font.TRUETYPE_FONT,20));
            System.out.println(startSpinner.getValue().toString());
            jTable = new JTable(new AccidentModel(new Controller().getAccidentedDrivers((Date)startSpinner.getValue(),(Date)endSpinner.getValue())));

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
            jTable.getTableHeader().setReorderingAllowed(false);
            sp.setPreferredSize(new Dimension(900,250));

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
