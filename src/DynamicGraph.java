import java.io.DataOutputStream;
import java.io.IOException;

public class DynamicGraph {
    GraphNode first = null;
    GraphNode lastinPI = null;
    static int time = 0;

    public DynamicGraph() {
    }


    public GraphEdge InsertEdge(GraphNode from, GraphNode to) {
        GraphEdge edge = new GraphEdge(from, to);
        from.setLastoutedge(edge);
        to.setLastinedge(edge);
        return edge;


    }

    public void deleteEdge(GraphEdge edge) {
        edge.from = null;
        edge.to = null;
        GraphEdge G;
        if ((edge.nextin != null) && (edge.previn != null)) //checking it's not the first or last edge
        {
            G = edge.previn;
            edge.nextin.previn = G;
            G = edge.nextin;
            edge.previn.nextin = G;
        }
        if ((edge.nextout != null) && (edge.prevout != null)) //checking it's not the first or last edge
             {
            G = edge.prevout;
            edge.nextout.prevout = G;
            G = edge.nextout;
            edge.prevout.nextout = G;
        }
        if (edge.nextin == null) { //if first in
            edge.previn.nextin = null;
        }
        if (edge.nextout == null) { // if first in
            edge.prevout.nextout = null;
        }
        if (edge.previn == null) { //if last in
            edge.nextin.previn = null;
        }
        if (edge.prevout == null) { // if last in
            edge.nextout.prevout = null;
        }
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


    private void bfs_initialization(GraphNode source) {
        GraphNode next_node = source.next;
        GraphNode prev_node = source.prev;
        while (next_node != null) {
            next_node.color = 0;
            next_node.distance = -1;
            next_node.bfs_parent = null;
            next_node = next_node.next;
        }
        while (prev_node != null) {
            prev_node.color = 0;
            prev_node.distance = -1;
            prev_node.bfs_parent = null;
            prev_node = next_node.next;
        }
        if (source != null) {
            source.color = 1;
            source.distance = 0;
            source.bfs_parent = null;
        }


    }


    private void dfs_initialization(GraphNode source) {
        GraphNode next_node = source.next;
        GraphNode prev_node = source.prev;
        while (next_node != null) {
            next_node.color = 0;
            next_node.distance = -1;
            next_node.bfs_parent = null;
            next_node = next_node.next;
        }
        while (prev_node != null) {
            prev_node.color = 0;
            prev_node.distance = -1;
            prev_node.bfs_parent = null;
            prev_node = next_node.next;
        }
        if (source != null) {
            source.color = 0;
            source.distance = 0;
            source.bfs_parent = null;
        }


    }

    public RootedTree bfs(GraphNode source) {
        bfs_initialization(source);
        int outdeg;
        TreeNode pointer = new TreeNode();
        GraphNode node = pointer;
        GraphEdge edge;
        TreeNode root = new TreeNode(source.getKey());
        while (true)// need to think what to do instad of Q, and how to solve haritage problem
        {
            outdeg = root.getOutDegree();
            for (int i = 0; i < outdeg; i++) {
                edge = root.lastoutedge;
                //pointer = edge.to;

            }

        }


    }


    public RootedTree scc() {
        TreeNode new_root = new TreeNode(0);
        dfs_initialization(GraphNode.lastnode);
        GraphNode vertex = GraphNode.lastnode;
        GraphNode.time = 0;
        while (vertex != null) {
            if (vertex.color == 0) {
                DFS_Visit(vertex);
            }
            vertex = vertex.next;
        }

    }

    public void DFS_Visit(GraphNode u){
        time = time +1;
        u.d = time;
        u.color = 1;
        GraphNode Adj = u.lastoutedge.to;
        while (Adj!=null){
            if (Adj.color == 0){
                Adj.bfs_parent = u;
                DFS_Visit(Adj);
            }
        }
        u.color =2;
        time = time +1;
        u.f = time;


    }
}