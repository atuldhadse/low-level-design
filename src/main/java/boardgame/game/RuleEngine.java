package boardgame.game;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

import boardgame.boards.TicTacToeBoard;

public class RuleEngine {

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

			result = checkStreak(i -> ticTacBoard.getCellSymbol(i, i), i -> ticTacBoard.getCellSymbol(i / 2, i / 2));
			if (result.isOver()) {
				return result;
			}

			result = checkStreak(i -> ticTacBoard.getCellSymbol(i, 2 - i),
					i -> ticTacBoard.getCellSymbol(i / 2, i / 2));
			if (result.isOver()) {
				return result;
			}

			return new GameResult(false, new Player("-"));

		} else {
			throw new IllegalArgumentException();
		}
	}

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

	public GameResult checkStreak(IntFunction<String> point, IntFunction<String> symbolFetcher) {
		int i;
		for (i = 1; i < 3 && point.apply(i) != null; i++) {
			if (!point.apply(i).equals(point.apply(i - 1))) {
				break;
			}
		}
		if (i == 3) {
			return new GameResult(true, new Player(symbolFetcher.apply(i)));
		}
		return new GameResult(false, null);
	}

}