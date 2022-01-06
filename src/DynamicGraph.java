import java.io.DataOutputStream;
import java.io.IOException;

public class DynamicGraph {
    GraphNode first = null;
    GraphNode lastinPI = null;
    GraphNode firstPI = null;
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
        firstPI = source;
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
            prev_node = prev_node.next;
        }
        if (source != null) {
            source.color = 1;
            source.distance = 0;
            source.bfs_parent = null;
        }


    }





    public RootedTree bfs(GraphNode source) {
        bfs_initialization(source);
        GraphNode pointer = new TreeNode();
        pointer = firstPI;
        GraphEdge edge;
        TreeNode root = new TreeNode(source.getKey(),null);
        RootedTree bfs_tree = new RootedTree(root);
        while (pointer!=null)// need to think what to do instad of Q, and how to solve haritage problem
        {

            edge = pointer.lastoutedge;
            while (edge!=null){
                if (edge.to.color==0)// if white
                {
                    edge.to.color=1;
                    edge.to.distance = pointer.distance+1;
                    TreeNode tnode = new TreeNode(pointer.key, (TreeNode)pointer);
                    firstPI.nextinPI= edge.to;

                }
                firstPI = firstPI.nextinPI;
                edge = edge.prevout;
            }
            pointer = pointer.nextinPI;

        }

        return bfs_tree;
    }








    public RootedTree scc() {
        TreeNode new_root = new TreeNode(0, null);
        RootedTree tree = new RootedTree(new_root);
        dfs();
        dfs_rev();
        GraphNode ver = lastinPI;
        while (ver!=null) {
            if (ver.bfs_parent == null) {
                new TreeNode(ver.getKey(), new_root);
            }
            else {
                new TreeNode(ver.getKey(), (TreeNode) ver.bfs_parent);
            }
            ver= ver.nextinPI;
        }
        return tree;
    }

    public void dfs() {
        GraphNode vertex = GraphNode.lastnode;
        while (vertex!=null){
            vertex.color = 0;
            vertex.bfs_parent = null;
            vertex = vertex.next;
        }
        time = 0;
        vertex = GraphNode.lastnode;
        while (vertex != null) {
            if (vertex.color == 0) {
                DFS_Visit_reg(vertex);
            }
            vertex = vertex.next;
        }
    }

    public void DFS_Visit_reg(GraphNode u){
        time = time +1;
        u.d = time;
        u.color = 1;
        GraphEdge edge= u.lastoutedge;
        while (edge!=null){
            GraphNode Adj =edge.to;
            if (Adj.color == 0){
                Adj.bfs_parent = u;
                DFS_Visit_reg(Adj);
            }
            edge=edge.nextout;

        }
        u.color =2;
        time = time +1;
        u.f = time;
        if(lastinPI !=null){
            lastinPI.previnPI = u;
            u.nextinPI = lastinPI;
            lastinPI = u;
        }
        else {
            lastinPI = u;
        }
    }
    public void dfs_rev(){
        GraphNode vertex = lastinPI;
        while (vertex!=null){
            vertex.color = 0;
            vertex.bfs_parent = null;
            vertex = vertex.nextinPI;
        }
        time = 0;
        vertex = lastinPI;
        while (vertex != null) {
            if (vertex.color == 0) {
                DFS_Visit_rev(vertex);
            }
            vertex = vertex.nextinPI;
        }
    }



    public void DFS_Visit_rev(GraphNode u1){
        time = time +1;
        u1.d = time;
        u1.color = 1;

        GraphEdge edge = u1.lastinedge;
        while (edge!=null){
            GraphNode Adj = edge.from;
            if (Adj.color == 0){
                Adj.bfs_parent = u1;
                DFS_Visit_reg(Adj);
            }
            edge = edge.nextin;
        }
        u1.color =2;
        time = time +1;
        u1.f = time;
    }
}