import java.util.Scanner;

/**
 * The P1 program plays through the game Tic Tac Toe and allows the users to repeat the game
 * as many times as they wish. It also displays a welcome and goodbye message.
 *
 * @author Tim Harris
 * @version 1.0
 */
public class P1 {
    /**
     * Calls methods to display welcome and goodbye messages and plays through the game
     * tic tac toe as many times as the user wishes.
     *
     * @param args A string array containting the command line arguments.
     */
    public static void main(String[] args) {
        final int BOARD_SIZE = 3;       // The size of the square game board.
        char repeat;        // Used to repeat the game.
        String input;       // Holds user input for whether they want to repeat the game.
        int[] stats = new int[BOARD_SIZE];      // An array to hold the number of wins by each
                                                // player and the number of ties.

        // Create a new Scanner object
        Scanner keyboard = new Scanner(System.in);

        // Displays welcome message
        welcome();

        // Plays through game and repeats as many times as user wishes
        do{
            // Play through game and return a character representing the winner
            stats = gamePlay(BOARD_SIZE, keyboard, stats);
            keyboard.nextLine();

            // Allow user to repeat
            System.out.print("Would you like to play again (y/n)?" );
            input = keyboard.nextLine();
            repeat = input.charAt(0);
        }while (repeat == 'Y' || repeat == 'y');

        // Displays goodbye message
        goodbye();

        // Close Scanner object
        keyboard.close();
    }

    /**
     * Plays through one round of tic tac toe and keeps track of the winner.
     *
     * @param BOARD_SIZE The size of the square game board.
     * @param keyboard Scanner object
     * @param stats An array that will hold the number of games won by each player and number of ties.
     * @return An array to keep track of the winner or whether there was a tie
     */
    public static int[] gamePlay(int BOARD_SIZE, Scanner keyboard, int[] stats){
        char gameOver = ' ';    // Keeps track of whether there was a winner and who won
        TicTacToe game = new TicTacToe(BOARD_SIZE); // Creates new TicTacToe object game

        // Creates a game board of chosen size and returns an empty board.
        char[][] board = game.createBoard(BOARD_SIZE);

        // Repeats until a player has won or there was a tied game
        while (gameOver == ' ') {
            // Print out the game board
            game.printBoard(board, BOARD_SIZE);
            // Play through a round of the game and return whether the game is over
            gameOver = game.playGame(board, BOARD_SIZE, keyboard);
        }

        // Keeps track in stats[] of number of wins by each player and ties
        if (gameOver == 'X')
            stats[0]++;
        if (gameOver == 'O')
            stats[1]++;
        if (gameOver == 'T')
            stats[2]++;

        // Displays the number of wins by each player and the number of ties.
        game.displayStatistics(stats);

        // Makes sure that the stats array is updated after each round
        return stats;
    }

    /**
     * Displays welcome message.
     */
    public static void welcome(){
        System.out.println("\nWelcome to TicTacToe, this is a two person game.\n" +
                "Let's play! Good luck!\n");
    }

    /**
     * Displays goodbye message.
     */
    public static void goodbye(){
        System.out.println("\nThank you for playing!\nGoodbye!");
    }
}
