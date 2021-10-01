package domain;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    static final int MAX_PLAYERS_ALLOWED = 8;

    private String name;
    private int rank;
    private boolean hasFinished;
    private int currentSquare;

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

    public Player(String name) {
        this.name = name;
        this.hasFinished = false;
        this.rank = 0;
        this.currentSquare = 1;
    }

    public static ArrayList<Player> inputPlayers(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();
        int i =1;
        int choice = 1;
        while (i <= MAX_PLAYERS_ALLOWED && choice==1){
            System.out.println("Enter the name of Player "+i);
            String playerName = scanner.nextLine();
            players.add(new Player(playerName));
            System.out.println("Enter 1 if you want to add one more player");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception e){
                System.out.println("Now, no more players can be added");
                break;
            }
        }
        if (i == MAX_PLAYERS_ALLOWED){
            System.out.println("Players more than "+MAX_PLAYERS_ALLOWED+" are not allowed");
        }
        scanner.close();
        return players;
    }
}
