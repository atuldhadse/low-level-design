package boardgame;

import java.util.Scanner;

import boardgame.api.GameEngine;
import boardgame.game.Board;
import boardgame.game.Cell;
import boardgame.game.Move;
import boardgame.game.Player;

public class Main {

	public static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {

		GameEngine gameEngine = new GameEngine();
		Board board = gameEngine.start("TicTacToe");

		Player human = new Player("O");
		Player computer = new Player("X");

		while (!gameEngine.isComplete(board).isOver()) {
			System.out.println("Human Make your move: ");
			System.out.println(board);
			int i = scn.nextInt();
			int j = scn.nextInt();
			gameEngine.makeMove(board, human, new Move(new Cell(i, j)));
			if (!gameEngine.isComplete(board).isOver()) {
				gameEngine.makeMove(board, computer, gameEngine.getSuggestedMove(board));
			}
		}

		System.out.println(board);
		System.out.println("Game Completed");
		System.out.println("Winner: " + gameEngine.isComplete(board).getPlayer().getSymbol());

	}

}
