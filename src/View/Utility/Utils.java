package View.Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Utils {

    public Utils() {}

    public static void addToMainContainer(Container mainContainer, JPanel panel){
        if(panel != null){
            mainContainer.removeAll();
            mainContainer.add(panel);
            mainContainer.repaint();
            mainContainer.validate();
        }
    }
    public static void cleanTextField(ArrayList<JTextField> textFields){
        for (JTextField textField: textFields) {
            textField.setText("");
        }
    }

    public static void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    public static void showInformationMessage(String message){
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean dateIsCorrect(JSpinner startSpinner, JSpinner endSpinner) {
        boolean correct = false;
        StringBuilder errorDate = new StringBuilder("Erreur : ");
        GregorianCalendar start, end, current;
        start = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(startSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(startSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(startSpinner.getValue())));
        end = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(endSpinner.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(endSpinner.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(endSpinner.getValue())));
        current = new GregorianCalendar();

        if(start.getTime().compareTo(current.getTime()) > 0){
            errorDate.append(" la date de début est après la date de ce jour");
        } else if(end.compareTo(current) > 0){
            errorDate.append(" la date de fin est après la date de ce jour");
        } else if(start.getTime().compareTo(end.getTime()) > 0){
            errorDate.append(" la date de début se situe après celle de fin");
        } else {
            correct = true;
        }
        if(!correct){
            JOptionPane.showMessageDialog(null, errorDate.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return correct;
    }
    public static JScrollPane centerTableData(JTable jTable){

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

        jTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(900,250));

        return sp;
    }


}
