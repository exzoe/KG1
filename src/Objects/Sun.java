package Objects;

import java.awt.*;

public class Sun {
    private final Graphics2D gr;
    private final int x;
    private final int y;

    public Sun(Graphics2D gr, int x, int y) {
        this.gr = gr;
        this.x = x;
        this.y = y;
    }

    public void draw() {
        gr.setColor(Color.yellow);
        gr.fillOval(x, y, 175, 175);

        for (int i = 0; i < 12; i++) {
            double angle = Math.toRadians(i * 30);
            int rayLength = 70;
            int startX = x + 87 + (int)(80 * Math.cos(angle));
            int startY = y + 87 + (int)(80 * Math.sin(angle));
            int endX = startX + (int)(rayLength * Math.cos(angle));
            int endY = startY + (int)(rayLength * Math.sin(angle));

            gr.setStroke(new BasicStroke(8));
            gr.drawLine(startX, startY, endX, endY);
        }
    }
}
