package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	private Snake s1; 
	private Food f1; 
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		
		s1= new Snake(); 
		f1= new Food(); 
		
		
	}
	
	public void play() {
		while (s1.isInbounds()) { //game only plays if the snake is in bounds
			int dir = getKeypress(); //calls getKeypress() to check if the player pressed W, A, S, or D
			s1.changeDirection(dir); //tells the snake to turn based on the direction of the key pressed
			s1.move(); //moves the snake forward
			if(s1.eatFood(f1)==true) { //checks to see if the snakes head overlaps with food
				f1=new Food(); //this is where the food respawns
			}
			updateDrawing(); //clears the frame and redraws everything
			StdDraw.pause(50);
			
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
		StdDraw.clear(); //clears the screen 
		StdDraw.text(0.5, 0.5, "Game Over! Final Score: " + s1.getScore()); //game over screen 
		StdDraw.show();

	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1; //up
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2; //down
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3; //left
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4; //right
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear(); 
		StdDraw.setPenColor(Color.white);//clears the previous display
		StdDraw.filledRectangle(0.5, 0.9, 0.5, 0.1);
		
		s1.draw(); 
		f1.draw(); 
		
		StdDraw.setPenColor(Color.black);
		StdDraw.text(0.5, 0.9, "Score: " + s1.getScore());
		
		
		
		StdDraw.show(); 
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
