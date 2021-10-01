package domain;

import java.io.Serializable;

public class Square implements Serializable {
    private int squareNumber;
    private Snake snake = null;
    private Ladder ladder = null;

    public Square(int squareNumber) {
        this.squareNumber = squareNumber;
        this.snake = null;
        this.ladder = null;
    }

    public boolean hasSnakeHead(){
        if (this.snake != null){
            return true;
        }
        return false;
    }

    public boolean hasLadderStart(){
        if (this.ladder != null){
            return true;
        }
        return false;
    }

    public int getSnakeTail(){
        return this.snake.getTailSquare();
    }

    public int getLadderEnd(){
        return this.ladder.getEndSquare();
    }

    public int getSquareNumber() {
        return squareNumber;
    }

    public void setSquareNumber(int squareNumber) {
        this.squareNumber = squareNumber;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public void setLadder(Ladder ladder) {
        this.ladder = ladder;
    }
}
