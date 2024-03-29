//region packages & imports
package View.Utility;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
//endregion

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


    //region abstract methods
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
    @Override
    public Class getColumnClass (int column) {
        Class c;
        switch (column) {
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
    //endregion

}
