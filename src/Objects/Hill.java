package Objects;

import java.awt.*;

public class Hill {
    private Graphics2D gr;
    private int x;
    private int y;
    private int width;
    private int height;
    private int[] color;

    public Hill(Graphics2D gr, int x, int y, int width, int height, int[] color) {
        this.gr = gr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void drawSmoothHill() {
        int pointsCount = 40;
        int[] xPoints = new int[pointsCount];
        int[] yPoints = new int[pointsCount];

        for (int i = 0; i < pointsCount; i++) {
            double progress = (double)i / (pointsCount - 1);
            xPoints[i] = x + (int)(width * progress);
            yPoints[i] = y - (int)(height * Math.sin(progress * Math.PI));
        }

        gr.setColor(new Color(color[0], color[1], color[2]));
        gr.fillPolygon(xPoints, yPoints, pointsCount);
    }


}
