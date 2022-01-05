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
    out.write(root.getKey());
    TreeNode temp = root;
    while (temp.rightsibiling!=null){
        out.writeUTF("," +(char)(temp.getKey()+'0'));
        temp = temp.rightsibiling;
    }
    if ()

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

private void bfs_initialization(){

}

public RootedTree bfs(GraphNode source)
{


}


}




