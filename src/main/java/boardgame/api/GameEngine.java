package boardgame.api;

import boardgame.boards.TicTacToeBoard;
import boardgame.game.Board;
import boardgame.game.Cell;
import boardgame.game.GameResult;
import boardgame.game.Move;
import boardgame.game.Player;

public class GameEngine {

	public Board start(String gameName) {
		if ("TicTacToe".equals(gameName)) {
			return new TicTacToeBoard();
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void makeMove(Board board, Move move) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			ticTacBoard.move(move);
		} else {
			throw new IllegalArgumentException();
		}

	}

}
