/**
 * This is a class that tests the Card class.
 */
public class CardTester {

    /**
     * The main method in this class checks the Card operations for consistency.
     *  @param args is not used.
     */
    public static void main(String[] args) {
        Card h3 = new Card("Three", "Heart",3);
        Card h4 = new Card("Three", "Heart",3);
        Card sJ = new Card("Jack", "Spades",11);
        Card dK = new Card("King", "Diamond", 13);
        
        
        System.out.println("Should be Heart: "+ h3.suit());
        System.out.println("Should be Spades: "+ sJ.suit());
        System.out.println("Should be Diamond: "+ dK.suit());
    
        System.out.println("Should be Three: "+ h3.rank());
        System.out.println("Should be Jack: "+ sJ.rank());
        System.out.println("Should be King: "+ dK.rank());
        
        System.out.println("Should be 3: "+ h3.pointValue());
        System.out.println("Should be 11: "+ sJ.pointValue());
        System.out.println("Should be 13: "+ dK.pointValue());
        
        System.out.println("Should be True: "+ h3.matches(h4));
        System.out.println("Should be False: "+ h3.matches(dK));
        
        System.out.println(h3);
        System.out.println(sJ);
        System.out.println(dK);
        
        
    }
}
