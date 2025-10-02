import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawPanel extends JPanel {
    private Windmill windmill;
    private Cloud[] clouds;
    private Flower[] flowers;
    private Tree[] trees;
    private PathToMill path;
    private Sun sun;
    private Hill h1, h2, h3;
    private AirBalloon airballoon1, airballoon2;
    private Tree t1;

    public DrawPanel() {
        initializeObjects();

        Timer timer = new Timer(1, e -> {
            windmill.setRotation(windmill.getRotation() + 0.005);
            repaint();
        });
        timer.start();
    }

    private void initializeObjects() {
        Random rnd = new Random();

        // Инициализация всех объектов в конструкторе
        sun = new Sun(970, -40);

        h1 = new Hill(70, 800, 1500, 370, new int[]{87, 214, 51});
        h2 = new Hill(-200, 800, 1200, 300, new int[]{47, 130, 23});
        h3 = new Hill(-340, 800, 800, 260, new int[]{2, 191, 15});

        t1 = new Tree(390, 370, 150);

        windmill = new Windmill(820, 470, 100);

        trees = new Tree[3];
        ArrayList<Point> treePositions = new ArrayList<>();
        int minDistance = 100;

        for (int i = 0; i < trees.length; i++) {
            int x, y;
            boolean positionFound = false;

            while (!positionFound) {
                x = rnd.nextInt(250) + 50;
                y = rnd.nextInt(200) + 450;

                boolean tooClose = false;
                for (Point p : treePositions) {
                    double distance = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
                    if (distance < minDistance) {
                        tooClose = true;
                        break;
                    }
                }

                if (!tooClose) {
                    trees[i] = new Tree(x, y, rnd.nextInt(100) + 100);
                    treePositions.add(new Point(x, y));
                    positionFound = true;
                }
            }
        }

        airballoon1 = new AirBalloon(200, 150, 150, 150);
        airballoon2 = new AirBalloon(450, 100, 150, 150);

        clouds = new Cloud[6];
        clouds[0] = new Cloud(rnd.nextInt(250) + 70, 70, 130);
        clouds[1] = new Cloud(rnd.nextInt(250) + 70, 70, 130);
        clouds[2] = new Cloud(rnd.nextInt(520) + 250, 100, 130);
        clouds[3] = new Cloud(rnd.nextInt(520) + 250, 100, 130);
        clouds[4] = new Cloud(rnd.nextInt(870) + 520, 40, 130);
        clouds[5] = new Cloud(rnd.nextInt(870) + 200, 40, 110);

        path = new PathToMill(820, 470, 300, 40);

        flowers = new Flower[12];
        Polygon roadPolygon = path.getRoadPolygon();
        final int MIN_DISTANCE = 50;

        int[][] xRanges = {
                {800, 400}, {800, 400}, {800, 400}, {800, 400}, {800, 400}, {800, 400},
                {800, 400}, {800, 400}, {800, 400}, {800, 400}, {800, 400}, {800, 400}, {800, 400}
        };
        int[][] colors = {
                {49, 103, 189}, {223, 228, 237}, {190, 120, 222}, {64, 152, 214}, {237, 194, 74},
                {235, 77, 66}, {49, 103, 189}, {223, 228, 237}, {190, 120, 222}, {64, 152, 214},
                {237, 194, 74}, {235, 77, 66}, {235, 77, 66}
        };

        for (int i = 0; i < flowers.length; i++) {
            int x, y, width;
            int[] range = xRanges[i];
            int[] color = colors[i];
            boolean isTooClose;
            boolean isOnRoad;

            do {
                x = rnd.nextInt(range[0]) + range[1];
                y = rnd.nextInt(200) + 450;
                width = rnd.nextInt(15) + 15;

                int stemBaseX = x + Flower.getVal(width, 0.47);
                int stemBaseY = y + Flower.getVal(width, 0.9) + Flower.getVal(width, 3);
                isOnRoad = roadPolygon.contains(stemBaseX, stemBaseY);

                isTooClose = false;
                for (int j = 0; j < i; j++) {
                    double distance = Math.sqrt(Math.pow(x - flowers[j].getX(), 2) + Math.pow(y - flowers[j].getY(), 2));
                    if (distance < MIN_DISTANCE) {
                        isTooClose = true;
                        break;
                    }
                }
            } while (isOnRoad || isTooClose);

            flowers[i] = new Flower(x, y, width, color);
        }
    }

    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2d = (Graphics2D) gr;

        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        sun.draw(g2d);

        h2.drawSmoothHill(g2d);
        h3.drawSmoothHill(g2d);
        h1.drawSmoothHill(g2d);

        t1.drawTree(g2d);

        for (Tree tree : trees) {
            tree.drawTree(g2d);
        }

        windmill.draw(g2d);

        airballoon1.draw(g2d);
        airballoon2.draw(g2d);

        for (Cloud cloud : clouds) {
            cloud.drawCloud(g2d);
        }

        path.drawPath(g2d);

        for (Flower flower : flowers) {
            flower.drawFlower(g2d);
        }
    }
}