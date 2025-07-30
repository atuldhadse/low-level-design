package boardgame.game;

import boardgame.boards.TicTacToeBoard;

public class AIEngine {

	public Move getSuggestedMove(Board board, Player player) {

		if (board instanceof TicTacToeBoard ticTacBoard) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (ticTacBoard.getCell(i, j) == null) {
						return new Move(new Cell(i, j), player);
					}
				}
			}
			throw new IllegalArgumentException();
		} else {
			throw new IllegalArgumentException();
		}

	}

}
