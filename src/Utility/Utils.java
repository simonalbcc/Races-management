package Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class Utils {

    public static ArrayList<String> getCountriesArray(){
        ArrayList<String> continents = new ArrayList<>();
        continents.add("Séléctionner...");
        for(String countryCode : Locale.getISOCountries()){
            continents.add(new Locale("", countryCode).getDisplayCountry(Locale.FRENCH));
        }
        return continents;
    }
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
}
