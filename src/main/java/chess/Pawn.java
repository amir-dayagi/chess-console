package chess;

import com.google.common.base.Preconditions;

public class Pawn extends Piece {	
	private final int direction;
	
	public Pawn(Color color) {
		super(color);
		direction = (color == Color.WHITE) ? 1 : -1;
	}
	
	public int getDirection() {
		return direction;
	}

	@Override
	public void validate(Move move, Board board) {
		super.validate(move, board);
		Position from = move.getFrom();
		Position to = move.getTo();
		int direction = getDirection();
		int numDiff = to.getNumIndex() - from.getNumIndex();
		boolean isToPositionTaken = board.getPieceAt(to) != null;
		boolean ver1 = isVerticalValid(move, board, 1) && !isToPositionTaken && numDiff == direction;
		boolean ver2 = isVerticalValid(move, board, 2) && !isMoved() && !isToPositionTaken && numDiff == 2*direction;
		boolean diagonal = isDiagonalValid(move, board, 1) && isToPositionTaken && numDiff == direction;
		Preconditions.checkState(ver1 || ver2 || diagonal, "Invalid move");
	}
	
	@Override
	public String toString() {
		return (getColor() == Color.WHITE) ? "WPa" : "BPa";
	}
}
