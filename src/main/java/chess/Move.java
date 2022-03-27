package chess;

public class Move {
	private final Position from;
	private final Position to;

	public Move(Position from, Position to) {
		if (from == to) {
			throw new IllegalArgumentException("Invalid move");
		}
		this.from = from;
		this.to = to;
	}
	
	public Position getFrom() {
		return from;
	}

	public Position getTo() {
		return to;
	}
	
}
