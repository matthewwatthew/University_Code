public class BirdScooters {

    Node root;

    /**
     * Constructor
     */
    public BirdScooters() {
        root = null;
    }

    public Node adder(Node current, double x, double y, int counter) {
        if (current == null) {
            Node noder = new Node(x, y, counter);
            return noder;
        }
        if (counter % 2 == 0) {
            if (x < current.x) {
                current.left = adder(current.left, x, y, counter + 1);
            } else {
                current.right = adder(current.right, x, y, counter + 1);
            }
        } else {
            if (y < current.y) {
                current.left = adder(current.left, x, y, counter + 1);
            } else {
                current.right = adder(current.right, x, y, counter + 1);
            }
        }
        return current;
    }

    /**
     * Adds a scooter store at the Node passed in
     *
     * @param scooter - location of the scooter store
     * @return true if added
     */
    public boolean add(Node scooter) {
        if (root == null) {
            root = scooter;
            scooter.level = 0;
            return true;
        }
        Node node = adder(root, scooter.x, scooter.y, 0);
        return true;
    }

    /**
     * Find the closest scooter to the provided location
     *
     * @param location
     * @return closest Node corresponding to the closest scooter store
     */
    double champion;
    Node championNode;

    public Node closestPoint(Node location) {
        double rootDist = Distance(root, location);
        championNode = root;
        champion = rootDist;
        closer(root, location);
        return championNode;
    }

    public void closer(Node rooter, Node location) {
        if (rooter == null) {
            return;
        }
        if (champion > Distance(rooter, location)) {
            champion = Distance(rooter, location);
            championNode = rooter;
        }
        if (rooter.level % 2 == 0) {
            if (location.x < rooter.x) {
                closer(rooter.left,location);
                if(Math.max(rooter.x,location.x) - Math.min(rooter.x,location.x) > champion){
                    return;
                }
                closer(rooter.right,location);
            } else {
                closer(rooter.right,location);
                if(Math.max(rooter.x,location.x) - Math.min(rooter.x,location.x) > champion){
                    return;
                }
                closer(rooter.left,location);
            }
        } else {
            if (location.y < rooter.y) {
                closer(rooter.left, location);
                if(Math.max(rooter.y,location.y) - Math.min(rooter.y,location.y)> champion){
                    return;
                }
                closer(rooter.right, location);
            } else {
                closer(rooter.right, location);
                if(Math.max(rooter.y,location.y) - Math.min(rooter.y,location.y) > champion){
                    return;
                }
                closer(rooter.left, location);
            }
        }
    }

    // Recurse on half with query point is on first, distance to line we're splitting, found champion. Subtract x1 from x2.
    // Root node, node we're on. Query point is greater than. recurse to right. Return some distance. Compare Root to query point.
    // This is the minimum distance there. If it is greater than, no point in checking other size.

    /***************************************************************************
     *  Helper function for Distance Formula
     ***************************************************************************/

    private double Distance(Node node, Node query) {
        return Math.sqrt(Math.pow(node.x - query.x, 2) + Math.pow(node.y - query.y, 2));
    }

    /***************************************************************************
     *  Main method
     ***************************************************************************/

    public static void main(String args[]){
        Node node1 = new Node(1, 3);
        Node node2 = new Node(1, 8);
        Node node3 = new Node(2, 2);
        Node node4 = new Node(2, 10);
        Node node5 = new Node(3,6);
        Node node6 = new Node(4,1);
        Node node7 = new Node(5, 4);
        Node node8 = new Node(6, 8);
        Node node9 = new Node(7, 4);
        Node node10 = new Node(7, 7);
        Node node11 = new Node(8, 2);
        Node node12 = new Node(8, 5);
        Node node13 = new Node(9, 9);
        Node location = new Node(4,8);
        BirdScooters birdo = new BirdScooters();
        birdo.add(node1);
        birdo.add(node2);
        birdo.add(node3);
        birdo.add(node4);
        birdo.add(node5);
        birdo.add(node6);
        birdo.add(node7);
        birdo.add(node8);
        birdo.add(node9);
        birdo.add(node10);
        birdo.add(node11);
        birdo.add(node12);
        birdo.add(node13);
        System.out.println(birdo.closestPoint(location));


    }

}