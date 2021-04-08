import java.util.*;

public class IcecreamCone implements Decider{
    // This decider accepts all valid ice cream cones
    // An ice cream cone is valid provided that it starts with a cone, then has a sequence of scoops of available flavors
    // The list of scoops on the cone is comma-separated
    // There is no limit on the number of scoops allowed.
    
    ArrayList<String> flavors; // The list of all the flavors that are available

    public IcecreamCone(){
        this.flavors = new ArrayList(Arrays.asList("chocolate", "vanilla", "strawberry", 
                        "cookies and cream", "rocky road", 
                        "cookie dough", "butter pecan", "coffee", 
                        "cotton candy", "peanutbutter cup", "moose tracks",
                        "mint chocolate chip", "salted caramel", "rum raisin",
                        "cake batter", "butterscotch", "bubblegum", 
                        "orange serbert", "rainbow", "tutti frutti", 
                        "cinnamon toast crunch", "cilantro lime"));
    }

    public boolean decide(String s){
        String[] scoops = s.split(",",0);
        if(scoops.length == 0 || !scoops[0].equals("cone")){
            // the ice cream cone must start with the cone
            return false;
        }
        for(int i = 1; i < scoops.length-1; i++){
            // after the cone, the rest of the string must be a comma-separated list of things from flavors
            if(!flavors.contains(scoops[i])){
                return false;
            }
        }
        return true;
        
    }
    
    public static void main(String[] args){
        Decider d = new IcecreamCone();
        System.out.println(d.decide("cone,vanilla,chocolate,bubblegum,rocky road,cilantro lime,rainbow,strawberry,strawberry,moose tracks"));
        System.out.println(d.decide("cone"));
        System.out.println(d.decide("cone,vanilla,vanilla,vanilla,vanilla,vanilla,vanilla,vanilla,vanilla"));
        System.out.println(d.decide("vanilla,vanilla,vanilla,vanilla,vanilla"));
        System.out.println(d.decide("cone,vanilla,vanilla,fish,vanilla,vanilla,vanilla"));
        System.out.println(d.decide("cone,,vanilla"));
    }

}
