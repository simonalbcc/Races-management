package View;


import Model.*;

import javax.swing.table.AbstractTableModel;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class AllDriverModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Model.Driver> drivers;

    public AllDriverModel(ArrayList<Model.Driver> drivers){
        columnNames = new ArrayList<>();
        this.drivers = drivers;
        columnNames.add("Numéro");
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
        return columnNames.size();
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
            case 2: return driver.getPhoneNumber();
            case 3: return driver.getStreetAndNumber();
            case 4: return driver.getNationality();
            case 5: return driver.getTeam();
            case 6: return driver.isHasRenewedCommitmentContract();
            case 7: return driver.getBirthdate();
            case 8: return driver.getHome();
            default:return null;
        }
    }
    public Class getColumnClass (int column)
    { Class c;
        switch (column)
        { case 0: c = Integer.class;
            break;
            case 1: c = String.class;
                break;
            case 2: c = Integer.class;
                break;
            case 3: c = String.class;
                break;
            case 4: c = String.class;
                break;
            case 5: c = Team.class;
                break;
            case 6: c = Boolean.class;
                break;
            case 7: c = GregorianCalendar.class;
                break;
            case 8: c = Locality.class;
                break;

            default: c = String.class;
        }
        return c;
    }

}
