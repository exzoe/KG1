package Objects;

import java.awt.*;

public class AirBalloon {
    private final Graphics2D gr;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public AirBalloon(Graphics2D gr, int x, int y, int width, int height) {
        this.gr = gr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private int getVal(double cnt) {
        return (int)(width * cnt);
    }

    public void draw() {
        gr.setColor(new Color(255, 128, 0));
        gr.fillOval(x, y, getVal(1.3), height);

        int[] xPoints = {
                x + getVal(0.07),                  // верх лево
                x + width + getVal(0.235),          // верх право
                x + width - 40 + getVal(0.07),     // низ право
                x + 40 + getVal(0.235)              // низ лево
        };

        int[] yPoints = {
                y + getVal(0.7),                  // верх лево
                y + getVal(0.7),                  // верх право
                y + height + getVal(0.7),         // низ право
                y + height + getVal(0.7)          // низ лево
        };
        gr.fillPolygon(xPoints, yPoints, 4);

        gr.setColor(new Color(66, 63, 61));
        gr.fillRect(x + 40 + getVal(0.235), y + height + getVal(0.7), getVal(0.05), getVal(0.2));
        gr.fillRect(x + width - 48 + getVal(0.07), y + height + getVal(0.7), getVal(0.05), getVal(0.2));

        gr.setColor(new Color(154, 120, 120));
        gr.fillRect(x + 40 + getVal(0.235), y + height + getVal(0.8), getVal(0.3), getVal(0.2));


    }

}
