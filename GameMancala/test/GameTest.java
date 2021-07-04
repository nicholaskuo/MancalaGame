
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

/**
 * You can use this file (and others) to test your implementation.
 */

public class GameTest {

    /*
     * Test MancalaBoard case: Test reset method
     */
    @Test
    public void testResetMethod() {
        MancalaBoard manc = new MancalaBoard();

        // each of the 6 pits in each row has 4 marbles, so sum of row 1 should be 24
        // same with row 2
        assertTrue(manc.isPlayer1Turn());
        assertEquals(24, manc.sumOfRow1());
        assertEquals(24, manc.sumOfRow2());
        assertEquals(4, manc.getCell(0, 0));
        assertEquals(4, manc.getCell(1, 0));
    }

    /*
     * Test MancalaBoard case: Test single move
     */
    @Test
    public void testSingleMove() {
        MancalaBoard manc = new MancalaBoard();
        manc.playTurn(0, 1);
        assertEquals(0, manc.getCell(0, 1));
        assertEquals(5, manc.getCell(1, 1));
        assertEquals(5, manc.getCell(2, 1));
        assertEquals(5, manc.getCell(3, 1));
        assertEquals(5, manc.getCell(4, 1));
        assertEquals(4, manc.getCell(5, 1));
    }

    /*
     * Test MancalaBoard case: Test single move with increment score (player 1)
     */
    @Test
    public void testSingleMoveScoreIncreasePlayer1() {
        MancalaBoard manc = new MancalaBoard();
        manc.playTurn(5, 1);
        assertEquals(1, manc.getPlayer1Score());
        assertEquals(0, manc.getPlayer2Score());
        assertEquals(5, manc.getCell(0, 0));
    }

    /*
     * Test MancalaBoard case: Test single move with increment score (player 2)
     */
    @Test
    public void testSingleMoveScoreIncreasePlayer2() {
        MancalaBoard manc = new MancalaBoard();
        // player 1
        manc.playTurn(5, 1);

        // player 2
        manc.playTurn(5, 0);
        assertEquals(1, manc.getPlayer1Score());
        assertEquals(1, manc.getPlayer2Score());
    }
    
    /*
     * Test MancalaBoard case: Test single move decreases the marble distribution count
     * by one when the pretend marble goes into the score pit
     */
    @Test
    public void numberMarbleProperDistributedScore() {
        MancalaBoard manc = new MancalaBoard();
        // player 1
        manc.setCell(5, 1, 1);
        manc.playTurn(5, 1);

        assertEquals(4, manc.getCell(0, 0));
        assertEquals(1, manc.getPlayer1Score());
    }

