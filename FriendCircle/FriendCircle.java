public class friend {

    private int[] parent;  // parent[i] = parent of i
    private int[] rank;   // rank[i] = rank of subtree rooted at i

    /**
     *
     * @param M the adjacency matrix of friends
     * @return number of friend circles
     */
    public int findCircleNum(int[][] M) {
        int[] list = new int[M.length];
        int[] list2 = new int[M.length];
        for(int x = 0; x < M.length; x++ ){
            list[x] = x;
        }
        for(int y = 0; y < M.length; y++){
            list2[y] = 1;
        }
        parent = list;
        rank = list2;
        for(int i = 0; i < M.length; i++){
            for(int j = 0; j < M.length; j++){
                if(M[i][j] == 1){
                    if(connected(i,j) == false && (i != j)) {
                        union(i, j);
                    }
                }
            }
        }
        int counter = 0;
        int x = 0;
        while(x < parent.length){
            if(parent[x] == x){
                counter +=1;
            }
            x+=1;
        }
        return counter;
    }


    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        while( p != parent[p]){
            p = parent[p];
        }
        return p;
    }


    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (rank[i] < rank[j]) {
            parent[i] = j;
            rank[j] += rank[i];
        } else {
            parent[j] = i;
            rank[i] += rank[j];
        }
    }

    public static void main(String[] args) {
        int [][] tester = {{1,1,0},{1,1,1},{0,1,1}};
        friend circle = new friend();
        System.out.println(circle.findCircleNum(tester));
    }
