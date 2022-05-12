package View;


import Controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DriverJTable extends JPanel {
    private JTable jTable;
    private JLabel title;
    private Controller controller;
    private GridBagConstraints gc;
    private ImageIcon imageIcon;
    private JScrollPane sp;

    public DriverJTable(){
        controller = new Controller();
        this.setLayout(new GridBagLayout());

        jTable = new JTable(new AllDriverModel(controller.getAllDrivers()));
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        title = new JLabel(" Liste des pilotes");
        title.setFont(new Font("Serif",Font.TRUETYPE_FONT,30));
        imageIcon = new ImageIcon("driver.png");
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

        this.sp = new JScrollPane(jTable);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.add(title, gc);
        gc.gridy = 1;
        this.add(sp, gc);
    }

    public JTable getjTable() {
        return jTable;
    }
}
