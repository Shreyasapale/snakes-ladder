package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayGame {

    private static Logger logger = LoggerFactory.getLogger(PlayGame.class);

    static Game snakesAndLadders = new Game();

    public static void startGame() throws InterruptedException {

        snakesAndLadders.initializeBoard();
        GameInterface.printWelcome();
        snakesAndLadders.setPlayers(GameInterface.inputPlayers());

        playGame();
    }

    private static void playGame() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(snakesAndLadders.getPlayers().size());
        executorService.invokeAll(snakesAndLadders.players);
        GameInterface.displayRanks(snakesAndLadders.getPlayers());
        executorService.shutdown();
    }

    public synchronized static void throwDiceAndMove(Player player) {
        int numberOnDice = Dice.getNumber();
        player.incrementMoves();
        int newSquare;
        System.out.println("\n\nPlayer: "+player.getName()+"\t CurrentSquare: "+player.getCurrentSquare()+"\t Number on Dice:"+numberOnDice);
        logger.info("\n\nPlayer: "+player.getName()+"\t CurrentSquare: "+player.getCurrentSquare()+"\t Number on Dice:"+numberOnDice);
        int squareAfterAdding = player.getCurrentSquare() + numberOnDice;

        if (squareAfterAdding > Game.SQUARES_ON_BOARD){
            System.out.println("Total greater than 100");
            return;

        } else if (squareAfterAdding == Game.SQUARES_ON_BOARD){
            newSquare = Game.SQUARES_ON_BOARD;
            System.out.println("New Square: "+newSquare);
            System.out.println(player.getName()+" has completed the game !!!!!!!!!!!");
            snakesAndLadders.playersCompleted += 1;
            player.setRank(snakesAndLadders.playersCompleted);
            player.setHasFinished(true);
            player.setCurrentSquare(Game.SQUARES_ON_BOARD);
            return;

        } else if (snakesAndLadders.gameBoard[squareAfterAdding].hasSnakeHead()){
            System.out.println("Swallowed by a snake");
            newSquare = snakesAndLadders.gameBoard[squareAfterAdding].getSnakeTail();
            player.setCurrentSquare(newSquare);
            System.out.println("New Square: "+newSquare);
            return;

        } else if (snakesAndLadders.gameBoard[squareAfterAdding].hasLadderStart()){
            System.out.println("Got a ladder ");
            newSquare = snakesAndLadders.gameBoard[squareAfterAdding].getLadderEnd();
            player.setCurrentSquare(newSquare);
            System.out.println("New Square: "+newSquare);
            return;

        } else {
            player.setCurrentSquare(squareAfterAdding);
            newSquare = squareAfterAdding;
            System.out.println("New Square: "+newSquare);
        }
    }
}
