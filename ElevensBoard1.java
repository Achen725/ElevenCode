import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard1 extends Board1 {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 9;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
        {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
        {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
     public ElevensBoard1() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
     }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid  group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() == 2) {
            return findPairSum11(selectedCards).size() > 0;
        }
        if (selectedCards.size() == 3) {
            return findJQK(selectedCards).size() > 0;
        }
        return false;
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        List<Integer> indexes = cardIndexes();
        if (!containsPairSum11(indexes)) {
            return containsJQK(indexes);
        }
        return true;
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        
        if (selectedCards.size() < 2) {
            return false;
        }
        for (int i = 0; i < selectedCards.size() - 1; i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 11) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for a JQK in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsJQK(List<Integer> selectedCards) {
       boolean hasJ = false;
       boolean hasQ = false;
       boolean hasK = false;
        if (selectedCards.size() < 3) {
            return false;
        }
        for (int i = 0; i < selectedCards.size(); i++) {
            if (cardAt(selectedCards.get(i)).rank() == "jack") {
                hasJ = true;
            }
            else if (cardAt(selectedCards.get(i)).rank() == "queen") {
                hasQ = true;
            }
            else if (cardAt(selectedCards.get(i)).rank() == "king") {
                hasK = true;
            }
        }
        return (hasJ && hasQ && hasK);
    }
     private ArrayList<Integer> findPairSum11 (List<Integer> selectedCards) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        
        if (selectedCards.size() < 2) {
            return list;
        }

        for (int i = 0; i < (selectedCards.size() - 1); i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {


                if(cardAt(selectedCards.get(i))==null || cardAt(selectedCards.get(j))==null){
                    continue;
                }
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 11) {

                    list.add(selectedCards.get(i));
                    list.add(selectedCards.get(j));
                    return list;
                }
            }
        }
        return list;
        
    }
    private ArrayList<Integer> findJQK(List<Integer> selectedCards) {
       
       ArrayList<Integer> list = new ArrayList<Integer>(); 
       int hasJ = -1;
       int hasQ = -1;
       int hasK = -1;
        if (selectedCards.size() < 3) {
            return list;
        }
        
        for (int i = 0; i < selectedCards.size(); i++) {
            if (cardAt(selectedCards.get(i)).rank() == "jack") {
                hasJ = selectedCards.get(i);
            }
            if (cardAt(selectedCards.get(i)).rank() == "queen") {
                hasQ = selectedCards.get(i);
            }
            if (cardAt(selectedCards.get(i)).rank() == "king") {
                hasK = selectedCards.get(i);
            }
        }

        
        if (hasJ >= 0  && hasQ >= 0 && hasK >= 0){
            list.add(hasJ);
            list.add(hasQ);
            list.add(hasK);
            
        }
        return list;
    }
    
    public boolean playIfPossible() {
        boolean three = playJQKIfPossible();
        if(three){ 
            return true;
        }
        boolean two = playPairSum11IfPossible();
        if(two) {
            return true;
        }
        return false;
        
    }

    /**
     * Looks for a pair of non-face cards whose values sum to 11.
     * If found, replace them with the next two cards in the deck.
     * The simulation of this game uses this method.
     * @return true if an 11-pair play was found (and made); false othewise.
     */
    private boolean playPairSum11IfPossible() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size(); i++ ){

            if(cardAt(i)!= null){
                list.add(i);

            }

        }
        ArrayList<Integer> index = new ArrayList<Integer>();
        index = findPairSum11(list);
        if(index.size() > 0 ){
            replaceSelectedCards(index);
            System.out.println("Normal REPLACE");
            return true;
        }
         return false; 
    }

    /**
     * Looks for a group of three face cards JQK.
     * If found, replace them with the next three cards in the deck.
     * The simulation of this game uses this method.
     * @return true if a JQK play was found (and made); false othewise.
     */
    private boolean playJQKIfPossible() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size(); i++ ){

            if(cardAt(i)!= null){
                list.add(i);

            }

        }
        ArrayList<Integer> index = new ArrayList<Integer>();
        index = findJQK(list);
        if(index.size() > 0 ){
            replaceSelectedCards(index);
            System.out.println("JQK REPLACE");
            return true;
        }
         return false; 
    }
}