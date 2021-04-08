import java.util.HashMap;

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUConstantTime obj = new LRUConstantTime(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LRUConstantTime {

    public HashMap<Integer, Node> hmap;
    public LRULinkedList LRULinkedList;
    public LRUConstantTime(int capacity) {
        this.hmap = new HashMap<Integer, Node>();
        this.LRULinkedList = new LRULinkedList(capacity);
    }

    /**
     * Returns the value for the key in data structure.
     * If the value is not in data return -1
     * @param key
     * @return value
     */
    public int get(int key) {
        Node noder = this.hmap.get(key);
        if(noder == null){
            return -1;
        }
        noder.next = this.hmap.get(key).next;
        noder.previous = this.hmap.get(key).previous;
        this.LRULinkedList.moveNodeToHead(noder);
        return noder.value;
    }

    /**
     * Put a key value pair in the cache. It value already exist
     * Override the value otherwise add the key value pair.
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if(this.hmap.get(key) != null){
            this.hmap.get(key).value = value;
            this.LRULinkedList.moveNodeToHead(this.hmap.get(key));
        }
        else{
            Node noder = new Node(key,value);
            Node noderific = this.LRULinkedList.tail;
            this.LRULinkedList.add(key, value);
            this.hmap.put(this.LRULinkedList.head.key, this.LRULinkedList.head);
            if(this.hmap.size() > this.LRULinkedList.capacity){
                this.hmap.remove(noderific.key);
            }
            }
        }

    public static void main(String[] args){
    }

}