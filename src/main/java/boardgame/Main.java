package boardgame;

import java.util.Scanner;

import boardgame.api.GameEngine;
import boardgame.game.AIEngine;
import boardgame.game.Board;
import boardgame.game.Cell;
import boardgame.game.RuleEngine;
import boardgame.game.Move;
import boardgame.game.Player;

public class Main {

	public static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		GameEngine gameEngine = new GameEngine();
		Board board = gameEngine.start("TicTacToe");

		RuleEngine ruleEngine = new RuleEngine();
		AIEngine aiEngine = new AIEngine();

		Player human = new Player("O");
		Player computer = new Player("X");

		int row;
		int col;

		while (!ruleEngine.getState(board).isOver()) {
			System.out.println("Human Make your move: ");
			System.out.println(board);
			row = scn.nextInt();
			col = scn.nextInt();
			gameEngine.makeMove(board, new Move(new Cell(row, col), human));
			if (!ruleEngine.getState(board).isOver()) {
				gameEngine.makeMove(board, aiEngine.getSuggestedMove(board, computer));
			}
		}

		System.out.println(board);
		System.out.println("Game Completed");
		System.out.println("Winner: " + ruleEngine.getState(board).getPlayer().getSymbol());

	}

}
