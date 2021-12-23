import java.io.DataOutputStream;
import java.io.IOException;

public class GraphNode {

    GraphNode next;
    GraphNode prev;
    static GraphNode lastnode = null;
    Graphedge lastinedge;
    Graphedge lastoutedge;
    final int key;

    GraphNode (){
        this.key = -1;
    }

    GraphNode(int key) {
        this.prev = null;
        this.key = key;
        if (lastnode != null) {
            this.next = lastnode;
            lastnode.prev = this;
            lastnode = this;
        } else {
            this.next = null;
            lastnode = this;
        }
        this.lastinedge=null;
        this.lastoutedge=null;
    }


    public int getKey(){
        return this.key;
    }

    public int getInDegree()
    {   int count =0;
        Graphedge edge=this.lastinedge;
        while (edge!=null){
            count++;
            edge=edge.nextin;
        }
    return count;
    }
    public int getOutDegree()
    {   int count =0;
        Graphedge edge=this.lastoutedge;
        while (edge!=null){
            count++;
            edge=edge.nextout;
        }
        return count;
    }
}



class TreeNode extends GraphNode{
    GraphNode parent;
    GraphNode leftChild;
    GraphNode rightChild;

    public TreeNode() {
        super();
        parent = null;
        leftChild = null;
        rightChild = null;
    }

    public TreeNode(int key){
     super (key);
     parent = this.lastinedge.from;
     leftChild = this.lastoutedge.to;
     rightChild = this.lastinedge.nextout.to;
    }

}

