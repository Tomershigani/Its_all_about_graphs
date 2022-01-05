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
}
