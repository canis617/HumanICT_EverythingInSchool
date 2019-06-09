package EmptyClass;

public class Replace{
		private String floor;
		private String object;
		private String number;
	 
		public String Replace_s(String floor, String number) {
			String ob = null;
			switch(floor) {
			case "9":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "8":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 중앙 계단";
					break;
				case "4":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "7":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "6":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "5":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
				case "2":
					ob = "층 중앙 계단";
				case "3":
					ob = "층 사이드 계단";
				}
				break;
			case "4":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 중앙 계단";
					break;
				case "4":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "3":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
				case "2":
					ob = "층 중앙 계단";
				case "3":
					ob = "층 중앙 계단";
				case "4":
					ob = "층 사이드 계단";
				}
				break;
			case "2":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 중앙 계단";
					break;
				case "4":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "1":
				switch(number) {
				case "1":
					ob = "층 사이드 계단";
					break;
				case "2":
					ob = "층 중앙 계단";
					break;
				case "3":
					ob = "층 지하로 가는 계단";
					break;
				case "4":
					ob = "층 사이드 계단";
					break;
				}
				break;
			case "지하 1":
				switch(number) {
				case "1":
					ob = "층 중앙 계단";
					break;
				}
				break;
			case "지하 2":
				switch(number) {
				case "1":
					ob = "층 중앙 계단";
					break;
				}
				break;
			case "지하 3":
				switch(number) {
				case "1":
					ob = "층 중앙 계단";
					break;
				}
				break;
			case "지하 4":
				switch(number) {
				case "1":
					ob = "층 중앙 계단";
					break;
				}
				break;
			}
			
			return ob;
		}
		public String Replace_e(String floor, String number) {
			String ob = null;
			switch(floor) {
			case "9":
				switch(number) {
				case "1": ob = "층 928호 앞 엘리베이터";
					break;
				case "2": ob = "층 901호 앞 엘리베이터";
					break;
				case "3": ob = "층 925호 앞 엘리베이터";
					break;
				}
				break;
			case "8":
				switch(number) {
				case "1": ob = "층 826호 앞 엘리베이터";
					break;
				case "2": ob = "층 801호 앞 엘리베이터";
					break;
				case "3": ob = "층 823호 앞 엘리베이터";
					break;
				}
				break;
			case "7":
				switch(number) {
				case "1": ob = "층 729호 앞 엘리베이터";
					break;
				case "2": ob = "층 701호 앞 엘리베이터";
					break;
				case "3": ob = "층 726호 앞 엘리베이터";
					break;
				}
				break;
			case "6":
				switch(number) {
				case "1": ob = "층 619호 앞 엘리베이터";
					break;
				case "2": ob = "층 602호 앞 엘리베이터";
					break;
				case "3": ob = "층 616호 앞 엘리베이터";
					break;
				}
				break;
			case "5":
				switch(number) {
				case "1": ob = "층 520호 앞 엘리베이터";
					break;
				case "2": ob = "층 502호 앞 엘리베이터";
					break;
				case "3": ob = "층 517호 앞 엘리베이터";
					break;
				}
				break;
			case "4":
				switch(number) {
				case "1": ob = "층 420호 앞 엘리베이터";
					break;
				case "2": ob = "층 402호 앞 엘리베이터";
					break;
				case "3": ob = "층 417호 앞 엘리베이터";
					break;
				}
				break;
			case "3":
				switch(number) {
				case "1": ob = "층 318호 앞 엘리베이터";
					break;
				case "2": ob = "층 301호 앞 엘리베이터";
					break;
				case "3": ob = "층 315호 앞 엘리베이터";
					break;
				}
				break;
			case "2":
				switch(number) {
				case "1": ob = "층 201호 앞 엘리베이터";
					break;
				case "2": ob = "층 Creative Complex 앞 엘리베이터";
					break;
				case "3": ob = "층 204호 앞 엘리베이터";
					break;
				}
				break;
			case "1":
				switch(number) {
				case "1": ob = "층 기숙사 방향 출입문쪽 엘리베이터";
					break;
				case "2": ob = "층 중앙계단 옆 엘리베이터";
					break;
				case "3": ob = "층 후문 방향 출입문쪽 엘리베이터";
					break;
				}
				break;
			case "지하 4":
				switch(number) {
				case "1": ob = "층 편의점 앞 엘리베이터";
					break;
				}
				break;
			case "지하 3":
				switch(number) {
				case "1": ob = "층 B306호 옆 엘리베이터";
					break;
				case "2": ob = "층 에스컬레이터 옆 엘리베이터";
					break;
				}
				break;
			case "지하 2":
				switch(number) {
				case "1": ob = "층 B208호 옆 엘리베이터";
					break;
				case "2": ob = "층 출입문쪽 엘리베이터";
					break;
				}
				break;
			case "지하 1":
				switch(number) {
				case "1": ob = "층 B113호 옆 엘리베이터";
					break;
				case "2": ob = "층 에스컬레이터 옆 엘리베이터";
					break;
				}
				break;
			}
			return ob;
		}
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
	    	case "e": //object = "층 엘리베이터";
	    		Replace r_e = new Replace();
	    		object = r_e.Replace_e(floor, number);
	    		number = "";
	    		break;
	    	case "s": //object = "층 계단";
	    		Replace r_s = new Replace();
	    		object = r_s.Replace_s(floor, number);
	    		number = "";
	    		break;
	    	case "t":
	    		object = "층 에스컬레이터";
	    		number = "";
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
