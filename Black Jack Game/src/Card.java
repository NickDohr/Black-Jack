
public class Card
	{
		String suit;
		String rank;
		int value;
		public Card (String r, String s, int v)
		{
			rank = r;
			suit = s;
			value = v;
		}
		public String getSuit()
			{
				return suit;
			}
		public void setSuit(String suit)
			{
				this.suit = suit;
			}
		public String getRank()
			{
				return rank;
			}
		public void setRank(String rank)
			{
				this.rank = rank;
			}
		public int getValue()
			{
				return value;
			}
		public void setValue(int value)
			{
				this.value = value;
			}
		
		
		
	}
	
