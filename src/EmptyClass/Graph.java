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

	public void add_floor(String floor_name) throws IOException {
		Floor floor = new Floor(floor_name);
		try {
			//one floor declaration like this
			String floorvertex = "data/" + floor_name + "facility.txt";
			String flooredge = "data/" + floor_name + "edge.txt";
			floor.read_floor_vertex(floorvertex);
			floor.read_floor_edge(flooredge);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//System.out.println(floor_7.get_floor_num()+"floor vertex, edge complete!");
		if (floorhead == null) {
			floorhead = floor;
			floortail = floor;
			floor.next = floor;
		} else {
			floortail.next = floor;
			floor.next = floorhead;
			floortail = floor;
		}
		//floor_7.head.print_all_vertex();
	}

	/*
		public void print_all_floornum() {
			System.out.println("print_all_floornum");
			Floor temp = floorhead;
			if(temp != null){
				while(temp!=null) {
					System.out.println(temp.floor_num);
				}
			}

		}
		public void printAllIndex() {
			System.out.println("print_all_index");
			Floor temp = floorhead;
			if(temp != null){
				System.out.println("floors");
			}
			while(temp!=null) {
				temp.PrintFloor();
			}
		}
	*/
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

	public int compareFloor(String start, String end) {
		return floorhead.compareFloor(start, end);
	}
}