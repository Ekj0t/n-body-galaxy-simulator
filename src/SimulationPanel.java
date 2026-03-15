import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class SimulationPanel extends JPanel implements Runnable {

    Thread thread;
    ArrayList<Body> bodies = new ArrayList<>();
    double G = 0.05;

    public SimulationPanel() {
        setBackground(Color.BLACK);

        createGalaxy(250, 450, 0.3);
        createGalaxy(650, 450, -0.3);

        thread = new Thread(this);
        thread.start();
    }

    public void createGalaxy(int centerX, int centerY, double vxOffset) {

        Random rand = new Random();

        Body core = new Body(centerX, centerY);
        core.mass = 10000;

        bodies.add(core);

        int starCount = 750; // half per galaxy

        int arms = 2;
        double armSpread = 0.4;

        for(int i = 0; i < starCount; i++){

            double radius = rand.nextDouble() * 300;

            double armAngle = (i % arms) * (2 * Math.PI / arms);

            double angle = armAngle + radius * 0.02;

            angle += rand.nextGaussian() * armSpread;

            double x = centerX + Math.cos(angle) * radius;
            double y = centerY + Math.sin(angle) * radius;

            Body star = new Body(x, y);

            double dx = x - centerX;
            double dy = y - centerY;

            double dist = Math.sqrt(dx*dx + dy*dy);

            double speed = Math.sqrt(0.05 * 800 / (dist + 20));

            star.vx = -dy / dist * speed + vxOffset;
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

                double force = G * b.mass / (dist * dist) * 0.05;

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

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fade previous frame (motion blur effect) -> less alpha : long trails
        g2.setColor(new Color(0,0,0,45));
        g2.fillRect(0,0,getWidth(),getHeight());

        for(Body b : bodies){
            b.draw(g2);
        }
    }
}