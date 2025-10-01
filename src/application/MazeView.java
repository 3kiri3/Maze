package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * MazeView Class - Handles all visual display of the game
 * 
 * This is the "View" in our MVC pattern
 * - It displays the game state but doesn't change it
 * - It receives data from the Model and draws it on screen
 * - It extends VBox to organize UI elements vertically
 * - It uses Canvas for custom graphics (drawing the maze)
 * 
 * The View only displays data, doesn't contain game logic
 */
public class MazeView extends VBox {
    
    // VISUAL CONSTANTS
    // Cell size determines visual scale
    // 30 pixels makes a 10x10 maze = 300x300 pixels total
    private static final int CELL_SIZE = 30;
    
    // UI COMPONENTS
    private Canvas canvas;          // Drawing surface for the maze
    private GraphicsContext gc;     // Drawing context (like a paintbrush)
    private Label statusLabel;      // Game messages (welcome, victory, etc.)
    private Label movesLabel;       // Move counter display
    
    /**
     * CONSTRUCTOR - Sets up the visual components
     * 
     * @param mazeWidth - Width of the maze in cells
     * @param mazeHeight - Height of the maze in cells
     * 
     * Constructor calculates pixel dimensions from cell dimensions
     */
    public MazeView(int mazeWidth, int mazeHeight) {
        
        // CREATE CANVAS FOR DRAWING
        // Canvas size = maze dimensions × cell size
        // If maze is 10x10 cells and each cell is 30 pixels, canvas is 300x300 pixels
        canvas = new Canvas(mazeWidth * CELL_SIZE, mazeHeight * CELL_SIZE);
        
        // GET GRAPHICS CONTEXT
        // GraphicsContext is like a paintbrush - we use it to draw shapes, colors, etc.
        gc = canvas.getGraphicsContext2D();
        
        // CREATE LABEL COMPONENTS
        statusLabel = new Label("Welcome to Maze Game!");  
        movesLabel = new Label("Moves: 0");                
        
        // LAYOUT SETUP
        this.setSpacing(10);
        
        // Add components in top-to-bottom order
        this.getChildren().addAll(statusLabel, movesLabel, canvas);
        
        this.setStyle("-fx-padding: 20; -fx-alignment: center;");
        
        // CSS-style properties can be applied to JavaFX components
        // -fx-padding: adds space around the edges
        // -fx-alignment: centers the content
    }
    
    /**
     * METHOD: render() - Main drawing method, called whenever game state changes
     * 
     * Takes current game state and draws it on the canvas
     * This is the most important method in the View class
     * 
     * @param model - The GameModel containing current game state
     * 
     * This method demonstrates the separation between Model and View:
     * - Model stores the data (maze layout, player position, game state)  
     * - View reads that data and converts it to visual representation
     */
    public void render(GameModel model) {
        
        // CLEAR PREVIOUS FRAME
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        // DRAW THE MAZE
        Maze maze = model.getMaze();  // Get maze data from model
        
        // NESTED LOOP - Draw every cell in the maze
        // We go through each cell and draw it based on its type
        for (int y = 0; y < maze.getHeight(); y++) {      // Each row
            for (int x = 0; x < maze.getWidth(); x++) {   // Each column in this row
                
                // CALCULATE PIXEL COORDINATES
                // Convert maze coordinates (0,1,2...) to pixel coordinates (0,30,60...)
                int cellX = x * CELL_SIZE;  // Left edge of this cell
                int cellY = y * CELL_SIZE;  // Top edge of this cell
                
                // CHOOSE COLOR BASED ON CELL TYPE
                // Color coding for different cell types
                Color color;
                switch (maze.getCellValue(x, y)) {
                    case Maze.WALL:  color = Color.BLACK; break;
                    case Maze.START: color = Color.LIGHTGREEN; break;
                    case Maze.END:   color = Color.LIGHTCORAL; break; 
                    default:         color = Color.WHITE; break;       
                }
                
                // DRAW THE CELL
                gc.setFill(color);  // Set the fill color
                gc.fillRect(cellX, cellY, CELL_SIZE, CELL_SIZE);  // Fill the rectangle
                
                // DRAW GRID LINES
                // Grid separate cells visually
                gc.setStroke(Color.GRAY);  // Set line color to gray
                gc.strokeRect(cellX, cellY, CELL_SIZE, CELL_SIZE);  // Draw rectangle outline
                
                // strokeRect() draws just the outline
                // fillRect() fills the entire area with color
            }
        }
        
        // DRAW THE PLAYER
        // Draw player as blue circle
        // Offset and size reduction centers circle in cell
        Player player = model.getPlayer();
        gc.setFill(Color.BLUE);  
        
        // Calculate player's pixel position
        int px = player.getX() * CELL_SIZE;  // Convert cell X to pixel X
        int py = player.getY() * CELL_SIZE;  // Convert cell Y to pixel Y
        
        // Draw player as circle, slightly smaller than cell and centered
        // +3 offset and -6 size centers a 24x24 circle in a 30x30 cell
        gc.fillOval(px + 3, py + 3, CELL_SIZE - 6, CELL_SIZE - 6);
        
        // UPDATE TEXT LABELS
        // Show current move count
        movesLabel.setText("Moves: " + player.getMoves());
        
        // CHECK FOR VICTORY
        if (model.isGameWon()) {
            // Update status message for victory
            statusLabel.setText("🎉 You Won! Moves: " + player.getMoves());
            // Make victory message more prominent
            statusLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");
        }
        
        // This render method is called every time something changes:
        // - When player moves
        // - When game is reset  
        // - When game is won
        // This ensures the display always matches the current game state
    }
    
    // This class does NOT:
    // - handle keyboard input (that's the Controller's job)
    // - decide if moves are valid (that's the Model's job)
    // - change game state (it only displays it)
    // This separation makes the code easier to understand and maintain
}