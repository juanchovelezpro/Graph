public class Main {

    public static void main(String[] args) {

        Graph<Integer, Integer> graph = new Graph<>();

        graph.addVertex(new Vertex<>(0));
        graph.addVertex(new Vertex<>(1));
        graph.addVertex(new Vertex<>(2));
        graph.addVertex(new Vertex<>(3));
        graph.addVertex(new Vertex<>(4));
        graph.addVertex(new Vertex<>(5));
        graph.addVertex(new Vertex<>(6));
        graph.addVertex(new Vertex<>(7));


        graph.addEdge(0, 1, 5, 5, true);
        graph.addEdge(0, 2, 3, 3, true);
        graph.addEdge(0, 3, 3, 3, true);
        graph.addEdge(0, 4, 1, 1, true);
        graph.addEdge(1, 3, 4, 4, true);
        graph.addEdge(2, 4, 9, 9, true);
        graph.addEdge(2, 6, 2, 2, true);
        graph.addEdge(3, 5, 9, 9, true);
        graph.addEdge(4, 7, 1, 1, true);
        graph.addEdge(5, 6, 7, 7, true);
        graph.addEdge(6, 7, 8, 8, true);


        System.out.println(graph);
        System.out.println(graph.verticesCount());
        System.out.println(graph.DFS(new Vertex<>(0)));
        System.out.println(graph.BFS(new Vertex<>(0)));

        Path path = graph.dijkstra(new Vertex<>(4));

        System.out.println(path);

        for (int i = 0; i < graph.verticesCount(); i++) {

            System.out.println(graph.createPath(path, new Vertex<>(i)));

        }
    }
}
