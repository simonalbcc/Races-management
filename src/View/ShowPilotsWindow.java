package View;

import javax.swing.*;
import java.awt.*;

public class ShowPilotsWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel welcome;
    private JList drivers;
    private JButton next;
    public ShowPilotsWindow() {
        //region initialisation window
        this.setBounds(100,100,500,500);
        this.setTitle("Afficher les pilotes");
        this.getContentPane();
        this.setLayout(new BorderLayout());
        //endregion
        //region initialisation button "suivant" et du message d'accueuil
            //next = new JButton("<html> <center> <u>S</u>uivant </center>");
        welcome = new JLabel("Veuillez selectionner un pilote dans la liste ci-dessous : ");
        //endregion
        //region ajout des composants à la window
        this.add(welcome);
        this.add(new DriversListPanel());
        this.add(next);
        //endregion
        this.setVisible(true);
    }


}
