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
}
