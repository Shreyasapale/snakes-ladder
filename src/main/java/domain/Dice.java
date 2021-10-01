package domain;

public class Dice {

    static final int MAX_NUMBER_ON_DICE = 6;

        public static int getNumber (){
        return (int)((MAX_NUMBER_ON_DICE * Math.random())+1);
    }
}
