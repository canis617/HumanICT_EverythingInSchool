package EmptyClass;

import java.io.*;

public class Main {

	//main function
	public static void main(String[] args) throws IOException {

		//start UI
		//Login startLogin = new Login();
		
		Graph g = new Graph();
		g.add_floor();
		//g.print_all_floornum();

		FindPath fp = new FindPath(g);
		fp.algorithm_in_a_floor("7c729", "7c706");
		
	}

}
