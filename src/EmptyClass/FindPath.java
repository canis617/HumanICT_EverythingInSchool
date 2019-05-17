package EmptyClass;

import java.util.ArrayList;

public class FindPath {
	Graph graph310;
	final int MAX = 1000;
	
	public FindPath() {
		
	}
	
	public FindPath(Graph g) {
		this.graph310 = g;
	}
	
	//to store facility & weight
	class classArray{
		String name;
		int weight;
		
		public classArray() {
			
		}
		public classArray(String name, int weight) {
			this.name = name;
			this.weight = weight;
		}
		public String getname() {
			return this.name;
		}
		public int getweight() {
			return this.weight;
		}
		public void setweight(int weight) {
			this.weight = weight;
		}
		
	}
	
	//to trace facility & path
	class pathArray{
		String name;
		String path;
		
		public pathArray() {
			
		}
		public pathArray(String name, String path) {
			this.name = name;
			this.path = path;
		}
		public String getname() {
			return this.name;
		}
		public String getpath() {
			return this.path;
		}
		public void setname(String name) {
			this.name = name;
		}
		public void setpath(String path) {
			this.path = path;
		}
	}
	
	
	public void algorithm_in_a_floor(String start, String end) {
		Graph.Floor afloor = graph310.floorhead;
		System.out.println("in a algorithm");
		System.out.println("start : " + start + "  end : " + end);
		
		
		while(true) {
			afloor = afloor.next;
			if(afloor.get_floor_num().equals("7"))
				break;
		}
		/////f = floor7
		System.out.println("floor_num : " + afloor.get_floor_num());
		
		
		ArrayList<classArray> list = new ArrayList<>();
		ArrayList<pathArray> path = new ArrayList<>();
		ArrayList<String> visited = new ArrayList<>();

		
		///initiallize arraylist(store weight to MAX except start point)
		Facility afacility = afloor.head;
		while(true) {
			if(afacility.getName().equals("head")) {
				//System.out.println("head pass & start (not add head)");
			}else if(afacility.getName().equals("tail")) {
				//System.out.println("tail pass & finish (not add tail)");
				System.out.println(afloor.get_floor_num() + "_floor's node_number = " + list.size());
				break;
			}else {
				if(afacility.getName().equals(start)) { // start weight initialize to 0
					list.add(new classArray(afacility.getName(), 0));
					//System.out.println("here is start : " + afacility.getName() + "  weight : " + 0);	
				}else {
					list.add(new classArray(afacility.getName(), MAX));
					//System.out.println("facility name : " + afacility.getName());	
				}
				path.add(new pathArray(afacility.getName(), null));
			}
			afacility = afacility.next;
		}
		

		afacility = afloor.head;
		visited.add(start);	
		
		//start at start facility
		afacility = afacility.search_facility(start);
		Facility.Edge e = afacility.edgehead;
		while(e.next!=null) {
			e = e.next;
			compare_weight(list, path, start, e.get_edge_end(), e.get_time_weight());
			visited.add(e.get_edge_end());
		}
		
		//start from visited to all
		while(true) {
			for(int i=0 ; i<visited.size() ; i++) {
				afacility = afloor.head;
				afacility = afacility.search_facility(visited.get(i));
				e = afacility.edgehead;
				//System.out.println("in loop = " + afacility.getName());
				while(e.next!=null) {
					e = e.next;
					compare_weight(list, path, visited.get(i), e.get_edge_end(), e.get_time_weight());
					if(!overlap_visited(visited, e.get_edge_end())) {
						visited.add(e.get_edge_end());
					}
				}
			}
			if(visited.size() == list.size()) {
				//System.out.println("visited all\n");
				break;
			}
		}
		
		//print_all_weight(list);
		System.out.println(start + " to " + end + " time_weight : " + return_time_weight(list, end));
		print_shortestpath(path, start, end);
		System.out.println(end);
	}
	
	//print start to All destination in a floor
	private void print_all_weight(ArrayList<classArray> list) {
		
		for(int i=0 ; i<list.size() ; i++) {
			System.out.println(list.get(i).getname() + " : " + list.get(i).getweight());
		}
	}
	
	//compare weight and store if it is shorter
	private void compare_weight(ArrayList<classArray> list, ArrayList<pathArray> path, String start, String end, int time_weight) {
		//System.out.println("start : " + start + "//end : " + end + "//time_weight : " + time_weight);
		//System.out.println(return_time_weight(list, end) + " > " + (time_weight + return_time_weight(list, start)) + " ?");
		
		if(return_time_weight(list, end) > time_weight + return_time_weight(list, start)) {
			set_time_weight(list, end, time_weight + return_time_weight(list, start));
			set_pathArray(path, start, end);
			//System.out.println(end + " value change to " + return_time_weight(list, end));
		}
		
	}
	
	//set_pathArray in compare_weight();
	private void set_pathArray(ArrayList<pathArray> path, String start, String end) {
		int i=0;
		while(true) {
			if(path.get(i).getname().equals(end)) {
				break;
			}
			i++;
		}
		path.get(i).setpath(start);
	}
	
	//print_shortestpath
	private void print_shortestpath(ArrayList<pathArray> path, String start, String end) {
		
		if(return_path(path, end) != null) {
			print_shortestpath(path, start, return_path(path, end));
			System.out.print(return_path(path, end) + " ");
		}
		
	}
	
	//return name's path info
	private String return_path(ArrayList<pathArray> path, String name) {
		int i=0;
		while(true) {
			if(path.get(i).getname().equals(name)) {
				//System.out.println("find name");
				break;
			}
			i++;
		}
		
		return path.get(i).getpath();
	}
	
	//return name's time_weight
	private int return_time_weight(ArrayList<classArray> list, String name) {
		int i=0;
		while(true) {
			if(list.get(i).getname().equals(name)) {
				//System.out.println("find name");
				break;
			}
			i++;
		}
		
		return list.get(i).getweight();
	}
	
	//set name's time_weight
	private void set_time_weight(ArrayList<classArray> list, String name, int time_weight) {
		int i=0;
		while(true) {
			if(list.get(i).getname().equals(name)) {
				//System.out.println("'find name");
				break;
			}
			i++;
		}
		
		list.get(i).setweight(time_weight);
	}
	
	//whether name is in visited
	private boolean overlap_visited(ArrayList<String> visited, String name) {
		for(int i=0; i<visited.size(); i++) {
			if(visited.get(i).equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	
}
