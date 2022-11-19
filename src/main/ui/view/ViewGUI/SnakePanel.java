package ui.view.ViewGUI;

import model.SnakeModel;
import persistence.JsonWriter;
import ui.controller.ControllerGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

// Displays snake game
public class SnakePanel extends JPanel {

    Tile[][] grid = new Tile[15][15];

    public SnakePanel() {
        this.setLayout(new GridLayout(15, 15));
        initializeGrid();
        this.setFocusable(true);
    }

    // MODIFIES: This
    // EFFECTS: Creates 15-by-15 tile grid
    public void initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Tile newTile = new Tile();
                grid[i][j] = newTile;
                this.add(newTile);
            }
        }
    }

    // MODIFIES: This
    // EFFECTS: Places snake and apple on snake panel grid according to game state
    public void updateGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') {
                    this.grid[i][j].setToSnake();
                } else if (grid[i][j] == 'A') {
                    this.grid[i][j].setToApple();
                } else {
                    this.grid[i][j].setToEmpty();
                }
            }
        }
    }
}
