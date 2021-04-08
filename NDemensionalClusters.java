import java.util.ArrayList;

public class NDemensionalClusters {

    /**
     * Adds a point to the dataSet being considered
     *
     * @param point
     */
    ArrayList<Point> pointer = new ArrayList<>();
    EdgeWeightedGraph grapher;
    int counter = 0;

    public void add(Point point) {
        counter += 1;
        grapher = new EdgeWeightedGraph(counter);
        pointer.add(point);
        for (int i = 0; i < pointer.size(); i++) {
            for (int j = 0; j < pointer.size(); j++) {
                double distance = pointer.get(i).distance(pointer.get(j));
                if (distance != 0.0) {
                    distance = distance * -1;
                }
                Edge edgy = new Edge(i, j, distance);
                grapher.addEdge(edgy);
            }
        }
    }

    public double getSum(int clusters) {
        if (clusters == grapher.V()) {
            return 0.0;
        }
        KruskalMST m = new KruskalMST(grapher);
        if (clusters == 1) {
            double broker = m.weight();
            broker = broker *-1;
            return broker;
        }
        //System.out.println(m.edges());
        double currentW = m.weight();
        currentW = currentW *-1;
        counter = 1;
        ArrayList<Edge> edgeArr = new ArrayList<>();
        for (Edge e : m.edges()) {
            edgeArr.add(e);
        }
        Edge fakeEdge = new Edge(0, 0, 12345);
        Edge minEdge = fakeEdge;
        while (counter != clusters) {
            double minWeight = 1000000000.0;
            for (Edge e : edgeArr) {
                double edgeWeight = e.weight();
                edgeWeight = edgeWeight *-1;
                if (edgeWeight < minWeight) {
                    minWeight = edgeWeight;
                    minEdge = e;
                }
            }
            edgeArr.remove(minEdge);
            //System.out.println(edgeArr);
            currentW -= minWeight;
            counter += 1;
        }
        return currentW;
    }

    public static void main(String[] args) {
        NDemensionalClusters n = new NDemensionalClusters();
        double[] c1 = {0, 0};
        Point p1 = new Point(c1);
        double[] c2 = {0, 1};
        double[] c3 = {1, 0};
        double[] c4 = {1, 1};
        Point p2 = new Point(c2);
        Point p3 = new Point(c3);
        Point p4 = new Point(c4);
        n.add(p1);
        n.add(p2);
        n.add(p3);
        n.add(p4);
        System.out.println(n.getSum(1));
    }
}