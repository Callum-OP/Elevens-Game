public class Card implements Comparable<Card>{
    //
    private final int RANK, SUIT;
    private static final String[] RANKS = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
    private static final String[] SUITS = {"Spades","Clubs","Diamonds","Hearts"};

    //creates a card with an entered rank and suit
    public Card(int rank, int suit) {
        RANK = rank;
        SUIT = suit;
    }

    //gets the string value of the rank
    public String getRank() {
        return RANKS[RANK];
    }

    //gets the number value of the rank
    public int getRankValue() {
        return RANK;
    }

    //gets the string value of the suit
    public String getSuit() {
        return SUITS[SUIT];
    }

    //returns the string values of the rank and suit of a card
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    //compare two cards to see if they equal 11
    public int compareTo(Card card2) {
        int combinedRank = this.getRankValue() + card2.getRankValue() + 2;
        if (combinedRank == 11) return 1;
        else return 0;
    }

    //compare two cards to see if they are a pair of court cards (King, Queen or Jack)
    public int compare2To(Card card2) {
        int combinedRank = this.getRankValue() + card2.getRankValue() + 2;
        if ((combinedRank == 23 ||combinedRank == 24 || combinedRank == 25)&&(this.getRankValue() > 9)&&(card2.getRankValue() > 9)&&(this.getRankValue() != card2.getRankValue())) return 1;
        else return 0;
    }

    //compare three cards to see if they are three different court cards (King, Queen and Jack)
    public int compare3To(Card card2, Card card3) {
        int combinedRank = this.getRankValue() + card2.getRankValue() + card3.getRankValue() + 3;
        if (combinedRank == 36) return 1;
        else return 0;
    }

    //tests the card methods
    public static void main(String[] args) {
        boolean aceTen = false;
        boolean twoNine = false;
        boolean threeEight = false;
        boolean fourSeven = false;
        boolean fiveSix = false;
        boolean kingQueenJack = false;
        boolean twoThree = false;
        boolean sixSeven = false;
        boolean kingQueenFive = false;

        int index = 0;
        Card[] spades = new Card[13];
        //creates full range of spades cards to test
        for (int rank = 0; rank < 13; rank++) {
            spades[index] = new Card(rank, 0);
            index++;
        }
        for (int i = 0; i < 13; i++){
            System.out.println(spades[i]);
        }
        //test all the pairs equaling 11 and King, Queen and Jack
        if (spades[0].compareTo(spades[9]) == 1) {
            aceTen = true;
        }
        if (spades[1].compareTo(spades[8]) == 1) {
            twoNine = true;
        }
        if (spades[2].compareTo(spades[7]) == 1) {
            threeEight = true;
        }
        if (spades[3].compareTo(spades[6]) == 1) {
            fourSeven = true;
        }
        if (spades[4].compareTo(spades[5]) == 1) {
            fiveSix = true;
        }
        if (spades[12].compare3To(spades[11], spades[10]) == 1){
            kingQueenJack = true;
        }
        //test invalid combinations that don't equal 11 and King, Queen and Jack
        if (spades[1].compareTo(spades[2]) == 1) {
            twoThree = true;
        }
        if (spades[5].compareTo(spades[6]) == 1) {
            sixSeven = true;
        }
        if (spades[12].compare3To(spades[11], spades[4]) == 1){
            kingQueenFive = true;
        }
        //print out the results of the tests
        System.out.println("\nChecking all possible valid selections of cards return true when compared:");
        System.out.println("Result of Ace and Ten check is " + aceTen);
        System.out.println("Result of Two and Nine check is " + twoNine);
        System.out.println("Result of Three and Eight check is " + threeEight);
        System.out.println("Result of Four and Seven check is " + fourSeven);
        System.out.println("Result of Five and Six check is " + fiveSix);
        System.out.println("Result of King, Queen and Jack check is " + kingQueenJack);
        System.out.println("\nChecking invalid selections of cards return false when compared:");
        System.out.println("Result of Two and Three check is " + twoThree);
        System.out.println("Result of Six and Seven check is " + sixSeven);
        System.out.println("Result of King, Queen and Five check is " + kingQueenFive);

        //check if the cards are correctly identified
        //print out the results
        System.out.println("\nChecking rank, suit and value for Nine of Spades is correct");
        System.out.println("Rank is " + spades[8].getRank());
        System.out.println("Suit is " + spades[8].getSuit());
        System.out.println("Rank value is " + spades[8].getRankValue());
        System.out.println("\nChecking rank, suit and value for Ace of Spades is correct");
        System.out.println("Rank is " + spades[0].getRank());
        System.out.println("Suit is " + spades[0].getSuit());
        System.out.println("Rank value is " + spades[0].getRankValue());
    }
}