package domain;

public class Ladder {
    private int startSquare;
    private int endSquare;

    public int getStartSquare() {
        return startSquare;
    }

    public void setStartSquare(int startSquare) {
        this.startSquare = startSquare;
    }

    public int getEndSquare() {
        return endSquare;
    }

    public void setEndSquare(int endSquare) {
        this.endSquare = endSquare;
    }

    public Ladder(int startSquare, int endSquare) {
        this.startSquare = startSquare;
        this.endSquare = endSquare;
    }
}
