package EmptyClass;

import java.io.*;

public class Main {

	//main function
	public static void main(String[] args) throws IOException {

		//start UI
		//Login startLogin = new Login();
		
		Graph g = new Graph();
		g.add_floor();
		g.print_all_floornum();

	}

}
