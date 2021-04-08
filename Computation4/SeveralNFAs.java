import java.util.*;

public class SeveralNFAs{

    // this gets to the epsilon character
    public static char eps = (new Epsilon()).getChar();
    
    //example regex
    public static String evenAoddB = "(aa)*(bb)*b";
    
    /* HOMEWORK
        * This String should be a regex describing
        * all strings that are valid UVA computing ids.
        * A UVA computing ID is formatted as:
        * between 2-3 lowercase letters a-z
        * one digit between 2-9
        * between 1-3 lowercase letters a-z
        *
        * To keep your regex from being too cluttered,
        * we will restrict letters to be a-c and numbers 2-3
    */
    public static String compid = "(a+b+c+" + eps +")(a+b+c)(a+b+c)(2+3)(a+b+c+"+eps+")(a+b+c+"+eps+")(a+b+c)";

    
    //use this alphabet for all NFAs below
    public static Set<Character> alphabet = new HashSet<>(Arrays.asList('a', 'g', 't', 'c')); 
    
    /* HOMEWORK
        * Huntington's disease is a trinucleotide repeat disorder.
        * Very often, the human genome has regions where 
        * the same nucleotides repeat many times.
        * Having the incorrect number of such repeats is the cause
        * of many genetic disorders.
        * Huntington's Disease is one such genetic disorder that is
        * caused by having too many copies of the trinucleotide 
        * sequence "cag".
        * An individual with <26 sequential repeats of "cag" in their 
        * genome is considered to be "normal".
        * An individual with between 26 and 35 repeats of "cag" is
        * considered to be a "carrier", and may give the disease
        * to their children.
        * An individual with between 36 and 39 repeats is said to
        * be "at risk", and may or may not ever show symptoms.
        * An individual with 40 or more repeats is said to be
        * "affected", and will eventually show symptoms of the disease.
        
        * This function should take a dna sequence (a string from the alphabet a,g,t,c)
        * and determined the classification for the individual
        * (i.e. normal, carrier, at risk, or affected)
        
        * Your function should operate by defining a regex for each category,
        * converting each to a nfa, then checking which category the given
        * dna sequence falls into.
        * The return value is the classification.
        * Note that the dna sequence may have characters before/after
        * the "cag" repeats.
        
        * source: https://en.wikipedia.org/wiki/Huntington%27s_disease
    */
    public static String huntingtons(String dna) {
        //Test 1:
        String huntings = "";
        huntings += "(a+t+g+c+" +eps+")*";
        for(int i = 0; i < 40; i++){
            huntings += "cag";
        }
        huntings += "(cag)*";
        huntings += "(a+t+g+c+"+eps+")*";
        NFA h = NFAClosure.regex2NFA(huntings);
        if(h.decide(dna) == true){
            return "affected";
        }

        //Test 2:
        String risk = "";
        risk += "(a+t+g+c+" + eps+")*";
        for(int i = 0; i < 3; i++){
            risk += "(cag+" +eps+")";
        }
        risk += "(";
        for(int i = 0; i < 36; i++){
            risk += "cag";
        }
        risk += ")";
        risk +="(a+t+g+c+"+eps+")*";
        NFA r = NFAClosure.regex2NFA(risk);
        if(r.decide(dna) == true){
            return "at risk";
        }

        //Test 3:

        String carrier = "";
        carrier += "(a+t+g+c+"+eps+")*";
        for(int i = 0; i < 9; i++ ){
            carrier += "(cag+"+eps+")";
        }
        carrier += "(";
        for(int i = 0; i < 26; i++){
            carrier += "cag";
        }
        carrier += ")";
        carrier += "(a+t+g+c"+eps+")*";
        NFA c = NFAClosure.regex2NFA(carrier);
        if(c.decide(dna) == true){
            return "carrier";
        }
        else {
            return "normal";
        }
    }
    
    
    /* HOMEWORK
        * For bioinformatics and network transmission, it's helpful 
        * to be able to measure how different various strings are
        * from one another. These metrics are often called string
        * distance. There are various methods from measuring string
        * distance, and which to use mostly depends on what is an
        * allowable change. 
        * For this problem we're asking you to write a function to
        * accept all strings that are within a certain Edit
        * distance (Levenshtein distance) of another string 
        * (the match parameter).
        * The Edit distance between two strings is the smallest
        * number of single-character substitutions, insertions, or
        * deletions that must be made to convert one string into the other.
        *
        * For example, if we invoked this function on:
        * match = "nate"
        * distance = 2
        *
        * The automaton should accept:
        * nate (distance 0, exact match)
        * nater (distance 1, inserting r)
        * ate (distance 1, deleting n)
        * note (distance 1, substituting a->o)
        * ale (distance 2, deleting n and subsituting t->l)
        *
        * The automaton should reject:
        * a (3 deletions required)
        * nono (3 substitutions required)
        * hatred (shortest distance is 3 from subtituting n->h, inserting r and d)
        *
        * To keep your automata from looking too cluttered,
        * we restrict our alphabet to be DNA bases (a,t,g,c).
    */
    public static NFA editDistance(String match, int distance){
        int stateNums = (match.length() + 1) * (distance + 1);
        char[] alph = {'a','t','g','c'};
        Set<Character> alphabet = new HashSet<>();
        for(int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, Set<String>> delta = new HashMap<>();
        int counter = 0;
        for(int i = 0; i < stateNums; i++){
            states.add("state"+counter);
            counter +=1;
        }

        int counter2 = 0;
        while(counter2 + (match.length()+1) < stateNums){
            Set<String> state = new HashSet<>();
            state.add("state"+(counter2 + match.length()+1));
            state.add("state"+(counter2 + match.length()+2));
            delta.put(new Tuple(("state"+counter2),'a'),state);
            delta.put(new Tuple(("state"+counter2),'t'),state);
            delta.put(new Tuple(("state"+counter2),'g'),state);
            delta.put(new Tuple(("state"+counter2),'c'),state);
            counter2+=1;
        }

        int counter3 = 0;
        int counter4 = 0;
        System.out.println(stateNums);
        while(counter3 != stateNums){
            while(counter4 != match.length()) {
                Set<String> state = new HashSet<>();
                state.add("state" + (counter3 + 1));
                if(counter3+ match.length()+1 < stateNums) {
                    state.add("state" + (counter3 + match.length() + 1));
                    state.add("state" + (counter3 + match.length() + 2));
                }
                delta.put(new Tuple(("state" + counter3), match.charAt(counter4)), state);
                counter3 += 1;
                counter4 +=1;
            }
            finals.add("state"+counter3);
            counter3 +=1;
            counter4 = 0;
        }
        int counter6 = 0;
        while(counter6 + (match.length()+1) < stateNums){
            Set<String> state = new HashSet<>();
            state.add("state"+(counter6 + match.length()+2));
            delta.put(new Tuple(("state"+counter6),eps),state);
            delta.put(new Tuple(("state"+counter6),eps),state);
            delta.put(new Tuple(("state"+counter6),eps),state);
            delta.put(new Tuple(("state"+counter6),eps),state);
            counter6+=1;
        }
        String start = "state0";
        return new NFA(states, alphabet, delta, start, finals);
    }
    
    public static NFA iCharFromEnd(char c, int i){
        // gives a NFA that accepts any string where character c is i away from
        // the end
        // for example, if c = 'g' and i = 4, we should accept aagata, but not agaata
        Set<String> states = new HashSet<String>();
        for(int j = 0; j <= i; j++ ){
            states.add(Integer.toString(j));
        }
        String start = "0";
        Map<Tuple, Set<String>> delta = new HashMap<Tuple, Set<String>>();
        Set<String> finals = new HashSet<String>();
        NFA.addToDelta(delta, start, c, "1"); // add transition from start to second node on c
        NFA.addAllToDelta(delta, start, alphabet, start); // start transitions to itself on entire alphabet
        for(int j = 1; j < i; j++ ){
            // every state transitions to the next on entire alphabet
            NFA.addAllToDelta(delta, Integer.toString(j), alphabet, Integer.toString(j+1));
        }
        finals.add(Integer.toString(i));
        NFA nfa = new NFA(states, alphabet, delta, start, finals);
        return nfa;    
    }


    public static void main(String[]arvs){
        //NFAClosure.kleene(nfa).toDot(); // Applying Kleene Start construction
        //NFAClosure.concatenate(iCharFromEnd('a',2), iCharFromEnd('a',3)).toDot(); // Applying concatenation construction
        //NFAClosure.union(iCharFromEnd('a',2), iCharFromEnd('a',3)).toDot(); // Applying union construction
    }
}
