package ui.view.viewgui;

import model.SnakeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

// Creates main JFrame.
public class ViewGUI extends JFrame {

    SnakePanel snakePanel;
    SidePanel sidePanel;

    public ViewGUI() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.setBackground(Color.white);

        this.snakePanel = new SnakePanel();
        this.sidePanel = new SidePanel();

        this.add(snakePanel, BorderLayout.CENTER);
        this.add(sidePanel, BorderLayout.EAST);
    }

    public SnakePanel getSnakePanel() {
        return snakePanel;
    }

    public ControlPanel getControlPanel() {
        return sidePanel.controlPanel;
    }

    public LeaderboardPanel getLeaderboardPanel() {
        return sidePanel.leaderboardPanel;
    }

}
