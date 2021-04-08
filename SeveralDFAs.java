import java.util.*;

public class SeveralDFAs{

    public static DFA evenA(){
        /*
        * This is the automaton for the language evenA from class.
        */
        char[] alph = {'a','b'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        states.add("E"); // We have 2 states: E and O
        states.add("O");
        
        String start = "E"; // The start state is state E
        
        finals.add("E"); // The only final state is state E
        
        delta.put(new Tuple("E",'a'), "O"); // when in state E, transition to state O on input a
        delta.put(new Tuple("E",'b'), "E");
        delta.put(new Tuple("O",'a'), "E");
        delta.put(new Tuple("O",'b'), "O");
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static DFA tripleA(){
        /*
        * This is the automaton for the language tripleA from class.
        */
        char[] alph = {'a','b'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        states.add("zero");
        states.add("one");
        states.add("two");
        String start = "zero";
        finals.add("zero");
        delta.put(new Tuple("zero",'a'), "one");
        delta.put(new Tuple("zero",'b'), "zero");
        delta.put(new Tuple("one",'a'), "two");
        delta.put(new Tuple("one",'b'), "one");
        delta.put(new Tuple("two",'a'), "zero");
        delta.put(new Tuple("two",'b'), "two");
        
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static DFA tripleAndEvenA(){
        return DFAClosure.intersection(evenA(), tripleA());
    }
    
    public static DFA compId(){
        /* HOMEWORK
        * This function should return a finite state automaton
        * which accepts all strings that are valid UVA computing ids.
        * A UVA computing ID is formatted as:
        * between 2-3 lowercase letters a-z
        * one digit between 2-9
        * between 1-3 lowercase letters a-z
        *
        * To keep your automata from appearing too cluttered,
        * we will restrict letters to be a-c and numbers 2-3
        */
        char[] alph = {'a','b','c','2', '3'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();

        states.add("start");
        states.add("trash");
        states.add("letter1");
        states.add("letter2");
        states.add("letter3");
        states.add("number");
        states.add("letter4");
        states.add("letter5");
        states.add("letter6");
        String start = "start";
        finals.add("letter4");
        finals.add("letter5");
        finals.add("letter6");

        delta.put(new Tuple("start",'a'), "letter1");
        delta.put(new Tuple("start",'b'), "letter1");
        delta.put(new Tuple("start",'c'),"letter1");
        delta.put(new Tuple("letter1",'a'), "letter2");
        delta.put(new Tuple("letter1",'b'), "letter2");
        delta.put(new Tuple("letter1",'c'),"letter2");

        delta.put(new Tuple("letter1",'2'), "trash");
        delta.put(new Tuple("letter1",'3'),"trash");

        delta.put(new Tuple("letter2",'a'), "letter3");
        delta.put(new Tuple("letter2",'b'), "letter3");
        delta.put(new Tuple("letter2",'c'), "letter3");
        delta.put(new Tuple("letter2",'2'), "number");
        delta.put(new Tuple("letter2", '3'),"number");
        delta.put(new Tuple("letter3",'2'),"number");
        delta.put(new Tuple("letter3", '3'),"number");

        delta.put(new Tuple("letter3",'a'),"trash");
        delta.put(new Tuple("letter3", 'b'),"trash");
        delta.put(new Tuple("letter3",'c'),"trash");


        delta.put(new Tuple("number",'a'), "letter4");
        delta.put(new Tuple("number",'b'), "letter4");
        delta.put(new Tuple("number",'c'),"letter4");

        delta.put(new Tuple("number",'2'), "trash");
        delta.put(new Tuple("number",'3'),"trash");

        delta.put(new Tuple("letter4",'a'), "letter5");
        delta.put(new Tuple("letter4",'b'), "letter5");
        delta.put(new Tuple("letter4",'c'),"letter5");

        delta.put(new Tuple("letter4",'2'), "trash");
        delta.put(new Tuple("letter4",'3'),"trash");

        delta.put(new Tuple("letter5",'a'), "letter6");
        delta.put(new Tuple("letter5",'b'), "letter6");
        delta.put(new Tuple("letter5",'c'),"letter6");

        delta.put(new Tuple("letter6",'2'), "trash");
        delta.put(new Tuple("letter6",'3'),"trash");

        delta.put(new Tuple("letter6",'a'), "trash");
        delta.put(new Tuple("letter6",'b'), "trash");
        delta.put(new Tuple("letter6",'c'),"trash");

        delta.put(new Tuple("trash",'2'), "trash");
        delta.put(new Tuple("trash",'3'),"trash");

        delta.put(new Tuple("trash",'a'), "trash");
        delta.put(new Tuple("trash",'b'), "trash");
        delta.put(new Tuple("trash",'c'),"trash");

        return new DFA(states, alphabet, delta, start, finals);
    }
    public static DFA NotTripleA(){
        char[] alph = {'a','b','1','2', '?','!'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        states.add("first");
        states.add("second");
        states.add("third");
        states.add("trash");

        String start = "first";
        finals.add("first");
        finals.add("second");
        finals.add("third");

        delta.put(new Tuple("first",'a'),"second");

        delta.put(new Tuple("first", 'b'),"first");
        delta.put(new Tuple("first",'1'),"first");
        delta.put(new Tuple("first", '2'),"first");
        delta.put(new Tuple("first",'?'),"first");
        delta.put(new Tuple("first", '!'),"first");

        delta.put(new Tuple("second",'a'),"third");

        delta.put(new Tuple("second", 'b'),"first");
        delta.put(new Tuple("second",'1'),"first");
        delta.put(new Tuple("second", '2'),"first");
        delta.put(new Tuple("second",'?'),"first");
        delta.put(new Tuple("second", '!'),"first");

        delta.put(new Tuple("third",'a'),"trash");

        delta.put(new Tuple("third", 'b'),"first");
        delta.put(new Tuple("third",'1'),"first");
        delta.put(new Tuple("third", '2'),"first");
        delta.put(new Tuple("third",'?'),"first");
        delta.put(new Tuple("third", '!'),"first");

        delta.put(new Tuple("trash",'a'),"trash");
        delta.put(new Tuple("trash", 'b'),"trash");
        delta.put(new Tuple("trash",'1'),"trash");
        delta.put(new Tuple("trash", '2'),"trash");
        delta.put(new Tuple("trash",'?'),"trash");
        delta.put(new Tuple("trash", '!'),"trash");
        DFA dot = new DFA(states, alphabet, delta, start, finals);
        //dot.toDot();
        return new DFA(states, alphabet, delta, start, finals);

    }
    public static DFA seven(){
        char[] alph = {'a','b','1','2', '?','!'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();

        states.add("one");
        states.add("two");
        states.add("three");
        states.add("four");
        states.add("five");
        states.add("six");
        states.add("seven");
        states.add("eight");
        finals.add("eight");
        String start = "one";
        delta.put(new Tuple("one",'a'),"two");
        delta.put(new Tuple("one", 'b'),"two");
        delta.put(new Tuple("one",'1'),"two");
        delta.put(new Tuple("one", '2'),"two");
        delta.put(new Tuple("one",'?'),"two");
        delta.put(new Tuple("one", '!'),"two");

        delta.put(new Tuple("two",'a'),"three");
        delta.put(new Tuple("two", 'b'),"three");
        delta.put(new Tuple("two",'1'),"three");
        delta.put(new Tuple("two", '2'),"three");
        delta.put(new Tuple("two",'?'),"three");
        delta.put(new Tuple("two", '!'),"three");

        delta.put(new Tuple("three",'a'),"four");
        delta.put(new Tuple("three", 'b'),"four");
        delta.put(new Tuple("three",'1'),"four");
        delta.put(new Tuple("three", '2'),"four");
        delta.put(new Tuple("three",'?'),"four");
        delta.put(new Tuple("three", '!'),"four");

        delta.put(new Tuple("four",'a'),"five");
        delta.put(new Tuple("four", 'b'),"five");
        delta.put(new Tuple("four",'1'),"five");
        delta.put(new Tuple("four", '2'),"five");
        delta.put(new Tuple("four",'?'),"five");
        delta.put(new Tuple("four", '!'),"five");

        delta.put(new Tuple("five",'a'),"six");
        delta.put(new Tuple("five", 'b'),"six");
        delta.put(new Tuple("five",'1'),"six");
        delta.put(new Tuple("five", '2'),"six");
        delta.put(new Tuple("five",'?'),"six");
        delta.put(new Tuple("five", '!'),"six");

        delta.put(new Tuple("six",'a'),"seven");
        delta.put(new Tuple("six", 'b'),"seven");
        delta.put(new Tuple("six",'1'),"seven");
        delta.put(new Tuple("six", '2'),"seven");
        delta.put(new Tuple("six",'?'),"seven");
        delta.put(new Tuple("six", '!'),"seven");

        delta.put(new Tuple("seven",'a'),"eight");
        delta.put(new Tuple("seven", 'b'),"eight");
        delta.put(new Tuple("seven",'1'),"eight");
        delta.put(new Tuple("seven", '2'),"eight");
        delta.put(new Tuple("seven",'?'),"eight");
        delta.put(new Tuple("seven", '!'),"eight");

        delta.put(new Tuple("eight",'a'),"eight");
        delta.put(new Tuple("eight", 'b'),"eight");
        delta.put(new Tuple("eight",'1'),"eight");
        delta.put(new Tuple("eight", '2'),"eight");
        delta.put(new Tuple("eight",'?'),"eight");
        delta.put(new Tuple("eight", '!'),"eight");

        DFA dot = new DFA(states, alphabet, delta, start, finals);
        //dot.toDot();
        return new DFA(states, alphabet, delta, start, finals);
    }
    public static DFA twoletters(){
        char[] alph = {'a','b','1','2', '?','!'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();

        states.add("one");
        states.add("two");
        states.add("three");
        finals.add("three");
        String start = "one";

        delta.put(new Tuple("one",'a'),"two");
        delta.put(new Tuple("one", 'b'),"two");
        delta.put(new Tuple("one",'1'),"one");
        delta.put(new Tuple("one", '2'),"one");
        delta.put(new Tuple("one",'?'),"one");
        delta.put(new Tuple("one", '!'),"one");

        delta.put(new Tuple("two",'a'),"three");
        delta.put(new Tuple("two", 'b'),"three");
        delta.put(new Tuple("two",'1'),"two");
        delta.put(new Tuple("two", '2'),"two");
        delta.put(new Tuple("two",'?'),"two");
        delta.put(new Tuple("two", '!'),"two");

        delta.put(new Tuple("three",'a'),"three");
        delta.put(new Tuple("three", 'b'),"three");
        delta.put(new Tuple("three",'1'),"three");
        delta.put(new Tuple("three", '2'),"three");
        delta.put(new Tuple("three",'?'),"three");
        delta.put(new Tuple("three", '!'),"three");
        DFA dot = new DFA(states, alphabet, delta, start, finals);
        //dot.toDot();
        return new DFA(states, alphabet, delta, start, finals);
    }
    public static DFA oneNumber(){
        char[] alph = {'a','b','1','2', '?','!'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        states.add("one");
        states.add("two");
        finals.add("two");
        String start = "one";

        delta.put(new Tuple("one",'a'),"one");
        delta.put(new Tuple("one", 'b'),"one");
        delta.put(new Tuple("one",'1'),"two");
        delta.put(new Tuple("one", '2'),"two");
        delta.put(new Tuple("one",'?'),"one");
        delta.put(new Tuple("one", '!'),"one");

        delta.put(new Tuple("two",'a'),"two");
        delta.put(new Tuple("two", 'b'),"two");
        delta.put(new Tuple("two",'1'),"two");
        delta.put(new Tuple("two", '2'),"two");
        delta.put(new Tuple("two",'?'),"two");
        delta.put(new Tuple("two", '!'),"two");

        DFA dot = new DFA(states, alphabet, delta, start, finals);
        //dot.toDot();
        return new DFA(states, alphabet, delta, start, finals);
    }

    public static DFA onePunct(){
        char[] alph = {'a','b','1','2', '?','!'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        states.add("one");
        states.add("two");
        finals.add("two");
        String start = "one";

        delta.put(new Tuple("one",'a'),"one");
        delta.put(new Tuple("one", 'b'),"one");
        delta.put(new Tuple("one",'1'),"one");
        delta.put(new Tuple("one", '2'),"one");
        delta.put(new Tuple("one",'?'),"two");
        delta.put(new Tuple("one", '!'),"two");

        delta.put(new Tuple("two",'a'),"two");
        delta.put(new Tuple("two", 'b'),"two");
        delta.put(new Tuple("two",'1'),"two");
        delta.put(new Tuple("two", '2'),"two");
        delta.put(new Tuple("two",'?'),"two");
        delta.put(new Tuple("two", '!'),"two");

        DFA dot = new DFA(states, alphabet, delta, start, finals);
        //dot.toDot();
        return new DFA(states, alphabet, delta, start, finals);
    }
    public static DFA twoLetOneNum(){
        return DFAClosure.intersection(twoletters(),oneNumber());
    }
    public static DFA validCharacter(){
        return DFAClosure.intersection(twoLetOneNum(),onePunct());
    }
    public static DFA AAAValid(){
        return DFAClosure.intersection(NotTripleA(),validCharacter());
    }
    /*HOMEWORK
     * This function should return a finite state automaton
     * which checks whether or not the given string is a
     * "valid" password. The allowed characters for our
     * passwords are {a,b,1,2,?,!}. To be valid, it must
     * satisfy the following rules:
     * It contains at least 2 letters, one number, and one punctuation
     * There are not 3 a's in a row
     * the total length of the string is at least 7 characters
     *
     * To approach this problem, I recommend making automata for each
     * requirement above, then combine them using closure properties.
     */
    public static DFA password(){
        return DFAClosure.intersection(AAAValid(),seven());
    }
    /* HOMEWORK
     * For bioinformatics and network transmission, it's helpful
     * to be able to measure how different various strings are
     * from one another. These metrics are often called string
     * distance. There are various methods from measuring string
     * distance, and which to use mostly depends on what is an
     * allowable change.
     * For this problem we're asking you to write a function to
     * accept all strings that are within a certain Hamming
     * distance of another string (the match parameter).
     * The Hamming distance between two strings is the smallest
     * number of single-character substitutions that must be
     * made to convert one string into the other.
     *
     * For example, if we invoked this function on:
     * match = "nate"
     * distance = 2
     *
     * The automaton should accept:
     * nate (distance 0, exact match)
     * gate (distance 1, substituting n->g)
     * note (distance 1, substituting a->o)
     * hath (distance 2, substituting n->h and e->h)
     * pale (distance 2, substituting n->p and t->l)
     *
     * The automaton should reject:
     * math (3 substitutions required)
     * rich (4 substitutions required)
     * naters (cannot be converted by substitution alone)
     *
     * To keep your automata from looking too cluttered,
     * we restrict our alphabet to be DNA bases (a,t,g,c).
     *
     * hint: you code will likely be simpler if you do this with
     * a cross product somewhere, but this is not required.
     */
    
    public static DFA hammingDistance(String match, int distance){
        char[] alph = {'a','t','g','c'};
        Set<Character> alphabet = new HashSet<Character>();
        for (int i = 0; i < alph.length; i++){
            alphabet.add(alph[i]);
        }
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        String start;

        int length = match.length();
        length = length + 1;
        int counter = 0;
        int counter2 = 0;
        int distanceCounter = 0;
        int distanceCopy = distance;
        int indexCounter = 1;
        int index = 0;
        while (distance != -1) {
            while (counter < length) {
                states.add("state" + counter2);
                if(counter < length - 1) {
                    delta.put(new Tuple(("state" + counter2), match.charAt(index)), ("state" + (counter2 + 1)));
                    if (distanceCounter == distanceCopy) {
                        if (match.charAt(index) == 'a') {
                            delta.put(new Tuple(("state" + counter2), 't'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'g'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'c'), "trash");
                        }
                        if (match.charAt(index) == 't') {
                            delta.put(new Tuple(("state" + counter2), 'a'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'g'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'c'), "trash");
                        }
                        if (match.charAt(index) == 'g') {
                            delta.put(new Tuple(("state" + counter2), 't'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'a'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'c'), "trash");
                        }
                        if (match.charAt(index) == 'c') {
                            delta.put(new Tuple(("state" + counter2), 't'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'g'), "trash");
                            delta.put(new Tuple(("state" + counter2), 'a'), "trash");
                        }
                    }
                    else {
                        if (match.charAt(index) == 'a') {
                            delta.put(new Tuple(("state" + counter2), 't'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'g'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'c'), ("state" + (counter2 + length)));
                        }
                        if (match.charAt(index) == 't') {
                            delta.put(new Tuple(("state" + counter2), 'a'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'g'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'c'), ("state" + (counter2 + length)));
                        }
                        if (match.charAt(index) == 'g') {
                            delta.put(new Tuple(("state" + counter2), 't'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'a'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'c'), ("state" + (counter2 + length)));
                        }
                        if (match.charAt(index) == 'c') {
                            delta.put(new Tuple(("state" + counter2), 't'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'g'), ("state" + (counter2 + length)));
                            delta.put(new Tuple(("state" + counter2), 'a'), ("state" + (counter2 + length)));
                        }
                    }
                }
                else{
                    delta.put(new Tuple(("state" + counter2),'a'),"trash");
                    delta.put(new Tuple(("state" + counter2),'t'),"trash");
                    delta.put(new Tuple(("state" + counter2),'g'),"trash");
                    delta.put(new Tuple(("state" + counter2),'c'),"trash");
                }
                counter += 1;
                counter2 +=1;
                index +=1;
            }
            distance -=1;
            distanceCounter +=1;
            finals.add("state" + (counter2 -1));
            length -= 1;
            counter = 0;
            index = indexCounter;
            indexCounter +=1;
        }
        delta.put(new Tuple("trash",'a'),"trash");
        delta.put(new Tuple("trash",'t'),"trash");
        delta.put(new Tuple("trash",'g'),"trash");
        delta.put(new Tuple("trash",'c'),"trash");
        start = "state0";
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static void main(String[] args){
        DFA s = hammingDistance("aaaa",3);
        s.toDot();
    }
}
