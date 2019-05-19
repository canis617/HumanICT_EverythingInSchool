package EmptyClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//personal schedule time table
public class Schedule {
	private String studentID;
	private String password;
	List<Map> classList;
	ClassInfoDB db;


	public Schedule(String studentID, String password) {
		this.studentID = studentID;
		this.password = password;
		db = new ClassInfoDB();
		classList = db.GetSchedule(Integer.parseInt(this.studentID));
	}

	public void PrintSchedule(){
		db.PrintListMap(classList);
	}

	//add new class
	public boolean AddClass(ClassInfo temp) {
		if(!temp.isFull()){
			throw new IllegalArgumentException("Class info error : not full information");
		}
		Map<String, String> map = temp.GetClassInfo();
		return false;
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
	//get-set function
}
