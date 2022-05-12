package Utility;

import Model.Driver;
import Model.Locality;
import Model.Team;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class FormUtils {
    private ArrayList<JTextField> textFields;
    private Integer iTextfield = 1;
    private StringBuilder errorInputMessage;

    public FormUtils(ArrayList<JTextField> textFields, StringBuilder errorInputMessage){
        this.textFields = textFields;
        this.errorInputMessage = errorInputMessage;
    }

    public boolean testRegex(String regex){
        iTextfield++;
        return textFields.get(iTextfield).getText().matches(regex);
    }
    public void resetTextField(){
        textFields.get(iTextfield).setText("");
    }

    public boolean isCorrect(){
        errorInputMessage = new StringBuilder("Action requise : \n");
        boolean filled = true;
        for (JTextField textField : textFields) {
            if(!textField.getName().equals("numéro de téléphone") && textField.getText().equals("")){
                filled = false;
                errorInputMessage.append("- Le champs '"+ textField.getName() +"' doit être remplis \n");
            }
        }
        if(filled){
            if(!testRegex("\\d{1,3}")){
                errorInputMessage.append("- Le numéro du pilote entré est invalide ("+ (textFields.get(0).getText().length() > 3 ? "trop long" : "contient des lettres") +")\n");
                resetTextField();
            }
            if(!textFields.get(1).getText().equals("") && !testRegex("\\d{4}\\.?\\/?(\\d+\\.?){3}")){
                errorInputMessage.append("- Le numéro de téléphone entré est invalide ("+ (textFields.get(1).getText().length() > 10 ? "trop long" : "contient des lettres") +")\n");
                resetTextField();
            }
            if(!testRegex("\\d{4,5}") && !textFields.get(2).getClass().getSimpleName().equals("Integer")){
                errorInputMessage.append("- Le code postal entré est invalide ("+ (textFields.get(2).getText().length() > 5 ? "trop long" : "ne contient pas de chiffres") +")\n");
                resetTextField();
            }
            if(!testRegex("[a-zA-Z-]{2,15}")){
                errorInputMessage.append("- Le nom de famille entré est invalide ("+(textFields.get(3).getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                resetTextField();
            }
            if(!testRegex("[a-zA-Z-]{2,15}")){
                errorInputMessage.append("- Le prénom entré est invalide ("+(textFields.get(4).getText().length() > 15 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                resetTextField();
            }
            if(!testRegex("(\\s?[a-zA-Z-]+\\s?)+") ||  textFields.get(0).getText().length() > 25){
                errorInputMessage.append("- Le nom de la rue entré est invalide ("+(textFields.get(5).getText().length() > 25 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                resetTextField();
            }
            if(!testRegex("\\d{1,3}")){
                errorInputMessage.append("- Le numéro du domicile entré est invalide ("+(textFields.get(6).getText().length() > 3 ? "trop long" : "doit contenir uniquement des chiffres")+")\n");
                resetTextField();
            }
            if(!testRegex("[a-zA-Z-]{4,20}")){
                errorInputMessage.append("- La ville entrée n'est pas valide ("+(textFields.get(7).getText().length() > 20 ? "trop long" : "doit contenir uniquement des lettres")+")\n");
                resetTextField();
            }
        }

        return (textFields.size() < 1 & filled);
    }
    public Driver createDriver(String country, JSpinner date, String origin, Team team, boolean hasRenewedContract){
        GregorianCalendar birthdate = new GregorianCalendar(Integer.parseInt(new SimpleDateFormat("yyyy").format(date.getValue())), Integer.parseInt(new SimpleDateFormat("MM").format(date.getValue()))-1, Integer.parseInt(new SimpleDateFormat("dd").format(date.getValue())));
        Locality locality = new Locality(null, Integer.parseInt(textFields.get(2).getText()), textFields.get(7).getText(), country);

        return new Driver(  Integer.parseInt(textFields.get(0).getText()),
                textFields.get(2).getText()+" "+textFields.get(3).getText(),
                               textFields.get(4).getText(),
                               textFields.get(5).getText().concat(" , "+textFields.get(6).getText()),
                               origin,
                               team,
                               hasRenewedContract,
                               birthdate,
                               locality);
    }
    public void cleanWrongTextField(){
        for (JTextField textField: textFields) {
            textField.setText("");
        }
    }
    public String getErrorInputMessage(){
        return this.errorInputMessage.toString();
    }

}
