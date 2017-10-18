package com.epam.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Graph implements Graphs {

  private List<Set<Integer>> vertices;

  public Graph() {
    this.vertices = new ArrayList<>();
  }

  public Graph(List<Set<Integer>> vertices) {
    this.vertices = vertices;
  }

  @Override
  public boolean addVertex(Integer vertex) {
    if (vertices.get(vertex) != null) {
      System.out.println("Vertex already exists");
      return false;
    } else {
      vertices.set(vertex, new TreeSet<>());
      return true;
    }
  }

  @Override
  public void deleteVertex(Integer vertex) {
    if (checkVertex(vertex)) {
      vertices.remove((int) vertex);
      for (Set<Integer> set : vertices) {
        set.removeIf(vert -> vert.equals(vertex));
      }
    }
  }

  @Override
  public boolean addEdge(Edge edge) {
    if (checkEdge(edge)) {
      Set<Integer> v1 = vertices.get(edge.getV1());
      Set<Integer> v2 = vertices.get(edge.getV2());
      if (v1 != null && v2 != null) {
        v1.add(edge.getV2());
        v2.add(edge.getV1());
        return true;
      } else {
        System.out.println("vertices in edge not found");
      }
    } else {
      System.out.println("Incorrect edge");
    }
    return false;
  }


  @Override
  public void deleteEdge(Edge edge) {
    if (checkEdge(edge)) {
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

  @Override
  public Integer verticesCount() {
    return vertices.size();
  }

  @Override
  public Integer edgesCount() {
    int result = 0;
    for (Set<Integer> vertex : vertices) {
      result += vertex.size();
    }
    return result / 2;
  }

  @Override
  public Integer degree(Integer vertex) {
    return checkVertex(vertex) ? vertices.get(vertex).size() : null;
  }

  @Override
  public List<Integer> findAdj(Integer vertex) {
    return checkVertex(vertex) ? new ArrayList<>(vertices.get(vertex)) : null;
  }

  @Override
  public List<Integer> findPath(Integer vertex1, Integer vertex2) {
    return null;
  }

  private boolean checkEdge(Edge edge) {
    return edge != null && edge.getV1() != null && edge.getV2() != null && edge.getV1() >= 0
        && edge.getV2() >= 0 && !edge.getV1().equals(edge.getV2());
  }

  private boolean checkVertex(Integer vertex) {
    if (vertices.get(vertex) == null) {
      System.out.println("Vertex not found");
      return false;
    }
    return true;
  }
}
