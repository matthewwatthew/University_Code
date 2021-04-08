public class EvenA implements Decider{
// This decider will accept all strings that have an even number of instances of the character 'a'.
// Any other characters that might be present are irrelevant.

    public boolean decide(String s){
        int number = 0;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'a'){
                number +=1;
            }
        }
        if((number % 2) == 0) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static void main(String[] args){
        Decider dec = new EvenA();
        System.out.println(dec.decide("hello")); // accept
        System.out.println(dec.decide("")); // accept
        System.out.println(dec.decide("a")); // reject
        System.out.println(dec.decide("aaba")); // reject
        System.out.println(dec.decide("aaaa")); // accept
        System.out.println(dec.decide("aabaa")); // accept
    }
}
