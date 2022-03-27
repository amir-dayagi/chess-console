package chess;

public class Position {
	private final int letterIndex;
	private final int numIndex;
	
	public Position(int letterIndex, int numIndex) {
		this.letterIndex = letterIndex;
		this.numIndex = numIndex;
	}
	
	public Position(char letter, int num) {
		letterIndex = Character.toUpperCase(letter) - 'A';
		numIndex = num-1;
	}

	public int getLetterIndex() {
		return letterIndex;
	}
	
	public int getNumIndex() {
		return numIndex;
	}
	
	public char getLetter() {
		return (char)(letterIndex + 'A');
	}

	public int getNum() {
		return numIndex + 1;
	}

	
	private boolean isBetweenZeroAndEight(int n) {
		return n >= 0 && n <= 8;
	}
	
	public boolean isInBoard() {
		return isBetweenZeroAndEight(getNumIndex()) && isBetweenZeroAndEight(getLetterIndex());
	}
}
