import java.util.Scanner;

public class TicTacToeGame {
    private static final char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        playGame();
    }

    // initialize board with dashes
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '-';
    }

    // print current state of the board
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // play game until win or draw
    public static void playGame() {
        boolean gameEnded = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameEnded) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            // validate move
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position! Try again.");
                continue;
            }
            if (board[row][col] != '-') {
                System.out.println("Cell already taken! Try again.");
                continue;
            }
            board[row][col] = currentPlayer;
            // chk win
            if (hasPlayerWon(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameEnded = true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameEnded = true;
            } else {
                // switch player
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }

        scanner.close();
    }

    // if current player won
    public static boolean hasPlayerWon(char player) {
        // Check rows
        for (int i = 0; i < 3; i++)
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true;

        // chk columns
        for (int i = 0; i < 3; i++)
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true;

        // chk diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true;
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    // if board is full
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '-')
                    return false;
        return true;
    }
}
