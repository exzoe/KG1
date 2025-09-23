package Objects;

import java.awt.*;

public class PathToMill {
    private Graphics2D gr;
    private final int x;
    private final int y;
    private final int roadWidth;
    private final int roadHeight;

    public PathToMill(Graphics2D gr, int x, int y, int roadWidth, int roadHeight) {
        this.gr = gr;
        this.x = x;
        this.y = y;
        this.roadWidth = roadWidth;
        this.roadHeight = roadHeight;
    }


    public void drawPath() {
        int pointsCount = 40;
        int[] xPoints = new int[pointsCount * 2];
        int[] yPoints = new int[pointsCount * 2];

        for (int i = 0; i < pointsCount; i++) {
            double progress = (double)i / (pointsCount - 1);

            // Позиция по Y (вертикальное движение)
            int currentY = y + (int)(roadWidth * progress);

            int waveAmplitude = 20;
            double waveFrequency = 3.0;
            int centerX = x + (int)(waveAmplitude * Math.sin(progress * waveFrequency * Math.PI));

            double widthFactor = 1.0 + (progress * 6.5);
            int currentRoadHeight = (int)(roadHeight * widthFactor);

            // левая точка
            xPoints[i] = centerX - currentRoadHeight / 2;
            yPoints[i] = currentY;

            // правая точка (зеркально левой)
            xPoints[pointsCount * 2 - 1 - i] = centerX + currentRoadHeight / 2;
            yPoints[pointsCount * 2 - 1 - i] = currentY;
        }

        gr.setColor(Color.GRAY);
        gr.fillPolygon(xPoints, yPoints, pointsCount * 2);
    }
}