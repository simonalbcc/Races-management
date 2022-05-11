package View;

import Model.Accident;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AccidentModel extends AbstractTableModel {
    private ArrayList<Accident> accidents;
    private ArrayList<String> columnsName;

    public AccidentModel(ArrayList<Accident> accidents) {
        this.columnsName = new ArrayList<>();
        this.accidents = accidents;

        columnsName.add("Date");
        columnsName.add("Nom et prénom");
        columnsName.add("Rue et numéro");
        columnsName.add("Ville");
        columnsName.add("Equipe");

    }

    public String getColumnName(int col){
        return columnsName.get(col);
    }
    @Override
    public int getRowCount() {
        return this.accidents.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Accident accident = accidents.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return accident.getDate();
            case 1:
                return accident.getDriver().getLastNameFirstName();
            case 2:
                return accident.getDriver().getStreetAndNumber();
            case 3:
                return accident.getDriver().getHome().getCity();
            case 4:
                return accident.getDriver().getTeam().getName();
            default:
                return null;
        }
    }

}
