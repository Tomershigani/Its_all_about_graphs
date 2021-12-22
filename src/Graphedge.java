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
        }
        else
            this.nextout = null;

        from.lastoutedge = this;
        if (to.lastinedge!= null) {
            this.nextin = from.lastinedge;
            from.lastinedge.prevout = this;
        }
        else
            this.nextin = null;
        to.lastinedge = this;

    }




}
