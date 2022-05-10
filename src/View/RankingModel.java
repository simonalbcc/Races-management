package View;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class RankingModel extends AbstractTableModel {
    private ArrayList<String> columnNames;
    private ArrayList<Model.Ranking> rankings;

    public RankingModel(ArrayList<Model.Ranking> rankings){
        this.rankings = rankings;
        this.columnNames = new ArrayList<>();

        columnNames.add("Position");
        columnNames.add("N° voiture");
        columnNames.add("Puissance voiture");
        columnNames.add("Nom et prénom du pilote");
        columnNames.add("Record en piste");
    }

    public String getColumnName(int col){
        return columnNames.get(col);
    }
    @Override
    public int getRowCount() {
        return rankings.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Model.Ranking ranking = rankings.get(rowIndex);
        switch (columnIndex){
            case 0 : return ranking.getPosition();
            case 1 : return ranking.getCar().getNumber();
            case 2 : return ranking.getCar().getPower();
            case 3 : return ranking.getDriver().getLastNameFirstName();
            case 4 : return ranking.getRecord();
            default: return null;
        }
    }
}
