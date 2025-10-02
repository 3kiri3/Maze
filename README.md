# Revenge Maze

**Students:** Clarisse, Maryatou, Yousra
**Coursework:** PIS 2 (Practical)  

---

## Project Overview

Revenge Maze is an interactive 2D game developed in JavaFX, combining maze navigation, puzzle-solving, and stealth mechanics. Players must progress through office-themed levels, avoiding obstacles, completing mini-games, and riding elevators. In the final stage, players must evade AI-controlled bosses to achieve their ultimate goal.

This project demonstrates the potential of interactive systems and highlights how a user-focused game can be engaging and entertaining.

### Game Context

You are an exhausted office worker, worn down by years of a soul-crushing corporate job. Your boss bullies you every day. They undermine you in front of your colleagues and push you to your limit. Today is different. Today, you decide it's time for revenge.

You arrive at the office with a clear plan: to make your way through the messy office and steal your boss's most important item. Each step forward is a battle against the clutter of the workplace. You will not have time to rest once you are inside the elevator. Solve the small challenges; this is the best way to distract yourself during the long ride. These include puzzles, word games, enigmas, and quick sketches that test your focus. Failure has a cost. If you lose, frustration consumes you and you find yourself back on the first floor, forced to begin the journey again.

Finally, you reach your boss's office floor. Here, the real danger begins. Your boss prowls the office. With every creak of the floor or glance over his shoulder, the risk of being caught grows. You move deliberately, crouching in the corners and slipping between the furniture, heart racing. The goal is clear: steal the divorce papers.  Seize them, unseen and victorious, and finally get your revenge.  

---

## Features

### Core Gameplay
- [✔] **Maze Navigation**  
- [ ] **Obstacles Assests** (tables, chairs, and furniture as barriers)  
- [ ] **Elevator System** (animated elevator transition between floors)  
- [ ] **Mini-Games in Elevator** (five unique challenges):
  - Mini Sudoku  
  - Mini Wordle (guess the correct word)  
  - Mini Enigme (choose the right French expression)  
  - Mini Drawing Game (draw the word displayed on screen)  
  - Tic-Tac-Toe  
- [ ] **Punishment System** (failure in a mini-game restarts from floor one)  
- [ ] **Boss AI Pathfinding** (boss uses pathfinding to chase the player on final floor)  
- [ ] **Winning Condition** (retrieve divorce papers from boss’s desk to win)  


### User Interface
- [ ] **Start Screen** (explain the what and why)  
- [ ] **Main Game Window** (2D maze view)  
- [ ] **Elevator Animation** (transition between floors with mini-games)  
- [ ] **Mini-Game Windows** (different UI for Sudoku, Wordle, Enigme, Drawing, Tic-Tac-Toe)  
- [ ] **HUD** (floor indicator, timer, puzzle progress)  
- [ ] **Game Over Screen** (caught by boss or failed puzzles)  

### Game Mechanics
- [✔] **Player Movement** (Arrow keys / WASD)  
- [ ] **Mini-Game Switching** (maze → elevator puzzles → back to maze)  
- [ ] **Boss AI Pathfinding** (dynamic movement to chase player on final floor)  
- [ ] **Stealth System** (boss detection radius)  

---

## Installation

### Prerequisites
- Java Development Kit (JDK)  
- JavaFX SDK  
- Eclipse IDE (or IntelliJ IDEA or VSCode)  

### Project Setup (GIT)
1. Open Eclipse and go to **File > Import...**  
2. Select **Git > Projects from Git** and click **Next**.  
3. Choose **Clone URI** and click **Next**.  
4. Enter the repository URI https://github.com/3kiri3/Maze.  
5. Click **Next** and **Finish**.  

### Project Setup (ZIP)
1. Download and extract the ZIP file.  
2. Open Eclipse and go to **File > Import...**  
3. Select **General > Existing Projects into Workspace** and click **Next**.  
4. Browse to the extracted project folder and click **Finish**.  

### Configuring JavaFX
1. Add the JavaFX `lib` folder to your project’s build path  
   (**Project > Properties > Java Build Path > Add External JARs**).  
2. Create a Run Configuration (**Run > Run Configurations...**).  
3. Set `Main.java` as the main class.  
4. Add VM arguments:  

### Running the Project

After setup, run the project from Eclipse. The **Revenge Maze** start window will launch automatically.

### Optional: Run via Terminal 
1. Make sure the run.sh script is executable `chmod +x run.sh`
2. Run the script: `./run.sh`

Note: If errors appear, check for missing dependencies and install them (e.g., libgl1-mesa-glx, libglfw3, etc.).

---


## How to Play

- Move around the office using arrow keys.
- Interact with mini-games in elevators using the mouse and the keyboard.
- On the boss’s floor, navigate stealthily, avoid detection, and steal the divorce papers.

---

## Game Objectives

- Navigate through the office maze to reach the elevator.  
- Complete mini-games to progress between floors.
- Avoid the boss on the final floor.
- Retrieve the divorce papers to win the game.

---

## Game Structure

**Front-end:** JavaFX with FXML for UI layout
**Game Engine:**  Custom game loop with 2D array-based maze 

**Key Classes:**
  - `Main.java` - Application entry point, JavaFX initialization
  - `GameController.java` - Main game logic and event handling
  - `GameModel.java` - Maze generation and grid management
  - `Maze.java` – defines maze structure
  - `MazeView.java` – renders maze and game visuals
  - `Player.java` – handles player state and movement

---

## Screenshots

Raw Version: ![Raw Version](/img/Raw-Screenshot.png)

---

## Development Team

- Clarisse Carvalho  
- Yousra Bourguiba  
- Maryatou Lorem