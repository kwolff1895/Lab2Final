package pokerBase;
import PokerEnums.eSuit;
import PokerEnums.eRank;

public class Card implements Comparable{
	
	private eSuit eSuit;
	private eRank eRank;
	private int iCardNumber;
	

	public Card(PokerEnums.eSuit eSuit, PokerEnums.eRank eRank, int iCardNumber) {
		super();
		this.eSuit = eSuit;
		this.eRank = eRank;
		this.iCardNumber = iCardNumber;
	}
	
	

	public eSuit geteSuit() {
		return eSuit;
	}



	public eRank geteRank() {
		return eRank;
	}



	public int getiCardNumber() {
		return iCardNumber;
	}


	public int compareTo(Object o) {
		Card c = (Card)o;
		return c.geteRank().compareTo(this.geteRank());
	}


	}

