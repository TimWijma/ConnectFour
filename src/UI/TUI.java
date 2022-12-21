package UI;

import AI.ComputerPlayer;
import Model.Game;
import Model.HumanPlayer;
import Model.Player;

import java.util.Scanner;

public class TUI {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        Player firstPlayer = new HumanPlayer("Tim", 1);
        Player secondPlayer = new ComputerPlayer("John", 2);

        Game game = new Game(6, 7, firstPlayer, secondPlayer);
        System.out.println(game.getBoard().toString());

        while (!game.isGameOver()) {
            Player currentPlayer = game.getTurn();

            int move = currentPlayer.determineMove(game);
            game.doMove(move, currentPlayer);

            System.out.println(game.getBoard().toString());
        }
        System.out.println(game.getBoard().hasWinner() == firstPlayer.getMark() ? "firstplayer wins" : "secondplayer wins");
    }
}
