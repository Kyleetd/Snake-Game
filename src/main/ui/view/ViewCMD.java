package ui.view;

import java.util.List;
import java.util.Scanner;

public class ViewCMD {

    public ViewCMD() {}

    // EFFECTS: Reads in user input from command line.
    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        return userInput;
    }

    // EFFECTS: Prints out the board passed into the method.
    public void printBoard(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    // EFFECTS: Prints main menu.
    public void printMainMenu() {
        System.out.println("Main Menu");
        System.out.println("Choose one of the following options by entering the letter and pressing enter:");
        System.out.println("s -> start game");
        System.out.println("l -> view leaderboard");
        System.out.println("q -> quit game");
    }

    // EFFECTS: Prints leaderboard. If leaderboard is empty it prints out a message to the user indicating
    //          that the leaderboard is empty.
    public void printLeaderBoard(List<String> leaderboard) {
        if (leaderboard.size() == 0) {
            System.out.println("The leaderboard is currently empty.");
            return;
        }

        System.out.println("Leaderboard:");
        for (String entry: leaderboard) {
            System.out.println(entry);
        }
    }

    // EFFECTS: Prints game over message.
    public void printGameOver(int score) {
        System.out.println("Game Over :(");
        System.out.println("Your score is: " + score);
        System.out.println("Enter your name: ");
    }

    // EFFECTS: Prints invalid input.
    public void printInvalidInput() {
        System.out.println("Invalid input. Please try again.");
    }

    // EFFECTS: Prints game instructions.
    public void printGameInstructions() {
        System.out.println("Choose one of the following options to move your snake:");
        System.out.println("u -> up");
        System.out.println("d -> down");
        System.out.println("r -> right");
        System.out.println("l -> left");
    }
}
