package org.cis1200.twentyfortyeight;

/*
 * CIS 120 HW09 - TicTacToe Demo
 * (c) University of Pennsylvania
 * Created by Bayley Tuch, Sabrina Green, and Nicolas Corona in Fall 2020.
 */

import javax.swing.*;
import java.awt.*;

/**
 * This class sets up the top-level frame and widgets for the GUI.
 * 
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 * 
 * In a Model-View-Controller framework, Game initializes the view,
 * implements a bit of controller functionality through the reset
 * button, and then instantiates a GameBoard. The GameBoard will
 * handle the rest of the game's view and controller functionality, and
 * it will instantiate a TicTacToe object to serve as the game's model.
 */
public class RunTwentyFortyEight implements Runnable {
    public void run() {
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Setting up...");
        status_panel.add(status);

        // Game board
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        // Instructions button
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame,
                    """
                    Welcome to 2048!
                    
                    How to Play:
                    - Use the arrow keys to move tiles up, down, left, or right.
                    - Tiles with the same value merge into one, and the 
                    resulting tile's value doubles.
                    - The goal is to create a tile with the value 2048.
    
                    Features:
                    - Dynamic Scoring: Earn points by merging tiles.
                    - Game Over Detection: The game ends when no moves are possible.
                    
                    Have fun playing!
                    """,
                    "Instructions",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        control_panel.add(instructions);

        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start the game
        board.reset();
    }
}