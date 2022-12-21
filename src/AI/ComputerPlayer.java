package AI;

import Model.Game;
import Model.Player;

public class ComputerPlayer extends Player {
    Strategy strategy;


    public ComputerPlayer(String name, int mark, Strategy strategy) {
        super(name, mark);
        this.strategy = strategy;
    }

    public ComputerPlayer(String name, int mark) {
        super(name, mark);
        this.strategy = new MinMax();
    }

    @Override
    public int determineMove(Game game) {
        return strategy.determineMove(game);
    }
}
