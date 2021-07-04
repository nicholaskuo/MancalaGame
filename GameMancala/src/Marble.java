
import java.awt.*;

public class Marble extends GameObj {
    public static final int SIZE = 15;
    private Color color;

    public Marble(int vx, int vy, int px, int py, int courtWidth, int courtHeight, Color color) {
        super(vx, vy, px, py, SIZE, SIZE, courtWidth, courtHeight);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        g.setColor(this.color);
        g.fillOval(this.getPx() + 1, this.getPy() + 1, this.getWidth() - 2, this.getHeight() - 2);
    }
}
