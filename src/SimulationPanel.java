import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel implements Runnable {

    Thread thread;
    ArrayList<Body> bodies = new ArrayList<>();

    public SimulationPanel() {
        setBackground(Color.BLACK);

        createGalaxy();

        thread = new Thread(this);
        thread.start();
    }

    public void createGalaxy() {

        Random rand = new Random();

        int centerX = 450;
        int centerY = 450;

        int starCount = 1500;

        for(int i = 0; i < starCount; i++){

            double angle = rand.nextDouble() * Math.PI * 2;
            double radius = rand.nextDouble() * 350;

            double x = centerX + Math.cos(angle) * radius;
            double y = centerY + Math.sin(angle) * radius;

            Body star = new Body(x, y);

            bodies.add(star);
        }
    }

    public void run() {

        while(true){

            updateSimulation();
            repaint();

            try{
                Thread.sleep(16); // ~60 FPS
            }catch(Exception e){}
        }
    }

    public void updateSimulation(){
        for(Body b : bodies){
            b.update();
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for(Body b : bodies){
            b.draw(g);
        }
    }
}