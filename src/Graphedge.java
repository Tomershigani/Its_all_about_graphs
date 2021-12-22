public class Graphedge {

    GraphNode from;
    GraphNode to;
    Graphedge nextin;
    Graphedge nextout;
    Graphedge previn;
    Graphedge prevout;


    Graphedge(GraphNode from,GraphNode to) {
        this.previn = null;
        this.prevout=null;
        this.from = from;
        this.to= to;
        if (from.lastoutedge!= null) {
            this.nextout = from.lastoutedge;
            from.lastoutedge.prevout = this;
            from.lastoutedge = this;
        } else {
            this.next = null;
            lastedge = this;
        }

    }




}
