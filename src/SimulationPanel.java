import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel implements Runnable {

    Thread thread;
    ArrayList<Body> bodies = new ArrayList<>();
    double G = 0.05;

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

        Body core = new Body(450, 450);
        core.mass = 10000;

        bodies.add(core);

        //NUMBER OF STARS
        int starCount = 1500;

        for(int i = 0; i < starCount; i++){

            double angle = rand.nextDouble() * Math.PI * 2;
            double radius = rand.nextDouble() * 350;

            double x = centerX + Math.cos(angle) * radius;
            double y = centerY + Math.sin(angle) * radius;

            Body star = new Body(x, y);

            double dx = x - centerX;
            double dy = y - centerY;

            double dist = Math.sqrt(dx*dx + dy*dy);

            double speed = Math.sqrt(0.05 * 1000 / dist);

            star.vx = -dy / dist * speed;
            star.vy = dx / dist * speed;

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

        for(int i = 0; i < bodies.size(); i++){

            Body a = bodies.get(i);

            double ax = 0;
            double ay = 0;

            for(int j = 0; j < bodies.size(); j++){

                if(i == j) continue;

                Body b = bodies.get(j);

                double dx = b.x - a.x;
                double dy = b.y - a.y;

                double dist = Math.sqrt(dx*dx + dy*dy);

                if(dist < 2) continue;

                double force = G * b.mass / (dist * dist);

                ax += force * dx / dist;
                ay += force * dy / dist;
            }

            a.vx += ax;
            a.vy += ay;
        }

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