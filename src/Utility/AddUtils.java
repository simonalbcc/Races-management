package Utility;

import javax.swing.*;
import java.awt.*;

public class AddUtils {
    public static void addToMainContainer(Container mainContainer, JPanel panel){
        if(panel != null){
            mainContainer.removeAll();
            mainContainer.add(panel);
            mainContainer.repaint();
            mainContainer.validate();
        } else {
            JOptionPane.showMessageDialog(null, "Problème avec un panel !", "Erreur", JOptionPane.ERROR_MESSAGE); // à enlever?
        }
    }



}
