package View;


import Model.*;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AllDriverModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Model.Driver> drivers;

    public AllDriverModel(ArrayList<Model.Driver> drivers){
        columnNames = new ArrayList<>();
        this.drivers = drivers;
        columnNames.add("№");
        columnNames.add("Nom et prénom");
        columnNames.add("Numéro de téléphone");
        columnNames.add("Adresse");
        columnNames.add("Nationalité");
        columnNames.add("Equipe");
        columnNames.add("Contrat renouvelé");
        columnNames.add("Date de naissance");
        columnNames.add("Domicile");
    }
    public String getColumnName(int col){
        return columnNames.get(col);
    }
    @Override
    public int getRowCount() {
        return drivers.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.size();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Driver driver = drivers.get(rowIndex);
        switch (columnIndex){
            case 0: return driver.getSerialNumber();
            case 1: return driver.getLastNameFirstName();
            case 2: return getPhoneOutput(driver);
            case 3: return driver.getStreetAndNumber();
            case 4: return driver.getNationality();
            case 5: return teamNameOfDriver(driver);
            case 6: return driver.isHasRenewedCommitmentContract();
            case 7: return getBirthdateOutput(driver);
            case 8: return getCityName(driver);
            default:return null;
        }
    }
    public Class getColumnClass (int column)
    { Class c;
        switch (column)
        {   case 0: c = Integer.class;
                break;
            case 6: c = Boolean.class;
                break;
            default: c = String.class;
        }
        return c;
    }

    public String teamNameOfDriver(Driver driver){
        return driver.getTeam().getName();
    }
    public String getBirthdateOutput(Driver driver){
        SimpleDateFormat output = new SimpleDateFormat("dd-MM-yyyy");
        output.setCalendar(driver.getBirthdate());
        return output.format(driver.getBirthdate().getTime());
    }
    public String getPhoneOutput(Driver driver){
        String output = String.valueOf(driver.getPhoneNumber());
        output = "+"+output.substring(0,2) +" "+  output.substring(2) ;
        return output;
    }
    public String getCityName(Driver driver){
        return driver.getHome().getCity();
    }

}
