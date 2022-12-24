package Model;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Board board;
    public Player firstPlayer;
    public Player secondPlayer;
    public Player currentPlayer;

    public Game(int rows, int cols, Player firstPlayer, Player secondPlayer) {
        this.board = new Board(rows, cols);
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.currentPlayer = firstPlayer;
    }

    public void doMove(int col, Player player) {
        if (isValidMove(col)) {
            board.setField(col, player.getMark());
            currentPlayer = currentPlayer == firstPlayer ? secondPlayer : firstPlayer;
        } else {
            System.out.println("Not a valid move");
        }
    }

    public void doFakeMove(int col, Player player) {
        if (isValidMove(col)) {
            board.setField(col, player.getMark());
        } else {
            System.out.println("Not a valid move");
        }
    }

    public void undoFakeMove(int col) {
        board.removeField(col);
    }

    public boolean isValidMove(int col) {
        boolean hasEmpty = false;
        for (int i = 0; i < board.rows; i++) {
            if (board.getField(i, col) == 0) {
                hasEmpty = true;
                break;
            }
        }

        return hasEmpty && col >= 0 && col <= board.cols;
    }

    public List<Integer> getValidMoves() {
        List<Integer> moves = new ArrayList<>();

        for (int i = 0; i < board.cols; i++) {
            if (isValidMove(i)) {
                moves.add(i);
            }
        }

        return moves;
    }

    public boolean isGameOver() {
        if (board.isFull()) {
            return true;
        } else {
            return board.hasWinner() != 0;
        }
    }

    public Player getTurn() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }
}
