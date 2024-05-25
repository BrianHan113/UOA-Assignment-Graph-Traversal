package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {

  private Map<T, List<T>> adjacencyMap;

  public Graph() {
    this.adjacencyMap = new HashMap<>();
  }

  public void addVertex(T node) {
    adjacencyMap.putIfAbsent(node, new LinkedList<>());
  }

  public void addEdge(T node1, T node2) {
    addVertex(node1);
    addVertex(node2);

    // adjacencies.csv file includes two-way connections, and the way the edges are initialized in
    // MapEngine.java means we only need to add one edge
    adjacencyMap.get(node1).add(node2);
  }

  public List<T> findPathBetween(T start, T end) { // TODO: Return path of cheapest cost
    Set<T> visited = new HashSet<>();
    Queue<T> queue = new LinkedList<>();
    Map<T, T> childToParentMap = new HashMap<>();
    List<T> path = new LinkedList<>(); // LinkedList has O(1) insertion for adding to beginning

    queue.add(start);
    visited.add(start);
    childToParentMap.put(start, null); // We say root node has no parent

    while (!queue.isEmpty()) {
      T node = queue.poll();

      // If we have reached the end node, we reconstruct the path taken
      if (node.equals(end)) {
        T current = end;
        while (current != null) {
          path.add(0, current); // Insert current node at beginning of list
          current = childToParentMap.get(current); // Move to parent node
        }
        return path;
      }

      for (T n : adjacencyMap.get(node)) {
        if (!visited.contains(n)) {
          visited.add(n);
          queue.add(n);
          childToParentMap.put(n, node);
        }
      }
    }

    return null;
  }
}
