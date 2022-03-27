package chess;

import com.google.common.base.Preconditions;

public class Rook extends Piece {

	public Rook(Color color) {
		super(color);
	}
	
	@Override
	public void validate(Move move, Board board) {
		super.validate(move, board);
		boolean ver = isVerticalValid(move, board, 8);
		boolean hor = isHorizontalValid(move, board, 8);
		Preconditions.checkState(ver || hor, "Invalid move");
	}
	
	@Override
	public String toString() {
		return (getColor() == Color.WHITE) ? "WRo" : "BRo";
	}
}
