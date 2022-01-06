import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    TreeNode root;

    public RootedTree() {
        root = new TreeNode();
    }

    public RootedTree(TreeNode node) {
        this.root = node;
    }


    public void printByLayer(DataOutputStream out) {
        TreeNode next_node = null;
        if (root.leftChild != null) {
            next_node = root.leftChild;
        }
        while (root != null) {
            if (root.leftChild == next_node) {
                System.out.println(root.getKey());
            } else System.out.print("," + root.getKey());
            TreeNode temp = root;
            while (temp.rightsibiling != null) {
                System.out.print("," + temp.getKey());
                if (next_node == null) {
                    next_node = temp.leftChild;
                }
                temp = temp.rightsibiling;
            }
            if(root.parent!=null) {

                if (root.parent.rightsibiling != null) {
                    root = root.parent.rightsibiling.leftChild;
                    continue;
                }
            }
            root = next_node;
            if(root!=null) {
                if (root.leftChild != null) {
                    next_node = root.leftChild;
                }
                else next_node = null;
            }


        }
    }

    public void preorderPrint(DataOutputStream out) {
        int from = 1; // 1 if comes from parent or sibiling,0 for child
        TreeNode new_root = this.root;
        while (new_root != null) {
            if (from == 1) {
                System.out.print(new_root.getKey());
                if (new_root.leftChild != null)
                    new_root = new_root.leftChild;
                else {
                    if (new_root.rightsibiling != null)
                        new_root = new_root.rightsibiling;
                    else {
                        from = 0;
                        new_root = new_root.parent;
                    }
                }

            } else {
                if (new_root.rightsibiling != null) {
                    from = 1;
                    new_root = new_root.rightsibiling;
                } else
                    new_root = new_root.parent;

            }
            if (new_root != null)
                 System.out.print(",");
        }
    }





}
