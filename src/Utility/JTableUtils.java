package Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class JTableUtils {
    public JScrollPane centerTableData(JTable jTable){

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
