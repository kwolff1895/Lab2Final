package pokerBase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import Exceptions.DeckException;
import Exceptions.HandException;
import PokerEnums.eCardNumber;
import PokerEnums.eHandStrength;

public class Hand {

	private ArrayList<Card> CardsInHand;
	private ArrayList<Card> BestCardsInHand;
	private HandScore HandScore;
	private boolean bScored = false;

	public Hand() {
		CardsInHand = new ArrayList<Card>();
		BestCardsInHand = new ArrayList<Card>();
	}

	public ArrayList<Card> getCardsInHand() {
		return CardsInHand;
	}

	public ArrayList<Card> getBestCardsInHand() {
		return BestCardsInHand;
	}

	public void setBestCardsInHand(ArrayList<Card> bestCardsInHand) {
		BestCardsInHand = bestCardsInHand;
	}

	public HandScore getHandScore() {
		return HandScore;
	}

	public void setHandScore(HandScore handScore) {
		HandScore = handScore;
	}

	public boolean isbScored() {
		return bScored;
	}

	public void setbScored(boolean bScored) {
		this.bScored = bScored;
	}

	public Hand AddCardToHand(Card c) {
		CardsInHand.add(c);
		return this;
	}

	public Hand Draw(Deck d) throws DeckException {
		CardsInHand.add(d.Draw());
		return this;
	}

	/**
	 * EvaluateHand is a static method that will score a given Hand of cards
	 * 
	 * @param h
	 * @return
	 * @throws HandException
	 */
	public static Hand EvaluateHand(Hand h) throws HandException {

		Collections.sort(h.getCardsInHand());

		// Collections.sort(h.getCardsInHand(), Card.CardRank);

		if (h.getCardsInHand().size() != 5) {
			throw new HandException(h);
		}

		HandScore hs = new HandScore();
		try {
			Class<?> c = Class.forName("pokerBase.Hand");

			for (eHandStrength hstr : eHandStrength.values()) {
				Class[] cArg = new Class[2];
				cArg[0] = pokerBase.Hand.class;
				cArg[1] = pokerBase.HandScore.class;

				Method meth = c.getMethod(hstr.getEvalMethod(), cArg);
				Object o = meth.invoke(null, new Object[] { h, hs });

				// If o = true, that means the hand evaluated- skip the rest of
				// the evaluations
				if ((Boolean) o) {
					break;
				}
			}

			h.bScored = true;
			h.HandScore = hs;

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return h;
	}

	public static boolean isHandFiveOfAKind(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.FiveOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
		}
		return bHandCheck;
	}

	public static boolean isHandRoyalFlush(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteSuit()) {
			if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr() == 10) {
				if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr() == 11) {
					if (h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr() == 12) {
						if (h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank().getiRankNbr() == 13) {
							if (h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()).geteRank()
									.getiRankNbr() == 14) {
							}

						}
					}
				}
			}
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.RoyalFlush.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);

		}
		return bHandCheck;
	}

	public static boolean isHandStraightFlush(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteSuit()) {
			if ((h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
					.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr())) {
				if ((h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
						.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr())) {
					if ((h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
							.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank().getiRankNbr())) {
						if ((h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank().getiRankNbr()
								+ 1) == (h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()).geteRank()
										.getiRankNbr())) {
						}
					}
				}
			}
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.StraightFlush.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
			hs.setKickers(kickers);
		}
		return bHandCheck;
	}

	public static boolean isHandFourOfAKind(Hand h, HandScore hs) {

		boolean bHandCheck = false;

		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FourthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.FourOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
			hs.setKickers(kickers);

		} else if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.FourOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		}

		return bHandCheck;
	}

	public static boolean isHandFullHouse(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.ThirdCard.getCardNo()).geteRank()) {
			if (h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
				bHandCheck = true;
				hs.setHandStrength(eHandStrength.FullHouse.getHandStrength());
				hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
				hs.setLoHand(0);
			}
		} else if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.SecondCard.getCardNo()).geteRank()) {
			if (h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
				bHandCheck = true;
				hs.setHandStrength(eHandStrength.FullHouse.getHandStrength());
				hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
				hs.setLoHand(0);
			}
		}
		return bHandCheck;

	}

	public static boolean isHandFlush(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteSuit() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteSuit()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.Flush.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);

		}
		return bHandCheck;
	}

	public static boolean isHandStraight(Hand h, HandScore hs) {
		boolean bHandCheck = false;
		if ((h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
				.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr())) {
			if ((h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
					.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr())) {
				if ((h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
						.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank().getiRankNbr())) {
					if ((h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank().getiRankNbr() + 1) == (h
							.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()).geteRank().getiRankNbr())) {
					}
				}
			}
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.Straight.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
			hs.setKickers(kickers);
		}

		return bHandCheck;

	}

	public static boolean isHandThreeOfAKind(Hand h, HandScore hs) {
		boolean bHandCheck = false;

		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.ThirdCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
			hs.setKickers(kickers);

		} else if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FourthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.ThreeOfAKind.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		}

		return bHandCheck;
	}

	public static boolean isHandTwoPair(Hand h, HandScore hs) {
		boolean bHandCheck = false;

		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.SecondCard.getCardNo()).geteRank()) {
			if (h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNumber.FourthCard.getCardNo()).geteRank()) {
				bHandCheck = true;
				hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
				hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
				hs.setLoHand(0);
				ArrayList<Card> kickers = new ArrayList<Card>();
				kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
				hs.setKickers(kickers);

			} else if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNumber.ThirdCard.getCardNo()).geteRank()) {
				if (h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
					bHandCheck = true;
					hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
					hs.setHiHand(h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr());
					hs.setLoHand(0);
					ArrayList<Card> kickers = new ArrayList<Card>();
					kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
					hs.setKickers(kickers);
				}
			} else if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
					.get(eCardNumber.ThirdCard.getCardNo()).geteRank()) {
				if (h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
						.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
					bHandCheck = true;
					hs.setHandStrength(eHandStrength.TwoPair.getHandStrength());
					hs.setHiHand(h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr());
					hs.setLoHand(0);
					ArrayList<Card> kickers = new ArrayList<Card>();
					kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
					hs.setKickers(kickers);
				}
			}
		}
			return bHandCheck;
		

	}

	public static boolean isHandPair(Hand h, HandScore hs) {
		boolean bHandCheck = false;

		if (h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.SecondCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.OnePair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FifthCard.getCardNo()));
			hs.setKickers(kickers);

		} else if (h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.ThirdCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.OnePair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.SecondCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FourthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.OnePair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		} else if (h.getCardsInHand().get(eCardNumber.FourthCard.getCardNo()).geteRank() == h.getCardsInHand()
				.get(eCardNumber.FifthCard.getCardNo()).geteRank()) {
			bHandCheck = true;
			hs.setHandStrength(eHandStrength.OnePair.getHandStrength());
			hs.setHiHand(h.getCardsInHand().get(eCardNumber.ThirdCard.getCardNo()).geteRank().getiRankNbr());
			hs.setLoHand(0);
			ArrayList<Card> kickers = new ArrayList<Card>();
			kickers.add(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()));
			hs.setKickers(kickers);
		}

		return bHandCheck;
	}

	public static boolean isHandHighCard(Hand h, HandScore hs) {
		boolean bHandCheck = false;
	
		bHandCheck = true;
		hs.setHandStrength(eHandStrength.HighCard.getHandStrength());
		hs.setHiHand(h.getCardsInHand().get(eCardNumber.FirstCard.getCardNo()).geteRank().getiRankNbr());
		hs.setLoHand(0);
		return bHandCheck;
	}
	

}