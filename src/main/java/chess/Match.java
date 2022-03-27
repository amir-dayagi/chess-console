package chess;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import com.google.common.base.Preconditions;

public class Match {
	private final Board board = new Board();
	private final Player p1;
	private final List<Piece> p1LostPieces = new ArrayList<>(); 
	private final Player p2;
	private final List<Piece> p2LostPieces = new ArrayList<>();
	private Player currentPlayer;
	
	public Match() {
		p1 = new Player(Color.WHITE, "Player 1");
		p2 = new Player(Color.BLACK, "Player 2");
		currentPlayer = p1;
	}
	
	public Match(String p1, String p2) {
		this.p1 = new Player(Color.WHITE, p1);
		this.p2 = new Player(Color.BLACK, p2);
		currentPlayer = this.p1;
	}
	
	private void validate(Move move) {
		Piece movingPiece = board.getPieceAt(move.getFrom());
		Preconditions.checkNotNull(movingPiece, "No piece at selected from position");
		movingPiece.validate(move, board);
		Preconditions.checkState(currentPlayer.getColor() == movingPiece.getColor(), "Selected piece is not yours");
		if (board.getPieceAt(move.getTo()) != null) {
			Preconditions.checkState(movingPiece.getColor() != board.getPieceAt(move.getTo()).getColor(), "Invalid move");
		}
		
	}
	
	private void makeMove(Move move) {
		Position from = move.getFrom();
		Position to = move.getTo();
		Piece movingPiece = board.getPieceAt(from);
		validate(move);
		Piece lostPiece = board.getPieceAt(to); 
		if (lostPiece != null) {
			if (currentPlayer == p1) {
				p2LostPieces.add(lostPiece);
			} else {
				p1LostPieces.add(lostPiece);
			}
		}
		if (movingPiece instanceof King && ((King)movingPiece).isCastleValid(move, board)) {
			board.setPosition(from, null);
			board.setPosition(to, movingPiece);
			Position rookFrom;
			Position rookTo;
			if (from.getLetterIndex() > to.getLetterIndex()) {
				if (movingPiece.getColor() == Color.WHITE) {
					rookFrom = new Position('A', 1);
					rookTo = new Position('C', 1);
				} else {
					rookFrom = new Position('A', 8);
					rookTo = new Position('C', 8);
				}
			} else {
				if (movingPiece.getColor() == Color.WHITE) {
					rookFrom = new Position('H', 1);
					rookTo = new Position('F', 1);
				} else {
					rookFrom = new Position('H', 8);
					rookTo = new Position('F', 8);
				}
			}
			board.setPosition(rookTo, board.getPieceAt(rookFrom));
			board.setPosition(rookFrom, null);
			movingPiece.notifyPieceMovement(move);
			board.getPieceAt(rookFrom).notifyPieceMovement(new Move(rookFrom, rookTo));
		} else {
			board.setPosition(from, null);
			board.setPosition(to, movingPiece);
			movingPiece.notifyPieceMovement(move);
		}
	}
	
	private boolean isOver() {
		int kingCount = 0;
		for (int n = 0; n < 8; n++) {
			for (int l = 0; l < 8; l++) {
				Piece piece = board.getPieceAt(new Position(l, n));
				if (piece instanceof King) {
					kingCount++;
				}
			}		
		}
		return (kingCount == 2) ? false : true;
	}
	
	public void play(Scanner c) {
		System.out.println(currentPlayer.toString() + " is whites.");
		while (!isOver()) {
			try {
				System.out.println(board);
				makeMove(currentPlayer.nextMove(c));
				if (currentPlayer == p1) {
					currentPlayer = p2;
				} else {
					currentPlayer = p1;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (currentPlayer == p1) {
			System.out.println(p2.toString() + " won!");
		} else {
			System.out.println(p1.toString() + " won!");
		}
	}
}

