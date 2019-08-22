import java.util.HashMap;
import java.util.ArrayList;

public class Proj05_Dijkstra_student implements Proj05_Dijkstra {

	private boolean isDigraph;
	private int dotNum;
	private HashMap<String, GraphNode> nodeMap;
	private ArrayList<String> nodeNameList;

	public Proj05_Dijkstra_student(boolean isDigraph) {
		this.isDigraph = isDigraph;
		this.dotNum = 0;
		this.nodeMap = new HashMap<String, GraphNode>();
		this.nodeNameList = new ArrayList<String>();

	}

	@Override
	public void addNode(String s) {
		nodeMap.put(s, new GraphNode(s));
		nodeNameList.add(s);
	}

	private void printGraph() {
		for (String s : nodeNameList) {
			System.out.print("NODE " + s + ": ");
			for (GraphEdge e : nodeMap.get(s).edgeList) {
				System.out.print("|" + e.to.getName() + "(" + e.weight + ")| ");
			}
			System.out.println();
		}

	}

	@Override
	public void addEdge(String from, String to, int weight) {
		GraphNode fromNode = nodeMap.get(from);
		GraphNode toNode = nodeMap.get(to);
		fromNode.edgeList.add(new GraphEdge(toNode, weight));
		if (!isDigraph) {
			toNode.edgeList.add(new GraphEdge(fromNode, weight));
		}
	}

	@Override
	public void runDijkstra(String startNodeName) {
		PriorityQueue nodeQueue = new PriorityQueue();

		// reset all best distance estimates and put all nodes on the queue
		for (String s : nodeNameList) {
			nodeMap.get(s).setBestDistance(Integer.MAX_VALUE);
			nodeQueue.insert(nodeMap.get(s));
		}

		GraphNode currNode = nodeMap.get(startNodeName);
		currNode.setBestDistance(0);
		currNode.setPred(currNode);

		while (!nodeQueue.isEmpty()) {
			currNode = nodeQueue.removeMin();
			for (GraphEdge e : currNode.edgeList) {
				GraphNode v = e.to;
				if (currNode.getBestDistance() == Integer.MAX_VALUE) {
					break;
				}
				int altDistance = currNode.getBestDistance() + e.weight;
				if (altDistance < v.getBestDistance()) {
					v.setBestDistance(altDistance);
					v.setPred(currNode);
					nodeQueue.bubbleUp(v.heapIndex);
				}
			}
		}
	}

	@Override
	public void printDijkstraResults(String startNodeName) {
		for (String s : nodeNameList) {
			GraphNode n = nodeMap.get(s);
			if (n.getBestDistance() == Integer.MAX_VALUE) {
				System.out.println(startNodeName + " -> " + s + ": NO PATH");
			} else {
				System.out.print(startNodeName + " -> " + s + ": best " + n.getBestDistance() + ": ");
				String path = "";
				StringBuilder pathSB = new StringBuilder();
				while (n.getName().compareTo(startNodeName) != 0) {
					path += n.getName() + " ";
					n = n.getPred();
				}
				path += startNodeName;
				pathSB.append(path);
				pathSB = pathSB.reverse();
				System.out.print(pathSB);
				System.out.println();

			}
		}

	}

	@Override
	public void writeSolutionDotFile() {
		// TODO Auto-generated method stub

	}

}
