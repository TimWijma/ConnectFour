package AI;

import Model.Game;
import Model.Player;

public class MinMax implements Strategy {
    private Player maxPlayer;
    @Override
    public String getName() {
        return "MinMax";
    }

    @Override
    public int determineMove(Game game) {
        int bestMove = 0;
        int bestValue = Integer.MIN_VALUE;

        long start = System.currentTimeMillis();
        for (int move: game.getValidMoves()) {
            Player currentPlayer = game.currentPlayer;
            maxPlayer = game.currentPlayer;
            game.doFakeMove(move, currentPlayer);
            int moveValue = minMax(game, 6, currentPlayer == game.firstPlayer ? game.secondPlayer : game.firstPlayer);
            game.undoFakeMove(move);

            if (moveValue > bestValue) {
                bestMove = move;
                bestValue = moveValue;
            }
        }
        System.out.println("Time elapsed:" + (System.currentTimeMillis() - start));
        return bestMove;
    }

    public int minMax(Game game, int depth, Player maximizingPlayer) {
        if (depth == 0 || game.isGameOver()) {
            return calculateScore(game, maxPlayer);
        }
        if (maximizingPlayer == game.currentPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int move : game.getValidMoves()) {
                game.doFakeMove(move, maximizingPlayer);
                int eval = minMax(game, depth - 1, maximizingPlayer == game.firstPlayer ? game.secondPlayer : game.firstPlayer);
                game.undoFakeMove(move);
                if (eval > maxEval) {
                    maxEval = eval;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int move : game.getValidMoves()) {
                game.doFakeMove(move, maximizingPlayer);
                int eval = minMax(game, depth - 1, maximizingPlayer == game.firstPlayer ? game.secondPlayer : game.firstPlayer);
                game.undoFakeMove(move);
                if (eval < minEval) {
                    minEval = eval;
                }
            }
            return minEval;
        }
    }

    public int calculateScore(Game game, Player player) {
        int score = 0;
        int winner = game.getBoard().hasWinner();
        int mark = player.getMark();
        if (winner == player.getMark()) {
            return 10000;
        } else if (winner != player.getMark() && winner != 0){
            return -10000;
        }

        int[][] rows = game.getBoard().getRows();
        int[][] columns = game.getBoard().getColumns();
        int[][] diagonals = game.getBoard().getDiagonals();

        for(int[] row: rows) {
            if (row[mark] == 3 && row[0] == 1) {
                score += 100;
            }
        }

        for(int[] col: columns) {
            if (col[mark] == 3 && col[0] == 1) {
                score += 100;
            }
        }

        for(int[] diagonal: diagonals) {
           if (diagonal[mark] == 3 && diagonal[0] == 1) {
               score += 100;
           }
        }
        return score;
    }
}
