# **TwentyFortyEight Game**

Welcome to **TwentyFortyEight**, a fully-featured implementation of the popular tile-sliding game. This project showcases a clean, modular design using core programming concepts and demonstrates skills in game development, GUI design, and robust testing.

---

## **Features**

### **Core Concepts**
This project utilizes the following key programming concepts:

1. **2D Arrays**  
   - **Feature**: Represents the game grid as a 4x4 2D array.  
   - **Reason**: Provides an efficient structure for modeling the board and manipulating the game state.

2. **Collections (LinkedList)**  
   - **Feature**: Handles tile merging dynamically during gameplay.  
   - **Reason**: The flexibility of a `LinkedList` ensures smooth processing without fixed size limitations or overhead.

3. **File I/O**  
   - **Feature**: Save and load game states using serialization.  
   - **Reason**: Enables game state persistence with proper handling of edge cases for portability and reliability.

4. **Unit Testing (JUnit)**  
   - **Feature**: Comprehensive tests for core functionality (movement, merging, scoring, and game-over conditions).  
   - **Reason**: Ensures correctness and predictability of game logic, independent of the GUI.

---

## **How It Works**

### **Game Flow**
- **Objective**: Combine tiles of the same number until you reach 2048!
- **Controls**: Use arrow keys (UP, DOWN, LEFT, RIGHT) to slide tiles and merge them.

### **Core Components**
- **`TwentyFortyEightModel`**: Encapsulates game logic, including movement, merging, scoring, and game-over detection.
- **`GameBoard`**: Implements the graphical user interface (GUI), rendering the game board and managing user interactions.
- **`Direction`**: An enum representing movement directions (UP, DOWN, LEFT, RIGHT).

---

## **Project Highlights**

### **Challenges & Solutions**
1. **Merge Logic**  
   - **Challenge**: Scoring errors during tile merging due to incorrect iteration.  
   - **Solution**: Refactored the merge logic to ensure accurate scoring and game-state updates.

2. **File I/O**  
   - **Challenge**: Debugging edge cases in deserialization of malformed input.  
   - **Solution**: Extensive testing and input validation for robust file handling.

### **Design Evaluation**
- **Separation of Functionality**: Adheres to the **Model-View-Controller (MVC)** pattern, ensuring clear separation between game logic (Model) and GUI (View).  
- **Encapsulation**: The game's state and logic are properly encapsulated within the `TwentyFortyEightModel` class, preventing unintended access.  
- **Future Improvements**: Modularize the rendering logic further for easier GUI updates and additional customizations.

---
