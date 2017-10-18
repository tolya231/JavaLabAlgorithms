package com.epam.graph;

public class Edge {

  private Integer v1;
  private Integer v2;

  public Edge(Integer v1, Integer v2) {
    this.v1 = v1;
    this.v2 = v2;
  }

  public Integer getV1() {
    return v1;
  }

  public void setV1(Integer v1) {
    this.v1 = v1;
  }

  public Integer getV2() {
    return v2;
  }

  public void setV2(Integer v2) {
    this.v2 = v2;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return
          false;
    }
    Edge edge = (Edge) o;
    return v1.equals(edge.v1) && v2.equals(edge.v2);
  }

  @Override
  public int hashCode() {
    int result = v1.hashCode();
    result = 31 * result + v2.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Edge{" +
        "v1=" + v1 +
        ", v2=" + v2 +
        '}';
  }
}
