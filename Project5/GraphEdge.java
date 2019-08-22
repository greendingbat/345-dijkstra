public class GraphEdge implements Comparable<GraphEdge>{

	public GraphNode to;
	public int weight;
	
	public GraphEdge(GraphNode to, int weight) {
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(GraphEdge o) {
		return (this.weight - o.weight);
	}
	
}
