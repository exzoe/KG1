import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DrawPanel extends JPanel {
    private double rotationAngle = 0;
    private Windmill windmill;
    private Cloud[] clouds;
    private Flower[] flowers;
    private Tree[] trees;
    private PathToMill path;
    private boolean cloudsInitialized = false;
    private boolean flowersInitialized = false;
    private boolean pathInitialized = false;
    private boolean treesInitialized = false;



    public DrawPanel() {
        Timer timer = new Timer(1, e -> {
            rotationAngle += 0.005;
            repaint();
        });
        timer.start();
    }

    private void initializeClouds(Graphics2D g2d) {
        Random rnd = new Random();
        clouds = new Cloud[6];

        clouds[0] = new Cloud(g2d, rnd.nextInt(250) + 70, 70, 130);
        clouds[1] = new Cloud(g2d, rnd.nextInt(250) + 70, 70, 130);
        clouds[2] = new Cloud(g2d, rnd.nextInt(520) + 250, 100, 130);
        clouds[3] = new Cloud(g2d, rnd.nextInt(520) + 250, 100, 130);
        clouds[4] = new Cloud(g2d, rnd.nextInt(870) + 520, 40, 130);
        clouds[5] = new Cloud(g2d, rnd.nextInt(870) + 200, 40, 110);


        cloudsInitialized = true;
    }

    private void initializeFlowers(Graphics2D g2d) {
        Random rnd = new Random();
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

            flowers[i] = new Flower(g2d, x, y, width, color);
        }

        flowersInitialized = true;
    }

    private void initializeTrees(Graphics2D g2d) {
        Random rnd = new Random();
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
                    trees[i] = new Tree(g2d, x, y, rnd.nextInt(100) + 100);
                    treePositions.add(new Point(x, y));
                    positionFound = true;
                }
            }
        }
        treesInitialized = true;
    }


    @Override
    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        Graphics2D g2d = (Graphics2D) gr;

        g2d.setColor(new Color(135, 206, 235));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Sun sun = new Sun(g2d, 970, -40);
        sun.draw();
        Hill h1 = new Hill(g2d, 70, 800, 1500, 370, new int[]{87, 214, 51});
        Hill h2 = new Hill(g2d, -200, 800, 1200, 300, new int[]{47, 130, 23});
        Hill h3 = new Hill(g2d, -340, 800, 800, 260, new int[]{2, 191, 15});

        h2.drawSmoothHill();
        h3.drawSmoothHill();
        h1.drawSmoothHill();

        Tree t1 = new Tree(g2d, 390, 370, 150);
        t1.drawTree();

        Windmill windmill = new Windmill(g2d, 820, 470, 100);
        windmill.setRotation(rotationAngle);
        windmill.draw();

        if (!treesInitialized) {
            initializeTrees(g2d);
        }
        for (Tree tree : trees) {
            Tree tempTree = new Tree(g2d, tree.getX(), tree.getY(), tree.getHeight());
            tempTree.drawTree();
        }

        AirBalloon airballoon1 = new AirBalloon(g2d, 200, 150, 150, 150);
        airballoon1.draw();

        AirBalloon airballoon2 = new AirBalloon(g2d, 450, 100, 150, 150);
        airballoon2.draw();

        if (!cloudsInitialized) {
            initializeClouds(g2d);
        }

        for (Cloud cloud : clouds) {
            Cloud tempCloud = new Cloud(g2d, cloud.getX(), cloud.getY(), cloud.getWidth());
            tempCloud.drawCloud();
        }

        if (!pathInitialized) {
            path = new PathToMill(g2d, 820, 470, 300, 40);
            pathInitialized = true;
        }

        path.setGraphics(g2d);
        path.drawPath();

        if (!flowersInitialized) {
            initializeFlowers(g2d);
        }

        for (Flower flower : flowers) {
            Flower tempFlower = new Flower(g2d, flower.getX(), flower.getY(), flower.getWidth(), flower.getColor());
            tempFlower.drawFlower();
        }


    }
}