package chess;

import com.google.common.base.Preconditions;

public class King extends Piece {
	
	public King(Color color) {
		super(color);
	}
	
	public boolean isCastleValid(Move move, Board board) {
		Position from = move.getFrom();
		Position to = move.getTo();
		char rookLetter;
		int rookNum;
		if (from.getLetterIndex() > to.getLetterIndex()) {
			rookLetter = 'A';
			if (getColor() == Color.WHITE) {
				rookNum = 1;
			} else {
				rookNum = 8;
			}
		} else {
			rookLetter = 'H';
			if (getColor() == Color.WHITE) {
				rookNum = 1;
			} else {
				rookNum = 8;
			}
		}
		Position rook = new Position(rookLetter, rookNum);
		return isInWay(move, board) && board.getPieceAt(rook) instanceof Rook && !isMoved() && !board.getPieceAt(rook).isMoved();
	}
	
	@Override
	public void validate(Move move, Board board) {
		super.validate(move, board);
		boolean ver = isVerticalValid(move, board, 1);
		boolean hor = isHorizontalValid(move, board, 1);
		boolean diagonal = isDiagonalValid(move, board, 1);
		Preconditions.checkState(ver || hor || diagonal || isCastleValid(move, board), "Invalid move");
	}
	
	@Override
	public String toString() {
		return (getColor() == Color.WHITE) ? "WKi" : "BKi";
	}
}
