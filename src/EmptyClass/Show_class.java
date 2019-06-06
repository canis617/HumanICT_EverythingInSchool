package EmptyClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Show_class {
	private StringProperty ct;
	private StringProperty mon;
	private StringProperty tue;
	private StringProperty wed;
	private StringProperty thu;
	private StringProperty fri;

	public Show_class(StringProperty ct, StringProperty mon, StringProperty tue, StringProperty wed, StringProperty thu,
			StringProperty fri) {
		this.ct = ct;
		this.mon = mon;
		this.tue = tue;
		this.wed = wed;
		this.thu = thu;
		this.fri = fri;
	}

	public StringProperty getclasstime() {
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

	public StringProperty thursday() {
		return thu;
	}

	public StringProperty friday() {
		return fri;
	}
}