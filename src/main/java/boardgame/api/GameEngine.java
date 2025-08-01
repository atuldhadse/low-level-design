package boardgame.api;

import boardgame.boards.TicTacToeBoard;
import boardgame.game.Board;
import boardgame.game.Move;

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
