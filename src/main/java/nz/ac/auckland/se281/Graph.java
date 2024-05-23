package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph<T> {

  private Map<T, List<T>> map;

  public Graph() {
    this.map = new HashMap<>();
  }

  public void addVertex(T node) {
    map.putIfAbsent(node, new LinkedList<>());
  }

  public void addEdge(T node1, T node2) {
    addVertex(node1);
    addVertex(node2);

    // Double edge pre-defined in the adjacencies.csv file, only need to add one edge
    map.get(node1).add(node2);
  }
}
