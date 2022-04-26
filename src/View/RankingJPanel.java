package View;

import Model.Car;
import Model.Ranking;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RankingJPanel extends JPanel {
    private static int numWindow;
    private Container mainContainer;

    private StateRanking first, second, third, finale;
    private StateRanking current;

    public RankingJPanel(Container mainContainer){

        // init container
        this.mainContainer = mainContainer;

        // set black borders
        this.setBorder(new BasicBorders.FieldBorder(Color.BLACK, Color.black, Color.BLACK, Color.BLACK));

        // add the first panel
        current = new StateRanking1(mainContainer);

    }
}