package domain;

public class PlayGame {

    static Game snakesAndLadders = new Game();

    public static void startGame(){

        snakesAndLadders.initializeBoard();
        GameInterface.printWelcome();
        snakesAndLadders.setPlayers(GameInterface.inputPlayers());

        playGame();
    }

    private static void playGame() {
        while (true){
            for (Player player :  snakesAndLadders.players) {
                if (player.HasFinished()==false){
                    throwDiceAndMove(player);
                }
            }
            if (snakesAndLadders.playersCompleted == snakesAndLadders.players.size()){
                GameInterface.displayRanks(snakesAndLadders.getPlayers());
                break;
            }
        }
    }

    public static void throwDiceAndMove(Player player) {
        int numberOnDice = Dice.getNumber();
        player.incrementMoves();
        int newSquare;
        System.out.println("\n\nPlayer: "+player.getName()+"\t Number on Dice:"+numberOnDice+"\t CurrentSquare: "+player.getCurrentSquare());
        int squareAfterAdding = player.getCurrentSquare() + numberOnDice;
        if (squareAfterAdding > Game.SQUARES_ON_BOARD){

            System.out.println("Total greater than 100");
            newSquare=player.getCurrentSquare();
            System.out.println("New Square: "+newSquare);
            return;

        } else if (squareAfterAdding == Game.SQUARES_ON_BOARD){

            System.out.println(player.getName()+" has completed the game !!!!!!!!!!!");
            snakesAndLadders.playersCompleted += 1;
            player.setRank(snakesAndLadders.playersCompleted);
            player.setHasFinished(true);
            player.setCurrentSquare(Game.SQUARES_ON_BOARD);
            newSquare = Game.SQUARES_ON_BOARD;
            System.out.println("New Square: "+newSquare);
            return;

        } else if (snakesAndLadders.gameBoard[squareAfterAdding].hasSnakeHead()){
            System.out.println("Swallowed by a snake");
            player.setCurrentSquare(snakesAndLadders.gameBoard[squareAfterAdding].getSnakeTail());
            newSquare = snakesAndLadders.gameBoard[squareAfterAdding].getSnakeTail();
            System.out.println("New Square: "+newSquare);
            return;

        } else if (snakesAndLadders.gameBoard[squareAfterAdding].hasLadderStart()){
            System.out.println("Got a ladder ");
            player.setCurrentSquare(snakesAndLadders.gameBoard[squareAfterAdding].getLadderEnd());
            newSquare = snakesAndLadders.gameBoard[squareAfterAdding].getLadderEnd();
            System.out.println("New Square: "+newSquare);
            return;

        } else {
            player.setCurrentSquare(squareAfterAdding);
            newSquare = squareAfterAdding;
            System.out.println("New Square: "+newSquare);
        }
    }
}
