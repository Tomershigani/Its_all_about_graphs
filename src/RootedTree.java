import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;
    private GraphNode new_root;

    public RootedTree() {
        root = new GraphNode();
    }

    public RootedTree(GraphNode node) {
        this.root = node;
        if(root.key==4050)
            System.out.print("zibi");
    }


    public void printByLayer(DataOutputStream out) {
        GraphNode next_node = null;
        GraphNode new_root= this.root;
        if (new_root.leftChild != null) {
            next_node = new_root.leftChild;
        }
        while (new_root != null) {
            if (new_root.leftChild == next_node) {
                System.out.println(new_root.getKey());
            } else System.out.print("," + new_root.getKey());
            GraphNode temp = new_root;
            while (temp.rightsibiling != null) {
                System.out.print("," + temp.getKey());
                if (next_node == null) {
                    next_node = temp.leftChild;
                }
                temp = temp.rightsibiling;
            }
            if(new_root.parent!=null) {

                if (new_root.parent.rightsibiling != null) {
                    new_root = new_root.parent.rightsibiling.leftChild;
                    continue;
                }
            }
            new_root = next_node;
            if(new_root!=null) {
                if (new_root.leftChild != null) {
                    next_node = root.leftChild;
                }
                else next_node = null;
            }


        }
    }

    public void preorderPrint(DataOutputStream out) {
        int from = 1; // 1 if comes from parent or sibiling,0 for child
        GraphNode new_root = this.root;
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
