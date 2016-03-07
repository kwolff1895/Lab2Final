package pokerBase;
import java.util.ArrayList;
import PokerEnums.eRank;
import PokerEnums.eSuit;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deckCards = new ArrayList<Card>();
	
	public Deck(){
		for (short i = 0; i<=3;i++){
			eSuit SuitValue = eSuit.values()[i];
			for (short j = 0; j<=12;j++){
				eRank RankValue = eRank.values()[j];			
				Card NewCard = new Card(SuitValue,RankValue,(13*i)+j+1);
				deckCards.add(NewCard);
		}
		
	}
		Collections.shuffle(deckCards);
	}
	public Card Draw(){
		Card c = deckCards.get(0);
		deckCards.remove(0);
		return c;
	}

}
