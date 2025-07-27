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

	public void makeMove(Board board, Player player, Move move) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			ticTacBoard.setCell(player.getSymbol(), move.getCell());
		} else {
			throw new IllegalArgumentException();
		}

	}

	public GameResult isComplete(Board board) {
		if (board instanceof TicTacToeBoard ticTacBoard) {
			for (int row = 0; row < 3; row++) {
				int col;
				for (col = 1; col < 3 && ticTacBoard.getCell(row, col) != null; col++) {
					if (!ticTacBoard.getCell(row, col).equals(ticTacBoard.getCell(row, col - 1))) {
						break;
					}
				}
				if (col == 3) {
					return new GameResult(true, new Player(ticTacBoard.getCell(row, 0)));
				}
			}

			for (int col = 0; col < 3; col++) {
				int row;
				for (row = 1; row < 3 && ticTacBoard.getCell(row, col) != null; row++) {
					if (!ticTacBoard.getCell(row, col).equals(ticTacBoard.getCell(row - 1, col))) {
						break;
					}
				}
				if (row == 3) {
					return new GameResult(true, new Player(ticTacBoard.getCell(0, col)));
				}
			}

			int cell;
			for (cell = 1; cell < 3 && ticTacBoard.getCell(cell, cell) != null; cell++) {
				if (!ticTacBoard.getCell(cell, cell).equals(ticTacBoard.getCell(cell - 1, cell - 1))) {
					break;
				}
			}
			if (cell == 3) {
				return new GameResult(true, new Player(ticTacBoard.getCell(0, 0)));
			}

			for (cell = 1; cell < 3 && ticTacBoard.getCell(cell, 2 - cell) != null; cell++) {
				if (!ticTacBoard.getCell(cell, 2 - cell).equals(ticTacBoard.getCell(cell - 1, 3 - cell))) {
					break;
				}
			}
			if (cell == 3) {
				return new GameResult(true, new Player(ticTacBoard.getCell(0, 2)));
			}
			return new GameResult(false, new Player("-"));

		} else {
			throw new IllegalArgumentException();
		}
	}

	public Move getSuggestedMove(Board board) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ticTacBoard.getCell(i, j) == null) {
						return new Move(new Cell(i, j));
					}
				}
			}
			throw new IllegalArgumentException();
		} else {
			throw new IllegalArgumentException();
		}

	}

}