    /*
     * Test MancalaBoard case: Test two moves from 2 different players (correctly)
     */
    @Test
    public void testTwoPlayersMove() {
        MancalaBoard manc = new MancalaBoard();
        manc.playTurn(0, 1);
        // note that (0,0) refers to the (0,5 panel)
        manc.playTurn(0, 0);
        assertEquals(24, manc.sumOfRow1());
        assertEquals(24, manc.sumOfRow2());
        assertEquals(0, manc.getCell(0, 0));
        assertEquals(5, manc.getCell(1, 0));
        assertEquals(5, manc.getCell(2, 0));
        assertEquals(5, manc.getCell(3, 0));
        assertEquals(5, manc.getCell(4, 0));
        assertEquals(4, manc.getCell(5, 0));

        // check that the scores are not affected
        assertEquals(0, manc.getPlayer1Score());
        assertEquals(0, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Basic Capture from player 1
     */
    @Test
    public void testBasicCapture1() {
        MancalaBoard manc = new MancalaBoard();
        manc.setCell(5, 1, 0);
        // should cause a capture
        manc.playTurn(1, 1);

        // check that both the pits vertical to one another are empty
        // specifically the pit represented by index (0,0) and (1,5)
        assertEquals(0, manc.getCell(0, 0));
        assertEquals(0, manc.getCell(5, 1));

        // test change of scores. player 1's score should have incremented by 4 + 1 = 5
        assertEquals(5, manc.getPlayer1Score());
        assertEquals(0, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Basic Capture from player 2
     */
    @Test
    public void testBasicCapture2() {
        MancalaBoard manc = new MancalaBoard();

        // should cause a capture
        manc.playTurn(0, 1);

        // there should be 5 marbles in (1, 1), so set if vertical pit is empty
        manc.setCell(4, 0, 0);
        manc.playTurn(0, 0);

        // check that both the pits vertical to one another are empty
        // specifically the pit represented by index (0,0) and (1,5)
        assertEquals(0, manc.getCell(4, 0));
        assertEquals(0, manc.getCell(1, 1));

        // test change of scores. player 2's score should have incremented by 5 + 1 = 6
        assertEquals(0, manc.getPlayer1Score());
        assertEquals(6, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Roundabout Capture (where a player's pit of marbles
     * goes counterclockwise, places a marble in every one of the opponent's pits,
     * and then goes around into an empty pit where it captures the opponent's
     * marbles
     */
    @Test
    public void roundaboutCapture() {
        MancalaBoard manc = new MancalaBoard();

        // set (1, 0) to be empty
        manc.setCell(0, 1, 0);

        // set (1, 5) to have 8 marbles (this is just an example: what should happen is
        // that
        // when (1, 5) is chosen by player 1, the last marble should be placed in (1,0)
        manc.setCell(5, 1, 8);

        // should cause a capture of 4+1 opponent marbles plus 1
        manc.playTurn(5, 1);

        // 6 captured pieces plus 1 piece stored during the distribution of marbles
        assertEquals(7, manc.getPlayer1Score());
        assertEquals(0, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Empty marble pit, should not change turn
     */
    @Test
    public void playTurnEmptyPit() {
        MancalaBoard manc = new MancalaBoard();

        // set (1, 0) to be empty
        manc.setCell(0, 1, 0);

        manc.playTurn(0, 1);

        // 6 captured pieces plus 1 piece stored during the distribution of marbles
        assertTrue(manc.isPlayer1Turn());
        assertEquals(0, manc.getPlayer1Score());
        assertEquals(0, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Free turn, still same player's turn
     */
    @Test
    public void freeTurn() {
        MancalaBoard manc = new MancalaBoard();

        assertTrue(manc.isPlayer1Turn());
        manc.playTurn(2, 1);
        assertTrue(manc.isPlayer1Turn());
        assertEquals(1, manc.getPlayer1Score());
    }

    /*
     * Test MancalaBoard case: End game when all marbles one row is gone Check
     * whether the score of the opponent player who did not play the last move gets
     * incremented by the amount of marbles he/she had in his/her row
     */
    @Test
    public void endGame() {
        MancalaBoard manc = new MancalaBoard();

        // set all cells in row 2 as 0 except (1, 5)
        manc.setCell(0, 1, 0);
        manc.setCell(1, 1, 0);
        manc.setCell(2, 1, 0);
        manc.setCell(3, 1, 0);
        manc.setCell(4, 1, 0);

        // this turn should end the game and add 4*3 + 5*3 = 27 to player 2 since player
        // 1 had
        // last move that ended the game
        manc.playTurn(5, 1);

        // indicates player 2 won
        assertEquals(2, manc.checkWinner());

        // check score
        assertEquals(1, manc.getPlayer1Score());
        assertEquals(27, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Checking winner correctly when capture is last move,
     * which also must check for caputure marbles for one player while the increased
     * score for other player due to the other player getting all the marbles stored
     * in his side's pits
     */
    @Test
    public void endGameWithCapture() {
        MancalaBoard manc = new MancalaBoard();

        // set all cells in row 2 as 0 except (1, 4), which will be one marble
        manc.setCell(0, 1, 0);
        manc.setCell(1, 1, 0);
        manc.setCell(2, 1, 0);
        manc.setCell(3, 1, 0);
        manc.setCell(4, 1, 1);
        manc.setCell(5, 1, 0);

        // player moves (1, 4) marble, should capture 4 of opponents' marble, but then
        // the other
        // 20 marbles should go to player 2, making player 2 the winner
        manc.playTurn(4, 1);

        // indicates player 2 won
        assertEquals(2, manc.checkWinner());
        // check score
        assertEquals(5, manc.getPlayer1Score());
        assertEquals(20, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: Incorrect pit pressed, nothing happens Example:
     * Player 1 presses player 2 pit (the move should not pass)
     */
    @Test
    public void incorrectPitPressed() {
        MancalaBoard manc = new MancalaBoard();

        // incorrect pit referenced on player 1 turn
        assertTrue(manc.isPlayer1Turn());
        manc.playTurn(0, 0);
        assertTrue(manc.isPlayer1Turn());
        manc.playTurn(3, 0);
        assertTrue(manc.isPlayer1Turn());
    }

    /*
     * Test MancalaBoard case: Pit has 14 marbles, should go around, score increase
     * for one player while skipping other player's score pit, then score again for
     * player 2, gaining a free turn
     */
    @Test
    public void roundaboutMarbleDistribution() {
        MancalaBoard manc = new MancalaBoard();

        // set (1, 5) to be as having 14 marbles for distribution
        manc.setCell(5, 0, 14);

        manc.playTurn(0, 1);

        // player 2 turn now
        assertFalse(manc.isPlayer1Turn());
        manc.playTurn(5, 0);

        // check that score of player 1 is 0 and player 2 is 2 and still player 2 turn
        assertFalse(manc.isPlayer1Turn());
        assertEquals(0, manc.getPlayer1Score());
        assertEquals(2, manc.getPlayer2Score());
    }

    /*
     * Test MancalaBoard case: When one player wins, the game ends and each player's
     * moves do not affect the 2D array stored in the Mancala Board
     */
    @Test
    public void endGameNoMoreMoves() {
        MancalaBoard manc = new MancalaBoard();

        // set all cells in row 2 as 0 except (1, 5)
        manc.setCell(0, 1, 0);
        manc.setCell(1, 1, 0);
        manc.setCell(2, 1, 0);
        manc.setCell(3, 1, 0);
        manc.setCell(4, 1, 0);

        // player 1 move should end game
        // last move that ended the game
        manc.playTurn(5, 1);

        assertTrue(manc.isPlayer1Turn());
        manc.playTurn(2, 1);
        assertTrue(manc.isPlayer1Turn());
    }

    /*
     * Test MarblePit case: Check default of newly created pit
     */
    @Test
    public void newPitMarbleSize() {
        MarblePit pitEx = new MarblePit();
        assertEquals(0, pitEx.getNumberOfMarbles());
    }

    /*
     * Test MarblePit case: Check decreasing of marbles to 0, and how this affects
     * the collection of marbles in this class
     */
    @Test
    public void setNumMarblesToZero() {
        MarblePit pitEx = new MarblePit();

        pitEx.setNumberOfMarbles(4);
        assertEquals(4, pitEx.getMarbleList().size());

        pitEx.setNumberOfMarbles(0);
        assertEquals(0, pitEx.getMarbleList().size());
    }

    /*
     * Test MarblePit case: Check Encapsulation
     */
    @Test
    public void checkMarbleEncapsulation() {
        MarblePit pitEx = new MarblePit();

        pitEx.setNumberOfMarbles(4);

        LinkedList<Marble> exList = pitEx.getMarbleList();
        assertEquals(4, exList.size());

        exList.remove();
        assertEquals(3, exList.size());

        assertEquals(4, pitEx.getMarbleList().size());
    }

    /*
     * Test MarblePit case: Check not changing the size of the marbles in a pit for
     * one turn, and whether the marbles are left in check, and that these marbles
     * contain the same coordinates as before
     */
    @Test
    public void sameSizeSameMarbles() {
        MarblePit pitEx = new MarblePit();

        pitEx.setNumberOfMarbles(2);

        // get x and y coords of the 2 marbles
        LinkedList<Marble> exList = pitEx.getMarbleList();
        int x1 = exList.get(0).getPx();
        int y1 = exList.get(0).getPy();
        int x2 = exList.get(1).getPx();
        int y2 = exList.get(1).getPy();

        pitEx.setNumberOfMarbles(2);
        LinkedList<Marble> exList1 = pitEx.getMarbleList();
        int nx1 = exList1.get(0).getPx();
        int ny1 = exList1.get(0).getPy();
        int nx2 = exList1.get(1).getPx();
        int ny2 = exList1.get(1).getPy();

        assertEquals(x1, nx1);
        assertEquals(y1, ny1);
        assertEquals(x2, nx2);
        assertEquals(y2, ny2);
    }

    /*
     * Test MarblePit case: Check if number of marbles is set to a greater number,
     * whether there are greater number of marbles
     */
    @Test
    public void greaterMarbleNumberAdded() {
        MarblePit pitEx = new MarblePit();

        pitEx.setNumberOfMarbles(2);

        // get x and y coords of the 2 marbles
        LinkedList<Marble> exList = pitEx.getMarbleList();
        int x1 = exList.get(0).getPx();
        int y1 = exList.get(0).getPy();
        int x2 = exList.get(1).getPx();
        int y2 = exList.get(1).getPy();

        pitEx.setNumberOfMarbles(3);
        LinkedList<Marble> exList1 = pitEx.getMarbleList();
        int nx1 = exList1.get(0).getPx();
        int ny1 = exList1.get(0).getPy();
        int nx2 = exList1.get(1).getPx();
        int ny2 = exList1.get(1).getPy();
        
        assertEquals(x1, nx1);
        assertEquals(y1, ny1);
        assertEquals(x2, nx2);
        assertEquals(y2, ny2);
        assertEquals(3, exList1.size());
    }

    /*
     * Test MarblePit cases: Test clearMarbleList
     */
    @Test
    public void test1241() {
        MarblePit pitEx = new MarblePit();

        pitEx.setNumberOfMarbles(5);

        // generate 5 marbles list
        LinkedList<Marble> exList = pitEx.getMarbleList();
        assertEquals(5, exList.size());
        pitEx.clearMarbleList();
        assertEquals(0, pitEx.getMarbleList().size());
    }
}
