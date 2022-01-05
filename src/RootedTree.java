import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    TreeNode root;

    public RootedTree(){
        root = new TreeNode();
    }
    public RootedTree(TreeNode node){
        this.root = node;
    }



public void printByLayer(DataOutputStream out){
    TreeNode next_node = null;
    if (root.leftChild!=null){
        next_node = root.leftChild;
    }
    while(root!=null){
        if (root.leftChild == next_node) {
            System.out.println(root.getKey());
        }
        else System.out.print("," +root.getKey());
        TreeNode temp = root;
        while (temp.rightsibiling!=null){
            System.out.print("," + temp.getKey());
            if (next_node == null){
                next_node = temp.leftChild;
            }
            temp = temp.rightsibiling;
        }
        if (root.parent.rightsibiling!=null){
            root = root.parent.rightsibiling.leftChild;
            continue;
        }
        root = next_node;
        if (root.leftChild !=null){
            next_node = root.leftChild;
        }
        else next_node = null;

    }
}
public void preorderPrint(DataOutputStream out)
{
        int from = 1; // 1 if comes from parent or sibiling,0 for child
        TreeNode new_root=this.root;
        while (new_root!=null)
        {
            if (from == 1)
            {
                out.write(new_root.getKey());
                if (new_root.leftChild!=null)
                    new_root=new_root.leftChild;
                else
                {
                    if (new_root.rightsibiling!=null)
                        new_root = new_root.rightsibiling;
                    else
                    {
                        from =0;
                        new_root = new_root.parent;
                    }
                }

            }
                else {
                if (new_root.rightsibiling != null) {
                    from = 1;
                    new_root = new_root.rightsibiling;
                } else
                    new_root = new_root.parent;

               }
            if(new_root!=null)
                out.writeChars(",");
        }
}

private void bfs_initialization(GraphNode source){
        GraphNode next_node=source.next;
        GraphNode prev_node=source.prev;
        while(next_node!=null)
        {
            next_node.color=0;
            next_node.distance=-1;
            next_node.bfs_parent=null;
            next_node=next_node.next;
        }
           while(prev_node!=null)
          {
            prev_node.color=0;
            prev_node.distance=-1;
            prev_node.bfs_parent=null;
            prev_node=next_node.next;
          }
           if (source!=null)
           {
               source.color=1;
               source.distance=0;
               source.bfs_parent=null;
           }


}



private void dfs_initialization(GraphNode source){
        GraphNode next_node=source.next;
        GraphNode prev_node=source.prev;
        while(next_node!=null)
        {
            next_node.color=0;
            next_node.distance=-1;
            next_node.bfs_parent=null;
            next_node=next_node.next;
        }
        while(prev_node!=null)
        {
            prev_node.color=0;
            prev_node.distance=-1;
            prev_node.bfs_parent=null;
            prev_node=next_node.next;
        }
        if (source!=null)
        {
            source.color=0;
            source.distance=0;
            source.bfs_parent=null;
        }


    }

public RootedTree bfs(GraphNode source)
{
    bfs_initialization(source);
    int outdeg;
    TreeNode pointer = new TreeNode();
    GraphNode node = pointer;
    GraphEdge edge;
    TreeNode root = new TreeNode(source.getKey());
    while (true)// need to think what to do instad of Q, and how to solve haritage problem
    {
        outdeg = root.getOutDegree();
        for (int i=0;i<outdeg;i++)
        {
           edge =  root.lastoutedge;
           //pointer = edge.to;

        }

    }


}




