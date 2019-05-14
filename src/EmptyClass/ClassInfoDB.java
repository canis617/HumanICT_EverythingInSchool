package EmptyClass;

import java.security.Key;
import java.sql.*;
import java.util.*;

public class ClassInfoDB {
    private String server;
    private String database;
    private String user_name;
    private String password;

    private Connection conn = null;
    private Statement state = null;
    private ResultSet rs = null;

    public ClassInfoDB(){
        //initialize DB data
        server = "13.125.207.238"; // MySQL 서버 주소
        database = "emptyclass"; // MySQL DATABASE 이름
        user_name = "root"; //  MySQL 서버 아이디
        password = ""; // MySQL 서버 비밀번호
    }

    //to set sql, get resultset from sql
    public List<Map> GetResultMap(String sql){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name,password);
            //connection debug
            //System.out.println("정상적으로 연결되었습니다.");

            state = conn.createStatement();
            rs = state.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            rs.last();
            int row_count = rs.getRow();
            rs.beforeFirst();
            int col_count = metaData.getColumnCount();

            List<Map> list = new ArrayList<Map>();
            Map<String, Object> map;
            String column;
            // rs의 내용을 돌려준다.
            while (rs.next())
            {
                // 내부에서 map을 초기화
                map = new HashMap<String, Object>();
                // Column의 갯수만큼 회전
                for (int i = 0; i < col_count; i++) // i : index of column
                {
                    column = metaData.getColumnName(i + 1);
                    // map에 값을 입력 map.put(columnName, columnName으로 getString)
                    map.put(column, rs.getString(column));
                }
                // list에 저장
                list.add(map);
            }
            return list;
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if(state != null) {
                    state.close();
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            try {
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return null;
    }


    public String GetServer() {
        return server;
    }
    public String GetDataBase(){
        return database;
    }
    public String GetUserName(){
        return user_name;
    }
    public String GetPassword(){
        return password;
    }

    //unit test for DB connection
    public static void main(String[] args) {
        Connection con = null;


        ClassInfoDB testDB = new ClassInfoDB();

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + testDB.GetServer() + "/" + testDB.GetDataBase() +
                    "?useSSL=false", testDB.GetUserName(), testDB.GetPassword());

            System.out.println("정상적으로 연결되었습니다.");
        } catch(SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        // 3.해제
        try {
            if(con != null)
                con.close();
        } catch (SQLException e) {}

        try{
            ClassInfoDB test = new ClassInfoDB();
            String sql = "Select distinct room From ClassInfo where building='310' ORDER BY room";
            List<Map> tempList = test.GetResultMap(sql);

            for(int i=0; i<tempList.size();i++){
                Map<String, String> current = tempList.get(i);
                for(String key : current.keySet()){
                    String value = current.get(key);
                    System.out.println(key+" : "+value);
                }
            }

        }catch(Exception e){
            e.getMessage();
        }finally{

        }
    }

}
