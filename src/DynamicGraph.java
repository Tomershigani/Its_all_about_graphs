import java.io.DataOutputStream;
import java.io.IOException;


public class DynamicGraph {
    GraphNode first = null;
    GraphNode lastinPI = null;
    GraphNode firstPI = null;
    static int time = 0;

    public DynamicGraph() {
        lastinPI = null;
    }


    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        if(from.getKey()==39290&&to.getKey()==1628)
            System.out.println("mark");
        GraphEdge edge = new GraphEdge(from, to);
        //from.setLastoutedge(edge);
        //to.setLastinedge(edge);
        return edge;


    }

    public void deleteEdge(GraphEdge edge) {
       //edge.from = null;
       //edge.to = null;
        GraphEdge G;
        if(edge.from.lastoutedge==edge)
            edge.from.lastoutedge=edge.nextout;
        if(edge.to.lastinedge==edge)
            edge.to.lastinedge=edge.nextin;


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
            if(edge.previn!=null)
                edge.previn.nextin = null;
           /* else {
                edge.from.lastoutedge = null;
                edge.to.lastinedge = null;
            }

            */
        }
        if (edge.nextout == null) { // if first out
            if(edge.prevout!=null)
                edge.prevout.nextout = null;
            /*
            else {
                edge.from.lastoutedge = null;
                edge.to.lastinedge=null;
            }

             */
        }
        if (edge.previn == null)  //if last in
            if(edge.nextin!=null)
            edge.nextin.previn = null;
            /*
            else
            {
                edge.from.lastoutedge = null;
                edge.to.lastinedge=null;
            }

             */

        if (edge.prevout == null) { // if last out
            if(edge.nextout!=null)
            edge.nextout.prevout = null;
            /*
            else   {
                edge.from.lastoutedge = null;
                edge.to.lastinedge=null;
            }

             */

        }
    }


    public GraphNode insertNode(int nodeKey) {
        first = new GraphNode(nodeKey);
        return first ;
    }

    public void deleteNode(GraphNode node) {
        if ((node.lastoutedge == null) && (node.lastinedge == null)){
            if (node.prev!=null) {
                node.prev.next = node.next;
            }
            if (node.next!=null) {
                node.next.prev = node.prev;
            }
            node.prev = null;
            node.next = null;
        }
    }

    private void PI_Reset(GraphNode source){
        GraphNode next_node = source.nextinPI;
        GraphNode prev_node = source.previnPI;
        while (next_node!=null)
        {
            source.nextinPI=null;
            next_node=next_node.nextinPI;
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
            next_node.parent =null;
            next_node.leftChild = null;
            next_node.rightsibiling=null;
            next_node.nextinPI=null;
            next_node.previnPI=null;
            next_node = next_node.next;
        }
        while (prev_node != null) {
            prev_node.color = 0;
            prev_node.distance = -1;
            prev_node.bfs_parent = null;
            prev_node.leftChild = null;
            prev_node.rightsibiling=null;
            prev_node.parent = null;
            prev_node.previnPI=null;
            prev_node.nextinPI = null;
            prev_node = prev_node.prev;

        }
        if (source != null) {
            source.color = 1;
            source.distance = 0;
            source.bfs_parent = null;
            source.leftChild = null;
            source.rightsibiling=null;
            source.parent=null;
            source.previnPI=null;
            source.nextinPI = null;
        }




    }

public void printnodekey(GraphNode node)
{
     GraphEdge edeg= node.lastoutedge;
     while (edeg!=null){
         System.out.print(edeg.to.getKey());
         edeg = edeg.prevout;
     }

}



    public RootedTree bfs(GraphNode source) {// to check why last in edge is null

        bfs_initialization(source);
        GraphNode pointer = firstPI;
        GraphEdge edge;
        //GraphNode root = new GraphNode(source.getKey(),null);
        //GraphNode root = source; // check this
        RootedTree bfs_tree = new RootedTree(source);
        while (pointer!=null)// need to think what to do instad of Q, and how to solve haritage problem
        {
            edge = pointer.lastoutedge;
            while (edge!=null){
                if (edge.to.color==0)// if white
                {
                    edge.to.color=1;
                    edge.to.distance = pointer.distance+1;
                    //GraphNode tnode = new GraphNode(edge.to.key,pointer);
                    edge.to.setparent(pointer);
                    firstPI.nextinPI= edge.to;
                    firstPI = firstPI.nextinPI;
                }
               // firstPI = firstPI.nextinPI;
                edge = edge.nextout;
            }
            pointer = pointer.nextinPI;

        }

        return bfs_tree;
    }



    private void dfs_rev_initialization(GraphNode source) {

        GraphNode next_node = source.nextinPI;
        GraphNode prev_node = source.previnPI;
        lastinPI = source;
        while (next_node != null) {
            next_node.color = 0;
            next_node.distance = -1;
            next_node.bfs_parent = null;
            next_node.parent =null;
            next_node.leftChild = null;
            next_node.rightsibiling=null;
            next_node = next_node.nextinPI;
        }
        while (prev_node != null) {
            prev_node.color = 0;
            prev_node.distance = -1;
            prev_node.bfs_parent = null;
            prev_node.leftChild = null;
            prev_node.rightsibiling=null;
            prev_node.parent = null;
            prev_node = prev_node.previnPI;

        }
        if (source != null) {
            source.color = 1;
            source.distance = 0;
            source.bfs_parent = null;
            source.leftChild = null;
            source.rightsibiling=null;
            source.parent=null;
        }




    }




    public RootedTree scc() {
        lastinPI = null;
        dfs();
        dfs_rev();
        GraphNode ver = lastinPI;
        GraphNode new_root = new GraphNode(0);
        RootedTree tree = new RootedTree(new_root);
        while (ver!=null){
            if (ver.bfs_parent == null) {
                if (ver.visited != true) {
                    //new GraphNode(ver.getKey(), new_root);
                     ver.setparent(new_root);
                }
            }
            ver.color = 0;
            ver= ver.nextinPI;
        }
        lastinPI = null;
        return tree;
    }

    public void dfs() {
        GraphNode vertex = GraphNode.lastnode;
        bfs_initialization(vertex);
        while (vertex!=null){
            vertex.color = 0;
            vertex.bfs_parent = null;
            vertex.leftChild = null;//
            vertex.parent = null;//
            vertex.rightsibiling = null;//
            vertex.visited = false;
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
        GraphEdge edge1= u.lastoutedge;
        while (edge1!=null){
            GraphNode Adj = edge1.to;
            if(Adj!=null) { //// cheking adj !=null
                if (Adj.color == 0) {
                    Adj.bfs_parent = u;
                    DFS_Visit_reg(Adj);
                }
            }
            edge1=edge1.nextout;

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
        dfs_rev_initialization(vertex);
        while (vertex!=null){
            vertex.color = 0;
            vertex.bfs_parent = null;
            vertex.leftChild = null;//
            vertex.parent = null;//
            vertex.rightsibiling = null;//
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
        GraphEdge edge2 = u1.lastinedge;
        while (edge2!=null){
            GraphNode Adj = edge2.from;
            if(Adj!=null) {
                if (Adj.color == 0) {
                   // if (u1!=null){
                    Adj.setparent(u1);
                    //    System.out.println(u1.getKey()+ "father of" +Adj.getKey());
                 //   }
                  //  else{
               // Adj.bfs_parent = u1;
                 //   }
                DFS_Visit_rev(Adj);
                }
            }
            edge2 = edge2.nextin;
        }
        u1.color =2;
        time = time +1;
        u1.f = time;
        }
    }
