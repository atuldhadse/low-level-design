package boardgame.game;

import boardgame.boards.TicTacToeBoard;

public class RuleEngine {

	public GameResult getState(Board board) {
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

}
