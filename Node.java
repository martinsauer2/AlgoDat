import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node> {
  
  private String name;
  private Integer distance = Integer.MAX_VALUE;
  private List<Node> shortestPath = new LinkedList<>();
  private Map<Node, Integer> adjacentNodes = new HashMap<>();

  public Node(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDistance() {
    return this.distance;
  }

  public void setDistance(Integer distance) {
    this.distance = distance;
  }

  public void addAdjacentNode(Node node, int weight) {
    adjacentNodes.put(node, weight);
  }

  public Map<Node, Integer> getAdjacentNodes() {
    return adjacentNodes;
  }

  public void setShortestPath(List<Node> shortestPath) {
    this.shortestPath = shortestPath;
  }

  public List<Node> getShortestPath() {
    return shortestPath;
  }

  public int compareTo(Node node) {
    return Integer.compare(this.distance, node.getDistance());
  }
}