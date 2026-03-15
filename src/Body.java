import java.awt.*;

public class Body {

    double x;
    double y;

    double vx;
    double vy;

    double mass = 1;

    Color color = Color.WHITE;

    float brightness = 1.0f;

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

    public void draw(Graphics2D g, double offsetX, double offsetY){
        if(mass < 1000){

            int drawX = (int)(x + offsetX);
            int drawY = (int)(y + offsetY);

            int r = (int)(color.getRed() * brightness);
            int gC = (int)(color.getGreen() * brightness);
            int b = (int)(color.getBlue() * brightness);

            g.setColor(new Color(r, gC, b));
            int size = brightness > 0.8 ? 3 : 2;
            g.fillOval(drawX, drawY, size, size);

        }
    }
}