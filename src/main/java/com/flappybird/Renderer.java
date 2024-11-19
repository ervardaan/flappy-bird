package com.flappybird;//same package as the flappy bird class

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel//this class extends jpanel class
{

	private static final long serialVersionUID = 1L;//serial version id is required to keep track of game

	@Override
	protected void paintComponent(Graphics g)//graphics class object collects all the graphics
	{
		super.paintComponent(g);//retaining super for the  class jpanel-method paintcomponent is method of jpanel class

		FlappyBird.flappyBird.repaint(g);//repainting with graphics object G sent
	}
	
}
