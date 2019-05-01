package EmptyClass;

import java.util.ArrayList;

//personal schedule time table
public class Schedule {
	private int studentID;
	private int password;
	
	private ArrayList classList;
	
	public Schedule() {
		classList = new ArrayList<ClassInfo>();
		// TODO Auto-generated constructor stub
	}

	//add new class
	public void AddClass(ClassInfo temp) {
		classList.add(temp);
	}
	//return all list of class
	//need to make funtion to return individual class for index(ID, days, time etc) 
	public ArrayList<ClassInfo> GetClass() {
		return classList;
	}
	
	//get-set function
}
