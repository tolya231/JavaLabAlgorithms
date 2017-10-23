package com.epam.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Graph {

  private List<Set<Integer>> vertices;

  public Graph() {
    this.vertices = new ArrayList<>();
  }

  public Graph(int capacity) {
    this.vertices = new ArrayList<>(capacity);
    for (int i = 0; i < capacity; i++) {
      vertices.add(null);
    }
  }

  public Graph(List<Set<Integer>> vertices) {
    this.vertices = vertices;
  }

  public void addVertex(Integer vertex) {
    if (!checkVertex(vertex)) {
      return;
    }
    if (vertices.get(vertex) != null) {
      System.out.println("Vertex already exist");
    } else {
      vertices.set(vertex, new TreeSet<>());
    }
  }

  public void deleteVertex(Integer vertex) {
    if (checkVertex(vertex)) {
      if (vertices.get(vertex) == null) {
        System.out.println("No such vertex");
      } else {
        vertices.set(vertex, null);
        for (Set<Integer> set : vertices) {
          if (set != null) {
            set.remove(vertex);
          }
        }
      }
    }
  }

  public void addEdge(Edge edge) {
    if (checkEdge(edge) && checkVertex(edge.getV1()) && checkVertex(edge.getV2())) {
      Set<Integer> v1 = vertices.get(edge.getV1());
      Set<Integer> v2 = vertices.get(edge.getV2());
      if (v1 != null && v2 != null) {
        v1.add(edge.getV2());
        v2.add(edge.getV1());
      } else {
        System.out.println("vertices in edge not found");
      }
    } else {
      System.out.println("Incorrect edge");
    }
  }

  public void deleteEdge(Edge edge) {
    if (checkEdge(edge) && Math.max(edge.getV1(), edge.getV2()) < vertices.size()) {
      Set<Integer> v1 = vertices.get(edge.getV1());
      Set<Integer> v2 = vertices.get(edge.getV2());
      if (v1 != null && v2 != null) {
        //check because there can be one of the vertices but not other
        if (v1.contains(edge.getV2()) && v2.contains(edge.getV1())) {
          v1.remove(edge.getV2());
          v2.remove(edge.getV1());
        } else {
          System.out.println("There is no such edge");
        }
      } else {
        System.out.println("vertices in edge not found");
      }
    } else {
      System.out.println("Incorrect edge");
    }
  }

  public Integer verticesCount() {
    return Integer
        .valueOf(Long.valueOf(vertices.stream().filter(Objects::nonNull).count()).toString());
  }

  public Integer edgesCount() {
    int result = 0;
    for (Set<Integer> vertex : vertices) {
      if (vertex != null) {
        result += vertex.size();
      }
    }
    return result / 2;
  }

  public Integer degree(Integer vertex) {
    if (checkVertex(vertex) && vertices.get(vertex) != null) {
      return vertices.get(vertex).size();
    } else {
      return null;
    }
  }

  public List<Integer> findAdj(Integer vertex) {
    if (checkVertex(vertex)) {
      return vertices.get(vertex) != null ? new ArrayList<>(vertices.get(vertex)) : null;
    } else {
      return null;
    }
  }

  public List<Integer> findPath(Integer vertex1, Integer vertex2) {
    if (checkVertex(vertex1) && checkVertex(vertex2)) {
      boolean[] colors = new boolean[vertices.size()];
      List<Integer> path = new LinkedList<>();
      path.add(vertex1);
      if (dfs(vertex1, vertex2, path, colors)) {
        return path;
      } else {
//        System.out.println("No path");
        return null;
      }
    } else {
      return null;
    }
  }

  private boolean checkEdge(Edge edge) {
    return edge != null && edge.getV1() != null && edge.getV2() != null && edge.getV1() >= 0
        && edge.getV2() >= 0 && !edge.getV1().equals(edge.getV2());
  }

  private boolean checkVertex(Integer vertex) {
    if (vertex == null || vertex < 0) {
      System.out.println("Not vertex");
      return false;
    }
    if (vertex >= vertices.size()) {
      increaseCapacity(vertex);
    }
    return true;
  }

  //How to improve?
  private void increaseCapacity(Integer vertex) {
    Integer max = vertex > vertices.size() * 2 ? vertex + 1 : vertices.size() * 2;
    ArrayList<Set<Integer>> newVertices = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      newVertices.add(null);
    }
    for (int i = 0; i < vertices.size(); i++) {
      newVertices.set(i, vertices.get(i));
    }
    vertices = newVertices;
  }

  private boolean dfs(Integer vertex1, Integer vertex2, List<Integer> path, boolean[] colors) {
    colors[vertex1] = true;
    for (Integer w : findAdj(vertex1)) {
      if (!colors[w]) {
        if (w.equals(vertex2)) {
          path.add(vertex2);
          return true;
        } else {
          path.add(w);
          boolean hasWay = dfs(w, vertex2, path, colors);
          if (hasWay) {
            return true;
          }
          path.remove(w);
          colors[w] = false;
        }
      }
    }
    return false;
  }
}
