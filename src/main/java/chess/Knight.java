package chess;

import com.google.common.base.Preconditions;

public class Knight extends Piece {

	public Knight(Color color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void validate(Move move, Board board) {
		super.validate(move, board);
		Position from = move.getFrom();
		Position to = move.getTo();
		int numDiff = Math.abs(to.getNumIndex() - from.getNumIndex());
		int letterDiff = Math.abs(to.getLetterIndex() - from.getLetterIndex());
		Preconditions.checkState((numDiff == 2 && letterDiff == 1) || (numDiff == 1 && letterDiff == 2), "Invalid move");
	}
	
	@Override
	public String toString() {
		return (getColor() == Color.WHITE) ? "WKn" : "BKn";
	}
}
