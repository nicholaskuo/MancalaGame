
/**
 * CIS 120 Game HW  
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private MancalaBoard mainBoard;

    private boolean playing = false; // whether the game is running
    private JLabel status; // Current status text, i.e. "Running..."

    // status on scores!!
    final JLabel player1ScoreStatus;
    final JLabel player2ScoreStatus;

    // status on game
    final JLabel updateGameStat;

    // status on marbles in pit
    final JLabel marbleCount1;
    final JLabel marbleCount2;
    final JLabel marbleCount3;
    final JLabel marbleCount4;
    final JLabel marbleCount5;
    final JLabel marbleCount6;
    final JLabel marbleCount7;
    final JLabel marbleCount8;
    final JLabel marbleCount9;
    final JLabel marbleCount10;
    final JLabel marbleCount11;
    final JLabel marbleCount12;
    final JLabel player1MarblePit;
    final JLabel player2MarblePit;

    // image for wooden panel
    private final static String IMG_FILE = "files/woodBackground.jpg";
    private static BufferedImage img;

    // Game constants
    public static final int COURT_WIDTH = 820;
    public static final int COURT_HEIGHT = 300;

    // List of pits
    private LinkedList<MarblePit> marblePitList;

    // Game Panels
    final MarblePit pit1;
    final MarblePit pit2;
    final MarblePit pit3;
    final MarblePit pit4;
    final MarblePit pit5;
    final MarblePit pit6;
    final MarblePit pit7;
    final MarblePit pit8;
    final MarblePit pit9;
    final MarblePit pit10;
    final MarblePit pit11;
    final MarblePit pit12;
    final ScorePit leftTopScorePit;
    final ScorePit rightTopScorePit;
    final ScorePitBottom leftBotScorePit;
    final ScorePitBottom rightBotScorePit;

    // Game buttons
    final JButton saveButton;
    final JButton loadButton;

    // Update interval for timer, in milliseconds
    public static final int INTERVAL = 35;

    public GameCourt(JLabel status, JLabel statusPlayer1, 
            JLabel statusPlayer2, JLabel updateMove, JButton saving,
            JButton loading) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // * creating background picture
        try {
            if (img == null) {
                img = ImageIO.read(new File(IMG_FILE));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }

        // Enable keyboard focus on the court area.
        // When this component has the keyboard focus, key events are handled by its key
        // listener.
        setFocusable(true);

        // **initialize model for the game!
        mainBoard = new MancalaBoard();

        // NEED TO ADD MOUSELISTENER HOVERER GIVES YOU # OF MARBLES
        leftTopScorePit = new ScorePit();
        rightTopScorePit = new ScorePit();
        leftBotScorePit = new ScorePitBottom();
        rightBotScorePit = new ScorePitBottom();

        player1MarblePit = new JLabel("" + mainBoard.getPlayer1Score());
        player1MarblePit.setForeground(Color.YELLOW);
        player2MarblePit = new JLabel("" + mainBoard.getPlayer2Score());
        player2MarblePit.setForeground(Color.YELLOW);
        player1MarblePit.setFont(new Font("Serif", Font.BOLD, 20));
        player2MarblePit.setFont(new Font("Serif", Font.BOLD, 20));
        leftTopScorePit.add(player2MarblePit);
        rightTopScorePit.add(player1MarblePit);

        pit1 = new MarblePit();
        pit1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(5, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount1 = new JLabel("" + mainBoard.getCell(5, 0));
        marbleCount1.setForeground(Color.WHITE);
        marbleCount1.setFont(new Font("Serif", Font.BOLD, 20));
        pit1.add(marbleCount1);

        pit2 = new MarblePit();
        pit2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(4, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount2 = new JLabel("" + mainBoard.getCell(4, 0));
        marbleCount2.setForeground(Color.WHITE);
        marbleCount2.setFont(new Font("Serif", Font.BOLD, 20));
        pit2.add(marbleCount2);

        pit3 = new MarblePit();
        pit3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(3, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount3 = new JLabel("" + mainBoard.getCell(3, 0));
        marbleCount3.setForeground(Color.WHITE);
        marbleCount3.setFont(new Font("Serif", Font.BOLD, 20));
        pit3.add(marbleCount3);

        pit4 = new MarblePit();
        pit4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(2, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount4 = new JLabel("" + mainBoard.getCell(2, 0));
        marbleCount4.setForeground(Color.WHITE);
        marbleCount4.setFont(new Font("Serif", Font.BOLD, 20));
        pit4.add(marbleCount4);

        pit5 = new MarblePit();
        pit5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(1, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount5 = new JLabel("" + mainBoard.getCell(2, 0));
        marbleCount5.setForeground(Color.WHITE);
        marbleCount5.setFont(new Font("Serif", Font.BOLD, 20));
        pit5.add(marbleCount5);

        pit6 = new MarblePit();
        pit6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(0, 0);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount6 = new JLabel("" + mainBoard.getCell(0, 0));
        marbleCount6.setForeground(Color.WHITE);
        marbleCount6.setFont(new Font("Serif", Font.BOLD, 20));
        pit6.add(marbleCount6);

        pit7 = new MarblePit();
        pit7.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(0, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount7 = new JLabel("" + mainBoard.getCell(0, 1));
        marbleCount7.setForeground(Color.WHITE);
        marbleCount7.setFont(new Font("Serif", Font.BOLD, 20));
        pit7.add(marbleCount7);

        pit8 = new MarblePit();
        pit8.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(1, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount8 = new JLabel("" + mainBoard.getCell(1, 1));
        marbleCount8.setForeground(Color.WHITE);
        marbleCount8.setFont(new Font("Serif", Font.BOLD, 20));
        pit8.add(marbleCount8);

        pit9 = new MarblePit();
        pit9.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(2, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount9 = new JLabel("" + mainBoard.getCell(2, 1));
        marbleCount9.setForeground(Color.WHITE);
        marbleCount9.setFont(new Font("Serif", Font.BOLD, 20));
        pit9.add(marbleCount9);

        pit10 = new MarblePit();
        pit10.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(3, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount10 = new JLabel("" + mainBoard.getCell(3, 1));
        marbleCount10.setForeground(Color.WHITE);
        marbleCount10.setFont(new Font("Serif", Font.BOLD, 20));
        pit10.add(marbleCount10);

        pit11 = new MarblePit();
        pit11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(4, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount11 = new JLabel("" + mainBoard.getCell(4, 1));
        marbleCount11.setForeground(Color.WHITE);
        marbleCount11.setFont(new Font("Serif", Font.BOLD, 20));
        pit11.add(marbleCount11);

        pit12 = new MarblePit();
        pit12.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playing) {
                    int n = mainBoard.playTurn(5, 1);
                    changeGameLabel(n);
                    updateStatus();
                    repaint();
                }
            }
        });
        marbleCount12 = new JLabel("" + mainBoard.getCell(5, 1));
        marbleCount12.setForeground(Color.WHITE);
        marbleCount12.setFont(new Font("Serif", Font.BOLD, 20));
        pit12.add(marbleCount12);

        marblePitList = new LinkedList<MarblePit>();
        marblePitList.add(pit1);
        marblePitList.add(pit2);
        marblePitList.add(pit3);
        marblePitList.add(pit4);
        marblePitList.add(pit5);
        marblePitList.add(pit6);
        marblePitList.add(pit7);
        marblePitList.add(pit8);
        marblePitList.add(pit9);
        marblePitList.add(pit10);
        marblePitList.add(pit11);
        marblePitList.add(pit12);

        // setting up the mancala board's layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 8));

        panel.add(leftTopScorePit);
        for (int i = 0; i < 6; i++) {
            panel.add(marblePitList.get(i));
        }
        panel.add(rightTopScorePit);
        panel.add(leftBotScorePit);

        for (int i = 6; i < 12; i++) {
            panel.add(marblePitList.get(i));
        }
        panel.add(rightBotScorePit);

        // set save and load buttons
        saveButton = saving;
        loadButton = loading;

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BufferedWriter bw = null;
                try {
                    Writer fileToWrite = new FileWriter("files/gameSave.txt", false);
                    bw = new BufferedWriter(fileToWrite);
                    if (mainBoard.isPlayer1Turn()) {
                        bw.write("player1");
                        bw.newLine();
                    } else {
                        bw.write("player2");
                        bw.newLine();
                    }
                    bw.write("" + mainBoard.getPlayer1Score());
                    bw.newLine();
                    bw.write("" + mainBoard.getPlayer2Score());
                    bw.newLine();
                    for (MarblePit e1 : marblePitList) {
                        bw.write("" + e1.getNumberOfMarbles());
                        bw.newLine();
                    }
                    bw.flush();
                    bw.close();
                } catch (IOException ex) {
                    System.out.println("Error");
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BufferedReader br = null;
                try {
                    Reader fileToWrite = new FileReader("files/gameSave.txt");
                    br = new BufferedReader(fileToWrite);
                    String s = br.readLine();
                    if (s != null && !s.equals("")) {
                        if (s.equals("player1")) {
                            mainBoard.setIsPlayer1Turn(true);
                        } else {
                            mainBoard.setIsPlayer1Turn(false);
                        }
                        mainBoard.setPlayer1Score(Integer.parseInt(br.readLine()));
                        mainBoard.setPlayer2Score(Integer.parseInt(br.readLine()));

                        for (int i = 5; i >= 0; i--) {
                            mainBoard.setCell(i, 0, Integer.parseInt(br.readLine()));
                        }

                        for (int i = 0; i < 6; i++) {
                            mainBoard.setCell(i, 1, Integer.parseInt(br.readLine()));
                        }
                        updateStatus();
                        repaint();
                    }
                } catch (IOException ex) {
                    System.out.println("Error");
                }
            }
        });

        this.add(panel);

        this.status = status;
        player1ScoreStatus = statusPlayer1;
        player2ScoreStatus = statusPlayer2;
        updateGameStat = updateMove;
    }

    // Updates the 3 JLabels to reflect the current state of the game
    private void updateStatus() {
        // update player turn
        if (playing) {
            if (mainBoard.isPlayer1Turn()) {
                status.setText("Player 1's Turn    ");
            } else {
                status.setText("PLayer 2's Turn    ");
            }

            // update ScorePits for player 1 and 2
            if (mainBoard.getPlayer1Score() >= 15) {
                rightBotScorePit.setNumberOfMarbles(15);
                rightTopScorePit.setNumberOfMarbles(mainBoard.getPlayer1Score() - 15);
            } else if (mainBoard.getPlayer1Score() < 15) {
                rightBotScorePit.setNumberOfMarbles(mainBoard.getPlayer1Score());
            }
            if (mainBoard.getPlayer2Score() >= 15) {
                leftTopScorePit.setNumberOfMarbles(15);
                leftBotScorePit.setNumberOfMarbles(mainBoard.getPlayer2Score() - 15);
            } else if (mainBoard.getPlayer2Score() < 15) {
                leftTopScorePit.setNumberOfMarbles(mainBoard.getPlayer2Score());
            }

            // check for winner
            int winner = mainBoard.checkWinner();
            if (winner != 0) {
                mainBoard.clear2DArray();
            }
            if (winner == 1) {
                playing = false;
                status.setText("Player 1 wins!!!  ");
            } else if (winner == 2) {
                playing = false;
                status.setText("Player 2 wins!!!  ");
            } else if (winner == 3) {
                playing = false;
                status.setText("It's a tie.   ");
            }
        }

        // update MancalaBoard
        pit1.setNumberOfMarbles(mainBoard.getCell(5, 0));
        pit2.setNumberOfMarbles(mainBoard.getCell(4, 0));
        pit3.setNumberOfMarbles(mainBoard.getCell(3, 0));
        pit4.setNumberOfMarbles(mainBoard.getCell(2, 0));
        pit5.setNumberOfMarbles(mainBoard.getCell(1, 0));
        pit6.setNumberOfMarbles(mainBoard.getCell(0, 0));
        pit7.setNumberOfMarbles(mainBoard.getCell(0, 1));
        pit8.setNumberOfMarbles(mainBoard.getCell(1, 1));
        pit9.setNumberOfMarbles(mainBoard.getCell(2, 1));
        pit10.setNumberOfMarbles(mainBoard.getCell(3, 1));
        pit11.setNumberOfMarbles(mainBoard.getCell(4, 1));
        pit12.setNumberOfMarbles(mainBoard.getCell(5, 1));

        // update MancalaBoard marble labels
        marbleCount1.setText("" + mainBoard.getCell(5, 0));
        marbleCount2.setText("" + mainBoard.getCell(4, 0));
        marbleCount3.setText("" + mainBoard.getCell(3, 0));
        marbleCount4.setText("" + mainBoard.getCell(2, 0));
        marbleCount5.setText("" + mainBoard.getCell(1, 0));
        marbleCount6.setText("" + mainBoard.getCell(0, 0));
        marbleCount7.setText("" + mainBoard.getCell(0, 1));
        marbleCount8.setText("" + mainBoard.getCell(1, 1));
        marbleCount9.setText("" + mainBoard.getCell(2, 1));
        marbleCount10.setText("" + mainBoard.getCell(3, 1));
        marbleCount11.setText("" + mainBoard.getCell(4, 1));
        marbleCount12.setText("" + mainBoard.getCell(5, 1));
        player1MarblePit.setText("" + mainBoard.getPlayer1Score());
        player2MarblePit.setText("" + mainBoard.getPlayer2Score());

        // update scores
        player1ScoreStatus.setText("Player 1's Score: " + mainBoard.getPlayer1Score() + "    ");
        player2ScoreStatus.setText("Player 2's Score: " + mainBoard.getPlayer2Score() + "    ");

    }

    // changes label for current game status
    private void changeGameLabel(int n) {
        if (n == 0) {
            if (mainBoard.isPlayer1Turn()) {
                updateGameStat.setForeground(Color.BLUE);
            } else {
                updateGameStat.setForeground(Color.RED);
            }
            updateGameStat.setText("Wrong Pit");
        } else if (n == 1) {
            if (mainBoard.isPlayer1Turn()) {
                updateGameStat.setForeground(Color.BLUE);
            } else {
                updateGameStat.setForeground(Color.RED);
            }
            updateGameStat.setText("Empty Pit");
        } else if (n == 2) {
            updateGameStat.setForeground(Color.BLUE);
            updateGameStat.setText("Capture!");
        } else if (n == 3) {
            updateGameStat.setForeground(Color.BLUE);
            updateGameStat.setText("Free Turn!");
        } else if (n == 4) {
            updateGameStat.setForeground(Color.RED);
            updateGameStat.setText("Capture!");
        } else if (n == 5) {
            updateGameStat.setForeground(Color.RED);
            updateGameStat.setText("Free Turn!");
        } else {
            updateGameStat.setForeground(Color.BLACK);
            updateGameStat.setText("Playing...");
        }
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        mainBoard.reset();
        pit1.setNumberOfMarbles(4);
        pit2.setNumberOfMarbles(4);
        pit3.setNumberOfMarbles(4);
        pit4.setNumberOfMarbles(4);
        pit5.setNumberOfMarbles(4);
        pit6.setNumberOfMarbles(4);
        pit7.setNumberOfMarbles(4);
        pit8.setNumberOfMarbles(4);
        pit9.setNumberOfMarbles(4);
        pit10.setNumberOfMarbles(4);
        pit11.setNumberOfMarbles(4);
        pit12.setNumberOfMarbles(4);
        leftTopScorePit.resetMarbleList();
        rightTopScorePit.resetMarbleList();
        leftBotScorePit.resetMarbleList();
        rightBotScorePit.resetMarbleList();

        // reset labels
        status.setText("Player 1's Turn    ");
        player1ScoreStatus.setText("Player 1's Score: " + mainBoard.getPlayer1Score() + "    ");
        player2ScoreStatus.setText("Player 2's Score: " + mainBoard.getPlayer2Score() + "    ");
        marbleCount1.setText("" + mainBoard.getCell(5, 0));
        marbleCount2.setText("" + mainBoard.getCell(4, 0));
        marbleCount3.setText("" + mainBoard.getCell(3, 0));
        marbleCount4.setText("" + mainBoard.getCell(2, 0));
        marbleCount5.setText("" + mainBoard.getCell(1, 0));
        marbleCount6.setText("" + mainBoard.getCell(0, 0));
        marbleCount7.setText("" + mainBoard.getCell(0, 1));
        marbleCount8.setText("" + mainBoard.getCell(1, 1));
        marbleCount9.setText("" + mainBoard.getCell(2, 1));
        marbleCount10.setText("" + mainBoard.getCell(3, 1));
        marbleCount11.setText("" + mainBoard.getCell(4, 1));
        marbleCount12.setText("" + mainBoard.getCell(5, 1));
        player1MarblePit.setText("" + mainBoard.getPlayer1Score());
        player2MarblePit.setText("" + mainBoard.getPlayer2Score());
        updateGameStat.setForeground(Color.BLACK);
        updateGameStat.setText("Playing...");

        repaint();

        playing = true;
        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, 820, 300, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}