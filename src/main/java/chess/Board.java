package chess;

public class Board {
	private final Piece[][] board;
	
	public Board() {
		board = new Piece[8][8];
		for (int n = 1; n < 8; n+= 5) {
			for (int l = 0; l < 8; l++) {
				Position pos = new Position(l, n);
				Color color = Color.BLACK;
				if (n == 1) {
					color = Color.WHITE;
				}
				setPosition(pos, new Pawn(color));
			}
		}
		setPosition(new Position('A', 1), new Rook(Color.WHITE));
		setPosition(new Position('B', 1), new Knight(Color.WHITE));
		setPosition(new Position('C', 1), new Bishop(Color.WHITE));
		setPosition(new Position('D', 1), new Queen(Color.WHITE));
		setPosition(new Position('E', 1), new King(Color.WHITE));
		setPosition(new Position('F', 1), new Bishop(Color.WHITE));
		setPosition(new Position('G', 1), new Knight(Color.WHITE));
		setPosition(new Position('H', 1), new Rook(Color.WHITE));
		setPosition(new Position('A', 8), new Rook(Color.BLACK));
		setPosition(new Position('B', 8), new Knight(Color.BLACK));
		setPosition(new Position('C', 8), new Bishop(Color.BLACK));
		setPosition(new Position('D', 8), new Queen(Color.BLACK));
		setPosition(new Position('E', 8), new King(Color.BLACK));
		setPosition(new Position('F', 8), new Bishop(Color.BLACK));
		setPosition(new Position('G', 8), new Knight(Color.BLACK));
		setPosition(new Position('H', 8), new Rook(Color.BLACK));
	}

	public Piece getPieceAt(Position pos) {
		return board[pos.getLetterIndex()][pos.getNumIndex()];
	}
	
	public void setPosition(Position pos, Piece piece) {
		board[pos.getLetterIndex()][pos.getNumIndex()] = piece;
	}
	
	public String toString() {
		String str = "   a   b   c   d   e   f   g   h\n" + 
				"  -------------------------------\n";
		for (int n = 7; n >= 0; n--) {
			str += Integer.toString(n+1) + "|";
			for (int l = 0; l < 8; l++) {
				Position pos = new Position(l, n);
				if (getPieceAt(pos) == null) {
					str += "   |";
				} else {
					str += getPieceAt(pos).toString() + "|";
				}
			}
			str += (n != 0) ? Integer.toString(n+1) + "\n" + 
					" |-------------------------------|\n" : Integer.toString(n+1) + "\n" + 
					"  -------------------------------\n";
		}
		str += "   a   b   c   d   e   f   g   h";
		return str;
	}
}
