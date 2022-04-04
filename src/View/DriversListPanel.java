package View;

import javax.swing.*;

public class DriversListPanel extends JPanel {
    private String[] driversArray;
    private JList driversList;
    public DriversListPanel() {
        // region
        // endregion
        driversArray = new String[]{"pilote 1", "pilote 2", "pilote 3", "pilote 4", "pilote 5", "pilote 6", "pilote 7", "pilote 8"}; // pour tester
        driversList = new JList(driversArray);
        this.add(driversList);

    }
}
