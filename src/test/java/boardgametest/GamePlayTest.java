package boardgametest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import boardgame.api.GameEngine;
import boardgame.game.AIEngine;
import boardgame.game.Board;
import boardgame.game.Cell;
import boardgame.game.Move;
import boardgame.game.Player;
import boardgame.game.RuleEngine;

public class GamePlayTest {

	GameEngine gameEngine;

	RuleEngine ruleEngine;

	AIEngine aiEngine;

	@Before
	public void initialize() {
		gameEngine = new GameEngine();
		ruleEngine = new RuleEngine();
		aiEngine = new AIEngine();
	}

	@Test
	public void checkForRow() {

		Board board = gameEngine.start("TicTacToe");
		Player human = new Player("O");
		Player computer = new Player("X");

		int[][] moves = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 } };
		playGame(board, moves, human, computer);

		Assert.assertTrue(ruleEngine.getState(board).isOver());
		Assert.assertEquals("O", ruleEngine.getState(board).getPlayer().getSymbol());

	}

	@Test
	public void checkForColumn() {

		Board board = gameEngine.start("TicTacToe");
		Player human = new Player("O");
		Player computer = new Player("X");

		int[][] moves = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
		playGame(board, moves, human, computer);

		Assert.assertTrue(ruleEngine.getState(board).isOver());
		Assert.assertEquals("O", ruleEngine.getState(board).getPlayer().getSymbol());

	}

	@Test
	public void checkForDiagonal() {

		Board board = gameEngine.start("TicTacToe");
		Player human = new Player("O");
		Player computer = new Player("X");

		int[][] moves = new int[][] { { 0, 0 }, { 1, 1 }, { 2, 2 } };
		playGame(board, moves, human, computer);

		Assert.assertTrue(ruleEngine.getState(board).isOver());
		Assert.assertEquals("O", ruleEngine.getState(board).getPlayer().getSymbol());

	}

	@Test
	public void checkForRevDiagonal() {

		Board board = gameEngine.start("TicTacToe");
		Player human = new Player("O");
		Player computer = new Player("X");

		int[][] moves = new int[][] { { 0, 2 }, { 1, 1 }, { 2, 0 } };
		playGame(board, moves, human, computer);

		Assert.assertTrue(ruleEngine.getState(board).isOver());
		Assert.assertEquals("O", ruleEngine.getState(board).getPlayer().getSymbol());

	}

	public void playGame(Board board, int[][] moves, Player human, Player computer) {
		int i = 0;
		int row;
		int col;
		while (!ruleEngine.getState(board).isOver()) {
			row = moves[i][0];
			col = moves[i][1];
			gameEngine.makeMove(board, new Move(new Cell(row, col), human));
			if (!ruleEngine.getState(board).isOver()) {
				gameEngine.makeMove(board, aiEngine.getSuggestedMove(board, computer));
			}
			i++;
		}
	}

}
