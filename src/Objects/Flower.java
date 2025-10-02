package Objects;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Flower {
    private final int x;
    private final int y;
    private final int width;
    private final int[] color;

    public Flower(int x, int y, int width, int[] color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.color = color;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int[] getColor() {return color;}

    public static int getVal(int width, double cnt) {
        return (int)(width * cnt);
    }

    public void drawFlower(Graphics2D gr) {
        gr.setColor(new Color(72, 140, 56));
        gr.fillRect(x + getVal(width, 0.47), y + getVal(width, 0.9), getVal(width, 0.3), getVal(width, 3));

        AffineTransform oldTransform = gr.getTransform();
        gr.setColor(new Color(color[0], color[1], color[2]));

        gr.translate(x + getVal(width, 0.65), y + getVal(width, 0.55));

        for (int i = 0; i < 8; i++) {
            gr.rotate(i * Math.PI / 4);
            gr.fillOval(0, 0, getVal(width, 0.7), getVal(width, 1.2));
            gr.rotate(-i * Math.PI / 4);
        }

        gr.setTransform(oldTransform);

        gr.setColor(Color.WHITE);
        gr.fillOval(x + getVal(width, 0.145), y + getVal(width, 0.145), width, width);
    }
}