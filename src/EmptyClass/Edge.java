package EmptyClass;

public class Edge{
    private String start;
    private String end;
    private int time_weight;
    public Edge next;

    /////
    public Edge(){
    }
    //for edgehead in Facility
    public Edge(String start, String end){
        this.start = start;
        this.end = end;
        this.time_weight = 0;
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

    //print edge's information
    public void PrintEdge(){
        System.out.println(start+" -> "+end+ " : "+time_weight);
    }
}