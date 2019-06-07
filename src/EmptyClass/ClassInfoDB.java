package EmptyClass;

/** File Name : ClassInfoDB.java
 *
 *
 *
 * */

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

	public ClassInfoDB() {
		// initialize DB data
		server = "15.164.26.48"; // MySQL host address
		database = "emptyclass"; // MySQL DATABASE name
		user_name = "root"; // MySQL user name
		password = "emptyClassq1"; // MySQL password
	}

	// to set sql, get resultset from sql
	public List<Map> GetResultMap(String sql) {
		if (sql == null) {
			throw new IllegalArgumentException("parameter error");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! <JDBC class> Driver load failed: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name,
					password);
			// connection debug

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
			// rs
			while (rs.next()) {

				map = new HashMap<String, Object>();

				for (int i = 0; i < col_count; i++) // i : index of column
				{
					column = metaData.getColumnName(i + 1);

					map.put(column, rs.getString(column));
				}

				list.add(map);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}

	public boolean SetData(String sql) {
		if (sql == null) {
			throw new IllegalArgumentException("parameter error");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! <JDBC connect> Driver load fail: " + e.getMessage());
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name,
					password);
			// connection debug

			state = conn.createStatement();
			if (state.execute(sql) == true) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return false;
	}

	// get empty class
	public List<Map> GetEmptyClass(int building, String day, double time) {
		if (building < 0 || time < 0 || time > 24 || (day != "mon" && day != "tues" && day != "wed" && day != "thurs"
				&& day != "fri" && day != "sat" && day != "sun")) {
			throw new IllegalArgumentException("parameter error");
		}
		String _time = Double.toString(time);
		String _building = Integer.toString(building);
		String sql;
		sql = String.format(
				"select distinct room from ClassInfo where day='%s' and building=%s and starttime<=%s and (starttime+lastingtime)>%s order by room",
				day, _building, _time, _time);
		List<Map> beingClass = GetResultMap(sql);

		sql = String.format("select distinct room from ClassInfo where building=%s order by room", _building);
		List<Map> allClass = GetResultMap(sql);

		List<Map> resultClass = exceptList(allClass, beingClass);
		return resultClass;
	}

	// get schedule
	// critical error !!
	// Same classID, classNum classes are exists! -> This is for Class Information
	// table
	public List<Map> GetSchedule(int studentID) {
		if (studentID < 0 || studentID > 100000000) {
			throw new IllegalArgumentException("student ID error");
		}
		String _ID = Integer.toString(studentID);
		String sql;
		sql = String.format(
				"select C.* from StudentInfo as P JOIN ClassInfo as C On P.classID=C.classID and P.classNum=C.classNum where P.studentID=%s",
				_ID);
		List<Map> classList = GetResultMap(sql);
		return classList;
	}

	// get student list
	public List<Map> GetStudentList() {
		String sql;
		sql = String.format("select distinct studentID from StudentInfo");
		List<Map> classList = GetResultMap(sql);
		return classList;
	}

	public boolean DeleteSchedule(String student_num, String className) {
//        if(studentID < 0 || studentID > 100000000 || classID <0 || classID > 100000 || classNum <0){
//            throw new IllegalArgumentException("schedule data error");
//        }
		boolean success = false;
		String sql;

		sql = String.format("select * from ClassInfo where className = '%s'", className);
		List<Map> List = GetResultMap(sql);
		if (List.size() == 0) {
			System.out.println("not valid className");
		} else {
			Map<String, String> element = List.get(0);

			String classID = element.get("classID");

			sql = String.format("delete from StudentInfo where studentID = '%s' and classID = '%s'", student_num,
					classID);
			try {
				success = SetData(sql);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println(sql + "successed");
			}
		}
		return success;
	}

	// set schedule

	public boolean SetSchedule(int studentID, int classID, int classNum) {
		if (studentID < 0 || studentID > 100000000 || classID < 0 || classID > 100000 || classNum < 0) {
			throw new IllegalArgumentException("schedule data error");
		}
		String _ID = Integer.toString(studentID);
		String _CID = Integer.toString(classID);
		String _CNum = Integer.toString(classNum);
		String sql;
		boolean success = false;
		sql = String.format("insert into StudentInfo values('%s', %s, %s)", _ID, _CID, _CNum);
		try {
			success = SetData(sql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(sql + "successed");
		}

		return success;
	}

	// set schedule

	// return a-b
	public List<Map> exceptList(List<Map> a, List<Map> b) {
		int bCount = 0;
		Map<String, String> compareList = b.get(bCount);
		for (int i = 0; i < a.size(); i++) {
			Map<String, String> current = a.get(i);
			if (current.equals(compareList) == true) {
				a.remove(i--);
				if (++bCount < b.size()) {
					compareList = b.get(bCount);
				}
			}
		}
		return a;
	}

	public void PrintListMap(List<Map> tempList) {
		Map<String, String> current = tempList.get(0);
		for (String key : current.keySet()) {
			String value = current.get(key);
			if (key == "className") {
				System.out.print(key + "\t\t\t\t");
			} else {
				System.out.print(key + "\t\t");
			}
		}
		System.out.println();
		for (int i = 0; i < tempList.size(); i++) {
			current = tempList.get(i);
			for (String key : current.keySet()) {
				String value = current.get(key);
				System.out.print(value + "\t\t");
			}
			System.out.println();
		}
		System.out.println("size : " + tempList.size());
	}

	public String GetServer() {
		return server;
	}

	public String GetDataBase() {
		return database;
	}

	public String GetUserName() {
		return user_name;
	}

	public String GetPassword() {
		return password;
	}

	// unit test for DB connection
	public static void main(String[] args) {
		Connection con = null;

		ClassInfoDB testDB = new ClassInfoDB();

		// 1. connect Mysql class
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! <JDBC Class> Driver load fail: " + e.getMessage());
			e.printStackTrace();
		}

		// 2. connect db
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://" + testDB.GetServer() + "/" + testDB.GetDataBase() + "?useSSL=false",
					testDB.GetUserName(), testDB.GetPassword());

			System.out.println("connection success");
		} catch (SQLException e) {
			System.err.println("con failed:" + e.getMessage());
			e.printStackTrace();
		}

		// 3. unit test
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
		}

		try {
			ClassInfoDB test = new ClassInfoDB();
			//
			// String sql = "Select * From ClassInfo where building='310' ORDER BY room";
			String sql;

			// get empty class test
			List<Map> tempList = test.GetEmptyClass(310, "mon", 12);

			// get student's class list
			List<Map> studentClassList = test.GetSchedule(20150101);
			test.PrintListMap(studentClassList);

			try {
				test.SetSchedule(20150864, 10000, 01);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {

		}
	}

}
