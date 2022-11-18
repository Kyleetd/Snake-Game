package ui.view;

import model.Coordinate;
import model.SnakeModel;

import java.awt.*;
import java.awt.geom.AffineTransform;

// Renderer for snake game
public class RenderSnake {
    private static final Color HEAD_COLOUR = new Color(150, 150, 10);
    private static final Color BODY_COLOUR = new Color(210, 210, 10);
    private SnakeModel snake;

    // EFFECTS: game to render is set to given game
    public RenderSnake(SnakeModel snake) {
        this.snake = snake;
    }

    // MODIFIES: graphics
    // EFFECTS:  draws snake game onto graphics
    public void draw(Graphics graphics) {
        drawApple(graphics);
        drawSnake(graphics);
    }

    // MODIFIES: graphics
    // EFFECTS:  draws apple onto graphics
    private void drawApple(Graphics graphics) {
        Coordinate applePosition = snake.getAppleCoordinate();
    }

    // MODIFIES: graphics
    // EFFECTS:  draws snake onto graphics
    private void drawSnake(Graphics graphics) {
        drawBody(graphics);
        drawHead(graphics);
    }

    // MODIFIES: graphics
    // EFFECTS:  draws head onto graphics
    private void drawHead(Graphics graphics) {
        switch (snake.getSnakeDirection()) {
            case 'l':
                drawAtAngle(graphics, Math.PI / 2);
                break;
            case 'r':
                drawAtAngle(graphics, -Math.PI / 2);
                break;
            case 'u':
                drawAtAngle(graphics, Math.PI);
                break;
            case 'd':
                drawAtAngle(graphics, 0.0);
                break;
            default:
                break;
        }
    }

    // MODIFIES: graphics
    // EFFECTS:  draws head at given angle onto graphics
    private void drawAtAngle(Graphics graphics, double angle) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        AffineTransform savedTransform = setUpTransform(graphics2D);

        drawBackground(graphics);

        graphics2D.rotate(angle);
        drawEyes(graphics);
        graphics2D.rotate(-angle);

        undoTransform(graphics2D, savedTransform);
    }

    // MODIFIES: graphics
    // EFFECTS:  draws head background onto graphics
    private void drawBackground(Graphics graphics) {
        graphics.setColor(HEAD_COLOUR);
        graphics.fillOval(-Coordinate.CELL_PIXELS / 2, -Coordinate.CELL_PIXELS / 2, Coordinate.CELL_PIXELS, Coordinate.CELL_PIXELS);
    }

    // MODIFIES: graphics
    // EFFECTS:  draws eyes onto graphics
    private void drawEyes(Graphics graphics) {
        final int OFFSET = Coordinate.CELL_PIXELS / 8;
        final int EYE_DIAMETER = Coordinate.CELL_PIXELS / 5;

        graphics.setColor(Color.BLACK);
        graphics.fillOval(-OFFSET - EYE_DIAMETER, OFFSET, EYE_DIAMETER, EYE_DIAMETER);
        graphics.fillOval(OFFSET, OFFSET, EYE_DIAMETER, EYE_DIAMETER);
    }

    // MODIFIES: g
    // EFFECTS:  draws snake's body onto graphics
    private void drawBody(Graphics graphics) {
        graphics.setColor(BODY_COLOUR);
        for (Coordinate next : snake.getSnake()) {
            drawCell(graphics, next);
        }
    }

    // MODIFIES: g
    // EFFECTS:  draws cell onto graphics
    private void drawCell(Graphics graphics, Coordinate cell) {
        graphics.fillOval(cell.getX(), cell.getY(),
                Coordinate.CELL_PIXELS, Coordinate.CELL_PIXELS);
    }

    // MODIFIES: graphics
    // EFFECTS:  transforms graphics so it is centred on cell
    private AffineTransform setUpTransform(Graphics2D graphics) {
        Coordinate head = snake.getSnake().getFirst();

        AffineTransform savedTransform = graphics.getTransform();
        graphics.translate(head.getX() + Coordinate.CELL_PIXELS / 2,
                head.getY() + Coordinate.CELL_PIXELS / 2);
        return savedTransform;
    }

    // MODIFIES: graphics
    // EFFECTS:  restores graphics to original transform
    private void undoTransform(Graphics2D graphics, AffineTransform savedTransform) {
        graphics.setTransform(savedTransform);
    }
}
