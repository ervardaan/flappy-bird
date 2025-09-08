package com.flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

public class FlappyBird implements ActionListener, MouseListener, KeyListener//class implements the actions interfaces
{
	//mouse listener is used to listen to mouse clicks and events
	//key listener is used to listen to presses of the key events
	//action listener is broad clas which looks for any changes "events"

	public static FlappyBird flappyBird;//creating a static instance of flappy bird class

	public final int WIDTH = 800, HEIGHT = 800;

	public Renderer renderer;//empty object right now-initialized in constructor

	public Rectangle bird;//creating a rectangle for representing a bird

	public ArrayList<Rectangle> columns;//list of all columns of the rectangles

	public int ticks, yMotion, score;//motion of bird and ticks

	//score is kept to count success

	public boolean gameOver, started;//booleans to keep track of starting and ending of the game

	public Random rand;

	public FlappyBird()
	{
		JFrame jframe = new JFrame();//creating a jframe class's object
		Timer timer = new Timer(20, this);//creating a timer object

		renderer = new Renderer();//creating object of renderer class
		rand = new Random();//a random class's object

		jframe.add(renderer);//adding renderer class's object to the gamee
		jframe.setTitle("Flappy Bird");//tiitle of the game
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing of jframe window
		jframe.setSize(WIDTH, HEIGHT);//setting size of jframe window
		jframe.addMouseListener(this);//adding a mouse listener
		jframe.addKeyListener(this);//adding a key pressed listener method
		jframe.setResizable(false);//adding resizable method and setting it to false
		jframe.setVisible(true);//setting frame to visible=true

		bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);//instantiating a rectangle object for the bird
		columns = new ArrayList<Rectangle>();//instantiating the list of columns we made earlier from rectangles

		addColumn(true);//adding 4 columns to get started with the game
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();//starting timer
	}

	public void addColumn(boolean start)//this method adds columns made up of rectangles
	{
		//if it is starting column then do this or add/append to the list of columns at the very end
		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);//giving space,height,and width

		if (start)//if game has started
		{
			columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));//creating a new rectangle object and adding it to columns list
			columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
		}
		else
		{//if game hasn't started
			columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));//pick last column's x coordinate and other details
			columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
		}
	}

	public void paintColumn(Graphics g, Rectangle column)
	{
		g.setColor(Color.green.darker());//darker maks the green normal color more dark
		g.fillRect(column.x, column.y, column.width, column.height);
	}//filing rectangle after giving color

	public void jump()
	{
		if (gameOver)
		{//if game has ended
			bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);//creating the bird
			columns.clear();//remove all columns when game is over
			yMotion = 0;
			score = 0;//when game is over then we set score to 0 for next time

			addColumn(true);//when game is over, we keep adding columns 4 times and more
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;//now changing game over to false
		}

		if (!started)
		{//if game hasn't started-set started to true
			started = true;
		}
		else if (!gameOver)//when game is not over and y motion is not 0
		{
			if (yMotion > 0)
			{//when game isn't over yet-check ymotion if it is greater than 0
				yMotion = 0;
			}

			yMotion -= 10;//decrementing y motion's value
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		int speed = 10;//setting speed to 10

		ticks++;//incrementing ticks

		if (started)//if game has started
		{
			for (int i = 0; i < columns.size(); i++)//getting all columns
			{
				Rectangle column = columns.get(i);//getting a column
//increasing and changing x coordinate
				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15)
			{
				//if ticks is even and motion of bird is less than 15
				yMotion += 2;//then increase motion of bird by 2(again even)
			}

			for (int i = 0; i < columns.size(); i++)//go through each column
			{
				Rectangle column = columns.get(i);

				if (column.x + column.width < 0)
				{
					columns.remove(column);//removing column

					if (column.y == 0)//if it is top column-then add other column set to false
					{
						addColumn(false);
					}
				}
			}

			bird.y += yMotion;//increasing motion of bird and changing y coordinate of the bird

			for (Rectangle column : columns)//for each rectangle in the columns list(getting each column)
			{
				//get each column
				if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - 10 && bird.x + bird.width / 2 < column.x + column.width / 2 + 10)
				{
					score++;//when the bird is in center of the column-then we increase the score as one other column has been passed
				}

				if (column.intersects(bird))//when column intersects the bird i.e the bird strikes a column-then we stop the game
				{
					gameOver = true;

					if (bird.x <= column.x)//now that game is over keep changing the coordinate x of the bird
					{
						bird.x = column.x - bird.width;

					}
					else
					{//if y coordinate of the bird is good to go
						if (column.y != 0)//if is is not top column
						{
							bird.y = column.y - bird.height;//bird's y is changed and decreased
						}
						else if (bird.y < column.height)//when bird's height is less than column's height
						{
							bird.y = column.height;//set bird's coordinate to column's height
						}
					}
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0)
			{
				gameOver = true;//when bird fell low
			}

			if (bird.y + yMotion >= HEIGHT - 120)
			{
				bird.y = HEIGHT - 120 - bird.height;
				gameOver = true;//when bird goes up-set game to ended
			}
		}

		renderer.repaint();//repainting using renderer object
	}

	public void repaint(Graphics g)
	{
		g.setColor(Color.cyan);//filing color cyan into graphics object G
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.orange);//orange is for the plains-first we fill color and then give a rectangle by filling it
		g.fillRect(0, HEIGHT - 120, WIDTH, 120);

		g.setColor(Color.green);//green is for the pipes
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.red);//red is for the bird
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		for (Rectangle column : columns)
		{
			paintColumn(g, column);//painting each column from the list of columns
		}

		g.setColor(Color.white);
		g.setFont(new Font("Arial", 1, 100));

		if (!started)
		{//whe game has not started, we represent the string "click to play"
			g.drawString("Click to start!", 75, HEIGHT / 2 - 50);
		}

		if (gameOver)//if game is over
		{
			g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
		}

		if (!gameOver && started)//if game is not yet over and game has started
		{
			//then keep changing value of score as score gets updated
			g.drawString(String.valueOf(score), WIDTH / 2 - 25, 100);//we represent the score at a particular width and height
		}
	}

	public static void main(String[] args)
	{
		flappyBird = new FlappyBird();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		jump();
	}//when mouse is pressed and clicked, then also the bird will jump

	@Override
	public void keyReleased(KeyEvent e)//key event is the name of the object class which gathers the key pressing event and the type of the key actually pressed
	{
		if (e.getKeyCode() == KeyEvent.VK_SPACE)//if the space key is pressed then action is done so do something and accept this action
		{
			jump();//jump is space is pressed
		}
	}
	//below are the placeholders for the functions for the interfaces we implement
	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}

	@Override
	public void keyPressed(KeyEvent e)
	{

	}

	    // --- Getters for testing ---
    public int getScore() {
        return score;
    }

    public Rectangle getBird() {
        return bird;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    // --- Methods for testing ---
    public void checkCollisions() {
        for (Rectangle column : columns) {
            if (column.intersects(bird)) {
                gameOver = true;
                return;
            }
        }
        if (bird.y > HEIGHT - 120 || bird.y < 0) {
            gameOver = true;
        }
    }

    public void updateScore() {
        score++;
    }

    // Wrapper for repaint logic (since test uses render())
    public void render(Graphics g) {
        repaint(g);
    }

    public void applyGravity() {
        if (yMotion < 15) {
            yMotion += 2;
        }
        bird.y += yMotion;
    }

    // Helper for tests: expose vertical motion
    public int getBirdSpeed() {
        return yMotion;
    }

	public void setBirdY(int y) {
    	bird.y = y;
	}


}
