import java.awt.*;

public class Body {

    double x;
    double y;

    double vx;
    double vy;

    double mass = 1;

    Color color = Color.WHITE;

    public Body(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void update(){

        vx *= 0.999;
        vy *= 0.999;

        x += vx;
        y += vy;
    }

    public void draw(Graphics2D g){
        if(mass < 1000){

            g.setColor(color);
            g.fillOval((int)x, (int)y, 2, 2);

        }
    }
}