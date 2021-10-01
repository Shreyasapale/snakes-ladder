package domain;

import bootstrap.Driver;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {

    int playersCompleted= 0;

    static final int SQUARES_ON_BOARD = 100;

    static int [] snakeHeads = {99,95,92,74,64,62,49,46,16};
    static int [] snakeTails = {80,75,88,53,60,19,11,25,6};
    static int [] ladderStarts = {2,7,8,15,21,28,36,51,71,78,87};
    static int [] ladderEnds = {38,14,31,26,42,84,44,67,91,98,94};

    Square [] gameBoard = new Square[SQUARES_ON_BOARD];
    ArrayList<Player> players ;

    public Square[] getGameBoard() {
        return gameBoard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setGameBoard(Square[] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void initializeBoard (){
        int ladderCounter = 0;
        int snakeCounter = 0;
        for (int i = 0; i < SQUARES_ON_BOARD; i++) {
            int squareNumber = i+1;
            Square currentSquare = new Square(squareNumber);
            if (snakeHeads[snakeCounter] == squareNumber){
                Snake newSnake = new Snake(snakeHeads[snakeCounter],snakeTails[snakeCounter]);
                currentSquare.setSnake(newSnake);
                snakeCounter += 1;
                if(snakeCounter>=snakeHeads.length){
                    snakeCounter = 0;
                }
            }
            if (ladderStarts[ladderCounter]==squareNumber){
                Ladder newLadder = new Ladder(ladderStarts[ladderCounter], ladderEnds[ladderCounter]);
                ladderCounter += 1;
                currentSquare.setLadder(newLadder);
                if (ladderCounter>=ladderStarts.length){
                    ladderCounter = 0;
                }
            }
            gameBoard[i] = currentSquare;
        }
        Driver.logger.info("Initialised the board with all the Snakes an =d Ladders");
    }


}
