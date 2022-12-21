package Model;

public abstract class Player implements PlayerInterface {
    private final String name;
    private final int mark;

    public Player(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public abstract int determineMove(Game game);

    public int getMark() {
        return mark;
    }
}
