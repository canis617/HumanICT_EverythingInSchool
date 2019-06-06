package EmptyClass;

import java.io.*;

public class Graph {
	public Floor floorhead;
	public Floor floortail;

	public Graph() {
		this.floorhead = null;
		this.floortail = null;
	}

	// floor 異붽�
	public void add_floor(String floor_name) throws IOException {
		Floor floor = new Floor(floor_name);
		try {
			// one floor declaration like this
			String floorvertex = "data/" + floor_name + "facility.txt";
			String flooredge = "data/" + floor_name + "edge.txt";
			// file read
			floor.read_floor_vertex(floorvertex);
			floor.read_floor_edge(flooredge);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (floorhead == null) {
			floorhead = floor;
			floortail = floor;
			floor.next = floor;
		} else {
			floortail.next = floor;
			floor.next = floorhead;
			floortail = floor;
		}
	}

	// read down_floor.txt
	public Floor add_down_floor(String start_floor, String end_floor, boolean comfort) throws IOException {
		String filename = "data/down_floor.txt";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;
		Floor floor = new Floor("mid");

		while ((line = br.readLine()) != null) {
			String[] str = new String[3];
			int num = 0;
			str = line.split("/");
			num = Integer.parseInt(str[2]);
			if(comfort) {
				if(str[0].charAt(1) == 's' && str[1].charAt(1) == 's') {
					num+=100;
				}
			}
			add_updown_edge(str[0], str[1], num, start_floor, end_floor, floor);
		}

		br.close();
		return floor;
	}

	// read up_floor.txt
	public Floor add_up_floor(String start_floor, String end_floor, boolean comfort) throws IOException {
		String filename = "data/up_floor.txt";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;
		Floor floor = new Floor("mid");

		while ((line = br.readLine()) != null) {
			String[] str = new String[3];
			int num = 0;
			str = line.split("/");
			num = Integer.parseInt(str[2]);
			if(comfort) {
				if(str[0].charAt(1) == 's' && str[1].charAt(1) == 's') {
					num+=100;
				}
			}
			add_updown_edge(str[0], str[1], num, start_floor, end_floor, floor);
		}

		br.close();
		return floor;
	}

	// add up/down edge to 'mid' floor
	public void add_updown_edge(String start, String end, int time_weight, String start_floor, String end_floor, Floor floor) {
		if (start == null || end == null || start_floor == null || end_floor == null) {
			throw new IllegalArgumentException("input is null");
		}
		Facility f;
		if (start_floor.charAt(0) == start.charAt(0)) { // 異쒕컻 痢� �븞�뿉 �엳�뒗 寃쎌슦
			f = this.getFloor(start_floor).getFacility(start);
			f.addEdge(end, time_weight, 0);
		} else if (end_floor.charAt(0) == start.charAt(0)) { // �룄李� 痢� �븞�뿉 �엳�뒗 寃쎌슦
			f = this.getFloor(end_floor).getFacility(start);
			f.addEdge(end, time_weight, 0);
		} else { // mid floor�뿉 facility, edge 異붽�
			boolean again = false;
			Facility fl = floor.head;
			while(fl!=null) { // 以묐났�쑝濡� 異붽��릺�뒗 寃쎌슦 諛⑹�
				if(fl.getName().equals(start)) {
					again = true;
					break;
				}
				fl = fl.next;
				if(fl == floor.head) {
					again = false;
					break;
				}
			}
			if(!again) { // 以묐났�씠 �뾾�쑝硫� �끂�뱶 異붽�
				floor.addFacility(start);
			}
			f = floor.getFacility(start);
			f.addEdge(end, time_weight, 0);
		}
	}

	// name�뿉 �빐�떦�릺�뒗 floor 諛섑솚
	public Floor getFloor(String floor) {
		Floor temp = floorhead;
		if (temp.get_floor_num().equals(floor)) {
			return temp;
		}
		temp = temp.next;
		while (temp != floorhead) {
			if (temp.get_floor_num().equals(floor)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}
	//unit test find path
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
		fp.find_path("DcSTORE", "7c720", false);
	}
}