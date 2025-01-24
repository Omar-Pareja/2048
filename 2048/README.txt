=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: Pareja
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays:
  Feature Implemented: The game grid is represented as a 4x4 2D array.
  Reason: This efficiently models the game board, allowing manipulation of the game state

  2. Collections:
  Feature Implemented: Used a LinkedList in the merge function to handle tile merging.
  Reason: A LinkedList provides a dynamic structure for processing merges without worrying about fixed
  sizes or unnecessary overhead.

  3. File I/O:
  Feature Implemented: Serialization and deserialization of game state.
  Reason: Custom methods for saving and loading the game state ensure persistence
  and proper handling of edge cases, making the game state portable.

  4.JUnit Testable Component:
  Feature Implemented: Comprehensive unit tests for core logic, including movement, merging, scoring,
  and game-over conditions.
  Reason: Validates the correctness of the game logic independently of the GUI, ensuring robust
  and predictable behavior.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

TwentyFortyEightModel: Encapsulates the game logic and state, including movement, merging, and game-over detection.
GameBoard: Implements the GUI for the game, handling rendering and user interactions.
Direction: Enum representing movement directions (UP, DOWN, LEFT, RIGHT). DirectionTest, TwentyFortyEightModelTest,
RunTwentyFortyEight: Runs the game
GameBoardTest: Test classes ensuring the correctness of logic and GUI.
DirectionTest: Test Direction works properly
TwentyFortyEightModelTest: Test if the game's functionalities are correct.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

Merge Logic: Initially, tile merging caused scoring errors due to incorrect iteration.
File I/O: Debugging deserialization edge cases required careful testing of malformed input.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

Separation of Functionality: Model and GUI are well-separated, adhering to the MVC pattern.
Encapsulation: The game state and logic are encapsulated in the model, minimizing unintended external access.
Future Refactoring: Could add a more modular approach to rendering for easier updates to the GUI.

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

JavaDocs for Swing components
Official Java documentation for file I/O handling