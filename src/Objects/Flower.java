package Objects;

import javax.swing.*;
import java.awt.*;

public class Flower {
    private final Graphics2D gr;
    private final int x;
    private final int y;
    private final int width;
    private final int[] color;

    public Flower(Graphics gr, int x, int y, int width, int[] color) {
        this.gr = (Graphics2D) gr;
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
    private int getVal(double cnt) {
        return (int)(width * cnt);
    }


    public void drawFlower() {
        gr.setColor(new Color(72, 140, 56));
        gr.fillRect(x + getVal(0.47), y + getVal(0.9), getVal(0.3), getVal(3));

        gr.setColor(new Color(color[0], color[1], color[2]));
        gr.fillOval(x + getVal(0.3), y - getVal(0.9), getVal(0.7), getVal(1.2));
        gr.fillOval(x + getVal(0.3), y + getVal(0.9), getVal(0.7), getVal(1.2));
        gr.fillOval(x - getVal(0.9), y + getVal(0.3), getVal(1.2), getVal(0.7));
        gr.fillOval(x + getVal(0.9), y + getVal(0.3), getVal(1.2), getVal(0.7));

        gr.setColor(Color.WHITE);
        gr.fillOval(x + getVal(0.145), y + getVal(0.145), width, width);


    }
}
