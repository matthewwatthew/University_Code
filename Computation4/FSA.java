import java.util.*;

public abstract class FSA implements Decider
{
/*
* A Finite State Automaton (FSA) is a kind of decider.
* Its input is always a string, and it either accepts or rejects that string.
* We're making this an abstract class for now (where not all parts are defined)
* because there are many different kinds of finite state automata. So far we've
* only looked at the simplest kind, but we'll be looking at more complex ones for
* the next homework assignment.
*/

    public Set<String> states; // Q
    public String start; // q_0
    public Set<String> finals; // F
    public Set<Character> alphabet; // \Sigma
    
    public boolean decide(String s){
        boolean accept = reset();
        accept = finals.contains(start);
        for(int i = 0; i < s.length(); i++){
            accept = step(s.charAt(i));
        }
        reset();
        return accept;
    }
    
    public abstract boolean step(char c);
    public abstract boolean reset();
    public abstract void toDot();

};
