import aud10.AdjacencyListGraph;
import aud10.AdjacencyMatrixGraph;

import java.util.*;

public class MainAud10 {

    static String input =
            """
                    9
                    A B C D E F G H Z
                    13
                    A B
                    B F
                    A E
                    A C
                    C D
                    D F
                    D G
                    C G
                    E F
                    E H
                    H Z
                    Z F
                    Z G
                    
                    E G
                    3
                    """;

    public static void main(String[] args) {
        AdjacencyMatrixGraph<String> gm = testAdjancencyMatrixGrapth();
        AdjacencyListGraph<String> transformed = transform(gm);
        System.out.println(transformed);

        AdjacencyListGraph<String> gl = testAdjancencyListGrapth();
        Set<String> visited = new HashSet<>();
        Map<String, String> pred = new HashMap<>();


        System.out.println("DFS Req");
        dfsR(gl, "E", "", visited, pred);
        System.out.println(pred);
        System.out.println(visited);

        System.out.println("DFS");
        visited = dfs(gl, "E");
        System.out.println(visited);

        System.out.println("BFS");
        visited = bfs(gl, "E");
        System.out.println(visited);

        System.out.println("BFS2");
        Map<String, Integer> distances = bfs_distances(gl, "E");
        System.out.println(distances);

        printPaths(gl, "E", 3);
    }

    private static void printPaths(AdjacencyListGraph<String> gl, String start, int dist) {
        printPaths(start, 0, new HashSet<>(), gl, dist, new LinkedList<>());
    }

    private static void printPaths(String curr, int dist, Set<String> visited, AdjacencyListGraph<String> gl, int target_dist, List<String> path) {
        if (visited.contains(curr)) return;
        visited.add(curr);
        path.addLast(curr);
        if (dist == target_dist) {
            System.out.println(path);
        } else
            for (String neighbor : gl.getNeighbors(curr)) {
                printPaths(neighbor, dist + 1, visited, gl, target_dist, path);
            }
        visited.remove(curr);
        path.removeLast();
    }

    private static AdjacencyListGraph<String> transform(AdjacencyMatrixGraph<String> graph) {
        AdjacencyListGraph<String> result = new AdjacencyListGraph<>();
        for (int i = 0; i < graph.numVertices; i++) {
            String vertex = graph.getVertex(i);
            result.addVertex(vertex);
        }
        for (int i = 0; i < graph.numVertices; i++) {
            String vertex = graph.getVertex(i);
            for (String neighbor : graph.getNeighbors(i)) {
                result.addEdge(vertex, neighbor);
            }
        }
        return result;
    }


    private static Set<String> bfs(AdjacencyListGraph<String> gl, String startingVertex) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startingVertex);
        visited.add(startingVertex);
        int distance = 0;
        while (!queue.isEmpty()) {
            int count_at_curr_distance = queue.size();
            for (int i = 0; i < count_at_curr_distance; i++) {
                String curr = queue.poll();
                System.out.print(curr + ":" + distance + "   ");
                for (String neighbor : gl.getNeighbors(curr)) {
                    if (visited.contains(neighbor)) continue;
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
            distance++;
        }
        System.out.println();

        return visited;
    }

    private static Map<String, Integer> bfs_distances(AdjacencyListGraph<String> gl, String startingVertex) {
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startingVertex);
        distances.put(startingVertex, 0);
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            for (String neighbor : gl.getNeighbors(curr)) {
                if (distances.containsKey(neighbor)) continue;
                queue.add(neighbor);
                distances.put(neighbor, distances.get(curr) + 1);
            }
        }
        System.out.println();

        return distances;
    }

    private static Set<String> dfs(AdjacencyListGraph<String> gl, String startingVertex) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.add(startingVertex);
        while (!stack.isEmpty()) {
            String curr = stack.pop();
            if (visited.contains(curr)) continue;
            visited.add(curr);
            System.out.print(curr + " ");
            for (String neighbor : gl.getNeighbors(curr)) {
                if (visited.contains(neighbor)) continue;
                stack.push(neighbor);
            }
        }
        System.out.println();

        return visited;
    }

    private static void dfsR(AdjacencyListGraph<String> gl, String current, String parent, Set<String> visited, Map<String, String> pred) {
        if (visited.contains(current)) return;
        visited.add(current);
        pred.put(current, parent);
        for (String neighbor : gl.getNeighbors(current)) {
            dfsR(gl, neighbor, current, visited, pred);
        }
    }


    private static AdjacencyMatrixGraph<String> testAdjancencyMatrixGrapth() {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(n);
        Map<String, Integer> node_to_index_map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String node = sc.next();
            graph.addVertex(i, node);
            node_to_index_map.put(node, i);
        }
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            String from = sc.next();
            String to = sc.next();
            int from_index = node_to_index_map.get(from);
            int to_index = node_to_index_map.get(to);
            graph.addEdge(from_index, to_index);
        }
        System.out.println(graph);
        return graph;
    }

    private static AdjacencyListGraph<String> testAdjancencyListGrapth() {
        Scanner sc = new Scanner(input);
        int n = sc.nextInt();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < n; i++) {
            String node = sc.next();
            graph.addVertex(node);
        }
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            String from = sc.next();
            String to = sc.next();
            graph.addEdge(from, to);
        }
        System.out.println(graph);
        return graph;
    }


}

class ZadacaLavirint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                """
                        6,6
                        ######
                        # # ##
                        # # S#
                        # # ##
                        # E  #
                        ######""");
        String[] strings = scanner.nextLine().split(",");
        int red = Integer.parseInt(strings[0]);
        int kol = Integer.parseInt(strings[1]);
        System.out.println(red + " " + kol);
        char[][] mat = new char[red][kol];
        int sr = 0, sk = 0, er = 0, ek = 0;
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < red; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < kol; j++) {
                mat[i][j] = line.charAt(j);
                if (mat[i][j] == 'S') {
                    sr = i;
                    sk = j;
                }
                if (mat[i][j] == 'E') {
                    er = i;
                    ek = j;
                }
                if (mat[i][j] == '#') continue;
                graph.addVertex(i + "," + j);
                if (mat[i - 1][j] != '#') {
                    graph.addEdge(i + "," + j, (i - 1) + "," + j);
                }
                if (mat[i][j - 1] != '#') {
                    graph.addEdge(i + "," + j, i + "," + (j - 1));
                }
            }
        }graph.findPath(sr + "," + sk, er + "," + ek);
         int rez = graph.shortestPath(sr + "," + sk, er + "," + ek);
        System.out.println(rez);
    }
}