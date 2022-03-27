package chess;

import com.google.common.base.Preconditions;

public class Bishop extends Piece {

	public Bishop(Color color) {
		super(color);
	}
	
	@Override
	public void validate(Move move, Board board) {
		super.validate(move, board);
		Preconditions.checkState(isDiagonalValid(move, board, 8), "Invalid move");
	}
	
	@Override
	public String toString() {
		return (getColor() == Color.WHITE) ? "WBi" : "BBi";
	}
}
