package EmptyClass;

//facility
public class Facility { // vertex
	private String name;			//id
	public Facility next;			//vertex list
	public Edge edgehead;			//edge list
	private Edge uphead;			//uphead edge list
	private Edge downhead;			//downhead edge list
	/////
	public Facility() {

	}
	//name으로 초기화
	public Facility(String name) {
		this.name = name;
		this.next = this;
		this.edgehead = null;
		this.uphead = null;
		this.downhead = null;
	}

	//facility name으로 찾아서 반환
//	public Facility search_facility(String name) {
//		Facility f = this;
//		if(f.getName().equals(name)) {
//			return f;
//		}
//		f = f.next;
//		while(f != this) {
//			if(f.getName().equals(name)) {
//				return f;
//			}
//			f = f.next;
//		}
//		System.out.println("Error : cannot find " + name + " facility");
//		return null;
//	}

	//return vertex's name
	public String getName(){
		return String.valueOf(this.name);
	}

	//add Edge start to end in start Facility  (head.addEdge())
	//option : 0 : 평행노드 / 1 : uphead 노드 / 2 : downhead 노드           //현재 구현은 전부 0으로 되어있음
	//현재 option 0만 제대로 작동 1, 2는 미구현
	public void addEdge(String end, int time_weight, int option){
		if(end == null || time_weight<0 ||option <0 || option >2){
			throw new IllegalArgumentException("add Edge argument error");
		}
		Edge edge = new Edge(name, end, time_weight);

		//default
		if(option == 0){
			edgehead = addEdge(edge, edgehead);
		}

//		//uphead
//		if(option == 1){
//			uphead = addEdge(edge, uphead);
//		}
//		//downhead
//		if(option == 2){
//			downhead = addEdge(edge, downhead);
//		}

	}

	//add edge (vertex는 이미 해당 클래스)
	public Edge addEdge(Edge edge, Edge head){
		Edge temp = head;
		if(temp == null){
			head = edge;
			return head;
		} else{
			while(temp.next!= null){
				temp = temp.next;
			}
			temp.next = edge;
		}

		return head;
	}

	//print vertex's information
	public void print_all_vertex(){
		System.out.println("vertex = " + name);
		Edge temp = edgehead;
		if(temp != null){
			System.out.println("edges");
		}
		while(temp != null){
			temp.PrintEdge();
			temp = temp.next;
		}
	}

}