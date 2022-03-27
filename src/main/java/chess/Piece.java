package chess;

import com.google.common.base.Preconditions;

public abstract class Piece {
	private final Color color;
	private boolean moved = false;
	
	public Piece(Color color) {
		this.color = color;	
	}

	public Color getColor() {
		return color;
	}
	
	public boolean isMoved() {
		return moved;
	}

	public void notifyPieceMovement(Move move) {
		moved = true;
	}
	
	public static boolean isInWay(Move move, Board board) {
		int fromNumIndex = move.getFrom().getNumIndex();
		int fromLetterIndex = move.getFrom().getLetterIndex();
		int toNumIndex = move.getTo().getNumIndex();
		int toLetterIndex = move.getFrom().getLetterIndex();
		int letterDiff = toLetterIndex - fromLetterIndex;
		int numDiff = toNumIndex - fromNumIndex;
		int horizontalDirection = (letterDiff == 0) ? 0 : (letterDiff > 0) ? 1 : -1;
		int verticalDirection = (numDiff == 0) ? 0 : (numDiff > 0) ? 1 : -1;
		for (int i = fromLetterIndex+horizontalDirection; i != toLetterIndex; i+=horizontalDirection) {
			for (int j = fromNumIndex+verticalDirection; i != toNumIndex; i+=verticalDirection) {
				Position temp = new Position(i, j);
				if (board.getPieceAt(temp) != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isVerticalValid(Move move, Board board, int maxNumOfSteps) {
		Position from = move.getFrom();
		Position to = move.getTo();
		int letterDiff = Math.abs(to.getLetterIndex() - from.getLetterIndex());
		int numDiff = Math.abs(to.getNumIndex() - from.getNumIndex());
		return letterDiff == 0 && numDiff <= maxNumOfSteps && !isInWay(move, board);
	}
	
	public static boolean isHorizontalValid(Move move, Board board, int maxNumOfSteps) {
		Position from = move.getFrom();
		Position to = move.getTo();
		int letterDiff = Math.abs(to.getLetterIndex() - from.getLetterIndex());
		int numDiff = Math.abs(to.getNumIndex() - from.getNumIndex());
		return numDiff == 0 && letterDiff <= maxNumOfSteps && !isInWay(move, board);
	}
	
	public static boolean isDiagonalValid(Move move, Board board, int maxNumOfSteps) {
		Position from = move.getFrom();
		Position to = move.getTo();
		int letterDiff = Math.abs(to.getLetterIndex() - from.getLetterIndex());
		int numDiff = Math.abs(to.getNumIndex() - from.getNumIndex());
		return numDiff == letterDiff && numDiff <= maxNumOfSteps && !isInWay(move, board);
	}
	
	public void validate(Move move, Board board) {
		Preconditions.checkState(move.getFrom().isInBoard(), "Selected from position is not on the board");
		Preconditions.checkState(move.getTo().isInBoard(), "Selected to position is not on the board");
	}
	
}
