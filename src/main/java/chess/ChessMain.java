package chess;

import java.util.Scanner;

public class ChessMain {
	
	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		System.out.println("--- Hello to Amir's Chess Game ---");
		boolean keepGoing = true;
		while (keepGoing) {
			System.out.print("Player 1's name: ");
			String p1 = c.nextLine();
			System.out.print("Player 2's name: ");
			String p2 = c.nextLine();
			Match m = new Match(p1, p2);
			m.play(c);
			System.out.print("Do you want to play another game?(y/n): ");
			keepGoing = (c.nextLine().toLowerCase().equals("y")) ? true : false;
		}
		c.close();
	}
}
