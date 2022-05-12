package View;

import Model.Sponsor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class SponsorModel extends AbstractTableModel {
    private ArrayList<Sponsor> sponsors;
    private ArrayList<String> columnsName;

    public SponsorModel(ArrayList<Sponsor> accidents) {
        this.columnsName = new ArrayList<>();
        this.sponsors = accidents;

        columnsName.add("Date");
        columnsName.add("Nom du team");
        columnsName.add("Sponsor");

    }

    public String getColumnName(int col){
        return columnsName.get(col);
    }
    @Override
    public int getRowCount() {
        return this.sponsors.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnsName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Sponsor sponsor = sponsors.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sponsor.getTeam();
            case 1:
                return sponsor.getTeam().toString();
            case 2:
                return sponsor.getCompany().toString();
            default:
                return null;
        }
    }
}
