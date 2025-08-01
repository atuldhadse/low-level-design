package boardgame.game;

import java.util.function.BiFunction;

import boardgame.boards.TicTacToeBoard;

public class RuleEngine {

	public GameResult isVictory(BiFunction<Integer, Integer, String> next) {
		for (int i = 0; i < 3; i++) {
			int j;
			for (j = 1; j < 3 && next.apply(i, j) != null; j++) {
				if (!next.apply(i, j).equals(next.apply(i, j - 1))) {
					break;
				}
			}
			if (j == 3) {
				return new GameResult(true, new Player(next.apply(i, 0)));
			}
		}
		return new GameResult(false, null);
	}

	public GameResult getState(Board board) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			GameResult result;

			result = isVictory((row, col) -> ticTacBoard.getCellSymbol(row, col));
			if (result.isOver()) {
				return result;
			}

			result = isVictory((row, col) -> ticTacBoard.getCellSymbol(col, row));
			if (result.isOver()) {
				return result;
			}

			int cell;
			for (cell = 1; cell < 3 && ticTacBoard.getCellSymbol(cell, cell) != null; cell++) {
				if (!ticTacBoard.getCellSymbol(cell, cell).equals(ticTacBoard.getCellSymbol(cell - 1, cell - 1))) {
					break;
				}
			}
			if (cell == 3) {
				return new GameResult(true, new Player(ticTacBoard.getCellSymbol(0, 0)));
			}

			for (cell = 1; cell < 3 && ticTacBoard.getCellSymbol(cell, 2 - cell) != null; cell++) {
				if (!ticTacBoard.getCellSymbol(cell, 2 - cell).equals(ticTacBoard.getCellSymbol(cell - 1, 3 - cell))) {
					break;
				}
			}
			if (cell == 3) {
				return new GameResult(true, new Player(ticTacBoard.getCellSymbol(0, 2)));
			}
			return new GameResult(false, new Player("-"));

		} else {
			throw new IllegalArgumentException();
		}
	}

}