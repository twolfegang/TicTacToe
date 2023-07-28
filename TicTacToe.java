import java.util.Scanner;

/**
 * The TicTacToe program plays through the game tic tac toe with 2 players and keeps track of
 * how many rounds are won by each player. It can be played as many times as the users decide.
 *
 * @author Tim Harris
 * @version 1.0
 */
public class TicTacToe {
    private boolean playerOneTurn = true;       // Keeps track of whose turn it is
    private int boardSize;                      // The size of the board

    /**
     * This constructor initializes the board for the game.
     * @param sizeOfBoard The size of the board for the game.
     */
    public TicTacToe(int sizeOfBoard){
        boardSize = sizeOfBoard;

        // Call createBoard to initialize the board.
        char[][] board = createBoard(boardSize);
    }

    /**
     * Creates the board and initializes the game.
     * @param boardSize The size of the board.
     * @return The empty board for tic tac toe.
     */
    public static char[][] createBoard(int boardSize){
        // Create new empty board into a 2D array
        char[][] board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                board[i][j] = ' ';
            }
        }
        return board;
    }

    /** Prints out the board for the game which is originally empty.
     *
     * @param board A 2D array containing the values for the game board.
     * @param boardSize The size of the square board.
     */
    public static void printBoard(char[][] board, int boardSize){
        System.out.println();
        System.out.print("    ");
        // Prints the column numbers
        for (int i = 0; i < boardSize; i++)
            System.out.print(i + ".   ");
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            // Prints the row numbers
            System.out.print(i + ".");
            for (int j = 0; j < boardSize; j++) {
                // Prints the values on the board.
                System.out.print(" |" + board[i][j] + "| ");
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Plays through one round of the game. Goes until one player has won the round or there is a tie.
     *
     * @param board A 2D array with the values that go on the board.
     * @param boardSize The size of the square game board.
     * @param keyboard Scanner object for user input.
     * @return A character that represents who won, whether there was a tie, or if nobody won yet.
     */
    public char playGame(char[][] board, int boardSize, Scanner keyboard){
        boolean inBounds = false;   // Makes sure the user chooses a space that is within the game board.
        char symbol;        // The symbol 'X' or 'O' for the person who's turn it is.
        int row = 0;        // Used to keep track of which row the user will place their symbol.
        int column = 0;     // Used to keep track of which column the user will place their symbol.
        char won = ' ';     // Used to determine if the game has been won yet.
        boolean tie = false;    // Used to determine if the game has ended in a tie.
        char gameOver = ' ';    // Returned to tell whether the game is over or still going.

        // Keeps track of which player's turn it is and which symbol they are using
        if (playerOneTurn) {
            symbol = 'X';
            System.out.println("X it is your turn.");
        }
        else{
            symbol = 'O';
            System.out.println("O it is your turn.");
        }

        // Makes sure user chooses an empty space within the game board.
        while (!inBounds){
            System.out.print("Which row? ");
            row = keyboard.nextInt();
            System.out.print("Which column? ");
            column = keyboard.nextInt();

            // Makes sure user does not choose a space that is outside of the game board.
            if (row > 2 || row < 0 || column > 2 || column < 0) {
                System.out.println("That is out of bounds! Please try again.");
            }

            // Makes sure user does not take a spot that is already taken.
            else if (board[row][column] != ' ') {
                System.out.println("You cannot go in a spot that is already taken. Try again.");
            }

            // If the user chooses an appropriate spot, return true and continue
            else{
                inBounds = true;
            }
        }

        // Places user's symbol in the spot that they chose
        board[row][column] = symbol;

        // Check to see if the user won
        won = checkWin(board, boardSize);

        // Check to see if the users tied
        tie = checkTie(board, boardSize);

        // If one of the players won, tell them and their character will be returned
        if (won == 'X'){
            printBoard(board, boardSize);
            System.out.println("The game has been won by X!");
            gameOver = 'X';
        }
        else if (won == 'O'){
            printBoard(board, boardSize);
            System.out.println("The game has been won by O!");
            gameOver = 'O';
        }

        // If the game was a tie, return the character for a tie
        else if (tie){
            printBoard(board, boardSize);
            System.out.println("The game is a tie!");
            gameOver = 'T';
        }

        // If the game is not over, make it the other player's turn
        else
            playerOneTurn = !playerOneTurn;

        // Return the character representing whether the game was won, tied, or neither
        return gameOver;
    }

    /**
     * Checks to see if either player won the game with the most recent guess.
     *
     * @param board A 2D array containing the filled out game board.
     * @param boardSize The size of the square game board.
     * @return Return the character of the winner or a black space if nobody won yet.
     */
    public char checkWin(char[][] board, int boardSize) {
        char win = ' ';     // Holds the character of the winner or blank if game not yet won
        char[] value = new char[boardSize]; // Array used to check for winner
        int size = 0;       // Keeps track of whether the values on the board match each other

        // Checks through each row to see if somebody won
        for (int i = 0 ; i < boardSize; i ++) {
            for (int j = 0; j < boardSize; j++) {
                // value[] holds values of each row
                value[j] = board[i][j];
            }
            for (int j = 1; j < boardSize; j++) {
                // If all the values in value[] match, add to size
                if (value[0] == value[j] && value[j] != ' ' && value[0] != ' ')
                    size++;
            }
            // If one row has all the same symbols, that player has won and their character will be returned
            if (size == boardSize - 1) {
                if (playerOneTurn)
                    win = 'X';
                else
                    win = 'O';
            }
            // Reset size to be zero
            size = 0;
        }
        size = 0;

        // Check through columns to see if somebody won
        for (int i = 0 ; i < boardSize; i ++) {
            for (int j = 0; j < boardSize; j++) {
                // value[] holds values of each column
                value[j] = board[j][i];
            }
            for (int j = 1; j < boardSize; j++) {
                // If all the values in value[] match, add to size
                if (value[0] == value[j] && value[j] != ' ' && value[0] != ' ')
                    size++;
            }
            // If one column has all the same symbols, that player has won and their character will be returned
            if (size == boardSize - 1) {
                if (playerOneTurn)
                    win = 'X';
                else
                    win = 'O';
            }
            size = 0;
        }
        //Check through diagonal to the right to see if somebody won
        for (int i = 0; i < boardSize; i++){
            // value[] holds values the diagonal
            value[i] = board[i][i];
        }
        for (int i = 1; i < boardSize; i++){
            // If all the values in value[] match, add to size
            if (value[0] == value[i] && value[i] != ' ' && value[0] != ' ')
                size++;
        }
        // If the diagonal has all the same symbols, that player has won and their character will be returned
        if (size == boardSize - 1) {
            if (playerOneTurn)
                win = 'X';
            else
                win = 'O';
        }
        size = 0;

        //Check through other diagonal to the left to see if somebody won
        for (int i = 0; i < boardSize; i++){
            for (int j = 0; j < boardSize; j++){
                if ((i+j) == boardSize - 1){
                    // value[] holds values of the diagonal
                    value[i] = board[i][j];
                }
            }
        }
        for (int i = 1; i < boardSize; i++){
            // If all the values in value[] match, add to size
            if (value[0] == value[i] && value[i] != ' ' && value[0] != ' ')
                size++;
        }
        // If the diagonal has all the same symbols, that player has won and their character will be returned
        if (size == boardSize - 1) {
            if (playerOneTurn)
                win = 'X';
            else
                win = 'O';
        }
        // Return boolean representing whether a player has won or not
        return win;
    }

    /**
     * Checks to see if the game board is full and nobody has won, resulting in a tie game.
     *
     * @param board A 2D array containing the values on the game board.
     * @param boardSize The size of the square game board.
     * @return A boolean representing whether or not there was a tie game result.
     */
    public static boolean checkTie(char[][]board, int boardSize) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // If the board is not full, the game is not a tie yet
                if (board[i][j] == ' ')
                    return false;
            }
        }
        // If the board is full and nobody won yet, the game results in a tie.
        return true;
    }

    /**
     * Displays how many times each player has won and how many tied games there have been.
     *
     * @param stats An array with the number of wins for each player and the number of tied games.
     */
    public void displayStatistics(int[] stats){
        System.out.printf("\nX has won %d times.\nO has won %d times." +
                "\nThere have been %d ties.\n", stats[0], stats[1], stats[2]);
    }
}

