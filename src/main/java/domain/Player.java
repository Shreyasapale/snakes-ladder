package domain;


import java.util.concurrent.Callable;

public class Player implements Comparable, Callable<Integer> {

    static final int MAX_PLAYERS_ALLOWED = 8;

    private int number;
    private String name;
    private int rank;
    private boolean hasFinished;
    private int currentSquare;
    private int noOfMoves;

    public int getNoOfMoves() {
        return noOfMoves;
    }

    public void setNoOfMoves(int noOfMoves) {
        this.noOfMoves = noOfMoves;
    }

    public void incrementMoves(){
        this.noOfMoves += 1;
    }

    public int getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(int currentSquare) {
        this.currentSquare = currentSquare;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean HasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Player(String name,int i) {
        this.number = i;
        this.name = name;
        this.hasFinished = false;
        this.rank = 0;
        this.currentSquare = 1;
        this.noOfMoves = 0;
    }


    @Override
    public int compareTo(Object o) {
        Player playerToCompareWith = (Player) o;
        if (noOfMoves < playerToCompareWith.getNoOfMoves()){
            return -1;
        } else if (noOfMoves > playerToCompareWith.getNoOfMoves() ){
            return 1;
        } else {
           if (number < playerToCompareWith.getNumber()){
               return -1;
           } else {
               return 1;
           }
        }
    }

    @Override
    public Integer call() throws Exception {
        Thread.currentThread().setName("Player "+this.getName());
        while (true){
            PlayGame.throwDiceAndMove(this);
            if (this.hasFinished){
                break;
            }
        }
        return this.noOfMoves;
    }
}
