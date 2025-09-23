package Objects;

import java.awt.*;

public class Tree {
    private final Graphics g;
    private final int x;
    private final int y;
    private final int height;

    public Tree(Graphics2D g, int x, int y, int height) {
        this.g = g;
        this.x = x;
        this.y = y;
        this.height = height;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHeight() {
        return height;
    }

    public int getVal(double cnt) {
        return (int)(height * cnt);
    }

    public void drawTree() {

        g.setColor(new Color(72, 140, 56));
        g.fillOval(x - getVal(0.16), y - getVal(0.23), getVal(0.35), getVal(0.35)); //верхний
        g.fillOval(x - getVal(0.25), y, getVal(0.35), getVal(0.35)); //левый верхний
        g.fillOval(x - getVal(0.25), y + getVal(0.3), getVal(0.35), getVal(0.35)); //левый нижний
        g.fillOval(x - getVal(0.1), y, getVal(0.35), getVal(0.35)); //правый верхний
        g.fillOval(x - getVal(0.1), y + getVal(0.3), getVal(0.35), getVal(0.35)); //правый нижний



        g.setColor(new Color(156, 135, 135));
        g.fillRect(x, y, getVal(0.05), height);

    }
}
