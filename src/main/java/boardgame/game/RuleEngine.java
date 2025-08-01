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
			result = checkStreak(i -> ticTacBoard.getCellSymbol(i, i));
			if (result.isOver()) {
				return result;
			}
			result = checkStreak(i -> ticTacBoard.getCellSymbol(i, 2 - i));
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
			int finalI = i;
			IntFunction<String> f = j -> next.apply(finalI, j);
			GameResult checkStreak = checkStreak(f);
			if (checkStreak.isOver()) {
				return checkStreak;
			}
		}
		return new GameResult(false, null);
	}

	public GameResult checkStreak(IntFunction<String> point) {
		String firstSymbol = point.apply(0);
		int i;
		for (i = 1; i < 3 && point.apply(i) != null; i++) {
			if (!point.apply(i).equals(point.apply(i - 1))) {
				break;
			}
		}
		if (i == 3) {
			return new GameResult(true, new Player(firstSymbol));
		}
		return new GameResult(false, null);
	}

}