package EmptyClass;

import java.io.*;

public class Main {

	//main function
	public static void main(String[] args) throws IOException {

		//start UI
		//Login startLogin = new Login();
		
		Graph g = new Graph();
		String floor7vertex = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\7facility.txt";
		String floor7edge = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\7edge.txt";
		
		g.read_floor_vertex(floor7vertex, g.tail);
		
		g.read_floor_edge(floor7edge, g.head);
		
		g.head.print_all_vertex();
	}

}
