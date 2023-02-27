import java.util.*;


class Graph<V extends Comparable<V>, E extends Comparable<E>> {

    private Map<Vertex<V>, ArrayList<Edge<E>>> map;
    ArrayList<Vertex<V>> vertices;
    ArrayList<Edge<E>> edges;

    public Graph() {
        map = new HashMap<>();
        edges = new ArrayList<>();
        vertices = new ArrayList<>();
    }

    public void addVertex(Vertex<V> vertex) {
        map.put(vertex, new ArrayList<>());
        vertices.add(vertex);
    }

    public void addEdge(V from, V destination, E value, double cost, boolean bidirectional) {

        Vertex<V> vFrom = new Vertex<>(from);
        Vertex<V> vDestination = new Vertex<>(destination);

        if (!map.containsKey(vFrom)) {
            addVertex(vFrom);
        }

        if (!map.containsKey(vDestination)) {
            addVertex(vDestination);
        }

        map.get(vFrom).add(new Edge<>(value, vFrom, vDestination, cost, bidirectional));
        if (bidirectional)
            map.get(vDestination).add(new Edge<>(value, vDestination, vFrom, cost, bidirectional));

        edges.add(new Edge<>(value, vFrom, vDestination, cost, bidirectional));

    }

    public int verticesCount() {
        return map.size();
    }

    public ArrayList<Vertex<V>> DFS(Vertex<V> vertex) {
        boolean[] visited = new boolean[verticesCount()];
        Stack<Vertex<V>> stack = new Stack<>();
        ArrayList<Vertex<V>> dfs = new ArrayList<>();
        return DFS(vertex, visited, stack, dfs);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Vertex<V>> DFS(Vertex<V> vertex, boolean[] visited, Stack<Vertex<V>> stack, ArrayList<Vertex<V>> dfs) {
        if (dfs.size() == verticesCount()) {
            return dfs;
        } else {

            int index = vertices.indexOf(vertex);

            if (!visited[index]) {
                visited[index] = true;
                stack.push(vertex);
                dfs.add(vertex);
            }

            for (int i = 0; i < map.get(vertex).size(); i++) {
                Vertex<V> destination = (Vertex<V>) map.get(vertex).get(i).destination;
                if (!visited[vertices.indexOf(destination)]) {
                    return DFS(destination, visited, stack, dfs);
                }
            }

            stack.pop();

            return DFS(stack.peek(), visited, stack, dfs);
        }
    }

    public ArrayList<Vertex<V>> BFS(Vertex<V> vertex) {
        boolean[] visited = new boolean[verticesCount()];
        Queue<Vertex<V>> queue = new LinkedList<>();
        ArrayList<Vertex<V>> bfs = new ArrayList<>();
        queue.add(vertex);
        return BFS(vertex, visited, queue, bfs);
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Vertex<V>> BFS(Vertex<V> vertex, boolean[] visited, Queue<Vertex<V>> queue, ArrayList<Vertex<V>> bfs) {
        if (bfs.size() == verticesCount()) {
            return bfs;
        } else {

            int index = vertices.indexOf(vertex);

            bfs.add(queue.poll());

            if (!visited[index]) {
                visited[index] = true;
            }

            for (int i = 0; i < map.get(vertex).size(); i++) {
                Vertex<V> destination = (Vertex<V>) map.get(vertex).get(i).destination;
                if (!visited[vertices.indexOf(destination)]) {
                    if (!queue.contains(destination)) {
                        queue.add(destination);
                    }
                }
            }

            return BFS(queue.peek(), visited, queue, bfs);
        }
    }

    public Path dijkstra(Vertex<V> vFrom) {

        double[] costs = new double[verticesCount()];
        boolean[] visited = new boolean[verticesCount()];
        int[] paths = new int[verticesCount()];
        Arrays.fill(paths, -1);
        Arrays.fill(costs, Double.MAX_VALUE);
        costs[vertices.indexOf(vFrom)] = 0.0;
        int visits = 0;

        return dijkstra(vFrom, visits, costs, visited, paths);
    }

    private int indexMinimumCost(double[] costs, boolean[] visited) {
        int index = 0;
        double min = Double.MAX_VALUE;

        for (int i = 0; i < costs.length; i++) {
            if (!visited[i]) {
                if (costs[i] < min) {
                    min = costs[i];
                    index = i;
                }
            }
        }
        return index;
    }

    public ArrayList<Vertex<V>> createPath(Path path, Vertex<V> destination) {

        ArrayList<Vertex<V>> vPath = new ArrayList<>();

        int destIndex = vertices.indexOf(destination);
        vPath.add(destination);

        while (path.paths[destIndex] != -1) {
            vPath.add(vertices.get(path.paths[destIndex]));
            destIndex = path.paths[destIndex];
        }

        Collections.reverse(vPath);

        return vPath;

    }

    @SuppressWarnings("unchecked")
    private Path dijkstra(Vertex<V> vFrom, int visits, double[] costs, boolean[] visited, int[] paths) {

        if (visits == verticesCount()) {
            return new Path(costs, paths);
        } else {

            int indexFrom = vertices.indexOf(vFrom);
            visited[indexFrom] = true;
            visits++;

            for (int i = 0; i < map.get(vFrom).size(); i++) {

                Vertex<V> destination = (Vertex<V>) map.get(vFrom).get(i).destination;
                int indexDest = vertices.indexOf(destination);

                if (!visited[indexDest]) {

                    double edgeCost = map.get(vFrom).get(i).cost;

                    if (costs[indexDest] > costs[indexFrom] + edgeCost) {
                        costs[indexDest] = costs[indexFrom] + edgeCost;
                        paths[indexDest] = indexFrom;
                    }

                }

            }

            int min = indexMinimumCost(costs, visited);
            vFrom = vertices.get(min);

            return dijkstra(vFrom, visits, costs, visited, paths);

        }

    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();

        for (Edge<E> edge : edges) {
            toPrint.append(edge);
            toPrint.append("\n");
        }

        return toPrint.toString();

    }
}