package View;

import Business.DriverManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class DriverJTable extends JTable {
    private JTable jTable;
    private JLabel title;
    private GridBagConstraints gcTable;
    private DriverManager driverManager;

    public DriverJTable(){
        // init layout & size
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(1000, 500));

        // init private attributes
        gcTable = new GridBagConstraints();
        driverManager = new DriverManager();

        title = new JLabel("Liste des pilotes");
        title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

        jTable = new JTable(new AllDriverModel(driverManager.getAllDrivers()));


        // resize & center columns
        jTable.setPreferredSize(new Dimension(1000,500));
        jTable.getColumnModel( ).getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel( ).getColumn(1).setPreferredWidth(150);
        jTable.getColumnModel( ).getColumn(2).setPreferredWidth(100);
        jTable.getColumnModel( ).getColumn(3).setPreferredWidth(180);
        jTable.setRowHeight(20);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        jTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        // add scroll pane
        JScrollPane sp = new JScrollPane(jTable);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // add components with constraints
        gcTable.weightx = 1;


        this.add(title,gcTable);
        gcTable.gridy = 1;
        gcTable.fill = GridBagConstraints.HORIZONTAL;
        this.add(sp, gcTable);
    }


}
