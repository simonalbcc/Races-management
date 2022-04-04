package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainJFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu applicationMenu, viewMenu, helpMenu;
    private JMenuItem close, darkMode, shortcuts, contactsInfos;
    private JPanel panel;
    private Container frameContainer;


    public MainJFrame(){
        super("Race Application");
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
        setBounds(10,10,400,400);

        frameContainer = this.getContentPane();
        frameContainer.setLayout(new BorderLayout());

//region menuBar
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        applicationMenu = new JMenu("Application");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");
        menuBar.add(applicationMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        close = new JMenuItem("Close");
        close.addActionListener(new closeListener());

        darkMode = new JMenuItem("Dark Mode");

        shortcuts = new JMenuItem("Shortcuts");

        contactsInfos = new JMenuItem("Contacts Infos");

        applicationMenu.add(close);
        viewMenu.add(darkMode);
        helpMenu.add(shortcuts);
        helpMenu.addSeparator();
        helpMenu.add(contactsInfos);
//endregion

        frameContainer.add(new WelcomeJPanel(), BorderLayout.CENTER);
        setVisible(true);
    }

    private class closeListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }
}
