package Utility;

import Model.Race;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SponsorModel extends AbstractTableModel {
    private ArrayList<Race> races;
    private ArrayList<String> columnsName;

    public SponsorModel(ArrayList<Race> races) {
        this.columnsName = new ArrayList<String>();
        this.races = races;

        columnsName.add("Date");
        columnsName.add("Nom du team");
        columnsName.add("Sponsor");

    }

    public String getColumnName(int col){
        return columnsName.get(col);
    }
    @Override
    public int getRowCount() {
        return this.races.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Race race = races.get(rowIndex);
        switch (columnIndex) {
            case 0: return race.getDate();
            case 1: return race.getRankings()[0].getCar().getMembership().getName();
            case 2: return race.getRankings()[0].getCar().getMembership().getCompanies()[0].getName();
            default: return null;
        }
    }
    public Class getColumnClass (int column)
    { Class c;
        switch (column)
        {
            case 0:
            case 1: c = Integer.class;
                break;
            case 2 :
            case 4 : c = Double.class;
                break;
            default: c = String.class;
        }
        return c;
    }
}
