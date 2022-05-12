package Utility;

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


}
