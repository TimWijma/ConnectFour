package AI;

import Model.Game;

public interface Strategy {
    String getName();

    int determineMove(Game game);
}
