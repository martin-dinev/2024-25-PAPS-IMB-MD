import aud11.lista_tezinski_nenasocen.AdjacencyListGraph;
import aud11.matrica_tezinski_nenasocen.AdjacencyMatrixGraph;
import aud11.matrica_tezinski_nenasocen.Edge;

import java.util.*;

public class MainAud11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int P = sc.nextInt();
        sc.nextLine();

        AdjacencyMatrixGraph<String> cityNetwork = new AdjacencyMatrixGraph<>(M);
        Map<String, Integer> mapping = new HashMap<>();
        for(int i=0;i<M;i++) {
            String city = sc.nextLine();
            cityNetwork.addVertex(i, city);
            mapping.put(city, i);
        }

        for(int i=0;i<N;i++) {
            String line = sc.nextLine();
            String parts[] = line.split(" ");
            cityNetwork.addEdge(mapping.get(parts[0]), mapping.get(parts[1]), Integer.parseInt(parts[2]));
        }

        for(int i=0;i<P;i++) {
            String line = sc.nextLine();
            String parts[] = line.split(" ");
            cityNetwork.addEdge(mapping.get(parts[0]), mapping.get(parts[1]), -1);
        }

        List<Edge> kruskal = cityNetwork.kruskal();
        int sum = 0, count = 0;
        for (int i = 0; i < kruskal.size(); i++) {
            if(kruskal.get(i).getWeight() < 0)continue;
            count ++;
            sum += kruskal.get(i).getWeight();
        }
        System.out.println(count + "\n" + sum);
    }
}

class ZadacaAud11{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        AdjacencyListGraph<Integer> waterNetwork = new AdjacencyListGraph<>();
        int values[] = new int[n];

        for(int i=0;i<n;i++) {
            int ind = sc.nextInt();
            int val = sc.nextInt();

            waterNetwork.addVertex(ind);
            values[ind] = val;
        }

        int m = sc.nextInt();

        for(int i=0;i<m;i++) {
            waterNetwork.addEdge(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        int p = sc.nextInt();

        for(int i=0;i<p;i++) {
            int startVertex = sc.nextInt();
            int endVertex = sc.nextInt();
            int increaseFactor = sc.nextInt();
            waterNetwork.addEdge(startVertex, endVertex, waterNetwork.getNeighbors(startVertex).get(endVertex)*increaseFactor);
        }

        Map<Integer, Integer> distances = shortestPath(0, waterNetwork, values);

        for (Integer i : distances.keySet()) {
            if(distances.get(i) == Integer.MAX_VALUE){
                System.out.println(i);
            }
        }

    }

    public static  <T extends Number> Map<T, Integer> shortestPath(T startVertex, AdjacencyListGraph<T> graph, int[] values) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        Set<T> explored = new HashSet<>();

        // Initialize distances
        for (T vertex : graph.adjacencyList.keySet()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(startVertex, 0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            if(explored.contains(current))continue;
            explored.add(current);
            for (Map.Entry<T, Integer> neighborEntry : graph.adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                int newDist = distances.get(current) + neighborEntry.getValue();

                if (newDist < distances.get(neighbor) && newDist < values[neighbor.intValue()]) {
                    distances.put(neighbor, newDist);

                    // Update priority queue
                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

}
