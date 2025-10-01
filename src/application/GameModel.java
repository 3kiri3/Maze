package application;

/**
 * GameModel Class - Manages the overall game state and logic
 * 
 * This is the main "Model" in our MVC pattern
 * - It coordinates between Maze and Player objects
 * - It enforces game rules (valid moves, win conditions)
 * - It maintains game state (has the game been won?)
 * - It provides a clean interface for the Controller to use
 * 
 * The Model contains business logic but no UI code
 */
public class GameModel {
    
    /**
     * ENUM: Direction - Represents the four possible movement directions
     * 
     * Enums are a special type that represents a fixed set of constants
     * This is better than using integers (0=UP, 1=DOWN, etc.) because:
     * - More readable code
     * - Type safety (can't accidentally pass wrong number)
     * - IDE can auto-complete the options
     */
	public enum Direction { UP, DOWN, LEFT, RIGHT }
	
    // GAME COMPONENTS - The objects that make up our game
	private Maze maze;        // The maze layout and rules
	private Player player;    // The player's position and statistics  
	private boolean gameWon;  // Whether the player has reached the goal
	
    /**
     * CONSTRUCTOR - Sets up a new game
     * 
     * This constructor coordinates the setup of all game components
     * - Creates the maze
     * - Creates the player at the maze's starting position  
     * - Initializes game state
     */
	public GameModel() {
		this.maze = new Maze();  // Create the maze
        
        // Create player at maze's starting position
        // We get the start position from the maze
		this.player = new Player(maze.getStartX(), maze.getStartY());
        
		this.gameWon = false;    // Game starts in "not won" state
	}
	
    /**
     * METHOD: movePlayer() - Attempts to move the player in a given direction
     * 
     * This is the core game logic method. It:
     * 1. Calculates where the player wants to move
     * 2. Checks if that move is legal (using maze rules)
     * 3. Updates player position if move is valid
     * 4. Checks for win condition
     * 
     * @param dir - The direction the player wants to move
     * @return true if the move was successful, false if it was blocked
     * 
     * This method demonstrates:
     * - Input validation (is move legal?)
     * - State updates (change player position)
     * - Rule enforcement (maze boundaries, walls)
     * - Win condition checking
     */
	public boolean movePlayer(Direction dir) {
        // If game is already won, don't allow more moves
		if(gameWon) return false;
		
        // CALCULATE NEW POSITION
        // Start with current position, then modify based on direction
		int newX = player.getX();  // Current x position
		int newY = player.getY();  // Current y position
		
        // MODIFY COORDINATES BASED ON DIRECTION
        // This switch statement converts direction enum to coordinate changes
		switch (dir) {
			case UP:    newY--; break;  // Moving up decreases Y coordinate
			case DOWN:  newY++; break;  // Moving down increases Y coordinate  
			case LEFT:  newX--; break;  // Moving left decreases X coordinate
			case RIGHT: newX++; break;  // Moving right increases X coordinate
		}
        
        // In most computer graphics systems:
        // - (0,0) is at top-left
        // - Y increases downward
        // - X increases rightward
		
        // VALIDATE THE MOVE
        // Ask the maze if this new position is legal
		if(maze.isValidMove(newX, newY)) {
            // MOVE IS VALID - Update player position
			player.moveTo(newX, newY);
			
            // CHECK WIN CONDITION
            // Ask the maze if this new position is the goal
			if(maze.isEnd(newX, newY)) {
				gameWon = true;  // Player has won the game!
			}
            
			return true;  // Move was successful
		}
        
        // MOVE IS INVALID - Don't update anything
		return false;
        
        // This method demonstrates the "Tell Don't Ask" principle
        // - We ask the maze "is this move valid?" instead of checking walls ourselves
        // - We ask the maze "is this the end?" instead of checking coordinates ourselves
        // This keeps the maze rules centralized in the Maze class
	}
	
    /**
     * METHOD: resetGame() - Restarts the game to initial state
     * 
     * Allows players to start over without creating new objects
     * This is more efficient than recreating the entire game
     */
	public void resetGame() {
        // Reset player to starting position and zero moves
		player.reset(maze.getStartX(), maze.getStartY());
        
        // Reset game state
		gameWon = false;
        
        // We don't need to reset the maze because it never changes
        // Only the player position and game state need to be reset
	}
	
    // GETTER METHODS - Provide read-only access to game components
    // These allow other classes (like the View) to read game state
    // but not modify it directly. All modifications should go through proper methods.
    
    /**
     * METHOD: getMaze() - Returns the maze object
     * @return The game's maze
     * 
     * The View needs this to draw the maze layout
     */
	public Maze getMaze() { 
        return maze; 
    }
    
    /**
     * METHOD: getPlayer() - Returns the player object  
     * @return The game's player
     * 
     * The View needs this to draw the player and show statistics
     */
	public Player getPlayer() { 
        return player; 
    }
    
    /**
     * METHOD: isGameWon() - Returns whether the game has been won
     * @return true if player reached the goal, false otherwise
     * 
     */
	public boolean isGameWon() { 
        return gameWon; 
    }
    
    // Notice the pattern in this class:
    // - Public methods that Controllers call (movePlayer, resetGame)
    // - Private game logic (coordinate calculation, validation)
    // - Read-only access to internal state (getter methods)
    // This creates a clean separation between game logic and user interface
}