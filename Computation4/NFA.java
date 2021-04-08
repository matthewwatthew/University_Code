import java.util.*;

public class NFA extends FSA
{

    public Set<String> activeStates;
    public Map<Tuple,Set<String>> delta; // \delta
    public static char eps = (new Epsilon()).getChar();;
    
    public NFA(Set<String> states, Set<Character> alphabet, Map<Tuple,Set<String>> delta, String start, Set<String> finals){
        //Standard constructore, similar to that for DFAs
        this.states = states;
        this.delta = delta;
        this.start = start;
        this.finals = finals;
        this.alphabet = alphabet;
        this.activeStates = new HashSet<String>();
        this.activeStates.add(start);
        this.epsClosure();
    }
    
    public NFA(){
        //An empty constructor. Gives a 1-state NFA that doesn't accept anything
        this.states = new HashSet<String>();
        this.delta = new HashMap<Tuple, Set<String>>();
        this.start = "start";
        this.finals = new HashSet<String>();
        this.alphabet = new HashSet<Character>();
        this.activeStates = new HashSet<String>();
        this.states.add(start);
        this.activeStates.add(start);
    }
    
    private void epsClosure(){
        // This method follows all epsilon transitions
        boolean expanded = true;
        while(expanded){ // as long as you've added more states
            expanded = false; // we haven't added new states yet
            Set<String> additionalActive = new HashSet<String>();
            for(String act : this.activeStates){
                Tuple t = new Tuple(act, eps);
                if (!this.delta.containsKey(t)){
                    continue;
                }
                for (String dest : this.delta.get(t)){
                    expanded = expanded || !this.activeStates.contains(dest);
                    additionalActive.add(dest);
                }
            }
            this.activeStates.addAll(additionalActive);
        }
    }
    
    private boolean accept(){
        // accept if any active state is final
        for(String state : activeStates){
            if(this.finals.contains(state)){
                return true;
            }
        }
        return false;
    }
    
    
    public boolean step(char c){
        // Take one transition in the machine
        Set<String> nextActive = new HashSet<String>();
        for(String active : this.activeStates){
            Tuple t = new Tuple(active, c);
            if(delta.containsKey(t)){
                nextActive.addAll(delta.get(t));
            }
        }
        this.activeStates = nextActive;
        this.epsClosure();
        return this.accept();
    }
    
    public boolean reset(){
        // resets the automaton to run on a new string
        // returns active state to be start state
        this.activeStates = new HashSet<String>();
        this.activeStates.add(start);
        this.epsClosure();
        return this.accept();
    }
    
    public void toDot(){
    /*
    * Prints out a description of the automaton in dot,
    * which is a graph specification language.
    * I recommend going to this url to convert it into
    * an image: https://dreampuf.github.io/GraphvizOnline/
    */
        System.out.println("digraph G {");
        
        String start_shape;
        if (this.finals.contains(start)){
            start_shape = "doubleoctagon";
        }
        else {
            start_shape = "octagon";
        }
        System.out.println("node [shape = " + start_shape + "]; " + this.start + ";");
        
        String double_circled = "node [shape = doublecircle];";
        boolean additional_finals = false;
        for(String s : this.finals){
            if (s.equals(start)){
                continue;
            }
            double_circled += " " + s;
            additional_finals = true;
        }
        if(additional_finals){
            System.out.println(double_circled + ";");
        }
        
        String single_circled = "node [shape = circle];";
        boolean additional_states = false;
        for(String s : this.states){
            if (s.equals(start) || finals.contains(s)){
                continue;
            }
            single_circled += " " + s;
            additional_states = true;
        }
        if (additional_states){
            System.out.println(single_circled);
        }
        
        for (Tuple t : delta.keySet()){
            for(String dest : delta.get(t)){
                String transition = "";
                transition += t.state + " -> " + dest + " [ label = \"" + t.input_char + "\" ];";
                System.out.println(transition);
            }
        }
        
        System.out.println("}");

    }
    
    public static void addToDelta(Map<Tuple, Set<String>> d, String source, char character, String dest){
        // This function makes it easier to add a transition to a delta map
        Tuple t = new Tuple(source, character);
        if(!d.containsKey(t)){
            d.put(t, new HashSet<String>());
        }
        d.get(t).add(dest);
    }
    
    public static void addAllToDelta(Map<Tuple, Set<String>> d, String source, Set<Character> chars, String dest){
        // This function makes it easier to add many transitions to a delta map
        for(char character : chars){
           addToDelta(d, source, character, dest); 
        }
    }

}
