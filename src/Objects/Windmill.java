package Objects;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Windmill {
    private final Graphics2D g;
    private final int x;
    private final int y;
    private final int size;
    private double rotation;

    public Windmill(Graphics2D g, int x, int y, int size) {
        this.g = g;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rotation = 0;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public void draw() {
        drawTower();
        drawWindow();
        drawDoor();
        drawRoof();
        drawBlades();

    }

    private int getVal(int value, double cnt) {
        return (int)(value * cnt);
    }

    private void drawTower() {
        g.setColor(new Color(189, 129, 70));

        int[] xPoints = {
                x - size,
                x + size,
                x + getVal(size,0.8),
                x - getVal(size,0.8)
        };

        int[] yPoints = {
                y,
                y,
                y - getVal(size,2.5),
                y - getVal(size,2.5)
        };
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawRoof() {
        g.setColor(new Color(222, 71, 60));

        int roofBaseY = y - getVal(size,2.5);
        int roofWidth = getVal(size,2.0);
        int roofHeight = getVal(size,1.2);

        int[] xPoints = {
                x - roofWidth / 2,     // левая точка основания
                x,                     // центр (вершина крыши)
                x + roofWidth / 2      // правая точка основания
        };

        int[] yPoints = {
                roofBaseY,             // основание крыши (низ)
                roofBaseY - roofHeight, // вершина крыши (верх)
                roofBaseY              // основание крыши (низ)
        };

        g.fillPolygon(xPoints, yPoints, 3);
        g.setColor(new Color(180, 50, 40));
    }

    private void drawWindow(){
        g.setColor(new Color(200, 230, 255));
        int windowSize = getVal(size, 0.4);
        int windowX = x - windowSize/2;
        int windowY = y - getVal(size, 1.7) - windowSize/2;
        g.fillRect(windowX, windowY, windowSize, windowSize);

        g.setColor(new Color(100, 70, 40));
        g.drawRect(windowX, windowY, windowSize, windowSize);
        g.drawLine(x, windowY, x, windowY + windowSize);
        g.drawLine(windowX, y - getVal(size, 1.7), windowX + windowSize, y - getVal(size, 1.7));
    }

    private void drawDoor(){
        g.setColor(new Color(99, 86, 66));
        int doorWidth = getVal(size, 0.4);
        int doorHeight = getVal(size, 0.7);
        int doorX = x - doorWidth/2;
        int doorY = y - doorHeight;
        g.fillRect(doorX, doorY, doorWidth, doorHeight);

        g.setColor(new Color(255, 255, 0));
        g.fillOval(x + doorWidth/4, y - doorHeight/2, getVal(size,0.1), getVal(size,0.1));
    }

    private void drawBlades() {
        AffineTransform oldTransform = g.getTransform();

        int centerX = x;
        int centerY = y - getVal(size, 2.5);

        g.translate(centerX, centerY);
        g.rotate(rotation);


        for (int i = 0; i < 4; i++) {
            int totalLength = getVal(size, 1.8);

            g.setColor(new Color(69, 58, 58));
            int thinPartLength = totalLength * 3 / 10;
            int thinWidth = getVal(size, 0.1);
            g.fillRect(-thinWidth/2, -thinWidth/2, thinPartLength, thinWidth);

            g.setColor(new Color(213, 220, 9));

            int thickPartLength = totalLength - thinPartLength;
            int thickWidth = getVal(size, 0.3);
            g.fillRect(-thickWidth/2 + thinPartLength, -thickWidth/2,
                    thickPartLength, thickWidth);
            g.setColor(new Color(180, 170, 50));
            int stripeCount = 3;
            int stripeSpacing = thickPartLength / (stripeCount + 1);

            for (int j = 1; j <= stripeCount; j++) {
                int stripePosition = thinPartLength + (j * stripeSpacing);
                int stripeWidth = getVal(size, 0.05);
                g.fillRect(-thickWidth/2 + stripePosition - stripeWidth/2,
                        -thickWidth/2 + thickWidth/4,
                        stripeWidth,
                        thickWidth/2);
            }
            g.rotate(Math.PI / 2);
        }

        g.setColor(new Color(141, 143, 74));
        int centerSize = getVal(size, 0.4);
        g.fillOval(-centerSize/2, -centerSize/2, centerSize, centerSize);

        g.setTransform(oldTransform);
    }
}