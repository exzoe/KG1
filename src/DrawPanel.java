import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DrawPanel extends JPanel {
    private double rotationAngle = 0;
    private Windmill windmill;
    private Cloud[] clouds;
    private Flower[] flowers;
    private boolean cloudsInitialized = false;
    private boolean flowersInitialized = false;


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
        flowers = new Flower[13];

        flowers[0] = new Flower(g2d, rnd.nextInt(250) + 70, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{49, 103, 189});
        flowers[1] = new Flower(g2d, rnd.nextInt(250) + 70, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{223, 228, 237});
        flowers[2] = new Flower(g2d, rnd.nextInt(520) + 250, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{190, 120, 222});
        flowers[3] = new Flower(g2d, rnd.nextInt(520) + 250, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{64, 152, 214});
        flowers[4] = new Flower(g2d, rnd.nextInt(870) + 520, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{237, 194, 74});
        flowers[5] = new Flower(g2d, rnd.nextInt(870) + 200, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{235, 77, 66});
        flowers[6] = new Flower(g2d, rnd.nextInt(250) + 70, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{49, 103, 189});
        flowers[7] = new Flower(g2d, rnd.nextInt(250) + 70, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{223, 228, 237});
        flowers[8] = new Flower(g2d, rnd.nextInt(520) + 250, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{190, 120, 222});
        flowers[9] = new Flower(g2d, rnd.nextInt(520) + 250, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{64, 152, 214});
        flowers[10] = new Flower(g2d, rnd.nextInt(870) + 520, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{237, 194, 74});
        flowers[11] = new Flower(g2d, rnd.nextInt(870) + 200, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{235, 77, 66});
        flowers[12] = new Flower(g2d, rnd.nextInt(870) + 200, rnd.nextInt(100) + 500, rnd.nextInt(15) + 15, new int[]{235, 77, 66});

        flowersInitialized = true;
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
        PathToMill path = new PathToMill(g2d, 820, 470, 300, 40);
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