package boardgame.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;

import boardgame.boards.TicTacToeBoard;

public class RuleEngine {

	Map<String, List<Rule<TicTacToeBoard>>> ruleMap;

	public GameInfo checkFork(Board board) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			GameResult gameState = getState(board);
			String[] playerSymbols = { "X", "0" };
			for (String playerSymbol : playerSymbols) {
				Player player = new Player(playerSymbol);
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						TicTacToeBoard board1 = ticTacBoard.copy();
						board1.move(new Move(new Cell(i, j), player));
						boolean canStillWin = false;
						for (int k = 0; k < 3; k++) {
							for (int l = 0; l < 3; l++) {
								TicTacToeBoard board2 = board1.copy();
								board2.move(new Move(new Cell(k, l), player.flip()));
								if (getState(board2).getPlayer().getSymbol().equals(player.flip().getSymbol())) {
									canStillWin = true;
									break;
								}
							}
							if (canStillWin) {
								break;
							}
						}
						if (canStillWin) {
							return new GameInfo(gameState, true, player.flip());
						}
					}
				}
			}
			return new GameInfo(gameState, false, null);
		} else {
			throw new IllegalArgumentException();
		}

	}

	public RuleEngine() {
		ruleMap = new HashMap<>();
		ruleMap.put(TicTacToeBoard.class.getName(), new ArrayList<>());
		List<Rule<TicTacToeBoard>> ticTacToeRuleList = ruleMap.get(TicTacToeBoard.class.getName());
		ticTacToeRuleList.add(new Rule<>(board -> outerTraversal((i, j) -> board.getCellSymbol(i, j))));
		ticTacToeRuleList.add(new Rule<>(board -> outerTraversal((i, j) -> board.getCellSymbol(j, i))));
		ticTacToeRuleList.add(new Rule<>(board -> traverse(i -> board.getCellSymbol(i, i))));
		ticTacToeRuleList.add(new Rule<>(board -> traverse(i -> board.getCellSymbol(i, 2 - i))));
		ticTacToeRuleList.add(new Rule<>(board -> {
			int filledCellCount = filledCellCount(board);
			if (filledCellCount == 9) {
				return new GameResult(true, new Player("-"));
			} else {
				return new GameResult(false, new Player("-"));
			}
		}));
	}

	public GameResult getState(Board board) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			List<Rule<TicTacToeBoard>> ticTacToeRules = ruleMap.get(TicTacToeBoard.class.getName());
			for (Rule<TicTacToeBoard> rule : ticTacToeRules) {
				GameResult gameResult = rule.condition.apply(ticTacBoard);
				if (gameResult.isOver()) {
					return gameResult;
				}
			}
			return new GameResult(false, new Player("-"));
		} else {
			throw new IllegalArgumentException();
		}

	}

	public int filledCellCount(Board board) {
		if (board instanceof TicTacToeBoard ticTacBoard) {
			int count = 0;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (ticTacBoard.getCellSymbol(row, col) != null) {
						count++;
					}
				}
			}
			return count;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public GameResult outerTraversal(BiFunction<Integer, Integer, String> next) {
		for (int i = 0; i < 3; i++) {
			int finalI = i;
			IntFunction<String> f = j -> next.apply(finalI, j);
			GameResult checkStreak = traverse(f);
			if (checkStreak.isOver()) {
				return checkStreak;
			}
		}
		return new GameResult(false, null);
	}

	public GameResult traverse(IntFunction<String> point) {
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

class Rule<T extends Board> {

	Function<T, GameResult> condition;

	public Rule(Function<T, GameResult> condition) {
		this.condition = condition;
	}

}
