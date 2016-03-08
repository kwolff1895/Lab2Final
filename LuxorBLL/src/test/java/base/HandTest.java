package base;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Exceptions.HandException;
import PokerEnums.eCardNumber;
import PokerEnums.eRank;
import PokerEnums.eSuit;
import pokerBase.Card;
import pokerBase.Hand;
import pokerBase.HandScore;

public class HandTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private Hand SetHand(ArrayList<Card> setCards, Hand h) {
		Object t = null;

		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("pokerBase.Hand");
			// Create a new instance 't' from the no-arg Deck constructor
			t = c.newInstance();
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method msetCardsInHand = c.getDeclaredMethod("setCardsInHand", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			msetCardsInHand.setAccessible(true);
			// invoke 'msetCardsInHand'
			Object oDraw = msetCardsInHand.invoke(t, setCards);

		} catch (ClassNotFoundException x) {
			x.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		h = (Hand) t;
		return h;

	}

	/**
	 * This test checks to see if a HandException is throw if the hand only has
	 * two cards.
	 * 
	 * @throws Exception
	 */
	@Test(expected = HandException.class)
	public void TestEvalShortHand() throws Exception {

		Hand h = new Hand();

		ArrayList<Card> ShortHand = new ArrayList<Card>();
		ShortHand.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		ShortHand.add(new Card(eSuit.CLUBS, eRank.ACE, 0));

		h = SetHand(ShortHand, h);

		// This statement should throw a HandException
		h = Hand.EvaluateHand(h);
	}

