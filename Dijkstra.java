import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Dijkstra {
  
  public static void main(String[] args) {
    Node nodeA = new Node("A");
    Node nodeB = new Node("B");
    Node nodeC = new Node("C");

    nodeA.addAdjacentNode(nodeB, 1);
    nodeA.addAdjacentNode(nodeC, 3);
    nodeB.addAdjacentNode(nodeC, 1);

    calculateShortestPath(nodeB);
    printPaths(Arrays.asList(nodeA, nodeB, nodeC));
  }
  
  public static void calculateShortestPath(Node source) {
    source.setDistance(0);

    Set<Node> settledNodes = new HashSet<>();
    Queue<Node> unsettledNodes = new PriorityQueue<>(Collections.singleton(source));

    while(!unsettledNodes.isEmpty()) {
      Node currentNode = unsettledNodes.poll();
      currentNode.getAdjacentNodes()
        .entrySet()
        .stream()
        .filter(entry -> !settledNodes.contains(entry.getKey()))
        .forEach(entry -> {
          evaluateDistanceAndPath(entry.getKey(), entry.getValue(), currentNode);
          unsettledNodes.add(entry.getKey());
        });
      settledNodes.add(currentNode);
    }
  }

  private static void evaluateDistanceAndPath(Node adjacentNode, Integer edgeWeight, Node sourceNode) {
    Integer newDistance = sourceNode.getDistance() + edgeWeight;
    if (newDistance < adjacentNode.getDistance()) {
      adjacentNode.setDistance(newDistance);
      adjacentNode.setShortestPath(
        Stream.concat(sourceNode.getShortestPath().stream(), Stream.of(sourceNode)).toList()
      );
    }
  }

  private static void printPaths(List<Node> nodes) {
    nodes.forEach(node -> {
      String path = node.getShortestPath()
                        .stream()
                        .map(Node::getName)
                        .collect(Collectors.joining(" -> "));
      System.out.println((path.isBlank()
        ? "%s : %s".formatted(node.getName(), node.getDistance())
        : "%s -> %s : %s".formatted(path, node.getName(), node.getDistance()))
        );
    });
  }

}
