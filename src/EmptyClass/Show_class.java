package EmptyClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Show_class {
	private IntegerProperty ct;
	private StringProperty mon;
	private StringProperty tue;
	private StringProperty wed;
	private IntegerProperty thu;
	private IntegerProperty fri;

	public Show_class(IntegerProperty ct, StringProperty mon, StringProperty tue, StringProperty wed, IntegerProperty thu,
			IntegerProperty fri) {
		this.ct = ct;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
	}

	public IntegerProperty getclasstime() {
		return ct;
	}
	
	public StringProperty monday() {
		return mon;
	}

	public StringProperty tuesday() {
		return tue;
	}

	public StringProperty wednesday() {
		return wed;
	}

	public IntegerProperty thursday() {
		return thu;
	}

	public IntegerProperty friday() {
		return fri;
	}
}