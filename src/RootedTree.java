import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    GraphNode root;


    public RootedTree() {
        root = new GraphNode();
    }

    public RootedTree(GraphNode node) {
        this.root = node;
    }

   public int hight(GraphNode r){
        int max_h = 0;
        if (r == null){
            return -1;
        }
        GraphNode temp = r.leftChild;
        while (temp != null){
                int cur_h = hight(temp);
                if (max_h < cur_h){
                    max_h = cur_h;
                }
                temp = temp.rightsibiling;
               }
        return max_h+1;
   }
   public void printrec(GraphNode n,int count,String[]print){
        if (n==null){
            return;
        }
        if(print[count] == null){
            print[count] = n.getKey()+ ",";
        }
        else {
            print[count] +=  n.getKey()+"," ;
        }
        printrec(n.leftChild , count+1 , print);
        printrec(n.rightsibiling , count, print);
   }
   public void printByLayer(DataOutputStream out) throws IOException{
        int hight1 = hight(this.root);
        String [] print = new String[hight1];
        int count = 0;
        int i = 0 ;
        printrec(this.root, count, print);
        for (i=0; i<print.length-1; i++){
           System.out.println(print[i].substring(0, print[i].length() - 1));
          // out.writeBytes(System.lineSeparator());
        }
      System.out.print(print[i].substring(0, print[i].length() - 1));
    }


    public void preorderPrint(DataOutputStream out) throws IOException{
        int from = 1;// 1 if comes from parent or sibiling,0 for child
        int count =0;
        GraphNode new_root = this.root;
        while (new_root != null) {
            if (from == 1) {
                if (count!=0)
                    System.out.print(",");
                System.out.print(new_root.getKey());
                count++;
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
        }
    }
}