	@Test
	public void TestRoyalFlush() {

		HandScore hs = new HandScore();
		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.KING, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.QUEEN, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.JACK, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.TEN, 0));

		Hand h = new Hand();
		h = SetHand(RoyalFlush, h);

		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpectedIsHandRoyalFlush = true;

		// Did this evaluate to Royal Flush?
		assertEquals(bActualIsHandRoyalFlush, bExpectedIsHandRoyalFlush);

	}

	public void TestRoyalFlushEval() {

		ArrayList<Card> RoyalFlush = new ArrayList<Card>();
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.KING, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.QUEEN, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.JACK, 0));
		RoyalFlush.add(new Card(eSuit.CLUBS, eRank.TEN, 0));

		Hand h = new Hand();
		h = SetHand(RoyalFlush, h);

		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {
			e.printStackTrace();
			fail("TestRoyalFlushEval failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandRoyalFlush = Hand.isHandRoyalFlush(h, hs);
		boolean bExpectedIsHandRoyalFlush = true;

		// Did this evaluate to Royal Flush?
		assertEquals(bActualIsHandRoyalFlush, bExpectedIsHandRoyalFlush);
	}

	@Test
	public void TestStraightFlush() {

		HandScore hs = new HandScore();
		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		StraightFlush.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		StraightFlush.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(StraightFlush, h);

		boolean bActualIsHandStraightFlush = Hand.isHandStraightFlush(h, hs);
		boolean bExpectedIsHandStraightFlush = true;

		// Did this evaluate to Straight Flush?
		assertEquals(bActualIsHandStraightFlush, bExpectedIsHandStraightFlush);
	}

	public void TestStraightFlushEval() {

		ArrayList<Card> StraightFlush = new ArrayList<Card>();
		StraightFlush.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		StraightFlush.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		StraightFlush.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		StraightFlush.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(StraightFlush, h);

		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {
			e.printStackTrace();
			fail("TestRoyalFlushEval failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandStraightFlush = Hand.isHandStraightFlush(h, hs);
		boolean bExpectedIsHandStraightFlush = true;

		// Did this evaluate to Straight Flush?
		assertEquals(bActualIsHandStraightFlush, bExpectedIsHandStraightFlush);

	}

	@Test
	public void TestFourOfAKind() {

		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.SPADES, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.DIAMONDS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FourOfAKind, h);

		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;

		// Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind, bExpectedIsHandFourOfAKind);
		// Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it a King?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}

	public void TestFourOfAKindEval() {

		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.SPADES, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.DIAMONDS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FourOfAKind, h);

		try {
			h = Hand.EvaluateHand(h);
		} catch (HandException e) {
			e.printStackTrace();
			fail("TestFourOfAKindEval failed");
		}
		HandScore hs = h.getHandScore();
		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;

		// Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind, bExpectedIsHandFourOfAKind);
		// Was the four of a kind an Ace?
		assertEquals(hs.getHiHand(), eRank.ACE.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it a King?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteRank(), eRank.KING);
	}

	@Test
	public void TestFourOfAKind2() {

		HandScore hs = new HandScore();
		ArrayList<Card> FourOfAKind = new ArrayList<Card>();
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FourOfAKind.add(new Card(eSuit.CLUBS, eRank.KING, 0));
		FourOfAKind.add(new Card(eSuit.HEARTS, eRank.KING, 0));
		FourOfAKind.add(new Card(eSuit.SPADES, eRank.KING, 0));
		FourOfAKind.add(new Card(eSuit.DIAMONDS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FourOfAKind, h);

		boolean bActualIsHandFourOfAKind = Hand.isHandFourOfAKind(h, hs);
		boolean bExpectedIsHandFourOfAKind = true;

		// Did this evaluate to Four of a Kind?
		assertEquals(bActualIsHandFourOfAKind, bExpectedIsHandFourOfAKind);
		// Was the four of a kind an King?
		assertEquals(hs.getHiHand(), eRank.KING.getiRankNbr());
		// FOAK has one kicker. Was it a Club?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteSuit(), eSuit.CLUBS);
		// FOAK has one kicker. Was it an Ace?
		assertEquals(hs.getKickers().get(eCardNumber.FirstCard.getCardNo()).geteRank(), eRank.ACE);
	}

	@Test
	public void TestFullHouse() {

		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.SPADES, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FullHouse, h);

		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;

		// Did this evaluate to Full House?
		assertEquals(bActualIsHandFullHouse, bExpectedIsHandFullHouse);
	}

	@Test
	public void TestFullHouse2() {

		HandScore hs = new HandScore();
		ArrayList<Card> FullHouse = new ArrayList<Card>();
		FullHouse.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.HEARTS, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.SPADES, eRank.ACE, 0));
		FullHouse.add(new Card(eSuit.SPADES, eRank.KING, 0));
		FullHouse.add(new Card(eSuit.DIAMONDS, eRank.KING, 0));

		Hand h = new Hand();
		h = SetHand(FullHouse, h);

		boolean bActualIsHandFullHouse = Hand.isHandFullHouse(h, hs);
		boolean bExpectedIsHandFullHouse = true;

		// Did this evaluate to Full House?
		assertEquals(bActualIsHandFullHouse, bExpectedIsHandFullHouse);
	}

	@Test
	public void TestFlush() {

		HandScore hs = new HandScore();
		ArrayList<Card> Flush = new ArrayList<Card>();
		Flush.add(new Card(eSuit.CLUBS, eRank.ACE, 0));
		Flush.add(new Card(eSuit.CLUBS, eRank.KING, 0));
		Flush.add(new Card(eSuit.CLUBS, eRank.JACK, 0));
		Flush.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		Flush.add(new Card(eSuit.CLUBS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(Flush, h);

		boolean bActualIsHandFlush = Hand.isHandFlush(h, hs);
		boolean bExpectedIsHandFlush = true;

		// Did this evaluate to Flush?
		assertEquals(bActualIsHandFlush, bExpectedIsHandFlush);
	}

	@Test
	public void TestStraight() {

		HandScore hs = new HandScore();
		ArrayList<Card> Straight = new ArrayList<Card>();
		Straight.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		Straight.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		Straight.add(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		Straight.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		Straight.add(new Card(eSuit.CLUBS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(Straight, h);

		boolean bActualIsHandStraight = Hand.isHandStraight(h, hs);
		boolean bExpectedIsHandStraight = true;

		// Did this evaluate to Straight?
		assertEquals(bActualIsHandStraight, bExpectedIsHandStraight);
	}

	@Test
	public void TestThreeOfAKind() {

		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.THREE, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(ThreeOfAKind, h);

		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsHandThreeOfAKind = true;

		// Did this evaluate to Three Of A Kind?
		assertEquals(bActualIsHandThreeOfAKind, bExpectedIsHandThreeOfAKind);
	}

	@Test
	public void TestThreeOfAKind2() {

		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.TWO, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(ThreeOfAKind, h);

		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsHandThreeOfAKind = true;

		// Did this evaluate to Three Of A Kind?
		assertEquals(bActualIsHandThreeOfAKind, bExpectedIsHandThreeOfAKind);
	}

	@Test
	public void TestThreeOfAKind3() {

		HandScore hs = new HandScore();
		ArrayList<Card> ThreeOfAKind = new ArrayList<Card>();
		ThreeOfAKind.add(new Card(eSuit.DIAMONDS, eRank.EIGHT, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.EIGHT, 0));
		ThreeOfAKind.add(new Card(eSuit.HEARTS, eRank.EIGHT, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		ThreeOfAKind.add(new Card(eSuit.CLUBS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(ThreeOfAKind, h);

		boolean bActualIsHandThreeOfAKind = Hand.isHandThreeOfAKind(h, hs);
		boolean bExpectedIsHandThreeOfAKind = true;

		// Did this evaluate to Three Of A Kind?
		assertEquals(bActualIsHandThreeOfAKind, bExpectedIsHandThreeOfAKind);
	}

	@Test
	public void TestTwoPair() {

		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(TwoPair, h);

		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsHandTwoPair = true;

		// Did this evaluate to Two Pair?
		assertEquals(bActualIsHandTwoPair, bExpectedIsHandTwoPair);
	}

	@Test
	public void TestTwoPair2() {

		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.SEVEN, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.SEVEN, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(TwoPair, h);

		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsHandTwoPair = true;

		// Did this evaluate to Two Pair?
		assertEquals(bActualIsHandTwoPair, bExpectedIsHandTwoPair);
	}

	@Test
	public void TestTwoPair3() {

		HandScore hs = new HandScore();
		ArrayList<Card> TwoPair = new ArrayList<Card>();
		TwoPair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.SIX, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		TwoPair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(TwoPair, h);

		boolean bActualIsHandTwoPair = Hand.isHandTwoPair(h, hs);
		boolean bExpectedIsHandTwoPair = true;

		// Did this evaluate to Two Pair?
		assertEquals(bActualIsHandTwoPair, bExpectedIsHandTwoPair);
	}

	@Test
	public void TestOnePair() {

		HandScore hs = new HandScore();
		ArrayList<Card> OnePair = new ArrayList<Card>();
		OnePair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.TWO, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(OnePair, h);

		boolean bActualIsHandOnePair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandOnePair = true;

		// Did this evaluate to One Pair?
		assertEquals(bActualIsHandOnePair, bExpectedIsHandOnePair);
	}

	@Test
	public void TestOnePair2() {
		HandScore hs = new HandScore();
		ArrayList<Card> OnePair = new ArrayList<Card>();
		OnePair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.THREE, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(OnePair, h);

		boolean bActualIsHandOnePair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandOnePair = true;

		// Did this evaluate to One Pair?
		assertEquals(bActualIsHandOnePair, bExpectedIsHandOnePair);
	}

	@Test
	public void TestOnePair3() {

		HandScore hs = new HandScore();
		ArrayList<Card> OnePair = new ArrayList<Card>();
		OnePair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.FIVE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.FIVE, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(OnePair, h);

		boolean bActualIsHandOnePair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandOnePair = true;

		// Did this evaluate to One Pair?
		assertEquals(bActualIsHandOnePair, bExpectedIsHandOnePair);
	}

	@Test
	public void TestOnePair4() {

		HandScore hs = new HandScore();
		ArrayList<Card> OnePair = new ArrayList<Card>();
		OnePair.add(new Card(eSuit.DIAMONDS, eRank.SIX, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.SIX, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.FOUR, 0));
		OnePair.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		OnePair.add(new Card(eSuit.HEARTS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(OnePair, h);

		boolean bActualIsHandOnePair = Hand.isHandPair(h, hs);
		boolean bExpectedIsHandOnePair = true;

		// Did this evaluate to One Pair?
		assertEquals(bActualIsHandOnePair, bExpectedIsHandOnePair);
	}

	@Test
	public void TestHighCard() {

		HandScore hs = new HandScore();
		ArrayList<Card> HighCard = new ArrayList<Card>();
		HighCard.add(new Card(eSuit.DIAMONDS, eRank.ACE, 0));
		HighCard.add(new Card(eSuit.CLUBS, eRank.TEN, 0));
		HighCard.add(new Card(eSuit.HEARTS, eRank.EIGHT, 0));
		HighCard.add(new Card(eSuit.CLUBS, eRank.THREE, 0));
		HighCard.add(new Card(eSuit.CLUBS, eRank.TWO, 0));

		Hand h = new Hand();
		h = SetHand(HighCard, h);

		boolean bActualIsHandHighCard = Hand.isHandHighCard(h, hs);
		boolean bExpectedIsHandHighCard = true;

		// Did this evaluate to High Card?
		assertEquals(bActualIsHandHighCard, bExpectedIsHandHighCard);
	}

}

// Status API Training Shop Blog About Pricing
// © 2016 GitHub, Inc. Terms Privacy Security Contact Help