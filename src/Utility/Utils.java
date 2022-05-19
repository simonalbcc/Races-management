package Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    public static void cleanWrongTextField(ArrayList<JTextField> textFields){
        for (JTextField textField: textFields) {
            textField.setText("");
        }
    }
}
