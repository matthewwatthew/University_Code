import java.util.*;

public class RegexToken{
    // You can think of a RegexToken as a node in a prefix tree for the regex
    // Each token has an operation and a list of operands to combine with that operation
    // For example, the regex a+b+c will result in a token with the operation +, and three
    // tokens as "children": one for each of a, b, and c.
    // If we have a NFA which accepts the strings described by the child tokens
    // we can produce an NFA which accepts the strings described by the parent token
    // by applying the identified construction to combine all child tokens.

    public char operation;
    public List<RegexToken> operands;
    
    public RegexToken(char operation, List<RegexToken> operands){
        this.operation = operation;
        this.operands = operands;
    }
    
    public RegexToken(char singleton){
        this.operation = singleton;
        this.operands = new ArrayList<RegexToken>();
    }
    
    public RegexToken(){
        this.operation = (new Epsilon()).getChar();
        this.operands = new ArrayList<RegexToken>();
    }
    
    public RegexToken(String regex){
        RegexToken rt = tokenize(regex);
        this.operation = rt.operation;
        this.operands = rt.operands;    
    }
    
    private static String insertConcat(String regex){
        // It was easier for me to parse the regex if I had a operation for concatenation
        // rather than just "smushing" the things together.
        // Here I add a . everyhwere there should be a concatenation.
        List<Character> noConcatAfter = Arrays.asList('(', '+', '.');
        List<Character> noConcatBefore = Arrays.asList(')', '+', '.', '*');
        String newRegex = "";
        newRegex += Character.toString(regex.charAt(0));
        for(int i = 1; i < regex.length(); i++){
            char newRegexEnd = newRegex.charAt(newRegex.length()-1);
            char nextChar = regex.charAt(i);
            if(!noConcatAfter.contains(newRegexEnd) && !noConcatBefore.contains(nextChar)){
                newRegex += ".";
            }
            newRegex += Character.toString(nextChar);
        }
        return newRegex;
    }
    
    private static RegexToken tokenize(String regex){
        // This is really the most complicated part of building the regex engine.
        // This is what takes the regex and converts it into the prefix representation.
        // Essentially, it checks that parentheses are balanced, and if so then it 
        // finds which operation is not contained in any parenteses (if one exists).
        // If there are multiple, it does union, then concatenation, then star
        // (this is the reverse of order of operations).
        // If there is no out-most operation, this means that the entire regex
        // was wrapped in outer-most parentheses, so it removes them.
        if(regex.length() == 1){
            return new RegexToken(regex.charAt(0));
        }
        if(regex.length() == 0){
            return new RegexToken();
        }
        regex = insertConcat(regex);
        char op = '\u2202'; // the default is to a character that I wouldn't expect you to ever really want. I picked a random unicode character. Basically, I just need to know when I didn't find an outer-most operation, which I do by checking if the default ever changed.
        int parens = 0;
        String currExpr = "";
        List<RegexToken> tokens = new ArrayList<RegexToken>();
        
        // this for loop checks for union
        for(int i = 0; i < regex.length(); i++){
            char c = regex.charAt(i);
            if(c == '('){
                parens++;
            }
            else if(c == ')'){
                parens--;
            }
            if(parens < 0){
                System.out.println("Unbalanced Parentheses!");
                return new RegexToken();
            }
            if ((c == '+') && (parens == 0)){
                //we're doing a union
                op = '+';
                //System.out.println(Character.toString(op) + " " + currExpr);
                tokens.add(tokenize(currExpr));
                currExpr = "";
            }
            else{
                currExpr += Character.toString(c);
            }
        }
        if(parens != 0){
            System.out.println("Unbalanced Parentheses!");
            return new RegexToken();
        }
        if(op != '\u2202'){
            tokens.add(tokenize(currExpr));
            return new RegexToken(op, tokens);
        }
        currExpr = "";
        tokens = new ArrayList<RegexToken>();
        // this for loop checks for concatenation
        for(int i = 0; i < regex.length(); i++){
            char c = regex.charAt(i);
            if(c == '('){
                parens++;
            }
            else if(c == ')'){
                parens--;
            }
            if ((c == '.') && (parens == 0)){
                //we're doing a union
                op = '.';
                tokens.add(tokenize(currExpr));
                currExpr = "";
            }
            else{
                currExpr += Character.toString(c);
            }
        }
        if(op != '\u2202'){
            tokens.add(tokenize(currExpr));
            return new RegexToken(op, tokens);
        }
        if(regex.charAt(regex.length()-1) == '*'){
            op = '*';
            currExpr = regex.substring(0, regex.length()-1);
            tokens.add(tokenize(currExpr));
            return new RegexToken(op, tokens);
        }
        currExpr = regex.substring(1, regex.length()-1);        
        return tokenize(currExpr);
    }
    
    public String toString(){
        String str = "[";
        str += Character.toString(this.operation);
        str += ":";
        for(RegexToken t : this.operands){
            str += t.toString() + ",";
        }
        str += "]";
        return str;
    }
    
    public static void main(String[] args){
        //String regex = "((aa)*+(ab)*)*ba*)";
        //String regex = "(aa)*+(ab)*";
        //String regex = "(aa)*(ab)*(bb)*";
        //String regex = "(ab)*";
        //String regex = "(ab)";
        String regex = "(x*+y*)(aa)*";
        //System.out.println(insertConcat(regex));
        RegexToken rt = new RegexToken(regex);
        NFA nfa = NFAClosure.token2NFA(rt);
        //nfa.toDot();
        System.out.println(nfa.decide("yyy"));
        System.out.println(nfa.decide("yyyaaa"));
    }
}
