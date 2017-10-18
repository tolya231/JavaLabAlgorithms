package com.epam.graph;

import java.util.List;

public interface Graphs {

  boolean addVertex(Integer vertex);

  void deleteVertex(Integer vertex);

  boolean addEdge(Edge edge);

  void deleteEdge(Edge edge);

  Integer verticesCount();

  Integer edgesCount();

  //степень вершины = количество смежных вершин
  Integer degree(Integer vertex);

  //поиск соседних вершин
  List<Integer> findAdj(Integer vertex);

  //поиск пусти от одной вершины к другой
  List<Integer> findPath(Integer vertex1, Integer vertex2);
}
