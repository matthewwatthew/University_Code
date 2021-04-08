public class PrimeChecker implements Decider{
// this decider accepts all strings whose length is a prime number

    public boolean isPrime(int n){
    //checks if the input n is a prime number
        if(n <= 1){ return false; }
        for(int i = 2; i < n; i++){
            // a prime number is on that isn't divisible by any numbers smaller than it.
            if(n % i == 0){ return false; }
        }
        return true;
    }

    public boolean decide(String s){
        // return true if and only if the length of the string is prime.
        return isPrime(s.length());
    }
    
    public static void main(String[] args){
        PrimeChecker primer = new PrimeChecker();
        System.out.println(primer.isPrime(5));
        System.out.println(primer.decide("aaaaa"));
        System.out.println(primer.decide("aaaaaa"));
        System.out.println(primer.decide("aaaaaaaaaaaaa"));
    }
}
