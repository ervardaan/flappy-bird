package com.flappybird;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.awt.Graphics;

public class FlappyBirdTest {
    
    private FlappyBird flappyBird;
    
    @BeforeEach
    public void setUp() {
        // Create an instance of FlappyBird before each test
        flappyBird = new FlappyBird();
    }

    // Test if the main class initializes without errors
    @Test
    public void testMainInitialization() {
        assertDoesNotThrow(() -> FlappyBird.main(new String[]{}));
    }
    
    // Test if the score is correctly initialized to 0
    @Test
    public void testInitialScore() {
        assertEquals(0, flappyBird.getScore());
    }

    // Test if the bird's position is set correctly
    @Test
    public void testBirdPosition() {
        int initialYPosition = flappyBird.getBird().getY();
        assertEquals(100, initialYPosition);  // Assuming initial Y position is 100
    }
    
    // Test the bird's jump method (if implemented)
    @Test
    public void testBirdJump() {
        int initialYPosition = flappyBird.getBird().getY();
        flappyBird.jump();
        assertTrue(flappyBird.getBird().getY() < initialYPosition, "Bird should move up on jump");
    }

    // Test if the game state changes after collision (e.g., when the bird hits a pipe)
    @Test
    public void testGameOverAfterCollision() {
        // Mocking the bird and pipe interaction
        flappyBird.getBird().setY(250);  // Bird at a position where it collides with pipe
        flappyBird.checkCollisions();    // Check for collision
        
        // After collision, the game should be in a "Game Over" state
        assertTrue(flappyBird.isGameOver(), "Game should be over after collision");
    }
    
    // Test if the score increases after the bird successfully passes a pipe
    @Test
    public void testScoreIncrement() {
        int initialScore = flappyBird.getScore();
        flappyBird.updateScore();
        assertEquals(initialScore + 1, flappyBird.getScore(), "Score should increment by 1");
    }

    // Test rendering method (mocking Graphics object)
    @Test
    public void testRenderMethod() {
        Graphics graphicsMock = Mockito.mock(Graphics.class);
        assertDoesNotThrow(() -> flappyBird.render(graphicsMock));  // Ensure render doesn't throw an exception
    }

    // Test the bird's speed or falling rate (in case of gravity)
    @Test
    public void testBirdGravityEffect() {
        int initialSpeed = flappyBird.getBird().getSpeed();  // Assuming there's a speed property
        flappyBird.applyGravity();
        assertTrue(flappyBird.getBird().getSpeed() > initialSpeed, "Bird's speed should increase due to gravity");
    }
}
