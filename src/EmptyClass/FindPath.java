package EmptyClass;

import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class FindPath {
	Graph graph310;
	
	public FindPath() {
		graph310=null;
	}

	//��ü graph�޾Ƽ� �ʱ�ȭ
	public FindPath(Graph g) {
		this.graph310 = g;
	}

	//find path ȣ��κ�
	//Dijkstra : subgraph ������ ��� path�� stack���� �����ؼ� ��ȯ����. �̸� ���
	public Stack<Edge> find_path(String start, String end) throws IOException {
		//get floor
		String start_floor = start.substring(0,1);
		String end_floor = end.substring(0,1);

		Floor temp = new Floor();
		Dijkstra subGraph = new Dijkstra();
		Stack<Edge> result = null;

		int compare = temp.compareFloor(start_floor,end_floor);
		if (compare == 0){
			subGraph.addFloor(graph310.getFloor(start_floor));
			result = subGraph.findPathStack(start, end);
		}
		else {
			//add two floor
			subGraph.addFloor(graph310.getFloor(start_floor));
			subGraph.addFloor(graph310.getFloor(end_floor));
			
			//start is up, end is down
			if(compare > 0){
				//get two floor and only start->end down edge
				subGraph.addFloor(graph310.add_down_floor(start_floor, end_floor));
				result = subGraph.findPathStack(start, end);
			}
			//start is down, end is up
			else {
				//get two floor and only start->end up edge
				subGraph.addFloor(graph310.add_up_floor(start_floor, end_floor));
				result = subGraph.findPathStack(start, end);
			}
		}

//		//return result : stack<Edge>
//		for(int i = 0; !result.empty();i++){
//			Edge tempEdge = result.pop();
//			System.out.println(tempEdge.get_edge_start()+" -> "+tempEdge.get_edge_end()+" = "+tempEdge.get_time_weight());
//		}
		return result;
	}

}
