import java.util.Random;

public class Deck {

    public static Card[] createDeck() {
        Card[] deck = new Card[52];
        //creates all 52 cards and stores them in the deck
        int index = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int rank = 0; rank < 13; rank++) {
                deck[index] = new Card(rank, suit);
                index++;
            }
        }
        return deck;
    }

    public static Stack<Card> shuffleDeck(Card[] deck) {
        //randomly pushes cards from the deck into the stack to create a shuffled deck
        final Random GENERATOR = new Random();
        Stack<Card> shuffledDeck = new Stack<Card>();
        int index = 0;
        for (int i = 0; i < 100000; i++) {
            index = GENERATOR.nextInt(deck.length);
            if (deck[index] != null)
                shuffledDeck.push(deck[index]);
            deck[index] = null;
        }
        return shuffledDeck;
    }

    //tests the deck methods
    public static void main(String[] args) {
        boolean deckCorrectSize = false;
        boolean shuffledDeckCorrectSize = false;
        Card[] deck = new Card[52];
        Stack<Card> shuffledDeck = new Stack<>();
        int deckSize = 0;
        int shuffledDeckSize = 0;
        //checks how many positions are in the deck
        for (int i = 0; i < deck.length; i++){
            deckSize++;
        }
        //print out results of test
        System.out.println("Empty deck array contains " + deckSize + " positions\n");

        deck = Deck.createDeck();
        deckSize = 0;
        //prints out full deck of cards and checks how many cards are in the deck
        for (int i = 0; i < deck.length; i++){
            System.out.println(deck[i]);
            deckSize++;
        }
        //print out results of tests
        System.out.println("\nDeck contains " + deckSize + " cards");
        if (deckSize == 52){
            deckCorrectSize = true;
        }
        System.out.println("It is " + deckCorrectSize + " that the deck has 52 cards");
        System.out.println(" ");

        //prints out full shuffled deck of cards and checks how many cards are in the deck
        shuffledDeck = Deck.shuffleDeck(deck);
        for (int i = 0; i < deck.length; i++){
            Card card = shuffledDeck.pop();
            System.out.println(card);
            shuffledDeckSize++;
        }
        //print out results of tests
        System.out.println("\nShuffled deck contains " + shuffledDeckSize + " shuffled cards");
        if (shuffledDeckSize == 52){
            shuffledDeckCorrectSize = true;
        }
        System.out.println("It is " + shuffledDeckCorrectSize + " that the shuffled deck has 52 cards");
    }
}
