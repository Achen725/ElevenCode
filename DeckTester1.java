/**
 * This is a class that tests the Deck class.
 */
public class DeckTester1 {

    /**
     * The main method in this class checks the Deck operations for consistency.
     *  @param args is not used.
     */
    public static void main(String[] args) {

        String[] suits = {"Hearts", "Clubs","Diamonds","Spades"};
        String[] rank = {"Jack", "Queen", "King" };
        int[] value = {11, 12, 13};
        
        String[] suits2 = {"Hearts", "Clubs","Diamonds","Spades"};
        String[] rank2 = {"One", "Two", "Three" };
        int[] value2 = {1, 2, 3};
        
        String[] suits3 = {"Hearts", "Clubs","Diamonds","Spades"};
        String[] rank3 = {"Four", "Seven", "Nine" };
        int[] value3 = {4, 7, 9};
        
        Deck deck = new Deck(rank, suits, value);    
        Deck deck2 = new Deck(rank2, suits2, value2);    
        Deck deck3 = new Deck(rank3, suits3, value3);    
        
        System.out.println("DECK 1: ");
        System.out.println(deck);
        System.out.println();
        System.out.println("DECK 2: ");
        System.out.println();
        System.out.println(deck2);
        System.out.println("DECK 3: ");
        System.out.println(deck3);

        System.out.println();
        System.out.println("IsEmpty is false : " +deck.isEmpty());
        System.out.println("Size is 12 : " +deck.size());
        System.out.println("Card is dealt : " +deck.deal());

    }
}
