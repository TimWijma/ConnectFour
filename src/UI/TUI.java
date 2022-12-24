package UI;

import AI.ComputerPlayer;
import Model.Game;
import Model.HumanPlayer;
import Model.Player;

public class TUI {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Player firstPlayer = new HumanPlayer("Tim", 1);
        Player secondPlayer = new ComputerPlayer("John", 2);
//        Player secondPlayer = new HumanPlayer("John", 2);

        Game game = new Game(6, 7, firstPlayer, secondPlayer);
//        System.out.println(game.getBoard().toString());
        game.getBoard().print();

        while (!game.isGameOver()) {
            Player currentPlayer = game.getTurn();

//            game.board.field = new int[][]{{0, 0, 0, 0, 0, 2, 0},
//                                            {0, 0, 0, 0, 2, 0, 0},
//                                            {1, 0, 0, 2, 0, 0, 0},
//                                            {1, 2, 2, 0, 0, 0, 0},
//                                            {2, 1, 1, 0, 0, 0, 0},
//                                            {1, 2, 1, 1, 0, 0, 0}};
            int move = currentPlayer.determineMove(game);
            game.doMove(move, currentPlayer);

//            System.out.println(game.getBoard().toString());
            game.getBoard().print();
        }
        System.out.println(game.getBoard().hasWinner() == firstPlayer.getMark() ? "firstplayer wins" : "secondplayer wins");
    }
}
