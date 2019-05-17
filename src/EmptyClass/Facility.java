package EmptyClass;

//facility
public class Facility { // vertex
	private String name;
	public Facility next;
	public Edge edgehead;
	private Edge uphead;
	private Edge downhead;
	/////
	public Facility() {
		
	}
	public Facility(String name) {
		this.name = name;
		this.next = null;
		this.edgehead = new Edge(name);
		this.uphead = new Edge(name);
		this.downhead = new Edge(name);
	}

	public class Edge{
		private String start;
		private String end;
		private int time_weight;
		public Edge next;
		
		/////
		public Edge(){

		}
		//for edgehead in Facility
		public Edge(String name){
			this.start = name;
			this.end = name;
			this.time_weight = -1;
			this.next = null;
		}
	
		//to create Edge
		public Edge(String start, String end, int time_weight){
			this.start = start;
			this.end = end;
			this.time_weight = time_weight;
			this.next = null;
		}
		
		public String get_edge_start() {
			return start;
		}
		
		public String get_edge_end() {
			return end;
		}
		
		public int get_time_weight() {
			return this.time_weight;
		}
		
	}
	
	//connect Facility head, tail
	public void connect_head_tail(Facility tail){
		this.next = tail;
		tail.next = this;
	}
	
	public Facility search_facility(String name) {
		Facility f = this;
		
		while(true) {
			if(f.getName().equals(name)) {
				return f;
			}else {
				f = f.next;
			}
			if(f.getName().equals("tail")) {
				System.out.println("Error : cannot find " + name + " facility");
				break;
			}
		}
		return f;
	}
	
	
	//add Facility at last order (tail.addFacility("name"))
	public void addFacility(String name){
		Facility f = new Facility(name);
		f.next = this;
		this.next.next = f;
		this.next = f;
	}

	//return vertex's name
	public String getName(){ 
      return String.valueOf(this.name);
	}
	
	//print all vertex's name (head.print_all_vertex())
	public void print_all_vertex(){
		Facility f = new Facility();
		f = this;
		while(true){
			System.out.println("vertex = " + f.getName());
			f.print_Edgeinfo();
			if(f.getName() == "tail"){
				System.out.println("Finish");
				break;
			}else{
				f = f.next;
			}
		}
	}
	
	//add Edge start to end in start Facility  (head.addEdge())
	public void addEdge(String start, String end, int time_weight){
		Facility f = new Facility();
		f = this;
		
		while(true){
			if(f.getName().equals(start)){
				break;
			}else{
				f = f.next;
			}
			
			if(f.getName().equals("tail")){
				System.out.println("fail to find start Facility(vertex) ");
				break;
			}
		}
		
		Edge e = new Edge();
		e = f.edgehead;
		while(true){
			if(e.next == null){
				e.next = new Edge(start, end, time_weight);
				break;
			}else{
				e = e.next;
			}
		}
		
		
		
		Facility t = new Facility();
		t = this;
		while(true){
			if(t.getName().equals(end)){
				break;
			}else{
				t = t.next;
			}
			
			if(t.getName().equals("tail")){
				System.out.println("fail to find start Facility(vertex)");
				break;
			}
		}
		
		Edge d = new Edge();
		d = t.edgehead;
		while(true){
			if(d.next == null){
				d.next = new Edge(end, start, time_weight);
				break;
			}else{
				d = d.next;
			}
		}
		
		
	}
	
	//print all Edge information in vertex
	public void print_Edgeinfo(){
		Edge e = new Edge();
		e = this.edgehead;
		while(true){
			if(e == null){
				System.out.println(this.getName() + "'s edge print finish\n");
				break;
			}else{
				if(e.time_weight == -1){
					System.out.println(this.getName() + "'s edgehead");
				}else{
					System.out.println(e.start + " to " + e.end + " time_weight : " + e.time_weight);
				}
				e = e.next;
			}
			
		}
	}
	
	
}
