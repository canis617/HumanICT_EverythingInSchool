package EmptyClass;

public class Replace{
		private String floor;
		private String object;
		private String number;
	 
	    public String Replace(String name){  
			// name = floor + object + number (7c729 = 7+c+729)
	    	String new_facility;
	    	floor = name.substring(0,1);	// 처음 문자는 항상 층수
	    	object = name.substring(1,2);	// 처음 다음 문자는 항상 어떤 형태인지 (ex. /e/s/t/i/c)
	    	number = name.substring(2);		// 그 이후 문자
	    	
	    	switch(floor) {
	    	case "D": floor = "지하 4";
	   			break;
	   		case "C": floor = "지하 3";
	   			break;
	   		case "B": floor = "지하 2";
	  			break;
	   		case "A": floor = "지하 1";
	   			break;
	   		default : 
	   			break;    			
	   		}
	    	
	    	switch(object) {
	    	case "e": object = "층 엘리베이터";
	    		break;
	    	case "s": object = "층 계단";
	    		break;
	    	case "t": object = "층 에스컬레이터";
	    		break;
	    	case "i": object = "층 교차로";
                break;
            case "o": object = "층 출입문";
                break;
	    	case "c":	
	    		object = "층 ";
	    		if (name.charAt(0) != 'D' || number.matches("(^[0-9]*$)")) { 
	    			// object가 'c'일때 D층에 대해서는 강의실이 없기 때문에 뒤에 '호'를 붙일 이유가 없음
	    			// 혹은 그 뒤에 값이 숫자 인경우에는 무조건 강의실임
	    			number = number + "호";
	    		}
	    		break;
	    	}
	    		new_facility = floor + object + number;
				return new_facility;
		}

			public String ReplaceR (String name) {
				// name = floor + number (729 = 7c729, 729호 = 7c729, B101 = AcB101)
				String new_facility;

				floor = name.substring(0,1);

				switch(floor){
					case "B":
						floor = name.substring(0,2);
						switch(floor){
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
						number = name.substring(0,4);
						break;
					default:
						floor = floor.concat("c");
						number = name.substring(0,3);
						break;
					}
				
				return new_facility = floor + number;
			}
} 	
