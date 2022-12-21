package Model;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, int mark) {
        super(name, mark);
    }

    @Override
    public int determineMove(Game game) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(game.currentPlayer.getName() + " make a move");
        int col = scanner.nextInt();

        while (!game.isValidMove(col)) {
            System.out.println("Not a valid move");
            System.out.println(game.currentPlayer.getName() + " make a move");
            col = scanner.nextInt();
        }

        return col;
    }
}
