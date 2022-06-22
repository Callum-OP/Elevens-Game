import java.util.EmptyStackException;
import java.util.Scanner;

public class Elevens {
    public static void main(String[] args) {
        boolean play = false;
        boolean played = false;
        boolean demo = false;
        boolean replay = false;
        int GameResult = 0;
        Card[] ReplayHand = new Card[2000];

        //constant loop
        while (true) {
            //creates array for a deck of cards
            Card[] deck = new Card[52];
            //creates stack for a shuffled deck of cards
            Stack<Card> shuffledDeck = new Stack<>();
            int ReplayCount = 0;

            //creates all 52 cards and stores them in the deck
            deck = Deck.createDeck();

            //randomly pushes cards from the deck into the stack to create a shuffled deck
            shuffledDeck = Deck.shuffleDeck(deck);

            //creates array for a hand of nine cards
            Card[] hand = new Card[9];
            //displays the menu interface of the game
            System.out.println("\n***************************************************************");
            System.out.println("***                    The Elevens Game                     ***");
            System.out.println("***************************************************************");
            System.out.println("Select two cards that equal 11 or select King, Queen and Jack");
            System.out.println("\nChoose an option");
            System.out.println("-----------------------");
            System.out.println("1. Start Game");
            System.out.println("2. Replay Previous Game");
            System.out.println("3. Demonstration Mode");
            System.out.println("4. Exit Game");
            System.out.println("-----------------------");
            //user is prompted to select a number from the menu
            System.out.println("\nOption: ");
            Scanner input = new Scanner(System.in);
            String number = input.next();
            //if user selected 1, take nine cards from the shuffled deck and start the game
            if (number.equals("1")) {
                for (int i = 0; i < 9; i++) {
                    hand[i] = shuffledDeck.pop();
                }
                //reset the previous saved game
                ReplayHand = new Card[2000];
                //begin play while loop
                played = true;
                play = true;
            }
            //if user selected 2
            else if (number.equals("2")) {
                if (played) {
                    replay = true;
                }
                else {
                    System.out.println("No games have been played yet");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            //if user selected 3
            else if (number.equals("3")) {
                for (int i = 0; i < 9; i++) {
                    hand[i] = shuffledDeck.pop();
                }
                demo = true;
            } else if (number.equals("4")) {
                break;
            }
            //loop while play = true
            while (play) {
                System.out.println(" ");
                int count = 0;
                //loop through and display each card in the hand
                for (int i = 0; i < 9; i++) {
                    count++;
                    System.out.println(count + ". " + hand[i]);
                    ReplayHand[ReplayCount] = hand[i];
                    ReplayCount++;
                }

                //prompt the user to select an option
                System.out.println("\nChoose: '0' to quit, '1' for a hint or '2' to select two cards or '3' to select three cards");
                Scanner choice = new Scanner(System.in);
                String playChoice = choice.next();

                //if user selected 1 give them a hint of some moves they can do
                if (playChoice.equals("1")) {
                    System.out.println("\nHint: ");
                    boolean validPlaysLeft = false;
                    //for each card in the hand compare it with other cards in the hand
                    for (int i = 0; i < 9; i++) {
                        Card card1 = hand[i];
                        for (int j = i + 1; j < 9; j++) {
                            Card card2 = hand[j];
                            if (i != j && (card1 != null) && (card2 != null)) {
                                //if card and other card equal 11 then display the hint
                                if (card1.compareTo(card2) == 1) {
                                    System.out.println("Try the " + card1 + " with the " + card2);
                                    validPlaysLeft = true;
                                }
                                //if card and other card are a king, queen or jack
                                if (card1.compare2To(card2) == 1) {
                                    for (int k = 0; k < 9; k++) {
                                        Card card3 = hand[k];
                                        if (k != i && k != j && (card3 != null)) {
                                            //if card and two other cards are king, queen and jack then display the hint
                                            if (card1.compare3To(card2, card3) == 1) {
                                                System.out.println("Try the " + card1 + " with the " + card2 + " and the " + card3);
                                                validPlaysLeft = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //if there are no moves left to make then end the game
                    if (!validPlaysLeft) {
                        GameResult = 1;
                        System.out.println("There are no more valid moves left the game is over");
                        play = false;
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                int chosen1 = 0;
                int chosen2 = 0;
                int chosen3 = 0;
                //if user selected 2 then ask for user to enter their two cards
                if (playChoice.equals("2")) {
                    try {
                        System.out.println("\nSelect a number for your first card");
                        Scanner input1 = new Scanner(System.in);
                        chosen1 = input1.nextInt();
                        if (chosen1 < 1 || chosen1 > 9) {
                            chosen1 = 0;
                        }
                        System.out.println("\nSelect a number for your second card");
                        Scanner input2 = new Scanner(System.in);
                        chosen2 = input2.nextInt();
                        if (chosen2 < 1 || chosen2 > 9) {
                            chosen2 = 0;
                        }
                    } catch(Exception e) {
                        chosen1 = 0;
                        chosen2 = 0;
                    }
                    if (chosen1 == 0 || chosen2 == 0) {
                        System.out.println("Incorrect card has been entered");
                    } else {
                        //select cards user chose
                        Card card1 = hand[chosen1 - 1];
                        Card card2 = hand[chosen2 - 1];

                        //if cards equal 11 remove from hand and replace if shuffled deck is not empty
                        //else tell user cards don't equal 11
                        if (card1 != null && card2 != null) {
                            if (card1.compareTo(card2) == 1) {
                                System.out.println("Cards equal 11");
                                hand[chosen1 - 1] = null;
                                hand[chosen2 - 1] = null;
                                try {
                                    hand[chosen1 - 1] = shuffledDeck.pop();
                                    hand[chosen2 - 1] = shuffledDeck.pop();
                                } catch (EmptyStackException e) {
                                    System.out.println("Deck is empty");
                                }
                            } else {
                                System.out.println("Cards don't equal 11");
                            }
                        } else {
                            System.out.println("Not a valid selection");
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                //if user selected 3 then ask for user to enter their three cards
                if (playChoice.equals("3")) {
                    try {
                        System.out.println("\nSelect a number for your first card");
                        Scanner input1 = new Scanner(System.in);
                        chosen1 = input1.nextInt();
                        if (chosen1 < 1 || chosen1 > 9) {
                            chosen1 = 0;
                        }
                        System.out.println("\nSelect a number for your second card");
                        Scanner input2 = new Scanner(System.in);
                        chosen2 = input2.nextInt();
                        if (chosen2 < 1 || chosen2 > 9) {
                            chosen2 = 0;
                        }
                        System.out.println("\nSelect a number for your third card");
                        Scanner input3 = new Scanner(System.in);
                        chosen3 = input3.nextInt();
                        if (chosen3 < 1 || chosen3 > 9) {
                            chosen3 = 0;
                        }
                    } catch(Exception e) {
                        chosen1 = 0;
                        chosen2 = 0;
                        chosen3 = 0;
                    }
                    if (chosen1 == 0 || chosen2 == 0 || chosen3 == 0) {
                        System.out.println("Incorrect card has been entered");
                    } else {
                        //select cards user chose
                        Card card1 = hand[chosen1 - 1];
                        Card card2 = hand[chosen2 - 1];
                        Card card3 = hand[chosen3 - 1];
                        //if cards equal king, queen and jack remove from hand and replace if shuffled deck is not empty
                        //else tell user cards are not valid
                        if (card1 != null && card2 != null && card3 != null) {
                            if (card1.compare3To(card2, card3) == 1) {
                                System.out.println("King, Queen and Jack selected");
                                hand[chosen1 - 1] = null;
                                hand[chosen2 - 1] = null;
                                hand[chosen3 - 1] = null;
                                try {
                                    hand[chosen1 - 1] = shuffledDeck.pop();
                                    hand[chosen2 - 1] = shuffledDeck.pop();
                                    hand[chosen3 - 1] = shuffledDeck.pop();
                                } catch (EmptyStackException e) {
                                    System.out.println("Deck is empty");
                                }
                            } else {
                                System.out.println("Cards selected are not a valid combination");
                            }
                        } else {
                            System.out.println("Not a valid selection");
                        }
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    //if user selected 0 then exit back to the menu
                    if (playChoice.equals("0")) {
                        GameResult = 2;
                        System.out.println("Thank you for the playing the game");
                        play = false;
                    }
                    //check if user has won or not
                    boolean continuePlaying = false;
                    for (int i = 0; i < 9; i++) {
                        Card card = hand[i];
                        if (card != null) {
                            continuePlaying = true;
                        }
                    }
                    if (!continuePlaying) {
                        GameResult = 3;
                        System.out.println("Congratulations all cards have been removed and you have won the game");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        play = false;
                    }
                }
            }
            while(replay){
                boolean continueReplay = false;
                System.out.println(" ");
                //check if card is not null to continue the replay
                for (int i = ReplayCount; i < ReplayCount + 9; i++) {
                    Card card = ReplayHand[i];
                    if (card != null) {
                        continueReplay = true;
                    }
                }
                //if the replay has ended print the end result
                if (!continueReplay) {
                    if (GameResult == 1) {
                        System.out.println("\nUser had no more valid moves left in the previous game");
                        replay = false;
                    }
                    if (GameResult == 2) {
                        System.out.println("\nUser exited the previous game");
                        replay = false;
                    }
                    if (GameResult == 3) {
                        System.out.println("\nUser won the previous game");
                        replay = false;
                    }
                }
                //if next move is to be replayed then show next move
                if (continueReplay){
                    for (int i = 0; i < 9; i++) {
                        System.out.println(ReplayHand[ReplayCount]);
                        ReplayCount++;
                    }
                    System.out.println(" ");
                }
                //create counter
                int count = 0;
                //if the user has made a move previously and game is not over
                if (ReplayCount > 8 && continueReplay) {
                    //count the cards that have been changed
                    for (int i = ReplayCount - 9; i < ReplayCount; i++) {
                        if (ReplayHand[i] != ReplayHand[i + 9]) {
                            count++;
                        }
                    }
                    //check cards that have been changed/selected
                    for (int i = ReplayCount - 9; i < ReplayCount; i++) {
                        if (ReplayHand[i] != ReplayHand[i + 9]) {
                            //if cards changed are less than 4, print those cards
                            if (count < 4) {
                                System.out.println(ReplayHand[i] + " was selected");
                            }
                        }
                    }
                    //if cards changed is 0 then
                    if (count == 0) {
                        System.out.println("User asked for a hint");
                    }
                }
                //prompt user to see next move
                System.out.println("\nPress any key to see next move");
                Scanner move = new Scanner(System.in);
                move.next();
            }

            while (demo) {
                boolean continuePlaying = false;
                boolean validPlaysLeft = false;
                Card card1 = null;
                Card card2 = null;
                Card card3 = null;
                Card result1 = null;
                Card result2 = null;
                Card result3 = null;
                int CardNumber = 0;

                System.out.println(" ");
                int count = 0;
                //loop through and display each card in the hand
                for (int i = 0; i < 9; i++) {
                    count++;
                    System.out.println(count + ". " + hand[i]);
                }
                //for each card in the hand compare it with other cards in the hand
                for (int i = 0; i < 9; i++) {
                    card1 = hand[i];
                    for (int j = i + 1; j < 9; j++) {
                        card2 = hand[j];
                        if (i != j && (card1 != null && card2 != null)) {
                            if (card1.compareTo(card2) == 1) {
                                result1 = card1;
                                result2 = card2;
                                validPlaysLeft = true;
                                CardNumber = 2;
                            }
                            if (card1.compare2To(card2) == 1) {
                                for (int k = 0; k < 9; k++) {
                                    card3 = hand[k];
                                    if (k != i && k != j && (card3 != null)) {
                                        if (card1.compare3To(card2, card3) == 1) {
                                            result1 = card1;
                                            result2 = card2;
                                            result3 = card3;
                                            validPlaysLeft = true;
                                            CardNumber = 3;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //if there are no moves left end the game
                if (!validPlaysLeft) {
                    System.out.println("\nThere are no more valid moves left the game is over");
                    demo = false;
                }
                //if there are two cards to select then select them
                if (CardNumber == 2) {
                    System.out.println("\nSelecting " + result1 + " with " + result2);
                    for(int i = 0; i < 9; i++){
                        if (result1 == hand[i]) {
                            hand[i] = null;
                            try {
                                hand[i] = shuffledDeck.pop();
                            } catch (EmptyStackException e) {
                                System.out.println(" ");
                            }
                        }
                        if (result2 == hand[i]) {
                            hand[i] = null;
                            try {
                                hand[i] = shuffledDeck.pop();
                            } catch (EmptyStackException e) {
                                System.out.println("Deck is empty");
                            }
                        }
                    }
                }
                //if there are three cards to select then select them
                if (CardNumber == 3) {
                    System.out.println("\nSelecting " + result1 + " with " + result2 + " and " + result3);
                    for(int i = 0; i < 9; i++){
                        if (result1 == hand[i]) {
                            hand[i] = null;
                            try {
                                hand[i] = shuffledDeck.pop();
                            } catch (EmptyStackException e) {
                                System.out.println(" ");
                            }
                        }
                        if (result2 == hand[i]) {
                            hand[i] = null;
                            try {
                                hand[i] = shuffledDeck.pop();
                            } catch (EmptyStackException e) {
                                System.out.println("Deck is empty");
                            }
                        }
                        if (result3 == hand[i]) {
                            hand[i] = null;
                            try {
                                hand[i] = shuffledDeck.pop();
                            } catch (EmptyStackException e) {
                                System.out.println(" ");
                            }
                        }
                    }
                }
                //check if there are no cards left in the hand
                for (int i = 0; i < 9; i++) {
                    Card card = hand[i];
                    if (card != null) {
                        continuePlaying = true;
                    }
                }
                //if there are no cards left end the game
                if (!continuePlaying) {
                    System.out.println("\nCongratulations all cards have been removed and you have won the game");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    demo = false;
                }
                //prompt user to see next move
                System.out.println("\nPress any key to see next move");
                Scanner move = new Scanner(System.in);
                move.next();
            }
        }
    }
}