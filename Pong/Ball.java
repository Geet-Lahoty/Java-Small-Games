package Pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ball {
    
    GamePannel gp;
    float posX, posY, speedX, speedY;
    int radius = 30;

    public Ball (GamePannel gp) {
        this.gp = gp;
        this.posX = (gp.screenWidth / 2) - (radius / 2);
        this.posY = (gp.screenHeight / 2) + (gp.playHeight / 2) - (radius / 2);
        this.speedX = 4;
        this.speedY = -4;
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.red);
        g2.fillOval((int)this.posX, (int)this.posY, radius, radius);
    }

    public void update() {

        if (gp.gameThread != null) {
            this.posX += this.speedX;
            this.posY += this.speedY;
        }

        if (this.posY <= gp.playHeight || this.posY >= gp.screenHeight - radius) {
            this.speedY = -this.speedY;
        }
    }
}
