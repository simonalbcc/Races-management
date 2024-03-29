//region packages & imports
package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
//endregion

public class WelcomeJPanel extends JPanel {
    private JLabel welcomeLabel;
    private ButtonJPanel buttons;
    private CarPanel carPanel;
    private GridBagConstraints gc;

    public WelcomeJPanel() {
        // set layout
        this.setLayout(new GridBagLayout());

        gc = new GridBagConstraints();
        welcomeLabel = new JLabel("<html> <h1> Bonjour  </h1> </html>");
        carPanel = new CarPanel();
        buttons = new ButtonJPanel();

        // add components
        gc.weighty = 1;
        gc.weightx = 1;
        gc.gridy = 0;
        this.add(welcomeLabel, gc);

        gc.gridy = 1;
        gc.fill = GridBagConstraints.BOTH;
        this.add(carPanel,gc);

        gc.gridy = 2;
        this.add(buttons, gc);

    }

    private class ButtonJPanel extends JPanel{
        private JButton speedUpButton, slowDownButton;
            public ButtonJPanel(){
            speedUpButton = new JButton("Accélérer !");
            speedUpButton.addActionListener(new ButtonListener());

            slowDownButton = new JButton("Ralentir...");
            slowDownButton.addActionListener(new ButtonListener());

            this.add(slowDownButton);
            this.add(speedUpButton);
        }
        private class ButtonListener implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == speedUpButton){
                    WelcomeJPanel.this.carPanel.graphicCar.addSpeed();
                }
                if(e.getSource() == slowDownButton){
                    WelcomeJPanel.this.carPanel.graphicCar.removeSpeed();
                }
            }
        }
    }
    private class CarPanel extends JPanel{
        private GraphicCar graphicCar;
        private Wall leftWall, rightWall;

        public CarPanel() {
            setLayout(new BorderLayout());
            graphicCar = new GraphicCar();
            leftWall = new Wall(0,0,20,150);
            rightWall = new Wall(1060,0,20,150);
            MovementCarThread movementCarThread = new MovementCarThread(this, graphicCar);
            movementCarThread.start();
        }
        public void paint(Graphics g){
            super.paint(g);
            leftWall.draw(g);
            rightWall.draw(g);
            graphicCar.paint(g);
        }

        private class MovementCarThread extends Thread{
            private JPanel panel;
            private GraphicCar graphicCar;

            public MovementCarThread(JPanel panel, GraphicCar graphicCar){
                this.panel = panel;
                this.graphicCar = graphicCar;
            }

            public void run(){
                while(true){
                    try{
                        Thread.sleep(graphicCar.speed);
                        this.graphicCar.move();
                        this.panel.repaint();
                    }
                    catch (InterruptedException exception){
                        JOptionPane.showMessageDialog(null, "Problème avec le mouvement de l'image!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        private class GraphicCar extends JPanel {
            private BufferedImage carImage;
            private Rectangle rectangle;
            private int deltaX;
            private int speed;

            public GraphicCar() {
                try{
                    carImage = ImageIO.read(new FileInputStream("images\\car.png"));
                }catch (IOException e){
                    JOptionPane.showMessageDialog(null, "Problème avec l'image de la voiture", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                rectangle = new Rectangle(20,25,120,100);
                speed = 11;
                deltaX = 1;
            }

            public void move() {
                if(carPanel.rightWall.collision(this) || carPanel.leftWall.collision(this)){
                    deltaX *= -1;
                    if(deltaX == -1){
                        try{
                            carImage = ImageIO.read(new FileInputStream("images\\carReversed.png"));
                        }catch (IOException e){
                            JOptionPane.showMessageDialog(null, "Problème avec l'image de la voiture", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        try{
                            carImage = ImageIO.read(new FileInputStream("images\\car.png"));
                        }catch (IOException e){
                            JOptionPane.showMessageDialog(null, "Problème avec l'image de la voiture", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                rectangle.x += deltaX;
            }

            public void paint(Graphics g){
                g.drawImage(carImage, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null );
            }

            public void addSpeed() {
                if (speed > 10) {
                    speed -= 10;
                }
            }

            public void removeSpeed(){
                if(speed < 100){
                    speed += 10;
                }
            }
        }
        private class Wall extends JPanel{
            private Rectangle rectangle;
            private Image line;

            public Wall(int x, int y, int width, int height){
                this.rectangle = new Rectangle(x,y,width,height);
            }

            public void draw(Graphics g){
                try{
                    line = ImageIO.read(new FileInputStream("images\\line.png"));
                }catch (IOException e){
                    e.printStackTrace();
                }
                g.drawImage(line, rectangle.x, rectangle.y, rectangle.width, rectangle.height,null);
            }

            public boolean collision(GraphicCar graphicCar){
                return this.rectangle.intersects(graphicCar.rectangle);
            }
        }
    }

}
