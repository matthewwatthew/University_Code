import java.util.*;
    
public class NFAClosure{

    private static char eps = (new Epsilon()).getChar();
    
    private static NFA combine(NFA nfa1, NFA nfa2, String newStart){ 
        // unions the state sets, transitions, alphabets.
        // set of finals will be empty       
        Set<String> states = new HashSet<String>();
        for(String state : nfa1.states){
            states.add("first_"+state);
        }
        for(String state : nfa2.states){
            states.add("second_"+state);
        }
        states.add(newStart);
        Set<String> finals = new HashSet<String>();
        Set<Character> alphabet = new HashSet<Character>();
        alphabet.addAll(nfa1.alphabet);
        alphabet.addAll(nfa2.alphabet);
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        for(Map.Entry<Tuple, Set<String>> trans : nfa1.delta.entrySet()){
            Tuple t = trans.getKey();
            for (String dest : trans.getValue()){
                NFA.addToDelta(delta, "first_"+t.state, t.input_char, "first_"+dest);
            }
        }
        for(Map.Entry<Tuple, Set<String>> trans : nfa2.delta.entrySet()){
            Tuple t = trans.getKey();
            for (String dest : trans.getValue()){
                NFA.addToDelta(delta, "second_"+t.state, t.input_char, "second_"+dest);
            }
        }
        return new NFA(states, alphabet, delta, newStart, finals);
    }

    public static NFA union(NFA nfa1, NFA nfa2){
        // Gives a NFA for the union of nfa1 and nfa2
        NFA combined = combine(nfa1, nfa2, "new_start");
        NFA.addToDelta(combined.delta, combined.start, eps, "first_"+nfa1.start);
        NFA.addToDelta(combined.delta, combined.start, eps, "second_"+nfa2.start);
        for(String state : nfa1.finals){
            combined.finals.add("first_"+state);
        }
        for(String state : nfa2.finals){
            combined.finals.add("second_"+state);
        }
        return combined;
    }
    
    public static NFA concatenate(NFA nfa1, NFA nfa2){
        // Gives a NFA for the union of nfa1 and nfa2
        NFA combined = combine(nfa1, nfa2, "first_"+nfa1.start);
        for(String f : nfa1.finals){
            NFA.addToDelta(combined.delta, "first_"+f, eps, "second_"+nfa2.start);    
        }
        for(String state : nfa2.finals){
            combined.finals.add("second_"+state);
        }
        return combined;
    }
    
    public static NFA kleene(NFA nfa){
        // Gives a NFA for the Kleene star of nfa
        Set<String> states = new HashSet<String>();
        for(String state : nfa.states){
            states.add("star_"+state);
        }
        
        String newFinal = "empty";
        states.add(newFinal);
        String start = "star_"+nfa.start;
        
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        for(Map.Entry<Tuple, Set<String>> trans : nfa.delta.entrySet()){
            Tuple t = trans.getKey();
            for (String dest : trans.getValue()){
                NFA.addToDelta(delta, "star_"+t.state, t.input_char, "star_"+dest);
            }
        }
        NFA.addToDelta(delta, start, eps, newFinal);
        
        Set<String> finals = new HashSet<String>();
        for(String state : nfa.finals){
            finals.add("star_"+state);
            NFA.addToDelta(delta, "star_"+state, eps, "star_"+nfa.start);
        }
        finals.add(newFinal);
        return new NFA(states, nfa.alphabet, delta, start, finals);
    }
    
    public static NFA singleton(char c){
        // gives a nfa that accepts only the character c
        Set<Character> alphabet = new HashSet<Character>();
        alphabet.add(c);
        
        String start = "start";
    
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, Set<String>> delta = new HashMap<Tuple, Set<String>>();
        
        states.add(start);
        String f;
        if (c == (new Epsilon()).getChar()){
            f = start;
            finals.add(f);
            return new NFA(states, alphabet, delta, start, finals);
        }
        f = ((Character)c).toString();
        states.add(f);
        finals.add(f);
        NFA.addToDelta(delta, start, c, f);
        return new NFA(states, alphabet, delta, start, finals);
    }
    
    public static NFA token2NFA(RegexToken rt){
        // Produces a NFA from the tokenized regular expression
        // You can think of a RegexToken as the root of a prefix tree
        // based on the operation associated with the token, it will apply
        // the proper closure construction to the NFA its sub-tokens represent
        List<Character> ops = Arrays.asList('+', '.', '*');
        if(!ops.contains(rt.operation)){
            return singleton(rt.operation);
        }
        if(rt.operation == '.'){
            NFA nfa = token2NFA(rt.operands.get(0));
            for(int i = 1; i < rt.operands.size(); i++){
                nfa = concatenate(nfa, token2NFA(rt.operands.get(i)));
            }
            return nfa;
        }
        if(rt.operation == '+'){
            NFA nfa = token2NFA(rt.operands.get(0));
            for(int i = 1; i < rt.operands.size(); i++){
                nfa = union(nfa, token2NFA(rt.operands.get(i)));
            }
            return nfa;
        }
        if(rt.operation == '*'){
            NFA nfa = kleene(token2NFA(rt.operands.get(0)));
            return nfa;
        }
        
        return new NFA();
    }
    
    public static NFA regex2NFA(String regex){
        // Create a NFA accepting the strings described by the regex
        RegexToken rt = new RegexToken(regex);
        return token2NFA(rt);
    }
    
}
