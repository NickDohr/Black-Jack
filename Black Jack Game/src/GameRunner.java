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
		static boolean dealerHanding = true;
		static boolean playerHanding = true;
		static int aceCounter;
		static int ace2Counter;
		
		
		static int tenCounter;
		public static void main(String[] args)
		{
			greetUser();
			Deck.shuffle();
			dealCards();
			playGame();
			
		}
		//chanage at home
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
//					if(playerHand.get(i).getValue() == 10)
//						{
//							tenCounter++;
//							if(tenCounter == 2)
//								{
//									System.out.println("Do you want to split your tens?");
//									String splitTens = userStringInput.nextLine();
//									if(splitTens.equals("Yes") || splitTens.equals("yes"))
//										{
//											//fill into next array and then use it to re-run game
//										}
//								}
//						}
				}
			for(int i = 0; i < 2; i++)
				{
					dealerHand.add(Deck.deck.get(0));
					Deck.deck.remove(0);
					totalDealerValue = totalDealerValue + dealerHand.get(i).getValue();
				}
		}

		//still need to add the splitting of tens and then betting maybe
		public static void playGame()
		{
			int tenCounter = 0;
			System.out.println("User, your hand is: ");
			for(int i = 0; i < playerHand.size(); i++)
				{
			System.out.println(playerHand.get(i).getRank() + " of "+ playerHand.get(i).getSuit() + " with a value of " + playerHand.get(i).getValue());
					if(playerHand.get(i).getValue()== 10)
						{
							aceCounter++;
						}
				}
			
			System.out.println();
			System.out.println("The value of all of your cards right now is: "+ totalPlayerValue);
			if(totalPlayerValue == 21)
				{
					checkForWinner();
				}
			System.out.println();
			System.out.println("The Dealers Bottom Card Is: "  + dealerHand.get(0).getRank() + " of " + dealerHand.get(0).getSuit() + " with a value of " + dealerHand.get(0).getValue());
			System.out.println();
			while(playerHanding = true)
				{
			System.out.println("User, do you want to hit or stand?");
			String playDecision = userStringInput.nextLine();
			
			if(playDecision.equals("hit") || playDecision.equals("Hit"))
				{
					playerHand.add(Deck.deck.get(0));
					totalPlayerValue = totalPlayerValue + Deck.deck.get(0).getValue();
					//add in here the code to check if something is an ace and if they bust, make it so that they get to choose if ace is worth one or 11
					
					System.out.println("User, you drew a ");
					System.out.println(Deck.deck.get(0).getRank() + " of "+ Deck.deck.get(0).getSuit() + " with a value of " + Deck.deck.get(0).getValue());
					System.out.println("The value of all of your cards right now is: "+ totalPlayerValue);
					if(totalPlayerValue == 21)
						{
							checkForWinner();
						}
					Deck.deck.remove(0);
					if(totalPlayerValue > 21)
						{
							for(int i = 0; i < playerHand.size(); i++)
								{
									if(playerHand.get(i).getValue() == 11)
										{
											if(tenCounter < aceCounter)
												{
											System.out.println("You have an ace in your hand, the ace will now be changed to a value of zero");
												tenCounter++;
								
											totalPlayerValue = totalPlayerValue - 10;
											playGame();
												}
											else if(tenCounter >= aceCounter)
												{
													checkForWinner();
												}
											else
												{
													playGame();
												}
										}
										
									
								}
						
							checkForWinner();
						}
				}
			else if(playDecision.equals("stand") || playDecision.equals("Stand"))
				{
					playerHanding = false;
					displayDealerHand();
				}
			else
				{
					System.out.println("You must either pick hit or stand,");
					//playGame();
				}
				}
			
		}
		public static void displayDealerHand()
		{
			int ten2Counter = 0;
			while(dealerHanding)
				{
					System.out.println();
			System.out.println("The Dealers Hand Was: ");
			for(int i = 0; i < dealerHand.size(); i ++)
				{
			System.out.println(dealerHand.get(i).getRank() + " of "+ dealerHand.get(i).getSuit() + " with a value of " + dealerHand.get(i).getValue());
			if(dealerHand.get(i).getValue()== 10)
				{
					ace2Counter++;
				}
				}
			System.out.println("The total Dealer value is " + totalDealerValue);
			if(totalDealerValue > 21)
				{
					for(int i = 0; i < dealerHand.size(); i++)
						{
							if(dealerHand.get(i).getValue() == 11)
								{
									if(ten2Counter < ace2Counter)
										{
								
										ten2Counter++;
						
									totalDealerValue = totalDealerValue - 10;
									displayDealerHand();
										}
									else if(ten2Counter >= ace2Counter)
										{
											checkForWinner();
										}
								}
								
							
						}
				
					checkForWinner();
				}
			else if(totalDealerValue >= 17)
				{
					dealerHanding = false;
					checkForWinner();
				}
			else if(totalDealerValue <=16)
				{
					dealerHand.add(Deck.deck.get(0));
					System.out.println();
					System.out.print("The Dealer Drew A ");
					System.out.print(Deck.deck.get(0).getRank() + " of "+ Deck.deck.get(0).getSuit() + " with a value of " + Deck.deck.get(0).getValue());
					totalDealerValue = totalDealerValue + Deck.deck.get(0).getValue();
					Deck.deck.remove(0);
				}
				}
		}
		public static void checkForWinner()
		{
			if(totalPlayerValue == totalDealerValue)
				{
					System.out.println();
					System.out.println("You and the dealer both had the same value, therefore it is a push and neither of you lose money!");
					System.exit(0);
				}
			
			else if(totalDealerValue > 21 && totalPlayerValue <= 21)
				{
					System.out.println();
					System.out.println("The Dealer went over 21, and therefore the player wins!");
					System.exit(0);
				}
			else if(totalDealerValue <= 21 && totalPlayerValue > 21)
				{
					System.out.println();
					System.out.println("The Player went over 21, and therefore the dealer wins!");
					System.exit(0);
				}
			else if(totalPlayerValue == 21 && totalDealerValue != 21)
				{
					System.out.println();
					System.out.println("The Player Won, Congratulations!");
					System.exit(0);
				}
			else if(totalDealerValue == 21 && totalPlayerValue != 21)
				{
					System.out.println();
					System.out.println("The Dealer has won! Too Bad!");
					System.exit(0);
				}
			else if(totalDealerValue > totalPlayerValue)
				{
					System.out.println();
					System.out.println("The Dealer wins! You lose!");
					System.exit(0);
				}
			else if(totalDealerValue < totalPlayerValue)
				{
					System.out.println();
					System.out.println("The Player Wins! The Dealer Loses!");
					System.exit(0);
				}
			
			
			
		}
	}
