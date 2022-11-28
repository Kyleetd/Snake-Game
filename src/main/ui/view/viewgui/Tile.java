package ui.view.viewgui;

import javax.swing.*;
import java.awt.*;

// Represents a tile or grid on the SnakePanel.
public class Tile extends JPanel {

    public Tile() {
        this.setBackground(Color.white);
//        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setFocusable(false);
    }

    // EFFECTS: Makes snake tiles green.
    public void setToSnake() {
        this.setBackground(Color.green);
    }

    // EFFECTS: Makes non-occupied tiles white.
    public void setToEmpty() {
        this.setBackground(Color.white);
    }

    // EFFECTS: Makes apple tiles red.
    public void setToApple() {
        this.setBackground(Color.red);
    }
}
