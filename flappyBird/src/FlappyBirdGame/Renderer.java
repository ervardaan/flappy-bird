package FlappyBirdGame;

import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    public static final long serialVersionUID=1L;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Game.flappyBird.repaint(g);
    }

}
