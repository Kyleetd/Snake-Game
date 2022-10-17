package model;

public class Model {

    SnakeModel snakeModel;
    LeaderboardModel leaderboardModel;

    // EFFECTS: Instantiates snake model and leaderboard model.
    public Model() {
        snakeModel = new SnakeModel(10, 10);
        leaderboardModel = new LeaderboardModel();
    }

    // MODIFIES: This.
    // EFFECTS: Resets the snake model.
    public void resetSnakeModel() {
        snakeModel = new SnakeModel(10, 10);
    }

    public SnakeModel getSnakeModel() {
        return snakeModel;
    }

    public LeaderboardModel getLeaderboardModel() {
        return leaderboardModel;
    }
}
