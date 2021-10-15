package domain;


public class Snake {
    private int headSquare;
    private int tailSquare;

    public int getHeadSquare() {
        return headSquare;
    }

    public void setHeadSquare(int headSquare) {
        this.headSquare = headSquare;
    }

    public int getTailSquare() {
        return tailSquare;
    }

    public void setTailSquare(int tailSquare) {
        this.tailSquare = tailSquare;
    }

    public Snake(int headSquare, int tailSquare) {
        this.headSquare = headSquare;
        this.tailSquare = tailSquare;
    }
}
