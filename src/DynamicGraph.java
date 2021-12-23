

public class DynamicGraph {
    GraphNode root = null;

    public DynamicGraph() {

    }

    public GraphNode insertNode(int nodeKey) {
        root = new GraphNode(nodeKey);
        return root ;
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