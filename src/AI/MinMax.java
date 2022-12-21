package AI;

import Model.Game;
import Model.Player;

public class MinMax implements Strategy {

    @Override
    public String getName() {
        return "MinMax";
    }

    @Override
    public int determineMove(Game game) {
        int[] result = minMax(game, 2, game.currentPlayer);
        System.out.println(result[1]);
        return 0;
    }

    public int[] minMax(Game game, int depth, Player maximizingPlayer) {
        int bestMove = 0;
        if (depth == 0 || game.isGameOver()) {
            return new int[]{calculateScore(game, maximizingPlayer), bestMove};
        }
        if (maximizingPlayer == game.firstPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int move : game.getValidMoves()) {
                game.doMove(move, maximizingPlayer);
                int[] result = minMax(game, depth - 1, game.secondPlayer);
                int eval = result[0];
                game.undoMove(move);
                if (eval > maxEval) {
                    maxEval = eval;
                    bestMove = result[1];
                }
            }
            return new int[]{maxEval, bestMove};
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int move : game.getValidMoves()) {
                game.doMove(move, maximizingPlayer);
                int[] result = minMax(game, depth - 1, game.firstPlayer);
                int eval = result[0];
                game.undoMove(move);
                if (eval < minEval) {
                    minEval = eval;
                    bestMove = result[1];
                }
            }
            return new int[]{minEval, bestMove};
        }
    }

    public int calculateScore(Game game, Player player) {
        int winner = game.getBoard().hasWinner();
        if (winner == player.getMark()) {
            return 100;
        } else if (winner == 0) {
            return 0;
        } else {
            return -100;
        }
    }
}
