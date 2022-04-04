package Race;

import javax.swing.*;
import java.awt.*;

public class ShowPilotsWindow extends JFrame {
    private JPanel mainPanel;
    private JLabel welcome;
    private JList drivers;
    private JButton next;
    public ShowPilotsWindow() {
        // initialisation de la fenêtre
        this.setBounds(100,100,500,500);
        this.setTitle("Afficher les pilotes");
        this.getContentPane();
        this.setLayout(new BorderLayout());


        // initialisation du bouton "suivant"
        next = new JButton("<html> <center> <u>S</u>uivant </center>");


        // ajout du panel à la window
        this.add(new DriversList());

        this.setVisible(true);
    }


}
