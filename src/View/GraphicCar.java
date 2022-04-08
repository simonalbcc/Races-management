package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


public class GraphicCar extends JPanel {
    private BufferedImage carImage;
    private Rectangle rectangle;
    private int deltaX = 1;

    public GraphicCar(){
        try{
            carImage = ImageIO.read(new FileInputStream("car.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        repaint();
        rectangle = new Rectangle(0,0,120,100);
    }

    public void move(){
        rectangle.x += deltaX;
    }

    public void paint(Graphics g){
        g.drawImage(carImage, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null );
    }


}


