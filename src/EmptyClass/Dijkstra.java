package EmptyClass;

//to store facility & weight

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

//다익스트라 자료구조 = 서브그래프, 다익스트라 구하기용
class Dijkstra {
    Facility vertex;
    int dist;
    Edge from;
    boolean visited;
    Dijkstra next;

    //초기화
    public Dijkstra() {
        this.vertex = null;
        dist = 1000000; // MAX
        from = null;
        visited = false;
        next = null;
    }
    //1개 vertex로 초기화
    public Dijkstra(Facility vertex) {
        this.vertex = vertex;
        dist = 1000000; // MAX
        from = null;
        visited = false;
        next = null;
    }
    //vertex 추가
    public boolean addVertex(Facility vertex){
        if(this.vertex == null){
            this.vertex = vertex;
            return true;
        }
        else{
            Dijkstra temp = this;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Dijkstra(vertex);
            return true;
        }
    }
    //floor 추가
    public boolean addFloor(Floor floor){
        if(floor == null){
            return false;
        }
        Facility node = floor.head;
        addVertex(node);
        node = node.next;
        while(node != floor.head){
            addVertex(node);
            node=node.next;
        }
        return true;
    }
    //다익스트라 알고리즘에 필요한 변수 초기화
    public void resetDijkstra(){
        Dijkstra temp = this;
        while(temp != null){
            temp.dist = 1000000;
            temp.visited = false;
            temp.from = null;
            temp = temp.next;
        }
    }

    //start vertex 설정 - 다익스트라 알고리즘 실행 전 초기화 개념
    public boolean setStartvertex(String name){
        resetDijkstra();
        Dijkstra temp = this;
        while(temp != null){
            if(temp.vertex.getName().equals(name)){
                temp.dist=0;
                temp.visited=false;
                temp.from= null;
                return true;
            }
            temp=temp.next;
        }
        return false;
    }

    //다익스트라 알고리즘
    public boolean runDijkstra(){
        Dijkstra temp;
        Dijkstra minNode = minDist();
        while(minNode != null){
            minNode.visited = true;
            Edge tempEdge = minNode.vertex.edgehead;
            while(tempEdge != null) {
                //fin a edge
                String edge_name = tempEdge.get_edge_end();
                //find edge's destination node
                temp = this;
                while (temp != null) {
                    if (temp.vertex.getName().equals(edge_name)) {
                        if (minNode.dist + tempEdge.get_time_weight() < temp.dist) {
                            temp.dist = minNode.dist + tempEdge.get_time_weight();
                            temp.from = tempEdge;
                        }
                        break;
                    }
                    temp = temp.next;
                }
                tempEdge=tempEdge.next;
            }
            minNode = minDist();
        }
        return true;
    }

    //최소 거리 vertex 골라서 return
    public Dijkstra minDist() {
        Dijkstra temp = this;
        Dijkstra min = new Dijkstra();
        while(temp != null){
            if(temp.visited == false && min.dist > temp.dist){
                min = temp;
            }
            temp=temp.next;
        }
        if(min.vertex !=null){
            return min;
        }
        return null;
    }

    //dijstra 함수 호출부분 결과는 stack으로 하여 return
    public Stack<Edge> findPathStack(String start, String end){

        setStartvertex(start);
        runDijkstra();

        String find = end;
        Stack<Edge> result = new Stack<Edge>();
        Edge tempEdge= null;
        Dijkstra temp = this;

        //start == end
        if(start.equals(end)){
            return null;
        }

        //find until start node
        do{
            while(temp!=null){
                if(temp.vertex.getName().equals(find)){
                    tempEdge = temp.from;
                    result.push(tempEdge);
                    find = tempEdge.get_edge_start();
                    break;
                }
                temp=temp.next;
            }
            temp = this;
        } while(!find.equals(start));
        if(result != null){
            return result;
        }
        return null;
    }

    //unit test
//    public static void main(String[] args) throws IOException {
//        Graph g = new Graph();
//        try {
//            g.add_floor("7");
//        } catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        FindPath findpath = new FindPath(g);
//
//        findpath.find_path("7c726", "7c706");
//
//    }
}