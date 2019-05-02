package EmptyClass;


public class Graph{

	public Graph() {
		
		Facility head = new Facility("head");
		Facility tail = new Facility("tail");
		head.connect_head_tail(tail);
		tail.addFacility("classroom_715");
		tail.addFacility("classroom_716");
		tail.addFacility("center");
		
		head.addEdge("classroom_715", "classroom_716", 1);
		head.addEdge("classroom_715", "center", 2);
		head.print_all_vertex();
		
	}

}