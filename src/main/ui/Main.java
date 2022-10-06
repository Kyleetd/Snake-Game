package ui;

import model.SnakeModel;

public class Main {
    public static void main(String[] args) {

        SnakeModel sss = new SnakeModel(10, 10);

        char[][] board = sss.getGameState();

        System.out.print(" ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("--");
        }
        System.out.println();
        for (char[] row: board) {
            System.out.print("|");
            for (char c: row) {
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        System.out.print(" ");
        for (int i = 0; i < board[0].length; i++) {
            System.out.print("--");
        }
    }
}
