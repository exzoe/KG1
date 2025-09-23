package Objects;

import java.awt.*;

public class Cloud {
    private final Graphics2D gr;
    private final int x;
    private final int y;
    private final int width;

    public Cloud(Graphics2D gr, int x, int y, int width) {
        this.gr = gr;
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }

    private int getVal(double cnt) {
        return (int)(width * cnt);
    }

    public void drawCloud() {
        gr.setColor(Color.WHITE);
        gr.fillOval(x, y + getVal(0.22), getVal(0.32), getVal(0.32));
        gr.fillOval(x + getVal(0.67), y + getVal(0.22), getVal(0.32), getVal(0.32));
        gr.fillOval(x + getVal(25.0/99), y, getVal(50.0/99), getVal(50.0/99));
        gr.fillRect(x + getVal(15.0/99), y + getVal(40.0/99), getVal(66.0/99), getVal(14.0/99));
    }
}