package PokerEnums;

public enum eCardNumber {
	
	FirstCard(0),
	SecondCard(1),
	ThirdCard(2),
	FourthCard(3),
	FifthCard(4);
	
	private eCardNumber(final int CardNo){
		this.CardNo = CardNo;
	}
	private int CardNo;
	
	public int getCardNo(){
		return CardNo;
	}
	
}
