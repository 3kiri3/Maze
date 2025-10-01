package application;

/**
 * Player Class - Represents the player character in the maze game
 * 
 * This is another "Model" class in MVC pattern
 * - It stores the player's state (position, move count)
 * - It provides methods to change that state
 * - It doesn't handle display or user input
 */
public class Player {
    
    // INSTANCE VARIABLES - The data that defines a player's current state
	private int x, y;      // Current position coordinates in the maze
	private int moves;     // Count of how many moves the player has made
	
    /**
     * CONSTRUCTOR - Creates a new player at a specific starting position
     * 
     * @param startX - Initial x coordinate
     * @param startY - Initial y coordinate
     */
	public Player(int startX, int startY) {
		this.x = startX;      // Set initial x position
		this.y = startY;      // Set initial y position
		this.moves = 0;       // Start with zero moves
        
        // "this." clarifies we're referring to instance variables
        // not the parameters (which have the same names)
	}
	
    /**
     * METHOD: moveTo() - Moves the player to a new position
     * 
     * Updates player position AND increments the move counter
     * 
     * @param newX - The x coordinate to move to
     * @param newY - The y coordinate to move to
     * 
     * This method does TWO things:
     * 1. Updates position
     * 2. Tracks move count
     */
	public void moveTo(int newX, int newY) {
		this.x = newX;     // Update x position
		this.y = newY;     // Update y position
		this.moves++;      // Increment move counter
        
        // ++ operator is shorthand for: this.moves = this.moves + 1;
	}
	
    /**
     * METHOD: reset() - Puts the player back to starting position with zero moves
     * 
     * Allows the game to be restarted without creating a new Player object
     * 
     * @param startX - The x coordinate to reset to
     * @param startY - The y coordinate to reset to
     */
	public void reset(int startX, int startY) {
		this.x = startX;      // Reset to starting x position
		this.y = startY;      // Reset to starting y position
		this.moves = 0;       // Reset move counter to zero
        
        // This method restores the player to initial state
        // as if we just created a new Player object
	}
	
    // GETTER METHODS - Provide read-only access to private data
    // These follow the "encapsulation" principle
    // Other classes can read player data but can't change it directly
    
    /**
     * METHOD: getX() - Returns the player's current x coordinate
     * @return Current x position
     */
	public int getX() { 
        return x; 
    }
    
    /**
     * METHOD: getY() - Returns the player's current y coordinate  
     * @return Current y position
     */
	public int getY() { 
        return y; 
    }
    
    /**
     * METHOD: getMoves() - Returns the number of moves the player has made
     * @return Total move count
     */
	public int getMoves() { 
        return moves; 
    }
    
    // We don't have "setX()" or "setY()" methods
    // This is intentional - we want moves to go through moveTo() so the counter updates
    // This is an example of controlling HOW other classes interact with our data
}