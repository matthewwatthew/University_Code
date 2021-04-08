public class Enumerate{
    Decider decider;
    char[] alphabet;
    // You're welcome to add any attributes that you find helpful

    public Enumerate(char[] alph, Decider d) { // do not change the signature of this constructor
        this.decider = d;
        this.alphabet = alph;
        // You're welcome to add any code to this constructor
    }

    public String[] enumerate(int n) { // do not change the signature of this method
        String[] out = new String[n];
        String stringer = "";
        String[] start = new String[alphabet.length];
        String[] previous = new String[alphabet.length];
        int kiri = 0;
        for (char c : alphabet) {
            previous[kiri] = Character.toString(c);
            kiri++;
        }
        int count = 0;
        if (decider.decide("") == true) {
            out[count] = "";
            count += 1;
            if (count == n) {
                return out;
            }
        }
        for (char c : alphabet) {
            String check = Character.toString(c);
            if (decider.decide(check) == true) {
                out[count] = check;
                count += 1;
                if (count == n) {
                    return out;
                }

            }
        }
        String[] next = previous.clone();
        while (count < n) {
            int counter = 0;
            double length = next.length;
            String[] copy = new String[(int) (Math.pow(length, 2))];
            for (int j = 0; j < previous.length; j++) {
                for (int o = 0; o < next.length; o++) {
                    stringer = previous[j] + next[o];
                    if (decider.decide(stringer) == true) {
                        out[count] = stringer;
                        count += 1;
                        if (count >= n) {
                            return out;
                        }
                    }
                    copy[counter] = stringer;
                    counter += 1;
                }
            }
            next = copy.clone();
        }
        return out;
    }

    public static void main(String[] args) {
        // You can use this main method to verify your enumerator works with SigmaStar and PrimeChecker
        // You'll likely want to create some of your own test cases as well

        Enumerate e = new Enumerate(new char[]{'b','a'}, new SigmaStar());
        String[] sigmaGiven = e.enumerate(8);
        String[] sigmaCorrect = {"", "b", "a", "bb", "ba", "ab", "aa", "bbb"};
        boolean sigmaIsCorrect = true;
        for (int i = 0; i < sigmaCorrect.length; i++) {
            sigmaIsCorrect = sigmaIsCorrect && (sigmaGiven[i].equals(sigmaCorrect[i]));
        }
        System.out.println("SigmaStar Output correct? " + sigmaIsCorrect);


        e = new Enumerate(new char[]{'a'}, new PrimeChecker());
        String[] primeGiven = e.enumerate(8);
        String[] primeCorrect = {"aa", "aaa", "aaaaa", "aaaaaaa", "aaaaaaaaaaa", "aaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaaaaaa"};
        boolean primeIsCorrect = true;

        for (int i = 0; i < primeCorrect.length; i++) {
            primeIsCorrect = primeIsCorrect && (primeGiven[i].equals(primeCorrect[i]));
        }
        System.out.println("PrimeChecker Output correct? " + primeIsCorrect);
    }
}