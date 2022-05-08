package View;

import javax.swing.*;
import java.awt.*;

public class DriverJTable extends JTable {
    private JTable jTable;
    private JLabel title;
    private GridBagConstraints gcTable;
    public DriverJTable(){
        // init layout
        this.setLayout(new GridBagLayout());
        gcTable = new GridBagConstraints();

        // init title
        title = new JLabel("Liste des pilotes");
        title.setFont(new Font("Arial",Font.TRUETYPE_FONT,15));

        jTable = new JTable(new AllDriverModel());

        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(300, 250));
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.add(title,gcTable);
        gcTable.gridy = 1;
        this.add(sp, gcTable);
    }


}
