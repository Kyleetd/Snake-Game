# Snake

## *A Game*
#
**Project Description**

The application will create a game that simulates a snake slithering around a board. The user controls the snake's movements and must prevent it from hitting the board boundaries and running into itself. The snake gets longer by eating apples that spawn on random locations on the board. The goal of the game is to eat as many apples as possible.

## *User Stories*

- As a user, I want to choose options from a main menu: start game, stop game, or quit game.
- As a user, I want to be able to load a previous game state or start a new game.
- As a user, I want to play the game by changing the direction of the snake.
#
- As a user, I want my to choose if whether my score and name will be uploaded on the leaderboard (add X to a Y).
- As a user, I want to be able to save the status of the game when the stop button is pressed.

#
- As a user, I want to view the top ten scores on the leaderboard.
- As a user, I want to choose whether to load or clear the previous leaderboard.

## *Phase 4: Task 2*
Sun Nov 27 19:20:59 PST 2022
INFO: Leaderboard was loaded


Sun Nov 27 19:21:10 PST 2022
INFO: Kylee's score was added to leaderboard.


Sun Nov 27 19:21:15 PST 2022
INFO: Lexy's score was added to leaderboard.


Sun Nov 27 19:21:28 PST 2022
INFO: Jaden's score was added to leaderboard.

## *Phase 4: Task 3*
To improve this project, I would remove nested classes. Specifically, all of the inner ButtonListener classes can be 
extracted into a new ?class.? This ?class? would take an argument that indicates the action to be performed.