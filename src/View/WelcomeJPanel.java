package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;


public class WelcomeJPanel extends JPanel {
    private JLabel welcomeLabel;
    private ButtonJPanel buttons;
    private CarPanel carPanel;
    private GridBagConstraints gc;

    public WelcomeJPanel(){
        this.setLayout(new GridBagLayout());
        gc = new GridBagConstraints();
        this.setPreferredSize(new Dimension(400,400));


        welcomeLabel = new JLabel("<html> <h1> Hello racer </h1> </html>");
        carPanel = new CarPanel();
        buttons = new ButtonJPanel();

        gc.weighty = 1;
        gc.gridx = 2;
        gc.anchor = GridBagConstraints.PAGE_START;
        this.add(welcomeLabel, gc);

        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        this.add(carPanel,gc);

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.PAGE_END;
        this.add(buttons, gc);
    }

    private class ButtonJPanel extends JPanel{
        private JButton pilotsButton, rankingButton, carsButton;
            public ButtonJPanel(){
            pilotsButton = new JButton("Show pilots");
            pilotsButton.addActionListener(new ButtonListener());

            rankingButton = new JButton("Show ranking");
            rankingButton.addActionListener(new ButtonListener());

            carsButton = new JButton("Show cars");

            this.add(pilotsButton);
            this.add(rankingButton);
            this.add(carsButton);
        }
        private class ButtonListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeJPanel.this.removeAll();
                if(e.getSource() == pilotsButton){
                    WelcomeJPanel.this.add(new AccidentsPanel());
                }
                if(e.getSource() == rankingButton){
                    WelcomeJPanel.this.add(new RankingPanel());
                }
                WelcomeJPanel.this.repaint();
                WelcomeJPanel.this.validate();
            }
        }
    }

    private class CarPanel extends JPanel{
        public GraphicCar graphicCar;
        public Wall leftWall, rightWall;

        public CarPanel(){
            setLayout(new BorderLayout());
            graphicCar = new GraphicCar();
            leftWall = new Wall(0,0,10,150);
            rightWall = new Wall(975,0,10,150);
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
                        Thread.sleep(15);
                        this.graphicCar.move();
                        this.panel.repaint();
                    }
                    catch (InterruptedException exception){
                        exception.printStackTrace();
                    }
                }
            }
        }

        private class GraphicCar extends JPanel {
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
                rectangle = new Rectangle(11,0,120,100);
            }

            public void move(){
                if(carPanel.rightWall.collision(this) || carPanel.leftWall.collision(this)){
                    deltaX *= -1;
                }
                rectangle.x += deltaX;
            }

            public void paint(Graphics g){
                g.drawImage(carImage, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null );
            }

            public Rectangle getRectangle() {
                return rectangle;
            }
        }

        private class Wall extends JPanel{
            private Rectangle rectangle;

            public Wall(int x, int y, int width, int height){
                this.rectangle = new Rectangle(x,y,width,height);
            }

            public void draw(Graphics g){
                g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }

            public boolean collision(GraphicCar graphicCar){
                return this.rectangle.intersects(graphicCar.getRectangle());
            }
        }
    }
}
