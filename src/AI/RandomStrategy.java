package AI;

import Model.Game;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {
    @Override
    public String getName() {
        return "Random";
    }

    @Override
    public int determineMove(Game game) {
        List<Integer> moves = game.getValidMoves();
        return moves.get(new Random().nextInt(moves.size()));
    }
}
