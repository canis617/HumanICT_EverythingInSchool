package EmptyClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Floor{
    public Facility head;
    public Facility tail;
    public Floor next;
    private String floor_num; // B4 ~ B1, 1 ~ 9
    public Floor() {

    }

    //default floor
    public Floor(String floor_num) {
        this.floor_num = floor_num;
        this.head = null;
        this.tail = null;
        this.next = this;
    }

    //vertex파일 읽어서 저장
    public void read_floor_vertex(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line = null;
        while((line = br.readLine()) != null) {
            addFacility(line);
        }
        br.close();
    }
    //edge 파일 읽어서 저장
    public void read_floor_edge(String filename) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = null;
            String[] str = new String[3];
            int num;

            while((line = br.readLine()) != null) {
                str = line.split("/");
                num = Integer.parseInt(str[2]);
                addEdge(str[0], str[1], num);
                addEdge(str[1], str[0], num);
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    //facility 추가
    public boolean addFacility(String name){
        if(name == null){
            throw new IllegalArgumentException("name is null");
        }
        Facility facility = new Facility(name);
        //if empty floor
        if(head == null){
            head = facility;
            tail = facility;
            return true;
        }
        //add new facility
        tail.next = facility;
        facility.next = head;
        tail = facility;
        return true;
    }

    //edge추가 start vertex 찾음 -> edge 추가
    public boolean addEdge(String start, String end, int time){
        if(start == null || end ==null || time<0){
            throw new IllegalArgumentException("add edge argument error");
        }
        Facility temp = head;
        if (temp == null) {
            System.out.println(start + "is not defined");
            return false;
        }
        if(temp.getName().equals(start)){
            temp.addEdge(end,time,0);
            return true;
        }
        temp = temp.next;
        while(temp != head){
            if(temp.getName().equals(start)){
                temp.addEdge(end,time,0);
                return true;
            }
            temp=temp.next;
        }
        return false;
    }

    public String get_floor_num() {
        return this.floor_num;
    }
    public void set_floor_num(String name) {
        this.floor_num = name;
    }

    public void PrintFloor(){
        System.out.println("floor : " +floor_num);
        Facility temp = head;
        if(temp != null){
            System.out.println("vertex");
        }
        while(temp!=null){
            temp.print_all_vertex();
        }

    }

    //floor name으로 비교
    //same : 0 / start > end : 1 / start < end : -1
    public int compareFloor(String start, String end){
        char start_floor = start.charAt(0);
        char end_floor = end.charAt(0);

        if(start_floor==end_floor){
            return 0;
        }else{
            //two floor + all up or down head
            if(start_floor >= 'A'){
                if(end_floor >= 'A'){
                    return end_floor-start_floor;
                }
                else{
                    return -1;
                }
            }
            else{
                if(end_floor >= 'A'){
                    return 1;
                }
                else {
                    return start_floor-end_floor;
                }
            }
        }
    }
}