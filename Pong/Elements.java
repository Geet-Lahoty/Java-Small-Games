package Pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Elements {
    
    GamePannel gp;

    // defining constructor
    public Elements(GamePannel gp) {
        this.gp = gp;
    } 

    public void draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        g2.drawLine(gp.screenWidth / 2, gp.playHeight, gp.screenWidth / 2, gp.screenHeight);
        g2.drawLine(0, gp.playHeight, gp.screenWidth, gp.playHeight);

        int radius = 120;
        g2.drawOval((gp.screenWidth / 2) - radius, (gp.screenHeight / 2) + (gp.playHeight / 2) - radius, 2*radius, 2*radius);

        // g2.drawString("PONG", gp.screenWidth / 2, gp.playHeight);
    }
}   
