package EmptyClass;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class FindPath {
	Graph graph310;

	public FindPath() {
		graph310 = null;
	}

	// initialize with g
	public FindPath(Graph g) {
		this.graph310 = g;
	}

	// find path �샇異쒕�遺�
	// Dijkstra : subgraph �깮�꽦�썑 寃곌낵 path瑜� stack�쑝濡� ���옣�빐�꽌 諛섑솚�빐以�. �씠瑜� 異쒕젰
	public Stack<Edge> find_path(String start, String end, boolean comfort) throws IOException {
		// get floor
		String start_floor = start.substring(0, 1);
		String end_floor = end.substring(0, 1);

		Floor temp = new Floor();
		Dijkstra subGraph = new Dijkstra();
		Stack<Edge> result = null;

		int compare = temp.compareFloor(start_floor, end_floor);
		if (compare == 0) {
			subGraph.addFloor(graph310.getFloor(start_floor));
			result = subGraph.findPathStack(start, end);
		} else {
			// add two floor
			subGraph.addFloor(graph310.getFloor(start_floor));
			subGraph.addFloor(graph310.getFloor(end_floor));

			// start is up, end is down
			if (compare > 0) {
				// get two floor and only start->end down edge
				if (comfort) {
					subGraph.addFloor(graph310.add_down_floor(start_floor, end_floor, comfort));
				} else {
					subGraph.addFloor(graph310.add_down_floor(start_floor, end_floor, comfort));
				}
				result = subGraph.findPathStack(start, end);
			}
			// start is down, end is up
			else {
				// get two floor and only start->end up edge
				if (comfort) {
					subGraph.addFloor(graph310.add_up_floor(start_floor, end_floor, comfort));
				} else {
					subGraph.addFloor(graph310.add_up_floor(start_floor, end_floor, comfort));
				}
				result = subGraph.findPathStack(start, end);
			}
		}
//result path debuging
//		for (int i = 0; !result.empty(); i++) {
//			Edge tempEdge = result.pop();
//			System.out.println(
//					tempEdge.get_edge_start() + " -> " + tempEdge.get_edge_end() + " = " + tempEdge.get_time_weight());
//		}

		return result;
	}

}