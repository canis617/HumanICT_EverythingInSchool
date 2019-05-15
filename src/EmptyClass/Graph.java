package EmptyClass;

import java.io.*;

public class Graph{
	public Floor floorhead;
	public Floor floortail;
	
	public Graph() {
		this.floorhead = new Floor();
		this.floorhead.set_floor_num("floorhead");
		this.floortail = new Floor();
		this.floortail.set_floor_num("floortail");
		this.floorhead.next = this.floortail;
		this.floortail.next = null;
	}
	
	//////////////////////////////////////////////////
	public class Floor{
		public Facility head; 
		public Facility tail;
		public Floor next;
		private String floor_num; // B4 ~ B1, 1 ~ 9
		
		public Floor() {
			
		}
		public Floor(String floor_num) {
			this.head = new Facility("head");
			this.tail = new Facility("tail");
			head.connect_head_tail(tail);
			this.floor_num = floor_num;
			
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
		
		public String get_floor_num() {
			return this.floor_num;
		}
		public void set_floor_num(String name) {
			this.floor_num = name;
		}
		
	}
	//////////////////////////////////////////////////
	
	
	public void add_floor() throws IOException {
		
		//이런식으로 한 층 선언
		Floor floor_7 = new Floor("7");
		String floor7vertex = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\7facility.txt";
		String floor7edge = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\7edge.txt";
		floor_7.read_floor_vertex(floor7vertex, floor_7.tail);
		floor_7.read_floor_edge(floor7edge, floor_7.head);
		System.out.println(floor_7.get_floor_num()+"floor vertex, edge complete!");
		connect_floorlist(floor_7);
		//floor_7.head.print_all_vertex();
		
		Floor floor_6 = new Floor("6");
		String floor6vertex = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\6facility.txt";
		floor_6.read_floor_vertex(floor6vertex, floor_6.tail);
		System.out.println(floor_6.get_floor_num()+"floor vertex complete!");
		connect_floorlist(floor_6);
		//floor_6.head.print_all_vertex();
		
		Floor floor_5 = new Floor("5");
		String floor5vertex = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\5facility.txt";
		floor_5.read_floor_vertex(floor5vertex, floor_5.tail);
		System.out.println(floor_5.get_floor_num()+"floor vertex complete!");
		connect_floorlist(floor_5);
		//floor_5.head.print_all_vertex();
		
		Floor floor_B1 = new Floor("B1");
		String floorB1vertex = "C:\\Users\\USER\\Desktop\\동욱\\workspace\\HumanICT_EverythingInSchool\\data\\B1facility.txt";
		floor_B1.read_floor_vertex(floorB1vertex, floor_B1.tail);
		System.out.println(floor_B1.get_floor_num()+"floor vertex complete!");
		connect_floorlist(floor_B1);
		//floor_B1.head.print_all_vertex();
	}
	
	//add floor to floorhead(make floor linkedlist in graph class)
	public void connect_floorlist(Floor f) {
		f.next = this.floorhead.next;
		this.floorhead.next = f;
	}
	
	public void print_all_floornum() {
		Floor f = this.floorhead;
		System.out.println("\nprint_all_floornum");
		while(true) {
			System.out.println(f.get_floor_num());
			f = f.next;
			if(f.next == null) {
				System.out.println(f.get_floor_num());
				System.out.println("finish_print_all_floor");
				break;
			}
		}
		
	}
}