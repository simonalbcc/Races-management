package View;

import Business.DriverManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DriverJTable extends JTable {
    private JTable jTable;
    private JLabel title;
    private DriverManager driverManager;
    private GridBagConstraints gc;
    private ImageIcon imageIcon;

    public DriverJTable(){
        driverManager = new DriverManager();
        this.setLayout(new GridBagLayout());

        jTable = new JTable(new AllDriverModel(driverManager.getAllDrivers()));
        gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        title = new JLabel(" Liste des pilotes");
        title.setFont(new Font("Serif",Font.TRUETYPE_FONT,30));
        imageIcon = new ImageIcon("volant.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(75,50, Image.SCALE_SMOOTH));
        title.setIcon(imageIcon);

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
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.add(title, gc);
        gc.gridy = 1;
        this.add(sp, gc);
    }
}