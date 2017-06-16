
import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends Board1 {

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

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
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ElevensBoard</code> instance.
     */
     public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
     }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *        false otherwise.
     */
    @Override
   
    public boolean isLegal(List<Integer> selectedCards) 
    {
        if (selectedCards.size() == 2) 
        {
            return containsPairSum13(selectedCards);
        }
        else if (selectedCards.size() == 1) 
        {
            return containsK(selectedCards);
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
    public boolean anotherPlayIsPossible() 
    {
        List<Integer> index = cardIndexes();
		return containsPairSum13(index) || containsK(index);

    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 11-pair.
     * @return true if the board entries in selectedCards
     *              contain an 11-pair; false otherwise.
     */
 
    
    private boolean containsPairSum13(List<Integer> selectedCards) {
        for (int i = 0; i < selectedCards.size(); i++) {
            int card1 = selectedCards.get(i);
            for (int j = i + 1; j < selectedCards.size(); j++) {
                int card2 = selectedCards.get(j);
                if (cardAt(card1).pointValue() + cardAt(card2).pointValue() == 13) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for a K in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a JQK group.
     * @return true if the board entries in selectedCards
     *              include a jack, a queen, and a king; false otherwise.
     */
    private boolean containsK(List<Integer> selectedCards) {
   
        boolean hasKing = false;
        if (selectedCards.size() < 1) {
            return false;
        }
        for (int i = 0; i < selectedCards.size(); i++) 
        {
            if(cardAt(selectedCards.get(i)).rank() == "king") {
                hasKing = true;
            }
        }
        return hasKing;
    }
  
    
    
    private ArrayList<Integer> findPairSum13 (List<Integer> selectedCards) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        
        if (selectedCards.size() < 2) {
            return list;
        }
       // System.out.println("Select Size"+selectedCards.size());
        for (int i = 0; i < (selectedCards.size() - 1); i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {
                //System.out.println(selectedCards.get(i) + "," + selectedCards.get(j));

                if(cardAt(selectedCards.get(i))==null || cardAt(selectedCards.get(j))==null){
                    continue;
                }
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 13) {
                    //System.out.println("done");
                    list.add(selectedCards.get(i));
                    list.add(selectedCards.get(j));
                    return list;
                }
            }
        }
        return list;
    }
    private ArrayList<Integer> findK(List<Integer> selectedCards) {
       
       ArrayList<Integer> list = new ArrayList<Integer>(); 
       int hasK = -1;
        if (selectedCards.size() < 3) {
            return list;
        }
        
        for (int i = 0; i < selectedCards.size(); i++) {
            if (cardAt(selectedCards.get(i)).rank() == "king") {
                hasK = selectedCards.get(i);
            }
        }
        //System.out.println(hasJ+" "+hasQ+" "+hasK);
        
        if (hasK >= 0){

            list.add(hasK);
            
        }
        return list;
    }
    
    public boolean playIfPossible() {
        boolean three = playKIfPossible();
        if(three) {
            return true;
        }
        boolean two = playPairSum13IfPossible();
        if(two){
            return true;
        }
        return false;
        
       
    }

    /**
     * Looks for a pair of non-face cards whose values sum to 13.
     * If found, replace them with the next two cards in the deck.
     * The simulation of this game uses this method.
     * @return true if an 13-pair play was found (and made); false othewise.
     */
    private boolean playPairSum13IfPossible() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size(); i++ ){

            if(cardAt(i)!= null){
                list.add(i);
            }
          
        }
        ArrayList<Integer> index = new ArrayList<Integer>();
        index = findPairSum13(list);
        if(index.size() > 0 ){
            replaceSelectedCards(index);
            //System.out.println("Normal REPLACE");
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
    private boolean playKIfPossible() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < size(); i++ ){

            if(cardAt(i)!= null){
                list.add(i);
            }
          
        }
        ArrayList<Integer> index = new ArrayList<Integer>();
        index = findK(list);
        if(index.size() > 0 ){
            replaceSelectedCards(index);
            //System.out.println("JQK REPLACE");
            return true;
        }
         return false; 
    }
}