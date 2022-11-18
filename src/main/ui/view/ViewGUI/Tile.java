package ui.view.ViewGUI;

import javax.swing.*;
import java.awt.*;

public class Tile extends JPanel {

    public Tile() {
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setFocusable(false);
    }

    public void setToSnake() {
        this.setBackground(Color.green);
    }

    public void setToEmpty() {
        this.setBackground(Color.white);
    }

    public void setToApple() {
        this.setBackground(Color.red);
    }
}
