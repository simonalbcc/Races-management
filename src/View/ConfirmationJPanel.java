//region packages & imports
package View;

import Utility.AddUtils;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//endregion

public class ConfirmationJPanel {
    //region private attributes & constructor
    private JPanel panel;
    private JPanel[] panels;
    private JLabel confirmationLabel;
    private Container mainContainer;

    public ConfirmationJPanel(Container mainContainer, JPanel...panels) {
        // panel & panels received init
        panel = new JPanel();
        panel.setSize(new Dimension(250, 100));
        panel.setLayout(null);

        this.panels = panels;

        // confirmation label init & add to the panel
        confirmationLabel = new JLabel("Êtes-vous sûrs de vouloir continuer? Les données seront perdues.");
        confirmationLabel.setBounds(20, 20, 200, 30);
        confirmationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(confirmationLabel);

        // panel displays
        int result = JOptionPane.showConfirmDialog(null, panel, "Avertissement", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        // add according to the result
        AddUtils.addToMainContainer(mainContainer, panels[result]);

    }
    //endregion
}
