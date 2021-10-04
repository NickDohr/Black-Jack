import java.util.ArrayList;
import java.util.Scanner;
public class GameRunner
	{
		static ArrayList<Card> playerHand = new ArrayList<Card>();
		static ArrayList<Card> dealerHand = new ArrayList<Card>();
		static Scanner userStringInput = new Scanner(System.in);
		static Scanner userIntInput = new Scanner(System.in);
		static int totalDealerValue;
		static int totalPlayerValue;
		public static void main(String[] args)
		{
			greetUser();
			Deck.shuffle();
			dealCards();
			displayUserHand();
			playGame();
			
		}
		public static void greetUser()
		{
			System.out.println("Hello user, what is your name?");
			String name = userStringInput.nextLine();
			System.out.println("You can have between 1 and 8 decks, how many do you want?");
			int numberOfDecks = userIntInput.nextInt();
			for(int i = 0; i < numberOfDecks; i++)
				{
					Deck.fillDeck();
				}
		}
		public static void dealCards()
		{
			for(int i = 0; i < 2; i++)
				{
					playerHand.add(Deck.deck.get(0));
					Deck.deck.remove(0);
					totalPlayerValue = totalPlayerValue + playerHand.get(i).getValue();
				}
			for(int i = 0; i < 2; i++)
				{
					dealerHand.add(Deck.deck.get(0));
					Deck.deck.remove(0);
					totalDealerValue = totalDealerValue + dealerHand.get(i).getValue();
				}
		}
		// combine into 1 class
		public static void displayUserHand()
			{
			
				if(totalPlayerValue >= 21)
					{
						checkForWinner();
					}
				System.out.println("User, your hand is: ");
				for(int i = 0; i < playerHand.size(); i++)
					{
				System.out.println(playerHand.get(i).getRank() + " of "+ playerHand.get(i).getSuit() + " with a value of " + playerHand.get(i).getValue());
				
					}
				
				System.out.println("The value of all of your cards right now is: "+ totalPlayerValue);
				playGame();
			}
		public static void playGame()
		{
			System.out.println();
			//System.out.println("The value of all of your cards right now is: "+ totalPlayerValue);
			
			if(totalPlayerValue == 21 && totalDealerValue == 21)
				{
					System.out.println("You and the dealer both had a total value of 21, therefore it is a push and neither of you lose money!");
				}
			
			System.out.println("User, do you want to hit or stand?");
			String playDecision = userStringInput.nextLine();
			
			if(playDecision.equals("hit") || playDecision.equals("Hit"))
				{
					playerHand.add(Deck.deck.get(0));
					totalPlayerValue = totalPlayerValue + Deck.deck.get(0).getValue();
					
					Deck.deck.remove(0);
					displayUserHand();
				}
			else if(playDecision.equals("stand") || playDecision.equals("Stand"))
				{
					
					displayDealerHand();
					
				}
			else
				{
					System.out.println("You must either pick hit or stand,");
					//playGame();
				}
			
		}
		public static void displayDealerHand()
		{
			//if(totalDealerValue >= 17)
				//{
			System.out.println("The Dealers Hand Was: ");
			for(int i = 0; i < dealerHand.size(); i ++)
				{
			System.out.println(dealerHand.get(i).getRank() + " of "+ dealerHand.get(i).getSuit() + " with a value of " + dealerHand.get(i).getValue());
				}
			checkForWinner();
				//}
			//else if(totalDealerValue <=16)
					dealerHand.add(Deck.deck.get(0));
					totalDealerValue = totalDealerValue + Deck.deck.get(0).getValue();
					Deck.deck.remove(0);
	
			//	}

			//System.out.println("The Total Dealer Value Was " + totalDealerValue);
			checkForWinner();
		}
		public static void checkForWinner()
		{
			if(totalPlayerValue == 21 && totalDealerValue == 21)
				{
					System.out.println("You and the dealer both had a total value of 21, therefore it is a push and neither of you lose money!");
				}
			else if(totalPlayerValue == 21 && totalDealerValue != 21)
				{
					System.out.println("The Player Won, Congratulations!");
					System.exit(0);
				}
			else if(totalDealerValue == 21 && totalPlayerValue != 21)
				{
					System.out.println("The Dealer has won! Too Bad!");
					System.exit(0);
				}
			
			
		}
	}
