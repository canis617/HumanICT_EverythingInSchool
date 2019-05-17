package EmptyClass;

import java.security.Key;
import java.util.List;
import java.util.Map;

public class ClassInfo {
	private int studentID;
	List<Map> classList;
	ClassInfoDB db;
	/**
	classList information
	private String className;
	private int classID;
	private int splitClassNumber;
	private int buildingID;
	private String roomNumber;
	private String day;
	private int startTime;
	private int classLasting;
	 */

	public ClassInfo(int studentID) {
		this.studentID = studentID;
		db = new ClassInfoDB();
		classList = db.GetSchedule(this.studentID);
	}

	public void PrintSchedule(){
		db.PrintListMap(classList);
	}

	public List<String> GetKey(String key){
		List<String> result = null;
		Map<String, String> current;
		for(int i=0; i<classList.size();i++){
			current = classList.get(i);
			result.add(current.get(key));
			System.out.println();
		}
		return result;
	}
	//unit test

}
