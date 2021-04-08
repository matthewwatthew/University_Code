

public class Palindrome implements Decider{
// This decider will accept all strings that are palindromes (the same forward and backward).

    public boolean decide(String s){
        int length = s.length();
        if(length == 0){
            return true;
        }
        if(length == 1){
            return true;
        }
        if(s.charAt(0) != s.charAt(length-1)){
            return false;
        }
        String stringer = s.substring(1,length-1);
        return decide(stringer);
    }
    
    public static void main(String[] args){
        Palindrome pal = new Palindrome();
        System.out.println(pal.decide("hello")); // reject
        System.out.println(pal.decide("hellolleh")); // accept
        System.out.println(pal.decide("elle")); // accept
        System.out.println(pal.decide("")); // accept
    }
}
