
/**
 * Write a description of class Q3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Q3 {
    public static String flip() {
        if (Math.random() > (.33)) {
            return "head";
        }
        else {
            return "tails";
        }
    }
    public static boolean arePermutations(int[] x, int[] y){
        
        for(int i = 0; i < x.length; i++){
            
            boolean check = false;
            
            for(int k = 0; k < y.length; k++){
                if(x[i] == y[k]){
                    check = true;
                }
            }
            if(check){
                return true;
            }
    
        }
        return false;
    }
}
