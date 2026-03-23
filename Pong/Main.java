package Pong;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("PONG");

        GamePannel pannel = new GamePannel();
        window.add(pannel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        pannel.startGameThread();
    }
}
