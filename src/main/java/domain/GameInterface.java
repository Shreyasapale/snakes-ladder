package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GameInterface {

    private static Logger logger = LoggerFactory.getLogger(GameInterface.class);

    public static void printWelcome(){
        logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!! Welcome to the game of Snakes and Ladders !!!!!!!!!!!!!!!!!!!!!!!11");

    }

    public static void displayRanks(ArrayList<Player> players) {

        logger.info("The final standings are : ");
        Collections.sort(players);

        for (int i = 0 ; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            currentPlayer.setRank(i+1);
            logger.info(currentPlayer.getName() + "\tRank: "+currentPlayer.getRank()+ "\tMoves: "+ currentPlayer.getNoOfMoves());
        }

        logger.info("\n\n The winner of this game is : "+players.get(0).getName()+"\n");
    }

    public static ArrayList<Player> inputPlayers(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("\nEnter the details of the players below : ");

        System.out.println("Enter the name of Player 1 : ");
        String playerName = scanner.nextLine();
        players.add(new Player(playerName,1));
        logger.info("Player 1: "+playerName+" entered the game");

        int i =2;
        int choice = 1;
        while (i <= Player.MAX_PLAYERS_ALLOWED && choice==1){

            System.out.println("\nEnter the name of Player "+i);
            playerName = scanner.nextLine();
            players.add(new Player(playerName,i));
            logger.info("Player "+i+ ": "+playerName+" entered the game");
            System.out.println("Enter 1 if you want to add one more player else enter -1");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                System.out.println(choice+" is the choice");

            }catch (Exception e){
                System.out.println("Now, no more players can be added");
                break;
            }
            i+=1;
        }
        if (i == Player.MAX_PLAYERS_ALLOWED){
            System.out.println("Players more than "+Player.MAX_PLAYERS_ALLOWED+" are not allowed");
        }
        scanner.close();
        return players;
    }
}
