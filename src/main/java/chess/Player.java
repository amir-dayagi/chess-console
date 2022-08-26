package chess;

import java.util.Scanner;

public class Player {
	private final Color color;
	private final String name;
	
	public Player(Color color) {
		this.color = color;
		name = "player";
	}
	
	public Player(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}
	
	public String toString() {
		return name;
	}

	public Move nextMove(Scanner c){
		System.out.print("Your move (ex. A1 to A2):");
		String moveInput = c.nextLine();
		String[] move = moveInput.split(" ");
		Position from = new Position(move[0].charAt(0), Integer.parseInt(move[0].substring(1)));
		Position to = new Position(move[2].charAt(0), Integer.parseInt(move[2].substring(1)));
		return new Move(from, to);
	}
}
