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


        // init of headers
        String[] headColumns = new String[]{"Numéro de série", "Nom", "Prénom", "Numéro de téléphone", "Adresse", "Nationalité","Âge"};

        // init fictive data
        Object[][] data = new Object[][] {
                {"1", "Albicocco", "Simon", "0457469786", "Andenne, Place des Tilleuls", "Belge", "20"},
                {"2", "De Winter", "Alexis","0498568675", "Ohey", "Russe", "20"}
        };
        jTable = new JTable(data, headColumns);

        JScrollPane sp = new JScrollPane(jTable);
        sp.setPreferredSize(new Dimension(300, 250));
        jTable.setFillsViewportHeight(true);

        this.add(title,gcTable);
        gcTable.gridy = 1;
        this.add(sp, gcTable);
    }


}
