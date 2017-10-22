import com.epam.graph.Edge;
import com.epam.graph.Graph;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

  Graph graph;

  @Before
  public void setUp() {
    graph = new Graph(5);
  }

  @Test
  public void addVertexTest() {
    graph.addVertex(1);
    System.out.println(graph);
    graph.addVertex(-1);
    System.out.println(graph);
    graph.addVertex(1);
    System.out.println(graph);
    graph.addVertex(2);
    System.out.println(graph);
    graph.addVertex(6);
    System.out.println(graph);
  }

  @Test
  public void deleteVertex() {
    graph.addVertex(1);
    graph.addVertex(5);
    graph.addEdge(new Edge(1, 5));
    System.out.println(graph);
    graph.deleteVertex(1);
    System.out.println(graph);
    graph.deleteVertex(1);
    System.out.println(graph);
    graph.deleteVertex(-1);
    System.out.println(graph);
    graph.deleteVertex(10);
    System.out.println(graph);
    graph.deleteVertex(5);
    System.out.println(graph);
  }

  @Test
  public void addEdge() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    System.out.println(graph);

    graph.addEdge(new Edge(1, -1));
    graph.addEdge(new Edge(-1, -1));
    graph.addEdge(new Edge(1, 1));
    graph.addEdge(new Edge(1, 3));
    graph.addEdge(new Edge(2, 11));
    graph.addEdge(new Edge(2, 1));
    graph.addEdge(new Edge(2, 1));
    System.out.println(graph);
  }

  @Test
  public void deleteEdge() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addEdge(new Edge(1, 3));
    graph.addEdge(new Edge(3, 2));
    graph.addEdge(new Edge(1, 2));
    System.out.println(graph);

    graph.deleteEdge(new Edge(1, 4));
    graph.deleteEdge(new Edge(2, 3));
    graph.deleteEdge(new Edge(2, 3));
    graph.deleteEdge(new Edge(3, 1));
    System.out.println(graph);
  }
  @Test
  public void verticesCount() {
    System.out.println(graph.verticesCount());
    graph.addVertex(1);
    System.out.println(graph.verticesCount());
    graph.addVertex(2);
    graph.addVertex(5);
    System.out.println(graph.verticesCount());
    graph.addEdge(new Edge(1, 2));
    System.out.println(graph.verticesCount());
  }

  @Test
  public void edgesCount() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    System.out.println(graph.edgesCount());
    graph.addEdge(new Edge(1,3));
    System.out.println(graph.edgesCount());
    graph.addEdge(new Edge(2,3));
    System.out.println(graph.edgesCount());
    graph.addEdge(new Edge(1,2));
    System.out.println(graph.edgesCount());
  }

  @Test
  public void degreeCount() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addVertex(4);
    System.out.println(graph.degree(1));
    graph.addEdge(new Edge(1, 2));
    graph.addEdge(new Edge(1, 3));
    graph.addEdge(new Edge(1, 4));
    System.out.println(graph.degree(1));
    graph.addEdge(new Edge(2, 3));
    System.out.println(graph.degree(3));
    System.out.println(graph.degree(5));
  }

  @Test
  public void adjacencyTest() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addVertex(4);
    System.out.println(graph.findAdj(1));
    graph.addEdge(new Edge(1, 2));
    graph.addEdge(new Edge(1, 3));
    graph.addEdge(new Edge(1, 4));
    System.out.println(graph.findAdj(1));
    graph.addEdge(new Edge(2, 3));
    System.out.println(graph.findAdj(1));
    System.out.println(graph.findAdj(5));
  }

  @Test
  public void pathTest() {
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addVertex(4);
    graph.addVertex(5);
    graph.addEdge(new Edge(1, 2));
    graph.addEdge(new Edge(1, 3));
    graph.addEdge(new Edge(2, 4));
    graph.addEdge(new Edge(4, 3));
    System.out.println(graph.findPath(1, 4));
    System.out.println(graph.findPath(1, 5));
    System.out.println(graph.findPath(2, 4));
    System.out.println(graph.findPath(4, 2));
    graph.deleteEdge(new Edge(1, 2));
    System.out.println(graph.findPath(1, 4));
    graph.addEdge(new Edge(2, 3));
    graph.addEdge(new Edge(2, 5));
    System.out.println(graph.findPath(1, 5));
    System.out.println(graph.findPath(4, 5));
    graph.deleteEdge(new Edge(2, 4));
    graph.deleteEdge(new Edge(2, 3));
    graph.deleteEdge(new Edge(2, 5));
    graph.addEdge(new Edge(1, 2));
    graph.addEdge(new Edge(3, 5));
    System.out.println(graph.findPath(1, 5));
    System.out.println(graph.findPath(2, 5));
    System.out.println(graph.findPath(4, 5));
  }
}
