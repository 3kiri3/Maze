package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // CREATE THE GAME CONTROLLER
        // GameController contains both the game logic AND the visual display
        // This is different from strict MVC - it's a combined View-Controller
        GameController controller = new GameController();
        
        Scene scene = new Scene(controller, 2000, 2000);
        
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        
        primaryStage.setTitle("Revenge Maze Game");  
        primaryStage.setScene(scene);                 
        
        // CRITICAL FOCUS SETUP
        // EXPLANATION: Lambda ensures focus after window shows
        // Common JavaFX pitfall: requesting focus before stage is shown doesn't work
        // The controller needs "focus" to receive keyboard input
        primaryStage.setOnShown(e -> controller.requestGameFocus());
        
        primaryStage.show();
        
        // HELPFUL CONSOLE MESSAGES
        System.out.println("Use arrow keys or WASD to move!");
        System.out.println("Press R to reset");
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}