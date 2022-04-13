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
    private JMenuItem close, stopAnimation, shortcuts, contactsInfos;
    private Container frameContainer;


    public MainJFrame(){
        super("Race Application");
        addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                  System.exit(0);
                              }
                          }
        );
        setBounds(10,10,1000,1000);

        frameContainer = this.getContentPane();

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
        close.addActionListener(new menuItemListner());

        stopAnimation = new JMenuItem("Stop Animation");

        shortcuts = new JMenuItem("Shortcuts");

        contactsInfos = new JMenuItem("Contacts Infos");
        contactsInfos.addActionListener(new menuItemListner());

        applicationMenu.add(close);
        viewMenu.add(stopAnimation);
        helpMenu.add(shortcuts);
        helpMenu.addSeparator();
        helpMenu.add(contactsInfos);
//endregion

        frameContainer.add(new WelcomeJPanel());
        setVisible(true);
        //setResizable(false);
    }

    private class menuItemListner implements ActionListener{
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == close){
                System.exit(0);
            }

            if(actionEvent.getSource() == contactsInfos){
                frameContainer.removeAll();
                frameContainer.add(new ContactsInfosJPanel());
                MainJFrame.this.validate();
            }
        }
    }
}
