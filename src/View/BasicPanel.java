package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class BasicPanel extends JPanel {
    private GridBagConstraints gc;

    public BasicPanel(JPanel... panels) {
        this.setLayout(new GridBagLayout());
        this.gc = new GridBagConstraints();
        JPanel[] var2 = panels;
        int var3 = panels.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            JPanel panel = var2[var4];
            ++this.gc.gridy;
            this.add(panel, this.gc);
        }

    }
}
