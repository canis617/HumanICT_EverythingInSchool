package EmptyClass;

public class ReplaceR {
	// 729호 >> 7c729, 729 >> 7c729, B101 >> AcB101
	
	private String ff;
	private String floor;
	private String room;
	
	public String ReplaceR (String name) {
		String facility = null;
		ff = name.substring(0,1);
		
		switch(ff) {
		case "B":
			
			ff = name.substring(0,2);
			switch(ff) {
			case "B1":
				floor = "Ac";
				break;
				
			case "B2":
				floor = "Bc";
				break;
				
			case "B3":
				floor = "Cc";
				break;
			}
			room = name.substring(0,4);	
			break;
		default:
			floor = ff.concat("c");
			room = name.substring(0,3);
			break;
		}
		
		facility = floor + room;
		
		return facility;
	}
}
