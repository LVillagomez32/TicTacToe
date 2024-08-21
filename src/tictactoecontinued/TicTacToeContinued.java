/*

In this programm, you will be able to Tic-Tac-Toe. User will choose a number
from the board to place their coresponding token ('x' or 'o'). if three tokens 
of the same connect, the player of that token is declared the winner.
 */
package tictactoecontinued;

import java.util.Scanner;

public class TicTacToeContinued {

    public static void main(String[] args) {
        
        boolean again = true;
        
        while (again){
        
        Scanner scanner = new Scanner(System.in);
        char[][] boardPlay = createBoard();
        printBoard(boardPlay);
        char token = 'x';
        int moves = 0;
        

        boolean won = false;
        
        
        do {
            System.out.println("Player " + token + "'s turn");
            int place = getPlace(scanner); // Get player's move
            boardPlay = placeToken(boardPlay, place, token); // Place token on the board
            printBoard(boardPlay); // Print updated board

            if (moves >= 4) { // Check winning condition after at least 5 moves
                won = checkWon(boardPlay);
            }

            if (won) {
                System.out.println("Congratulations! Player " + token + " won!");
                break;
            }

            token = (token == 'x') ? 'o' : 'x'; // Switch player
            moves++;
        } while (moves < 9 && !won);

        if (!won) {
            System.out.println("It's a draw!");
        }
        
            again =playAgain(scanner);
            System.out.println("");
        }
    }
    
    public static boolean playAgain(Scanner scanner){
        
        System.out.println("Do you want to plat again? (yes/no)");
        String s = scanner.next();
        
        while (!s.equalsIgnoreCase("yes") && !s.equalsIgnoreCase("no"))
        {
            System.out.println("Do you want to play again? (yes/no)");
            s = scanner.next();
        }
        
        if (s.equalsIgnoreCase("yes"))
        {
            System.out.println("Game will continue");
            return true ; // returns true, game will retstart and play again
        }
        else
        {
            System.out.println("Game will end");
            return false; // program ends and game is over
        }  
        
    } // end of playAgain method

    public static char[][] createBoard() {
        char[][] board = new char[3][3]; // Create a 3x3 board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' '; 
            }
        }
        return board;
    }

    public static void printBoard(char[][] board) {
        
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]); 
                if (col < 2) {
                    System.out.print('|'); // Print vertical lines
                }
            }
            System.out.println();
            if (row < 2) {
                System.out.println("-+-+-"); // Print horizontal lines
            }
        }
    }

    public static char[][] placeToken(char[][] board, int place, char token) {
        int row = (place - 1) / 3; // Calculate row index
        int col = (place - 1) % 3; // Calculate column index
        
        if (board[row][col] == 'x' || board[row][col] == 'o') { // checks if the spot is already taken
            System.out.println("Invalid move. Position already taken.");
            return placeToken(board, getPlace(new Scanner(System.in)), token); // retry
    }

    board[row][col] = token; // Place the token on the board
        System.out.println("");
    return board; // Return the updated board
    }

    public static int getPlace(Scanner scanner) {
        int place;
        do {
            System.out.println("Enter position (1-9):"); // Prompt user for input
            System.out.println("");
            place = scanner.nextInt(); 
        } while (place < 1 || place > 9); 
        return place; 
    }

    public static boolean checkWon(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false;
    }
}