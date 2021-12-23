

public class DynamicGraph {
    GraphNode first = null;

    public DynamicGraph() {
    }

    public GraphNode insertNode(int nodeKey) {
        first = new GraphNode(nodeKey);
        return first ;
    }

    public void deleteNode(GraphNode node) {
        if ((node.lastoutedge == null) && (node.lastinedge == null)){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }
    }

}