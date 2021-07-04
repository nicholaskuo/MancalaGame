/**
 * CIS 120 Game HW 
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {
        // NOTE : recall that the 'final' keyword notes immutability even for local
        // variables.

        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Mancala");
        frame.setLocation(300, 300);

        final String INSTRUCTIONS = ("How To Play (2 Player Game):\n"
                + "The 6 marble pits on the bottom row belong to player 1, and \n"
                + "the 6 marble pits on the top row belong to player 2. The right score pocket \n"
                + "belongs to player 1 and the left pocket to player 2. The objective of the \n"
                + "game is to collect the most marbles in your score pocket by the end of the \n"
                + "game. When it is a player's turn, he/she chooses a marble pit that belongs to\n"
                + "him/her, and then all the marbles in that pit gets distributed counter- \n"
                + "wise to the other pits, one in each pit, until the stones run out. If a \n"
                + "marble to be distributed has run into your player score pocket, then \n"
                + "put one marble in the pocket. A player's marble distribution would skip \n"
                + "over the opponents score pocket. If the last piece of marble you drop \n"
                + "is in your own score pocket, you get another turn. If the last marble \n"
                + "you drop is in an empty pit on your side, then you capture your piece \n"
                + "plus all the opponents marbles in the opponent's pit directly across \n"
                + "the originally empty pit (gaining all these marbles in your score pit). \n"
                + "In order for a player to select his/her marble pit, use the mouse to click\n"
                + "on the pit. If a player presses the wrong marble pit or an empty pit, \n"
                + "an update message in the player's designated color (blue for player 1,\n"
                + "red for player 2) will inform the player. Using the mouse to hover over\n"
                + "a pit will highlight its edges in yellow to indicate which pit you\n"
                + "would be selecting if mouse clicked. If you want to save the current board\n"
                + "to return to later, press the save button. To reload the previous game board\n"
                + "saved, press the reload button. Have fun playing!");

        // Status panel (includes status of game and scores)
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...       ");
        final JLabel player1Label = new JLabel("Player 1 Score: 0" + "    ");
        final JLabel player2Label = new JLabel("Player 2 Score: 0" + "    ");
        player1Label.setForeground(Color.BLUE);
        player2Label.setForeground(Color.RED);
        final JLabel updateOnMove = new JLabel("Playing... ");
        status_panel.add(status);
        status_panel.add(player1Label);
        status_panel.add(player2Label);
        status_panel.add(updateOnMove);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        // Create a button for saving
        final JButton saving = new JButton("Save Current Game");
        control_panel.add(saving);

        // Create a button for loading past game
        final JButton loading = new JButton("Load Previous Game");
        control_panel.add(loading);

        // Main playing area
        final GameCourt court = new GameCourt(status, 
                player1Label, player2Label, updateOnMove, saving, loading);
        frame.add(court, BorderLayout.CENTER);


        // Note here that when we add an action listener to the reset button, we define
        // it as an
        // anonymous inner class that is an instance of ActionListener with its
        // actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be
        // called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Create a button for instructions
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, INSTRUCTIONS, 
                        "Mancala Instructions", JOptionPane.PLAIN_MESSAGE);
            }
        });
        control_panel.add(instructions);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements
     * specified in Game and runs it. IMPORTANT: Do NOT delete! You MUST include
     * this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}