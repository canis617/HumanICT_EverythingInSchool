package EmptyClass;

import java.io.*;

public class Graph{
	public Facility head; 
	public Facility tail;
	
	public Graph() {
		this.head = new Facility("head");
		this.tail = new Facility("tail");
		head.connect_head_tail(tail);
//		tail.addFacility("classroom_715");
//		tail.addFacility("classroom_716");
//		tail.addFacility("center");
//		head.addEdge("classroom_715", "classroom_716", 1);
//		head.addEdge("classroom_715", "center", 2);
//		head.print_all_vertex();
		
		
	}
	
	public void read_floor_vertex(String filename, Facility tail) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			tail.addFacility(line);
		}
		
		br.close();
	}
	
	public void read_floor_edge(String filename, Facility head) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		
		String line = null;
		String[] str = new String[3];
		int num;
		
		while((line = br.readLine()) != null) {
			str = line.split("/");
			num = Integer.parseInt(str[2]);
			head.addEdge(str[0], str[1], num);
		}
		
		br.close();
	}

	
	
}