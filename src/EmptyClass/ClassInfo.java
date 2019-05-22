package EmptyClass;

import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassInfo {
	private String className;
	private String classID;
	private String classNumber;
	private String building;
	private String room;
	private String day;
	private String starttime;
	private String lastingtime;

	public ClassInfo(){
		className=null;
		classID=null;
		classNumber=null;
		building=null;
		room=null;
		day=null;
		starttime=null;
		lastingtime=null;
	}

	public ClassInfo(String className,int classID,int classNumber,int building, String room, String day, int starttime, int lastingtime){
		this.className=className;
		this.classID=Integer.toString(classID);
		this.classNumber=Integer.toString(classNumber);;
		this.building=Integer.toString(building);
		this.room=room;
		this.day=day;
		this.starttime=Integer.toString(starttime);
		this.lastingtime=Integer.toString(lastingtime);
	}

	public boolean isFull(){
		if(className != null && classID !=null && classNumber!=null&& building !=null && room != null && day !=null && starttime !=null && lastingtime != null){
			return true;
		}
		return false;
	}

	public Map<String, String> GetClassInfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("classID",classID);
		map.put("classNum",classNumber);
		map.put("className",className);
		map.put("day",day);
		map.put("starttime",starttime);
		map.put("lastingtime",lastingtime);
		map.put("building",building);
		map.put("room",room);
		return map;
	}
}
