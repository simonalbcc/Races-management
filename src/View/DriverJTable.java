//region packages & imports
package View;

import Controller.Controller;
import View.Utility.AllDriverModel;
import View.Utility.ButtonsJPanel;
import View.Utility.Utils;

import javax.swing.*;
import java.awt.*;
//endregion

public class DriverJTable extends JPanel {
    //region privates attributes & constructor
    private JTable jTable;
    private JLabel title;
    private Controller controller;
    private GridBagConstraints gc;
    private ImageIcon imageIcon;
    private ButtonsJPanel buttonsPanel;
    private Container mainContainer;

    public DriverJTable(Container mainContainer) throws Exception {
        // table init with settings
        // controller, button panel & main container init
        controller = new Controller();
        this.mainContainer = mainContainer;

        buttonsPanel = new ButtonsJPanel();
        buttonsPanel.getNext().setVisible(false);
        buttonsPanel.getBack().addActionListener(e -> Utils.addToMainContainer(mainContainer, new WelcomeJPanel()));

        // set layout with constraints
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;

        // title init
        title = new JLabel(" Liste des pilotes");
        title.setFont(new Font("Serif",Font.TRUETYPE_FONT,30));
        imageIcon = new ImageIcon("images\\volant.png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH));
        title.setIcon(imageIcon);

        jTable = new JTable(new AllDriverModel(controller.getAllDrivers()));

        JScrollPane sp = Utils.centerTableData(jTable);

        // add to the panel
        this.add(title, gc);
        gc.gridy = 1;
        this.add(sp, gc);
        gc.gridy = 2;
        this.add(buttonsPanel, gc);
    }
    //endregion
    //region getters
    public JTable getjTable() {
        return jTable;
    }
    public ButtonsJPanel getButtonsPanel() {
        return buttonsPanel;
    }

    //endregion
}
