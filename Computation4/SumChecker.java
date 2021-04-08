

public class SumChecker implements Decider{
// accepts any string of the form a+b=c where a,b,c are substrings representing natural numbers, and the states sum is valid
    
    public boolean decide(String s){
        String first = "";
        String second = "";
        String third = "";
        int i;
        for(i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '+') {
                String adder = String.valueOf(s.charAt(i));
                first += adder;
            }
            if (s.charAt(i) == '+'){
                break;
            }
        }
        int j;
        for(j = i; j < s.length(); j++) {
            if(s.charAt(j) != '='){
                String adder = String.valueOf(s.charAt(j));
                second += adder;
            }
            if(s.charAt(j) == '='){
                break;
            }
        }
        int k;
        for(k = j; k < s.length(); k++){
            String adder = String.valueOf(s.charAt(k));
            third += adder;
        }
        if(second.length()!= 0){
            if(second.charAt(0) != '+') {
                return false;
            }
        }
        if(first.length() == 0){
            return false;
        }
        if(third.length() == 0){
            return false;
        }
        int firstNum;
        try{
            firstNum = Integer.parseInt(first);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        int secondNum;
        try{
            secondNum = Integer.parseInt(second.substring(1));
        }
        catch(NumberFormatException nfe){
            return false;
        }
        int thirdNum;
        try{
            thirdNum = Integer.parseInt(third.substring(1));
        }
        catch (NumberFormatException nfe){
            return false;
        }
        if(firstNum + secondNum == thirdNum) {
            return true;
        }
        else{
            return false;
        }
    }
    
    public static void main(String[] args){
        SumChecker sc = new SumChecker();
        System.out.println(sc.decide("346+23=369")); // accept
        System.out.println(sc.decide("346+23=368")); //reject
        System.out.println(sc.decide("346=346")); //reject
        System.out.println(sc.decide("346+2=345+1")); //reject
        System.out.println(sc.decide("346+a=368a")); //reject
        System.out.println(sc.decide("1+2=3")); //reject
    }

}
