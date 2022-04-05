package View;

import javax.swing.*;

public class MovementCarThread extends Thread{
    private JPanel panel;
    private GraphicCar graphicCar;

    public MovementCarThread(JPanel panel, GraphicCar graphicCar){
        this.panel = panel;
        this.graphicCar = graphicCar;
    }

    public void run(){
        while(true){
            try{
                Thread.sleep(1);
                this.graphicCar.move();
                this.panel.repaint();
            }
            catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
}
