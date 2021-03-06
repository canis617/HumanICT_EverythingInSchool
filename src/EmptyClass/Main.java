package EmptyClass;

import java.io.*;

public class Main {
	//main function
	public static void main(String[] args) throws IOException {

		//start UI
		//Login startLogin = new Login();

		Graph g = new Graph();

		String[] floorList = new String[13];
		//read index list
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/floorindex.txt"));
			String line = null;
			int index = 0;
			while ((line = br.readLine()) != null) {
				floorList[index++] = new String(line);
			}
			br.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		for(int i=0; i<floorList.length;i++){
			g.add_floor(floorList[i]);
		}

		FindPath fp = new FindPath(g);
		fp.find_path("DcFLOWER", "1s1", false);

	}

}