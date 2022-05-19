package Utility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utils {
    static CheckerFactory checkerFactory;

    public Utils() {
        this.checkerFactory =new CheckerFactory();
    }

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

    public static boolean isCorrect(ArrayList<JTextField> textFields, StringBuilder errorInputMessage){
        String regex;
        for (JTextField textField : textFields) {
            if(textField.getName().equals("numéro de téléphone") && !textField.equals("")){
                errorInputMessage.append("- Le champs '"+ textField.getName() +" est invalide ("+ (textField.getText().length() > checkerFactory.chooseSize(textField.getName()) ? "trop long" : checkerFactory.selectErrorReason(textField.getName())) +")\n");
           } else if(textField.getText().equals("")){
                errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être rempli \n");
            } else {
                regex = checkerFactory.createRegex(textField.getName());
                if(textField.getText().matches(regex)){
                    errorInputMessage.append("- Le champs '"+ textField.getName() +" est invalide ("+ (textField.getText().length() > checkerFactory.chooseSize(textField.getName()) ? "trop long" : checkerFactory.selectErrorReason(textField.getName())) +")\n");
                 }
            }
        }

    }

    public static void cleanWrongTextField(ArrayList<JTextField> textFields){
        for (JTextField textField: textFields) {
            textField.setText("");
        }
    }
    private class CheckerFactory{
        public CheckerFactory(){}
        String createRegex(String textFieldName){
            switch (textFieldName){
                case "numéro de téléphone":
                    return "\\d{4}\\.?\\/?(\\d+\\.?){3}";
                case "code postal":
                    return "\\d{4,5}";
                case "nom de rue":
                    return "(\\s?[À-ÖØà-ÿ-a-zA-Z-]+\\s?){1,25}";
                case "numéro maison":
                    return "\\d{1,3}";
                case "nom":
                case "prénom":
                    return "[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,6}\\s[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,9}|[A-ZÀ-ÖØà-ÿa-z][à-ÿa-z]{1,14}";
                case "ville":
                    return "[À-ÖØà-ÿ-a-zA-Z-]{4,20}";
                default:return "";
            }
        }
        Integer chooseSize(String textFieldName){
            switch (textFieldName){
                case "numéro de téléphone":
                    return 10;
                case "code postal":
                    return 5;
                case "nom de rue":
                    return 25;
                case "numéro maison":
                    return 3;
                case "nom":
                case "prénom":
                    return 15;
                case "ville":
                    return 20;
                default:return 0;
            }
        }
        String selectErrorReason(String textFieldName){
            switch (textFieldName){
                case "numéro de téléphone":
                case "code postal":
                case "numéro maison":
                    return "contient des lettres ou caractères invalides";
                case "nom de rue":
                case "nom":
                case "prénom":
                case "ville":
                    return "contient des chiffres ou caractères invalides";
                default:return "";
            }
        }
    }
}
