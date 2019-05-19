package EmptyClass;

import java.awt.*;
import java.io.*;

public class Graph {
	public Floor floorhead;
	public Floor floortail;

	public Graph() {
		this.floorhead = null;
		this.floortail = null;
	}

	// floor 추가
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
		// System.out.println(floor_7.get_floor_num()+"floor vertex, edge complete!");
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
	public Floor add_down_floor(String start_floor, String end_floor) throws IOException {
		String filename = "data/down_floor.txt";
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = null;

		Floor floor = new Floor("mid");
		
		System.out.println("in add_down_floor");
		
		while ((line = br.readLine()) != null) {
			String[] str = new String[3];
			int num = 0;
			str = line.split("/");
			num = Integer.parseInt(str[2]);
			//System.out.println(str[0] + " " + str[1] + " " + num);
			add_down_edge(str[0], str[1], num, start_floor, end_floor, floor);
		}
		
	
		br.close();
		return floor;
	}

	public void add_down_edge(String start, String end, int time_weight, String start_floor, String end_floor, Floor floor) {
		if (start == null || end == null || start_floor == null || end_floor == null) {
			throw new IllegalArgumentException("input is null");
		}
		Facility f;
		
		if (start_floor.charAt(0) == start.charAt(0)) { // 출발 층 안에 있는 경우
			f = this.getFloor(return_under(start_floor)).getFacility(start);
			
			f.addEdge(end, time_weight, 0);
			
		} else if (end_floor.charAt(0) == start.charAt(0)) { // 도착 층 안에 있는 경우
			f = this.getFloor(return_under(end_floor)).getFacility(start);
			
			f.addEdge(end, time_weight, 0);
		} else { // mid floor에 facility, edge 추가
			boolean again = false;
			Facility fl = floor.head;
			
			while(fl!=null) { //중복으로 추가되는 경우 방지
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
			if(!again) {
				floor.addFacility(start);
			}
			
			f = floor.getFacility(start);
			f.addEdge(end, time_weight, 0);
//			if(end.equals("As1")) {
//				Edge e = f.edgehead;
//				while(e != null) {
//					e.PrintEdge();
//					e = e.next;
//				}
//				System.out.println("/////////////////////////");
//			}
		}
	}
	
	public String return_under(String str) {
		String alpha;
		if(str.charAt(0) == 'A')
			alpha = "B1";
		else if(str.charAt(0) == 'B')
			alpha = "B2";
		else if(str.charAt(0) == 'C')
			alpha = "B3";
		else if(str.charAt(0) == 'D')
			alpha = "B4";
		else
			alpha = str;
		
		return alpha;
	}
	
	// name에 해당되는 floor 반환
	public Floor getFloor(String floor) {
		Floor temp = floorhead;
		String str = return_under(floor);
		
		if (temp.get_floor_num().equals(str)) {
			return temp;
		}
		temp = temp.next;
		while (temp != floorhead) {
			if (temp.get_floor_num().equals(str)) {
				//System.out.println(str);
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

}