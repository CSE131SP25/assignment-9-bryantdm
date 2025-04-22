package assignment9;

import java.util.LinkedList;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	private int score; 
	
	public Snake() {
		score = 0; 
		deltaX = 0;
		deltaY = 0;
		segments = new LinkedList <>(); 
		BodySegment b1 = new BodySegment(deltaX, deltaX, SEGMENT_SIZE); 
		segments.add(b1); 
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE; //makes the snake move upward
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE; 
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		for (int i = segments.size() -1; i > 0; i--) { //this loop goes backwards through the snake body starting from the tail bc each segment needs to copy the position of the one in front of it but i>0 stops before the head bc the head moves differently 
			BodySegment current = segments.get(i);  //gets the current segment
			BodySegment previous = segments.get(i-1); //gets the segment right before the current one
			
			current.setX(previous.getX()); //sets the current X position the same as the one before it
			current.setY(previous.getY()); //sets the current Y position the same as the one before it
		}
		BodySegment head = segments.get(0); //snake's head
		head.setX(head.getX() + deltaX); //moves the head in the X direction depending on the key pressed
		head.setY(head.getY() + deltaY); //moves the head in the Y direction depending on the key pressed
		
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (int i =0; i < segments.size(); i++) {
			segments.get(i).draw();
		}
	}
	public int getScore() { 
		return score; 
	}
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) { //method that takes a Food object and returns true if the snake eats food and false otherwise
		BodySegment head = segments.get(0); //this is the head of snake since its the first element in the linked list
		double distance = Math.sqrt((Math.pow(f.getX()- head.getX(), 2)) + (Math.pow(f.getY() - head.getY(), 2))); //calculates the distance between the head of the snake and the food using the distance formula
		if(distance < 2*SEGMENT_SIZE) { //checks to see if the distance is less than twice the size of the segment bc if they are closer than two radii then theyre touching
			BodySegment tail = segments.get(segments.size() - 1); //gets the tail of the snake, last in the list
			BodySegment newSegment = new BodySegment(tail.getX(), tail.getY(), SEGMENT_SIZE); //creates a new body segment at the position of the tail
			segments.add(newSegment); //adds new segment
			score++; 
			return true; 
		}else {
			return false; //if the food was not eaten
		}
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
	    BodySegment head = segments.get(0); //gets the head
	    double x = head.getX();
	    double y = head.getY();
	    
	    // Check left, right, bottom, and top boundaries
	    if (x < 0 || x > 1 || y < 0 || y > 1) {
	        return false; //means snake is not in bounds
	    }
	    return true;
	}

	}

