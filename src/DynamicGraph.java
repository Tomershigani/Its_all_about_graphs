public class DynamicGraph {
    GraphNode head;


    DynamicGraph() {
        head = new GraphNode(-1);
    }

    public GraphEdge InsertEdge(GraphNode from, GraphNode to) {
        GraphEdge edge = new GraphEdge(from, to);
        from.setLastoutedge(edge);
        to.setLastinedge(edge);
        return edge;


    }

    public void deleteEdge(GraphEdge edge) {
        edge.from = null;
        edge.to = null;
        GraphEdge G;
        if ((edge.nextin != null) && (edge.previn != null)) //checking it's not the first or last edge
        {
            G = edge.previn;
            edge.nextin.previn = G;
            G = edge.nextin;
            edge.previn.nextin = G;
        }
        if ((edge.nextout != null) && (edge.prevout != null)) //checking it's not the first or last edge
             {
            G = edge.prevout;
            edge.nextout.prevout = G;
            G = edge.nextout;
            edge.prevout.nextout = G;
        }
        if (edge.nextin == null) { //if first in
            edge.previn.nextin = null;
        }
        if (edge.nextout == null) { // if first in
            edge.prevout.nextout = null;
        }
        if (edge.previn == null) { //if last in
            edge.nextin.previn = null;
        }
        if (edge.prevout == null) { // if last in
            edge.nextout.prevout = null;
        }
    }
}