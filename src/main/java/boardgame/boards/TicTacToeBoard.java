package boardgame.boards;

import java.util.Arrays;

import boardgame.game.Board;
import boardgame.game.Cell;
import boardgame.game.Move;

public class TicTacToeBoard implements Board {

	private final String[][] cells = new String[3][3];

	public String getCellSymbol(int i, int j) {
		return cells[i][j];
	}

	public void setCell(String symbol, Cell cell) {
		cells[cell.getI()][cell.getJ()] = symbol;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.getCellSymbol(i, j) == null) {
					output += "_";
				} else {
					output += this.getCellSymbol(i, j);
				}
				if (j != 2) {
					output += " | ";
				}
			}
			output += "\n";
		}
		return output;
	}

	@Override
	public void move(Move move) {
		this.setCell(move.getPlayer().getSymbol(), move.getCell());
	}

	public TicTacToeBoard copy() {
		TicTacToeBoard board = new TicTacToeBoard();
		for (int i = 0; i < 3; i++) {
			board.cells[i] = Arrays.copyOf(this.cells[i], this.cells[i].length);
		}
		return board;
	}

}