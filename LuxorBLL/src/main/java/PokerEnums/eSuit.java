package PokerEnums;

public enum eSuit {
	HEARTS(1), SPADES(2), CLUBS(3), DIAMONDS(4);
	
	private int eSuitNbr;

	private eSuit(int eSuitNbr) {
		this.eSuitNbr = eSuitNbr;
	}

	public int geteSuitNbr() {
		return eSuitNbr;
	}
	

}
