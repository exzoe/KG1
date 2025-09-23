import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
        mw.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mw.setSize(1200, 750);
        mw.getContentPane().setBackground(Color.BLUE);
        mw.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mw.setVisible(true);
    }
}