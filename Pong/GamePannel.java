package Pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePannel extends JPanel implements Runnable {
 
    // screen resolution settings
    public final int screenWidth = 800;
    public final int screenHeight = 600;

    public final int playHeight = 50; // extra room to display title and score

    int FPS = 60;

    Thread gameThread;
    Elements elements = new Elements(this);
    Ball ball = new Ball(this);

    // game pannel consructor
    GamePannel () {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // game loop setup
    public void run() {
        
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        elements.draw(g2);
        ball.draw(g2);

        g2.dispose();
    }

    public void update() {

        ball.update();
    }
}
