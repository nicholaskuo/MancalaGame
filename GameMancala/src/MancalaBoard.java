public class MancalaBoard {
    private int[][] board;
    private boolean isPlayer1Turn;
    private int player1Score;
    private int player2Score;

    public MancalaBoard() {
        reset();
    }

    // ** BE CAREFUL WITH COLUMN: When calling this method, must do proper
    // conversion first
    // A given panel must correspond to the correct column
    public int playTurn(int c, int r) {
        if (r == 0 && isPlayer1Turn) {
            return 0;
        }

        if (r == 1 && !isPlayer1Turn) {
            return 0;
        }

        if (board[r][c] == 0) {
            return 1;
        }

        // player 1 or player 2 turn
        if (isPlayer1Turn) {
            // player 1 turn

            int numberOfMarbles = board[r][c];
            board[r][c] = 0;
            int i = 0;
            int currentRow = r;
            int currentCol = c;

            while (i < numberOfMarbles) {
                if (currentCol + 1 >= 6) {
                    if (currentRow == 0) {
                        currentRow = 1;
                        currentCol = 0;
                        board[currentRow][currentCol] = board[currentRow][currentCol] + 1;
                    } else {
                        player1Score++;
                        currentRow = 0;
                        currentCol = -1;
                    }
                } else {
                    currentCol++;
                    board[currentRow][currentCol] = board[currentRow][currentCol] + 1;
                }
                i++;
            }

            // check for capture
            boolean checkCapture1 = false;
            if (currentRow == 1 && isPlayer1Turn) {
                if (currentCol != -1) {
                    // check if other row's same column has marbles
                    if (board[currentRow][currentCol] == 1) {
                        int checkOtherRowSameColumn = 5 - currentCol;
                        if (board[0][checkOtherRowSameColumn] != 0) {
                            int otherRowsMarble = board[0][checkOtherRowSameColumn];
                            int addToScore = otherRowsMarble + 1;
                            board[currentRow][currentCol] = 0;
                            board[0][checkOtherRowSameColumn] = 0;
                            player1Score += addToScore;
                            checkCapture1 = true;
                        }
                    }
                }
            }

            // check for free turn or change turn
            boolean checkFreeTurn1 = false;
            if (checkWinner() == 0 && currentCol != -1) {
                isPlayer1Turn = !isPlayer1Turn;
            }
            if (currentCol == -1) {
                checkFreeTurn1 = true;
            }

            if (checkWinner() != 0) {
                player2Score += sumOfRow1();
            }

            // Return Values:
            if (checkCapture1) {
                return 2;
            }
            if (checkFreeTurn1) {
                return 3;
            }

        } else {
            // player 2 turn

            int numberOfMarbles = board[r][c];
            board[r][c] = 0;
            int i = 0;
            int currentRow = r;
            int currentCol = c;

            while (i < numberOfMarbles) {
                if (currentCol + 1 >= 6) {
                    if (currentRow == 0) {
                        player2Score++;
                        currentRow = 1;
                        currentCol = -1;
                    } else {
                        currentRow = 0;
                        currentCol = 0;
                        board[currentRow][currentCol] = board[currentRow][currentCol] + 1;
                    }
                } else {
                    currentCol++;
                    board[currentRow][currentCol] = board[currentRow][currentCol] + 1;
                }
                i++;
            }

            // check for capture
            boolean checkCapture2 = false;
            if (currentRow == 0 && !isPlayer1Turn) {
                if (currentCol != -1) {
                    // check if other row's same column has marbles
                    if (board[currentRow][currentCol] == 1) {
                        int checkOtherRowSameColumn = 5 - currentCol;
                        if (board[1][checkOtherRowSameColumn] != 0) {
                            int otherRowsMarble = board[1][checkOtherRowSameColumn];
                            int addToScore = otherRowsMarble + 1;
                            board[currentRow][currentCol] = 0;
                            board[1][checkOtherRowSameColumn] = 0;
                            player2Score += addToScore;
                            checkCapture2 = true;
                        }
                    }
                }
            }

            // check for free turn or change turn
            boolean checkFreeTurn2 = false;
            if (checkWinner() == 0 && currentCol != -1) {
                isPlayer1Turn = !isPlayer1Turn;
            }
            if (currentCol == -1) {
                checkFreeTurn2 = true;
            }

            if (checkWinner() != 0) {
                player1Score += sumOfRow2();
            }

            // Return Values:
            if (checkCapture2) {
                return 4;
            }
            if (checkFreeTurn2) {
                return 5;
            }
        }
        return 6;
    }

    /**
     * checkWinner checks whether the game has reached a win condition. checkWinner
     * only looks for horizontal wins.
     * 
     * @return 0 if nobody has won yet, 1 if player 1 has won, and 2 if player 2 has
     *         won, 3 if tie
     */
    public int checkWinner() {
        // check if one of the two rows are empty
        int sumOfRow1 = sumOfRow1();
        int sumOfRow2 = sumOfRow2();

        if (isPlayer1Turn) {
            if (sumOfRow2 == 0) {
                if (player1Score > player2Score) {
                    return 1;
                } else if (player2Score > player1Score) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
        if (!isPlayer1Turn) {
            if (sumOfRow1 == 0) {
                if (player1Score > player2Score) {
                    return 1;
                } else if (player2Score > player1Score) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
        return 0;
    }

    public int sumOfRow1() {
        int sumOfRow1 = 0;
        for (int i = 0; i < 6; i++) {
            sumOfRow1 += board[0][i];
        }
        return sumOfRow1;
    }

    public int sumOfRow2() {
        int sumOfRow2 = 0;
        for (int i = 0; i < 6; i++) {
            sumOfRow2 += board[1][i];
        }
        return sumOfRow2;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    // true if player 1 turn, false if player 2
    public boolean isPlayer1Turn() {
        return isPlayer1Turn;
    }

    // returns a cell's marble count
    public int getCell(int c, int r) {
        return board[r][c];
    }

    public void clear2DArray() {
        board = new int[2][6];
    }

    public void setCell(int c, int r, int newVal) {
        board[r][c] = newVal;
    }

    public void setPlayer1Score(int score) {
        player1Score = score;
    }

    public void setPlayer2Score(int score) {
        player2Score = score;
    }

    public void setIsPlayer1Turn(boolean val) {
        isPlayer1Turn = val;
    }

    public void reset() {
        board = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 4;
            }
        }
        player1Score = 0;
        player2Score = 0;
        isPlayer1Turn = true;
    }
}
