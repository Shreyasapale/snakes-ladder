package domain;

import java.util.ArrayList;

public class Game {

    static int playersCompleted= 0;

    static final int SQUARES_ON_BOARD = 100;

    static int [] snakeHeads = {99,95,92,74,64,62,49,46,16};
    static int [] snakeTails = {80,75,88,53,60,19,11,25,6};
    static int [] ladderStarts = {2,7,8,15,21,28,36,51,71,78,87};
    static int [] ladderEnds = {38,14,31,26,42,84,44,67,91,98,94};

    static Square [] gameBoard = new Square[SQUARES_ON_BOARD];
    static ArrayList<Player> players ;

    static void initializeBoard (){
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
    }

    public static void startGame(){

        initializeBoard();

        players = Player.inputPlayers();
        for (int i = 0; i < 5; i++) {
            if (players.size()<=1){
                System.out.println("You need more than one player to play the game");
                players = Player.inputPlayers();
            }else {
                break;
            }
        }

        while (true){
            for (Player player :  players) {
                if (player.HasFinished()==false){
                    throwDice(player);
                }
            }
            if (playersCompleted == players.size()){
                displayRanks();
                break;
            }
        }
    }

    private static void displayRanks() {
        for (int i = 0 ; i < players.size(); i++) {
            System.out.println(i+1 + " "+players.get(i).getName() + "\t\tRank: "+players.get(i).getRank());
        }
    }

    private static void throwDice(Player player) {
        int diceThrow = Dice.getNumber();
        int squareAfterAdding = player.getCurrentSquare() + diceThrow;
        if (squareAfterAdding > SQUARES_ON_BOARD){
            System.out.println("Total greater than 100");
            return;
        } else if (squareAfterAdding == 100){
            System.out.println(player.getName()+" has completed the game !!!!!!!!!!!");
            playersCompleted += 1;
            player.setRank(playersCompleted);
            player.setHasFinished(true);

            return;
        } else if (gameBoard[squareAfterAdding].hasSnakeHead()){
            player.setCurrentSquare(gameBoard[squareAfterAdding].getSnakeTail());
            return;
        } else if (gameBoard[squareAfterAdding].hasLadderStart()){
            player.setCurrentSquare(gameBoard[squareAfterAdding].getLadderEnd());
            return;
        } else {
            player.setCurrentSquare(squareAfterAdding);
        }
    }
}
