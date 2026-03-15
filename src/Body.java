import java.awt.Graphics;
import java.awt.Color;

public class Body {

    double x;
    double y;

    double vx;
    double vy;

    double mass = 1;

    public Body(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void update(){
        x += vx;
        y += vy;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval((int)x, (int)y, 2, 2);
    }
}