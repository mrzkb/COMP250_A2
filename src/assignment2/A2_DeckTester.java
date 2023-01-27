package assignment2;

import assignment2.Deck;

public class A2_DeckTester {
    public static void main(String[] args){
        DeckTest1(); // AC 2C AD 2D RJ BJ
        DeckTest2(); // // CA C2 C3 C4 C5 JR JB
        DeckTest3(); // Invalid number of suits, Should raise Exception.
        DeckTest4(); // Invalid number of cards, Should raise Exception.
    }
    public static void DeckTest1() {
        Deck deck1 = new Deck(2, 2);
        // AC 2C AD 2D RJ BJ

        System.out.println("Expected output: Number of Cards = 6");
        System.out.println("AC\n2C\nAD\n2D\nRJ\nBJ");

        System.out.println("*********\nYour Output: Number of Cards = " + deck1.numOfCards );
        Deck.Card tempCard = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            if (tempCard instanceof Deck.Joker) {
                System.out.println(tempCard);
            } else {
                System.out.println(tempCard);
            }
            tempCard=tempCard.next;
        }
        System.out.println("\n**************\n**************\n\n");
    }


    public static void DeckTest2() {
        Deck deck1 = new Deck(5, 1);
        // CA C2 C3 C4 C5 JR JB

        System.out.println("Expected output: Number of Cards = 7");
        System.out.println("AC\n2C\n3C\n4C\n5C\nRJ\nBJ");

        System.out.println("*********\nYour Output: Number of Cards = " + deck1.numOfCards );
        Deck.Card tempCard = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            if (tempCard instanceof Deck.Joker) {
                System.out.println(tempCard);
            } else {
                System.out.println(tempCard);
            }
            tempCard=tempCard.next;
        }
        System.out.println("\n**************\n**************\n\n");
    }

    public static void DeckTest3() {
        Deck deck1 = new Deck(5, 0);
        // Invalid number of suits, should raise Exception

        System.out.println("Expected output: Number of Cards = 6");
        System.out.println("clubs 0 \nclubs 1 \nclubs 3 \nclubs 4 \nJoker Red \nJoker Black");

        System.out.println("*********\nYour Output: Number of Cards = " + deck1.numOfCards );
        Deck.Card tempCard = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            if (tempCard instanceof Deck.Joker) {
                System.out.println("Joker " + ((Deck.Joker) tempCard).redOrBlack);
            } else {
                System.out.println(((Deck.PlayingCard) tempCard).suit +" "+((Deck.PlayingCard) tempCard).rank);
            }
            tempCard=tempCard.next;
        }
        System.out.println("**************\n**************\n\n");
    }

    public static void DeckTest4() {
        Deck deck1 = new Deck(15, 0);
        // Invalid number of Cards, should raise Exception

        System.out.println("Expected output: Number of Cards = 17");
        System.out.println("clubs 0 \nclubs 1 \nclubs 3 \nclubs 4 \nJoker Red \nJoker Black");

        System.out.println("*********\nYour Output: Number of Cards = " + deck1.numOfCards );
        Deck.Card tempCard = deck1.head;
        for (int i = 0; i < deck1.numOfCards; i++) {
            if (tempCard instanceof Deck.Joker) {
                System.out.println("Joker " + ((Deck.Joker) tempCard).redOrBlack);
            } else {
                System.out.println(((Deck.PlayingCard) tempCard).suit +" "+((Deck.PlayingCard) tempCard).rank);
            }
            tempCard=tempCard.next;
        }
        System.out.println("**************\n**************\n\n");
    }
}
