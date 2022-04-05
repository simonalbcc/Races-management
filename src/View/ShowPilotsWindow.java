package View;

import Model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ShowPilotsWindow extends JPanel {
    private JLabel selectDriver, driver;
    private String[] driversArray;
    private JComboBox driversCombobox;
    private JPanel panelDrivers;
    private JPanel panelDriver;
    private JButton accidentsButton;
    public ShowPilotsWindow() {
        this.setLayout(new BorderLayout());
        panelDrivers = new JPanel(new BorderLayout());
        panelDriver = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 2;
        gc.insets = new Insets(75, 0,0,0);

        selectDriver = new JLabel("<html> <h2> <u> You must select the driver in the list below : </u> </h2> </html>");
        selectDriver.setHorizontalAlignment(SwingConstants.CENTER);

        driversArray = new String[]{"Select driver", "driver 1", "driver 2", "driver 3"};
        driversCombobox = new JComboBox(driversArray);
        driversCombobox.addItemListener(new ComboboxItemListener());

        driver = new JLabel();
        driver.setText("Here will appear a summary of the driver");
        accidentsButton = new JButton("Show accident");
        accidentsButton.setVisible(false);
        accidentsButton.addActionListener(new AccidentButtonListener());


        panelDriver.add(driver);
        panelDriver.add(accidentsButton, gc);


        panelDrivers.add(driversCombobox, BorderLayout.NORTH);
        panelDrivers.add(panelDriver, BorderLayout.CENTER);

        this.add(selectDriver, BorderLayout.NORTH);
        this.add(panelDrivers, BorderLayout.CENTER);

        this.setVisible(true);
    }
    private class ComboboxItemListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
                if(e.getSource() == driversCombobox && driversCombobox.getSelectedIndex() != 0){
                    driver.setText(driversCombobox.getSelectedItem().toString());
                    accidentsButton.setVisible(true);
                } else {
                    driver.setText("Here will appear a summary of the driver"); // possibilité de faire autrement?
                    accidentsButton.setVisible(false);
                }
            }
        }
        private class AccidentButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }
    }



