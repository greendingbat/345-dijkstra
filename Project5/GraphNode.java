import java.util.ArrayList;

public class GraphNode implements Comparable<GraphNode>{
	private String nodeName;
	private int bestDistance;
	public ArrayList<GraphEdge> edgeList;
	private GraphNode pred;
	public int heapIndex;
	
	public GraphNode (String name) {
		this.nodeName = name;
		this.bestDistance = -1;
		this.edgeList = new ArrayList<GraphEdge>();
		this.pred = null;
		this.heapIndex = -1;
	}
	
	public String getName() {
		return this.nodeName;
	}
	
	public int getBestDistance() {
		return this.bestDistance;
		
	}
	
	public void setBestDistance(int i) {
		this.bestDistance = i;
	}
	
	public void addEdge(GraphEdge e) {
		edgeList.add(e);
	}
	
	public void setPred(GraphNode v) {
		this.pred = v;
	}
	
	public GraphNode getPred() {
		return this.pred;
	}
	
	public int compareTo(GraphNode other) {
		return this.bestDistance - other.bestDistance;
	}
	
}
