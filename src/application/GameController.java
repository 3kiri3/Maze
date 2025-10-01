package application;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * GameController Class - Coordinates between user input, game logic, and display
 * 
 * This is the "Controller" in our MVC pattern
 * - It handles user input (keyboard events)
 * - It tells the Model what to do (move player, reset game)  
 * - It tells the View when to update (redraw the screen)
 * - It extends VBox to act as a container for the view
 * 
 * DESIGN PRINCIPLE: The Controller is the "coordinator" - it doesn't do the work itself,
 * but it orchestrates the interaction between Model and View
 */
public class GameController extends VBox {
    
    // MVC COMPONENTS
    private GameModel model;  // The game logic and state
    private MazeView view;    // The visual display
    
    /**
     * CONSTRUCTOR - Sets up the game components and connects them
     * 
     * Constructor does four main things:
     * 1. Create the Model (game logic)
     * 2. Create the View (visual display)  
     * 3. Set up event handling (keyboard input)
     * 4. Show the initial game state
     */
    public GameController() {
        
        // CREATE MODEL AND VIEW
        model = new GameModel();        // Create game logic
        view = new MazeView(30, 30);    // Create visual display (10x10 cells)
        
        Region spacer = new Region(); 
        spacer.setId("spacer1");
        spacer.setPrefHeight(3); 
        HBox fond = new HBox();
        fond.setId("fond");
        fond.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(fond, Priority.ALWAYS);
        
        // ADD VIEW TO LAYOUT
        // Since this controller extends VBox, we can add the view to it
        fond.getChildren().addAll(spacer,view);
        this.getChildren().add(fond);
        
        // SET UP EVENT HANDLING
        // Lambda expression for event handling
        // this::handleKeyPress is shorthand for e -> handleKeyPress(e)
        // This means "when a key is pressed, call our handleKeyPress method"
        this.setOnKeyPressed(this::handleKeyPress);
        
        // CRITICAL FOCUS SETUP
        // CRITICAL - without this, controller won't receive key events
        // In JavaFX, only the component with "focus" receives keyboard input
        this.setFocusTraversable(true);
        
        // INITIAL RENDER
        // Show the game state when it first loads
        view.render(model);
        
        // The constructor establishes the MVC connections:
        // User presses key → Controller receives event → Controller updates Model → Controller tells View to refresh
    }
    
    /**
     * METHOD: handleKeyPress() - Processes keyboard input from the user
     * 
     * PURPOSE: This is the main input handling method. It:
     * 1. Converts keyboard codes to game directions
     * 2. Handles special commands (reset)
     * 3. Updates the model based on user input
     * 4. Refreshes the display
     * 
     * @param event - The keyboard event containing information about which key was pressed
     * 
     * This method demonstrates how Controllers mediate between user input and game logic
     * 
     * 
     */
    
    
    private void handleKeyPress(KeyEvent event) {
        
        // IGNORE INPUT AFTER GAME WON
        // If game is already over, don't process movement keys
        if (model.isGameWon()) return;
        
        GameModel.Direction dir = null;  // Direction to move (starts as null = no movement)
        
        // KEYBOARD MAPPING
        // Map keyboard input to game directions
        // Supporting both arrow keys and WASD
        switch (event.getCode()) {
            case UP:    case W: dir = GameModel.Direction.UP; break;
            case DOWN:  case S: dir = GameModel.Direction.DOWN; break;
            case LEFT:  case A: dir = GameModel.Direction.LEFT; break;
            case RIGHT: case D: dir = GameModel.Direction.RIGHT; break;
            
            // SPECIAL COMMAND - Reset game
            case R:
                model.resetGame();    // Tell model to reset
                view.render(model);   // Tell view to redraw
                return;               
        }
        
        // PROCESS MOVEMENT
        // Controller coordinates Model-View interaction
        // 1. Tell Model to update state
        // 2. Tell View to redraw based on new state
        if (dir != null) {  // If user pressed a movement key
            model.movePlayer(dir);  // Tell model to try moving player
            view.render(model);     // Tell view to redraw with new state
            
            // The Controller doesn't care if the move succeeded or failed
            // The Model handles move validation, the View handles displaying the result
            // The Controller just coordinates the interaction
        }
        
        // Typical Controller pattern:
        // 1. Receive input
        // 2. Translate input to commands
        // 3. Call Model methods
        // 4. Call View methods
        // The Controller doesn't contain game logic or drawing code - it just coordinates
    }
    
    /**
     * METHOD: requestGameFocus() - Ensures controller has keyboard focus
     * 
     * PURPOSE: Called from Main.java to make sure this controller can receive keyboard events
     * 
     * In JavaFX, focus management is important for keyboard input
     * Only the focused component receives key events
     */
    public void requestGameFocus() {
        this.requestFocus();
        
        // TEACHING POINT: This method is called after the window is shown
        // because requesting focus before the window appears doesn't work reliably
    }
}