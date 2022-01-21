import jdk.nashorn.api.tree.Tree;

public class GraphNode {

    GraphNode next;
    GraphNode prev;
    static GraphNode lastnode = null;
    GraphEdge lastinedge;
    GraphEdge lastoutedge;
    final int key;
    int color; // the color of node in the bfs tree, 0- white,1-gray,2-black
    int distance;// the distance from root in bfs tree
    GraphNode bfs_parent;// the parent in bfs tree
    int d;
    int f;
    GraphNode nextinPI;
    GraphNode previnPI;
    GraphNode parent;
    GraphNode leftChild;
    GraphNode rightsibiling;
    boolean visited = false;


    GraphNode (){
        this.key = -1;
    }

    GraphNode(int key) {
        this.parent = null;
        this.leftChild = null;
        this.rightsibiling = null;
        this.color =0;
        this.distance=-1;
        this.bfs_parent=null;
        this.prev = null;
        this.key = key;
        if (key!=0) {
            if (lastnode != null) {
                this.next = lastnode;
                lastnode.prev = this;
                lastnode = this;
            } else {
                this.next = null;
                lastnode = this;
            }
        }
        this.lastinedge=null;
        this.lastoutedge=null;
    }
    public GraphNode(int key , GraphNode pi) {
        this(key);
        if(pi!=null) { //// add if if pi == null noting
            this.parent = pi;
            if (parent.leftChild != null) {
                GraphNode temp = parent.leftChild;
                while (temp.rightsibiling != null) {
                    temp = temp.rightsibiling;
                }
                temp.rightsibiling = this;
            }
            else {
                parent.leftChild = this;
            }
        }
    }




    public int getKey(){
        return this.key;
    }

    public void setLastinedge(GraphEdge edge){
        this.lastinedge=edge;
    }

    public void setLastoutedge(GraphEdge edge){
        this.lastoutedge=edge;
    }

    public int getInDegree()
    {   int count =0;
        GraphEdge edge=this.lastinedge;
        while (edge!=null){
            count++;
            edge=edge.nextin;
        }
    return count;
    }
    public int getOutDegree()
    {   int count =0;
        GraphEdge edge=this.lastoutedge;
        while (edge!=null){
            count++;
            edge=edge.nextout;
        }
        return count;
    }
    public void setparent(GraphNode pi)
    {
        this.visited = true;
        if(pi!=null) {//// add if if pi == null noting
            this.parent = pi;
            if (pi.leftChild != null) {
                GraphNode temp = this.parent.leftChild;
                while (temp.rightsibiling != null) {
                    temp = temp.rightsibiling;
                }
                temp.rightsibiling = this;
            }
            else {
                parent.leftChild = this;
            }
        }
    }

    }



/*

class TreeNode extends GraphNode{
    TreeNode parent;
    TreeNode leftChild;
    TreeNode rightsibiling;

    public TreeNode() {
        super();
        TreeNode parent = null;
        TreeNode leftChild = null;
        TreeNode rightsibiling = null;
    }

    public TreeNode(int key , TreeNode pi) {
        super(key);
        if(pi!=null) { //// add if if pi == null noting
            this.parent = pi;
            if (parent.leftChild != null) {
                TreeNode temp = parent.leftChild;
                while (temp.rightsibiling != null) {
                    temp = temp.rightsibiling;
                }
                temp.rightsibiling = this;
            }
            else {
                parent.leftChild = this;
            }
        }
    }

}

*/
