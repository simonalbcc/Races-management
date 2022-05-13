//region packages & imports
package View;

import Controller.Controller;
import Utility.AddUtils;
import Utility.AllDriverModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//endregion

public class DriverJTable extends JPanel {
    //region privates attributes & constructor
    private JTable jTable;
    private JLabel title;
    private Controller controller;
    private GridBagConstraints gc;
    private ImageIcon imageIcon;
    private JScrollPane sp;
    private ButtonsPanel buttonsPanel;
    private Container mainContainer;

    public DriverJTable(Container mainContainer){

        // controller, button panel & main container init
        controller = new Controller();
        this.mainContainer = mainContainer;

        buttonsPanel = new ButtonsPanel();
        buttonsPanel.getNext().setVisible(false);
        buttonsPanel.addActionListener(e -> AddUtils.addToMainContainer(mainContainer, new WelcomeJPanel()));

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

        // table init with settings
        jTable = new JTable(new AllDriverModel(controller.getAllDrivers()));
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jTable.getColumnModel( ).getColumn(0).setPreferredWidth(40);
        for(int iCell = 1; iCell < jTable.getColumnCount()-1; iCell++){
            jTable.getColumnModel( ).getColumn(iCell).setPreferredWidth(132);
        }
        jTable.getColumnModel( ).getColumn(jTable.getColumnCount()-1).setPreferredWidth(101);

        jTable.setRowHeight(40);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // used to center cells
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int cell=0; cell < jTable.getColumnCount();cell++){
            if(cell != 6){
                jTable.getColumnModel().getColumn(cell).setCellRenderer(centerRenderer);
            }
        }

        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // add to the panel
        this.sp = new JScrollPane(jTable);
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
    public ButtonsPanel getButtonsPanel() {
        return buttonsPanel;
    }

    //endregion
}
