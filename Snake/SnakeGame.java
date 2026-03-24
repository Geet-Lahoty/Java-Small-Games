import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

enum Direction {
    UP, DOWN, LEFT, RIGHT
}

class GamePanel extends JPanel implements ActionListener{

    static final int Cell_Size = 20;
    static final int rows = 30;
    static final int columns = 30;
    Direction direction = Direction.RIGHT;
    Point food;
    int score = 0;
    boolean gameover = false;

    Timer timer;

    ArrayList<Point> Snake = new ArrayList<>();

    GamePanel()
    {
        Snake.add(new Point(15, 15));
        Snake.add(new Point(14, 15));
        Snake.add(new Point(13, 15));

        spawnFood();

        setBackground(new Color(63, 155, 11));
        setFocusable(true);

        timer = new Timer(150, this);
        timer.start();

        addKeyListener(new KeyAdapter(){

            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyCode())
                {
                    case KeyEvent.VK_UP -> {
                        if (direction != Direction.DOWN)
                        {
                            direction = Direction.UP;
                        }
                    }

                    case KeyEvent.VK_DOWN -> {
                        if (direction != Direction.UP)
                        {
                            direction = Direction.DOWN;
                        }
                    }

                    case KeyEvent.VK_RIGHT -> {
                        if (direction != Direction.LEFT)
                        {
                            direction = Direction.RIGHT;
                        }
                    }

                    case KeyEvent.VK_LEFT -> {
                        if (direction != Direction.RIGHT)
                        {
                            direction = Direction.LEFT;
                        }
                    }

                    case KeyEvent.VK_R -> {
                        if (gameover)
                        {
                            Snake.clear();
                            Snake.add(new Point(15, 15));
                            Snake.add(new Point(14, 15));
                            Snake.add(new Point(13, 15));

                            direction = Direction.RIGHT;
                            score = 0;
                            gameover = false;
                            spawnFood();
                            timer.start();
                        }
                    }
                }
            }
        }
        );
    }

    void spawnFood() {
        while (true)
        {
            int x = (int)(Math.random() * columns);
            int y = (int)(Math.random() * rows); 

            Point p = new Point(x, y);

            if (!Snake.contains(p))
            {
                food = p;
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        movesnake();
        repaint();
    }
    

    void movesnake()
    {
        if (gameover) return;

        Point head = Snake.get(0);
        Point newHead = new Point(head);

        switch (direction) {
            case UP -> newHead.y--;
            case DOWN -> newHead.y++;
            case RIGHT -> newHead.x++;
            case LEFT -> newHead.x--;
        }

        if (checkCollision(newHead))
        {
            gameover = true;
            timer.stop();
            repaint();
            return;
        }
         
        Snake.add(0, newHead);

        if (newHead.equals(food))
        {
            score++;
            spawnFood();
        }
        else
        {
            Snake.remove(Snake.size() - 1);
        }
    }

    boolean checkCollision(Point head)
    {
        if (head.x < 0 || head.x >= columns || head.y < 0 || head.y >= rows)
        {
            return true;
        }

        for (int i=0; i<Snake.size(); i++)
        {
            if (head.equals(Snake.get(i)))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.RED);
        g.fillOval(food.x * Cell_Size, food.y * Cell_Size, Cell_Size, Cell_Size);

        g.setColor(new Color(100, 120, 255));
        Point head = Snake.get(0);
        g.fillRect(head.x * Cell_Size, head.y * Cell_Size, Cell_Size, Cell_Size);

        g.setColor(new Color(0, 120, 212));
        for (int i=1; i<Snake.size(); i++)
        {
            Point p = Snake.get(i);
            int x = p.x * Cell_Size;
            int y = p.y * Cell_Size;
            g.fillRect(x, y, Cell_Size, Cell_Size);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, Cell_Size));
        g.drawString("Score: " + score, 10, 15);

        if (gameover)
        {
            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 180, 300);

            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Press R to restart", 220, 340);
        }
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(columns * Cell_Size, rows * Cell_Size);
    }
}

public class SnakeGame {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Snake");
        GamePanel panel = new GamePanel();

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}