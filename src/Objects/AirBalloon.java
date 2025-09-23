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
        GradientPaint gp = new GradientPaint(
                xPoints[0], yPoints[0], new Color(255, 128, 0),
                xPoints[2], yPoints[2], new Color(186, 116, 50)
        );
        gr.setPaint(gp);
        gr.fillPolygon(xPoints, yPoints, 4);
        gr.setPaint(null);

        gr.setColor(new Color(66, 63, 61));
        gr.fillRect(x + 40 + getVal(0.235), y + height + getVal(0.7), getVal(0.05), getVal(0.2));
        gr.fillRect(x + width - 48 + getVal(0.07), y + height + getVal(0.7), getVal(0.05), getVal(0.2));

        gr.setColor(new Color(154, 120, 120));
        gr.fillRect(x + 40 + getVal(0.235), y + height + getVal(0.8), getVal(0.3), getVal(0.2));

        int arcWidth = getVal(1.3);
        int arcHeight = height;
        int arcX = x;
        int arcY = y;
        int startAngle = 0;
        int arcAngle = 36; //360 / 10

        gr.setColor(Color.YELLOW);
        gr.fillArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);
        startAngle += arcAngle;

        gr.setColor(Color.RED);
        gr.fillArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);
        startAngle += arcAngle;

        gr.setColor(Color.WHITE);
        gr.fillArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);
        startAngle += arcAngle;

        gr.setColor(Color.PINK);
        gr.fillArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);
        startAngle += arcAngle;

        gr.setColor(Color.MAGENTA);
        gr.fillArc(arcX, arcY, arcWidth, arcHeight, startAngle, arcAngle);


    }

}
