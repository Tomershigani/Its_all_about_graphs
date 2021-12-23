import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;

    public RootedTree(){
        root = new TreeNode();
    }
    public RootedTree(TreeNode node){
        this.root = node;
    }
}
