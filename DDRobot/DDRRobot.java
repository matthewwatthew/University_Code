//Matthew Jordan (Mrj3dd)

import java.util.*;

public class DDRRobot {
    public Digraph G = null;
    private int Demension;

    public DDRRobot(int squares){
       Demension = squares;
       G = new Digraph(squares);
    }

    public void addTile(int square, int arrow){
        if(arrow == 1){
            if((square + 1) % 3 != 0) {
                G.addEdge(square, square + 1);
            }
        }
        if(arrow == 2){
            double squareroot = Math.sqrt(Demension);
            int finalInt = (int) squareroot;
            G.addEdge(square,square - finalInt);
        }
        if(arrow == 3) {
            if ((square) % 3 != 0){
                G.addEdge(square, square - 1);
            }
        }
        if(arrow == 4){
            double squareroot = Math.sqrt(Demension);
            int finalInt = (int) squareroot;
            G.addEdge(square,square + finalInt);
        }
    }

    private int[] id;
    private int count;
    private boolean[] marked;

    public ArrayList<Integer> getPlayOptions(){
        DepthFirstOrder ordered = new DepthFirstOrder(G.reverse());
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v: ordered.reversePost()){
            if(!marked[v]){
                dfs(G,v);
                count++;
            }
        }
        HashMap<Integer,Integer>  hasher = new HashMap<Integer,Integer>();
        int count1  = 1;
        int maxValue = 0;

        for(int i = 0; i < id.length; i++) {

            if (hasher.get(id[i]) != null) {

                int count = hasher.get(id[i]);
                count++;
                hasher.put(id[i], count);

                if(count > count1) {
                    count1  = count;
                    maxValue = id[i];
                }
            }

            else
                hasher.put(id[i],1);
        }
        ArrayList<Integer> finalList = new ArrayList<Integer>();
        for(int i = 0; i <G.V(); i++){
            if(id[i] == maxValue){
             finalList.add(i);
            }
        }
        return finalList;
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    public class DepthFirstOrder {
        private boolean[] marked;          // marked[v] = has v been marked in dfs?
        private int[] post;                // post[v]   = postorder number of v
        private LinkedList<Integer> postorder;  // vertices in postorder
        private int postCounter;           // counter for postorder numbering

        /**
         * Determines a depth-first order for the digraph {@code G}.
         * @param G the digraph
         */
        public DepthFirstOrder(Digraph G) {
            post   = new int[G.V()];
            postorder = new LinkedList<>();
            marked    = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++)
                if (!marked[v]) dfs(G, v);

        }

        // run DFS in digraph G from vertex v and compute preorder/postorder
        private void dfs(Digraph G, int v) {
            marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
            postorder.add(v);
            post[v] = postCounter++;
        }

        public Iterable<Integer> reversePost() {
            Collections.reverse(postorder);
            return postorder;
        }



    }
    public static void main(String[] args) {

    }
}