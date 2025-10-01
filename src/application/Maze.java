package application;

/**
 * Maze Class - Represents the game maze and its rules
 * 
 * This is a "Model" class in MVC pattern
 * - It holds the data (maze layout)
 * - It knows the rules (what moves are valid)
 * - It doesn't handle display or user input
 */
public class Maze {
	
    // CONSTANTS - These numbers represent different types of cells in our maze
    // Using constants makes code more readable than magic numbers
    // Instead of "if (maze[y][x] == 1)" we can write "if (maze[y][x] == WALL)"
	public static final int EMPTY = 0;  // Empty space - player can move here
	public static final int WALL = 1;   // Wall - player cannot move here
	public static final int START = 2;  // Starting position for the player
	public static final int END = 3;    // Goal position - win when player reaches here
	
	// INSTANCE VARIABLES - Data that each Maze object stores
	private int [][] maze;        // 2D array representing the maze layout
	private int width, height;    // Dimensions of the maze
	private int startX, startY;   // Coordinates of the starting position
	private int endX, endY;       // Coordinates of the goal position
	
	/**
     * CONSTRUCTOR - Creates a new maze when Maze object is created
     *
     * This constructor does three things:
     * 1. Defines the maze layout (hardcoded for simplicity)
     * 2. Calculates the maze dimensions
     * 3. Finds the special positions (start and end)
     */
	public Maze() {
        // DEFINE MAZE LAYOUT
        // This is a 2D array (array of arrays)
        // Each number represents a different type of cell
		this.maze = new int[][] {
		    // 30 colonnes par ligne, 30 lignes au total
		    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		    {1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1},
		    {1,0,1,0,0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,1,0,0,0,0,0,1,0,1,0,1},
		    {1,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,0,1,0,1,0,1},
		    {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1},
		    {1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,0,1,1,0,1},
		    {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1},
		    {1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1},
		    {1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,1},
		    {1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1,1,1},
		    {1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1},
		    {1,0,1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,1},
		    {1,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,1},
		    {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,0,1,0,1},
		    {1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1},
		    {1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,0,1},
		    {1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,1},
		    {1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,1,1,1,1,1,1,1,0,1,0,1},
		    {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1},
		    {1,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1},
		    {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1},
		    {1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,1,1,0,1,1,1},
		    {1,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1},
		    {1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1},
		    {1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1},
		    {1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1,1,0,1},
		    {1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
		    {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1},
		    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};

        
        // CALCULATE DIMENSIONS
        // .length gives us the number of elements in an array
		this.height = maze.length;        // Number of rows
		this.width = maze[0].length;      // Number of columns (length of first row)
		
		// FIND SPECIAL POSITIONS
        // We need to know where START and END are located for game logic
		findSpecialPosition();
	}
	
	/**
     * METHOD: findSpecialPosition() - Locates START and END positions in the maze
     * 
     * Searches through the entire maze to find where START(2) and END(3) are located
     * This is called once during construction to set up startX, startY, endX, endY
     * 
     * This is a nested loop - we check every cell in the 2D array
     */
	private void findSpecialPosition() {
        // NESTED LOOP - Go through every cell in the maze
        // Outer loop: each row (y coordinate)
		for(int y = 0; y < height; y++) {
            // Inner loop: each column in this row (x coordinate)
			for(int x = 0; x < width; x++) {
                // CHECK WHAT TYPE OF CELL THIS IS
				if(maze[y][x] == START) {
                    // Found the starting position
					startX = x;  // Store x coordinate
					startY = y;  // Store y coordinate
                    
				} else if(maze[y][x] == END){
                    // Found the goal position
					endX = x;    // Store x coordinate
					endY = y;    // Store y coordinate
				}
			}
		}
	}
	
	/**
     * METHOD: isValidMove() - Checks if a player can move to a specific position
     * 
     * This enforces the game rules - where can the player go?
     * 
     * @param x - The x coordinate to check
     * @param y - The y coordinate to check
     * @return true if the move is allowed, false if not
     * 
     * This method prevents the player from:
     * - Moving outside the maze boundaries
     * - Moving through walls
     */
	public boolean isValidMove(int x, int y) {
        // CHECK BOUNDARIES AND WALL STATUS
        // All conditions must be true (&&) for move to be valid:
		return x >= 0 &&              // Not too far left
               x < width &&           // Not too far right  
			   y >= 0 &&              // Not too far up
               y < height &&          // Not too far down
			   maze[y][x] != WALL;    // Not trying to move into a wall
        
        // If any condition is false, the entire expression is false
        // Player can move to EMPTY(0), START(2), or END(3) cells, but not WALL(1)
	}
	
	/**
     * METHOD: isEnd() - Checks if player has reached the goal
     * 
     * Determines win condition for the game
     * 
     * @param x - Player's current x position
     * @param y - Player's current y position  
     * @return true if player is at the goal, false otherwise
     */
	public boolean isEnd(int x, int y) {
		return x == endX && y == endY;  // Both coordinates must match goal position
	}
	
	// GETTER METHODS - Provide read-only access to private data
    // This is encapsulation - we control how other classes access our data
    
	public int getWidth() { 
        return width; 
    }
    
	public int getHeight() { 
        return height; 
    }
    
	public int getStartX() {
        return startX; 
    }
    
	public int getStartY() {
        return startY; 
    }
	
    /**
     * METHOD: getCellValue() - Gets the type of cell at specific coordinates
     * 
     * Allows other classes to read maze data safely
     * 
     * @param x - x coordinate to check
     * @param y - y coordinate to check
     * @return The cell type (EMPTY, WALL, START, or END), or WALL if coordinates are invalid
     * 
     * This method includes boundary checking for safety
     */
	public int getCellValue(int x, int y) {
        // BOUNDARY CHECK - Make sure coordinates are valid
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return maze[y][x];  // Return the actual cell value
		}
        // If coordinates are outside the maze, treat it as a wall
		return WALL;
        
        // This prevents ArrayIndexOutOfBoundsException
        // and makes the maze behave as if it's surrounded by walls
	}
}