package EmptyClass;

public class Replace{
	// facility = floor + object + number (7c729 = 7+c+729)
		
		private String floor;
		private String object;
		private String number;
	 
	    public String Replace(String name){        
	    	String new_facility;
	    	floor = name.substring(0,1);	// ó�� ���ڴ� �׻� ����
	    	object = name.substring(1,2);	// ó�� ���� ���ڴ� �׻� � �������� (ex. /e/s/t/i/c)
	    	number = name.substring(2);		// �� ���� ����
	    	
	    	switch(floor) {
	    	case "D": floor = "���� 4";
	   			break;
	   		case "C": floor = "���� 3";
	   			break;
	   		case "B": floor = "���� 2";
	  			break;
	   		case "A": floor = "���� 1";
	   			break;
	   		default : 
	   			break;    			
	   		}
	    	
	    	switch(object) {
	    	case "e": object = "�� ����������";
	    		break;
	    	case "s": object = "�� ���";
	    		break;
	    	case "t": object = "�� �����÷�����";
	    		break;
	    	case "i": object = "�� ������";
                break;
            case "o": object = "�� ���Թ�";
                break;
	    	case "c":	
	    		object = "�� ";
	    		if (name.charAt(0) != 'D' || number.matches("(^[0-9]*$)")) { 
	    			// object�� 'c'�϶� D���� ���ؼ��� ���ǽ��� ���� ������ �ڿ� 'ȣ'�� ���� ������ ����
	    			// Ȥ�� �� �ڿ� ���� ���� �ΰ�쿡�� ������ ���ǽ���
	    			number = number + "ȣ";
	    		}
	    		break;
	    	}
	    		new_facility = floor + object + number;
				return new_facility;
	    	}  	    	
}