package assignment2; /**
* Your name here: Mira Kandlikar-Bloch
* Your McGill ID here: 261035244
**/
import java.util.Random;


public class Deck {
	public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
	public static Random gen = new Random();

	public int numOfCards; // contains the total number of cards in the deck
	public Card head; // contains a pointer to the card on the top of the deck

	/*
	 * TODO: Initializes a assignment2.Deck object using the inputs provided
	 */
	public Deck(int numOfCardsPerSuit, int numOfSuits) {
		if (1 <= numOfCardsPerSuit && 13 >= numOfCardsPerSuit && 1 <= numOfSuits && suitsInOrder.length >= numOfSuits) {
			for (int i = 0; i < numOfSuits; i++) {
				for (int j = 1; j <= numOfCardsPerSuit; j++) {
					Card c = new PlayingCard(suitsInOrder[i], j);
					this.addCard(c);
				}
			}
			Card rJ = new Joker("Red");
			Card bJ = new Joker("Black");
			this.addCard(rJ);
			this.addCard(bJ);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * TODO: Implements a copy constructor for assignment2.Deck using Card.getCopy().
	 * This method runs in O(n), where n is the number of cards in d.
	 */
	public Deck(Deck d) {
		Card c = d.head;
		this.head = c;
		for (int i = 0; i < d.numOfCards; i++) {
			this.addCard(c.getCopy());
			c = c.next;
		}
	}

	/*
	 * For testing purposes we need a default constructor.
	 */
	public Deck() {
	}

	/*
	 * TODO: Adds the specified card at the bottom of the deck. This
	 * method runs in $O(1)$.
	 */
	public void addCard(Card c) {
		if (this.numOfCards == 0) {
			this.head = c;
			c.next = c;
			c.prev = c;
		} else if (this.numOfCards == 1) {
			this.head.prev = c;
			c.next = this.head;
			this.head.next = c;
			c.prev = this.head;
		} else {
			Card tmp = this.head.prev;
			this.head.prev = c;
			c.next = head;
			c.prev = tmp;
			tmp.next = c;
		}
		this.numOfCards++;
	}

	/*
	 * TODO: Shuffles the deck using the algorithm described in the pdf.
	 * This method runs in O(n) and uses O(n) space, where n is the total
	 * number of cards in the deck.
	 */
	public void shuffle() {
		if (this.numOfCards == 0) {
			return;
		}
		if (this.numOfCards == 1) {
			return;
		}
		Card[] cards = new Card[this.numOfCards];
		Card c = this.head;
		int k;
		for (int i = 0; i < this.numOfCards; i++) {
			cards[i] = c;
			c = c.next;
		}
		for (int j = this.numOfCards - 1; j > 0; j--) {
			k = gen.nextInt(j + 1);
			Card tmp = cards[j];
			cards[j] = cards[k];
			cards[k] = tmp;
		}
		this.head.next = null;
		this.head.prev = null;
		this.head = null;
		this.numOfCards = 0;

		for (int m = 0; m < cards.length; m++) {
			this.addCard(cards[m]);
		}

	}

	/*
	 * TODO: Returns a reference to the joker with the specified color in
	 * the deck. This method runs in O(n), where n is the total number of
	 * cards in the deck.
	 */
	public Joker locateJoker(String color) {
		if (this.numOfCards == 0)
			return null;
		Card c = this.head;
		char x = color.toUpperCase().charAt(0);
		for (int i = 0; i < this.numOfCards; i++) {
			String y = c.toString();
			if (c instanceof Joker && y.charAt(0) == x) {
				return (Joker) c;
			} else {
				c = c.next;
			}

		}
		return null;
	}

	/*
	 * TODO: Moved the specified Card, p positions down the deck. You can
	 * assume that the input Card does belong to the deck (hence the deck is
	 * not empty). This method runs in O(p).
	 */
	public void moveCard(Card c, int p) {
		if (c != null) {
			c.prev.next = c.next;
			c.next.prev = c.prev;

			Card pointer = c;
			for (int i = 1; i <= p; i++) {
				pointer = pointer.next;
			}
			c.next = pointer.next;
			c.prev = pointer;

			pointer.next.prev = c;
			pointer.next = c;
		}
	}

	/*
	 * TODO: Performs a triple cut on the deck using the two input cards. You
	 * can assume that the input cards belong to the deck and the first one is
	 * nearest to the top of the deck. This method runs in O(1)
	 */
	public void tripleCut(Card firstCard, Card secondCard) {
		if (firstCard == this.head) {
			if (secondCard == this.head.prev) {
				return;
			} else {
				this.head = secondCard.next;
			}
		} else if (secondCard == this.head.prev) {
			this.head = firstCard;
		} else {
			Card head2 = secondCard.next;
			Card tail = firstCard.prev;
			Card beforeFirst = this.head.prev;
			Card afterSecond = this.head;

			firstCard.prev = beforeFirst;
			beforeFirst.next = firstCard;

			secondCard.next = afterSecond;
			afterSecond.prev = secondCard;

			head2.prev = tail;
			tail.next = head2;
			this.head = head2;
		}
	}


	/*
	 * TODO: Performs a count cut on the deck. Note that if the value of the
	 * bottom card is equal to a multiple of the number of cards in the deck,
	 * then the method should not do anything. This method runs in O(n).
	 */
	public void countCut() {
		if (this.head != null && this.numOfCards > 3) {
			int tailValue = this.head.prev.getValue();
			if (tailValue != 0) {

				int cutValue = this.head.prev.getValue() % this.numOfCards;
				if (this.head.next.next == this.head.prev && cutValue == 1) {
					return;
				}

				Card temp = this.head;
				Card tail = this.head.prev;

				tail.next.prev = tail.prev;
				tail.prev.next = tail.next;
				this.numOfCards = this.numOfCards - 1;

				for (int i = 0; i < cutValue; i++) {
					this.head = temp.next;
					temp.next.prev = temp.prev;
					temp.prev.next = temp.next;
					numOfCards--;
					this.addCard(temp);
					temp = temp.next;
				}
				this.addCard(tail);
			}
		}
		return;
	}


	/*
	 * TODO: Returns the card that can be found by looking at the value of the
	 * card on the top of the deck, and counting down that many cards. If the
	 * card found is a Joker, then the method returns null, otherwise it returns
	 * the Card found. This method runs in O(n).
	 */
	public Card lookUpCard() {
		int headValue = this.head.getValue();
		Card j = this.head;
		for (int i = 0; i < headValue; i++) {
			j = j.next;
		}
		if (j.toString().charAt(1) == 'J')
			return null;
		return j;
	}

	/*
	 * TODO: Uses the Solitaire algorithm to generate one value for the keystream
	 * using this deck. This method runs in O(n).
	 */
	public int generateNextKeystreamValue() {
		int keystreamValue;
		Card keyStreamCard=null;

		while (keyStreamCard== null) {
			Card redJoker = this.locateJoker("Red");
			Card blackJoker = this.locateJoker("Black");
			this.moveCard(redJoker, 1);
			this.moveCard(blackJoker, 2);

			Card pointer = this.head;
			boolean isBlack = false;

			while (pointer != redJoker) {
				if (pointer == blackJoker) {
					isBlack = true;
					break;
				}
				pointer = pointer.next;
			}

			if (isBlack) {
				this.tripleCut(blackJoker, redJoker);
			} else {
				this.tripleCut(redJoker, blackJoker);
			}
			this.countCut();

			keyStreamCard = this.lookUpCard();

		}
		keystreamValue=keyStreamCard.getValue();
		return keystreamValue;
	}


	public abstract class Card {
		public Card next;
		public Card prev;

		public abstract Card getCopy();

		public abstract int getValue();

	}

	public class PlayingCard extends Card {
		public String suit;
		public int rank;

		public PlayingCard(String s, int r) {
			this.suit = s.toLowerCase();
			this.rank = r;
		}

		public String toString() {
			String info = "";
			if (this.rank == 1) {
				//info += "Ace";
				info += "A";
			} else if (this.rank > 10) {
				String[] cards = {"Jack", "Queen", "King"};
				//info += cards[this.rank - 11];
				info += cards[this.rank - 11].charAt(0);
			} else {
				info += this.rank;
			}
			//info += " of " + this.suit;
			info = (info + this.suit.charAt(0)).toUpperCase();
			return info;
		}

		public PlayingCard getCopy() {
			return new PlayingCard(this.suit, this.rank);
		}

		public int getValue() {
			int i;
			for (i = 0; i < suitsInOrder.length; i++) {
				if (this.suit.equals(suitsInOrder[i]))
					break;
			}

			return this.rank + 13 * i;
		}

	}

	public class Joker extends Card {
		public String redOrBlack;

		public Joker(String c) {
			if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black"))
				throw new IllegalArgumentException("Jokers can only be red or black");

			this.redOrBlack = c.toLowerCase();
		}

		public String toString() {
			//return this.redOrBlack + " Joker";
			return (this.redOrBlack.charAt(0) + "J").toUpperCase();
		}

		public Joker getCopy() {
			return new Joker(this.redOrBlack);
		}

		public int getValue() {
			return numOfCards - 1;
		}

		public String getColor() {
			return this.redOrBlack;
		}
	}

}



