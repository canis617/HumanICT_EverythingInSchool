package EmptyClass;

import java.util.Calendar;

public class Edge {
	private String start;
	private String end;
	private int time_weight;
	public Edge next;

	public Edge() {
	}

	// for edgehead in Facility
	public Edge(String start, String end) {
		this.start = start;
		this.end = end;
		this.time_weight = 0;
		this.next = null;
	}

	// to create Edge
	public Edge(String start, String end, int time_weight) {
		this.start = start;
		this.end = end;
		if (start.substring(1, 2).equals("e") && end.substring(1, 2).equals("e"))
			this.time_weight = time_change_elevator(time_weight);
		else
			this.time_weight = time_weight;
		this.next = null;
	}
	
	// 시간 따라 엘리베이터 time_weight 가중치 변경
	public int time_change_elevator(int time_weight) {
		int num;
		Calendar cal = Calendar.getInstance();
		num = cal.get(Calendar.MINUTE);
		if (num >= 50 && num < 60) // 이부분 가중치 수정 요망
			num = time_weight * 20;
		else
			num = time_weight;

		return num;
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

	// print edge's information
	public void PrintEdge() {
		System.out.println(start + " -> " + end + " : " + time_weight);
	}

//	// unit test
//	public static void main(String[] args) {
//		int weight = 1;
//		int num;
//		num = time_change_elevator(weight);
//		
//		System.out.println(weight + " " + num );
//
//	}

}