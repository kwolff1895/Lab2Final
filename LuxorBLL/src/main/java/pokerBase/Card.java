package pokerBase;
import PokerEnums.eSuit;

import java.util.Comparator;

import PokerEnums.eRank;

public class Card implements Comparable<Card> {

	
	private eSuit eSuit;
	private eRank eRank;
	private int iCardNbr;
	
	public Card(PokerEnums.eSuit eSuit, PokerEnums.eRank eRank, int iCardNbr) {
		super();
		this.eSuit = eSuit;
		this.eRank = eRank;
		this.iCardNbr = iCardNbr;
	}

	public eSuit geteSuit() {
		return eSuit;
	}

	public eRank geteRank() {
		return eRank;
	}

	public int getiCardNbr() {
		return iCardNbr;
	}

	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.geteRank().getiRankNbr();
		   int Cno2 = c2.geteRank().getiRankNbr();

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }
};

	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		Card c = (Card)o;
		return c.geteRank().compareTo(this.geteRank());
		
	}
}