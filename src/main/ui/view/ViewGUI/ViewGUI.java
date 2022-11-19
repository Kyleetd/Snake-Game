package ui.view.ViewGUI;

import model.SnakeModel;
import ui.controller.ControllerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Creates main JFrame
public class ViewGUI extends JFrame {

    SnakeModel snakeModel;
    SnakePanel snakePanel;
    SidePanel sidePanel;

    public ViewGUI(SnakeModel snakeModel) {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());
        this.setFocusable(true);

        addKeyListener(new KeyArrowListener());

        this.snakeModel = snakeModel;
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

    public class KeyArrowListener extends KeyAdapter {

        // MODIFIES: SnakeModel, SnakePanel
        // EFFECTS: Moves snake on SnakePanel when key event is detected by updating direction in Snake Model
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                snakeModel.changeSnakeDirection('u');
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                snakeModel.changeSnakeDirection('d');
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                snakeModel.changeSnakeDirection('l');
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                snakeModel.changeSnakeDirection('r');
            }

            snakePanel.revalidate();
            snakePanel.repaint();
        }
    }
}
