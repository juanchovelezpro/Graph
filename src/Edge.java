class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> {

    E value;
    Vertex<?> from;
    Vertex<?> destination;
    double cost;
    boolean bidirectional;

    public Edge(E value, Vertex<?> from, Vertex<?> destination, double cost, boolean bidirectional) {
        this.value = value;
        this.from = from;
        this.destination = destination;
        this.cost = cost;
        this.bidirectional = bidirectional;
    }

    @Override
    public int compareTo(Edge<E> e) {
        if (this.cost - e.cost < 0) {
            return -1;
        } else if (this.cost - e.cost > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String toPrint = "";
        String info =  value + " Cost:  " + cost;

        if(bidirectional){
            toPrint += from + " <-- " + info + " --> " + destination;
        }else {
            toPrint += from + " --- " + info + " --> " + destination;
        }

        return toPrint;
    }
}