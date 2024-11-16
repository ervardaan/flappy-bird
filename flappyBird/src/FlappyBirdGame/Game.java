package FlappyBirdGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game implements ActionListener {
    public static Game flappyBird;
    public Rectangle bird;
    public Random r;
    static JFrame frame;
    public final int WIDTH=800,HEIGHT=800;
    public Renderer renderer;
    public ArrayList<Rectangle> columns;

    @Override
    public void actionPerformed(ActionEvent e) {
        renderer.repaint();

    }
    public void paintColumn(Graphics g,Rectangle r)
    {
        g.setColor(Color.green.darker());
        g.fillRect(r.x,r.y,r.width,r.height);
    }


    public Game()
    {
        Timer timer=new Timer(1000,this);

        frame=new JFrame("First frame");
        renderer=new Renderer();
        bird=new Rectangle(WIDTH/2-10,HEIGHT/2-10,20,20);
        frame.add(renderer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setTitle("Flappy Bird Game");
        r=new Random();
        columns=new ArrayList<Rectangle>();
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        timer.start();

    }
    public void addColumn(boolean old)
    {
        int space=100;
        int width=300;
        int height=50+r.nextInt(300);
        if(old) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else{
            columns.add(new Rectangle(columns.get(columns.size()-1).x+600, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size()-1).x, 0, width, HEIGHT - height - space));

        }
    }

    public void repaint(Graphics g)
    {
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        g.setColor(Color.orange);
        g.fillRect(0,HEIGHT-120,WIDTH,120);
        g.setColor(Color.green);
        g.fillRect(0,HEIGHT-120,WIDTH,20);
        g.setColor(Color.red);
        g.fillRect(bird.x,bird.y, bird.width, bird.height);

    }


    public static void main(String[] args)
    {
//        frame.getContentPane().add(BorderLayout.CENTER);
//        frame.pack();
//        frame.setVisible(true);
        flappyBird=new Game();

    }


}
