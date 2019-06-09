package EmptyClass;

import java.awt.Label;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.*;

public class Controller implements Initializable {
	@FXML
	private TableView<Show_class> ClassTableView;
	@FXML
	private TableColumn<Show_class, String> Classtime;
	@FXML
	private TableColumn<Show_class, String> MON;
	@FXML
	private TableColumn<Show_class, String> TUE;
	@FXML
	private TableColumn<Show_class, String> WED;
	@FXML
	private TableColumn<Show_class, String> THU;
	@FXML
	private TableColumn<Show_class, String> FRI;

	@FXML
	private TextField classname_label;
	@FXML
	private ChoiceBox<String> classday_label;
	@FXML
	private TextField classtime_label;
	@FXML
	private ChoiceBox<String> classlist_Box;

	@FXML
	private Button search;
	@FXML
	private Button add_btn;
	@FXML
	private TextField delete_name;
	@FXML
	private Button del_btn;
	@FXML
	private Button find_path_btn;
	@FXML
	private Button emptyclass_btn;

	ObservableList<Show_class> myList = FXCollections.observableArrayList();

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		ClassInfoDB db = new ClassInfoDB();

		// get student id from login
		if (CurrentStudentInfo.studnetID.isEmpty()) {
			System.exit(-1);
		}
		int num = Integer.parseInt(CurrentStudentInfo.studnetID.get(0));

		// make init Table
		List<Map> cl = db.GetSchedule(num);
		String[][] table = new String[20][5];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				table[i][j] = null;
			}
		}

		table = return_table(table, cl);

		Classtime.setCellValueFactory(cellData -> cellData.getValue().getclasstime());
		MON.setCellValueFactory(cellData -> cellData.getValue().monday());
		TUE.setCellValueFactory(cellData -> cellData.getValue().tuesday());
		WED.setCellValueFactory(cellData -> cellData.getValue().wednesday());
		THU.setCellValueFactory(cellData -> cellData.getValue().thursday());
		FRI.setCellValueFactory(cellData -> cellData.getValue().friday());
		ClassTableView.setItems(myList);

		for (int i = 0; i < 20; i++) {
			String j;
			if (i % 2 == 0) {
				j = Integer.toString(9 + i / 2);
			} else {
				j = null;
			}
			ClassTableView.getItems()
					.add(new Show_class(new SimpleStringProperty(j), new SimpleStringProperty(table[i][0]),
							new SimpleStringProperty(table[i][1]), new SimpleStringProperty(table[i][2]),
							new SimpleStringProperty(table[i][3]), new SimpleStringProperty(table[i][4])));
		}

		String str = "";
		String sql;
		sql = String.format("select * from ClassInfo where building=310 order by room");
		List<Map> allClass = db.GetResultMap(sql);
		Map<String, String> current = allClass.get(0);
		for (int i = 0; i < allClass.size(); i++) {
			current = allClass.get(i);
			for (String key : current.keySet()) {
				String value = current.get(key);
				// System.out.println(value + "\t\t");
				str = str + "\t" + value;
			}
			classlist_Box.getItems().add(str);
			str = "";
		}
		classday_label.getItems().add(null);
		classday_label.getItems().add("mon");
		classday_label.getItems().add("tues");
		classday_label.getItems().add("wed");
		classday_label.getItems().add("thurs");
		classday_label.getItems().add("fri");
	}

	public boolean check_readd(String line, List<Map> cl) {
		Map<String, String> element;
		String str[] = new String[8];
		str = line.split("\t");
		// str[2] = lastingtime
		// str[5] = starttime
		// str[6] = day
		float new_starttime = Float.parseFloat(str[5]);
		float new_lastingtime = Float.parseFloat(str[2]);
		float new_bound = new_starttime + new_lastingtime;

		for (int i = 0; i < cl.size(); i++) {
			element = cl.get(i);
			String id = element.get("classID");
			String day = element.get("day");
			float starttime = Float.parseFloat(element.get("starttime"));
			float lasttingtime = Float.parseFloat(element.get("lastingtime"));
			float bound = starttime + lasttingtime;

			if (id.equals(str[1])) {
				System.out.println("re name add");
				return false;
			}
			if (day.equals(str[6])) {
				if (!(new_bound <= starttime || bound <= new_starttime)) {
					System.out.println("re time add");
					return false;
				}
			}
		}

		return true;
	}

	public String[][] return_table(String[][] table, List<Map> cl) {
		Map<String, String> element = cl.get(0);

		for (int i = 0; i < cl.size(); i++) {
			element = cl.get(i);
			String className = element.get("className");
			String day = element.get("day");
			int starttime = (int) ((Float.parseFloat(element.get("starttime")) - 9) * 2);
			// 9:00 -> 0
			float lastingtime = Float.parseFloat(element.get("lastingtime"));
			// System.out.println(className + " " + starttime + " " + lastingtime);
			switch (day) {
			case "mon":
				for (int j = 0; j < (lastingtime * 2); j++) {
					table[starttime + j][0] = className;
				}
				break;
			case "tues":
				for (int j = 0; j < (lastingtime * 2); j++) {
					table[starttime + j][1] = className;
				}
				break;
			case "wed":
				for (int j = 0; j < (lastingtime * 2); j++) {
					table[starttime + j][2] = className;
				}
				break;
			case "thurs":
				for (int j = 0; j < (lastingtime * 2); j++) {
					table[starttime + j][3] = className;
				}
				break;
			case "fri":
				for (int j = 0; j < (lastingtime * 2); j++) {
					table[starttime + j][4] = className;
				}
				break;
			default:
				System.out.println("day search error");
				break;
			}
		}

//		for (int i = 0; i < 10; i++) {
//			for (int j = 0; j < 5; j++) {
//				System.out.print(table[i][j] + "\t");
//			}
//			System.out.println("");
//		}
		return table;
	}

	@FXML
	void add_btn(ActionEvent event) {
		ClassInfoDB db = new ClassInfoDB();
		int num = Integer.parseInt(CurrentStudentInfo.studnetID.get(0));

		String str[] = new String[8];
		String line = classlist_Box.getValue();

		if (line == null) {
			System.out.println("add button input is null");
		} else {
			str = line.split("\t");
			// str[1] = classID
			// str[4] = classNum

			List<Map> cl = db.GetSchedule(num);

			if (check_readd(line, cl)) {
				try {
					db.SetSchedule(Integer.parseInt(CurrentStudentInfo.studnetID.get(0)), Integer.parseInt(str[1]),
							Integer.parseInt(str[4]));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				System.out.println("add reject!");
			}

			String[][] table = new String[20][5];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					table[i][j] = null;
				}
			}
			cl = db.GetSchedule(num);
			table = return_table(table, cl);

			myList.removeAll(myList);

			Classtime.setCellValueFactory(cellData -> cellData.getValue().getclasstime());
			MON.setCellValueFactory(cellData -> cellData.getValue().monday());
			TUE.setCellValueFactory(cellData -> cellData.getValue().tuesday());
			WED.setCellValueFactory(cellData -> cellData.getValue().wednesday());
			THU.setCellValueFactory(cellData -> cellData.getValue().thursday());
			FRI.setCellValueFactory(cellData -> cellData.getValue().friday());
			ClassTableView.setItems(myList);

			for (int i = 0; i < 20; i++) {
				String j;
				if (i % 2 == 0) {
					j = Integer.toString(9 + i / 2);
				} else {
					j = null;
				}

				ClassTableView.getItems()
						.add(new Show_class(new SimpleStringProperty(j), new SimpleStringProperty(table[i][0]),
								new SimpleStringProperty(table[i][1]), new SimpleStringProperty(table[i][2]),
								new SimpleStringProperty(table[i][3]), new SimpleStringProperty(table[i][4])));
			}

		}
	}

	@FXML
	void del_btn(ActionEvent event) {
		String str = delete_name.getText();
		if (str.equals("")) {
			System.out.println("no input delete name!");
		} else {
			ClassInfoDB db = new ClassInfoDB();
			db.DeleteSchedule(CurrentStudentInfo.studnetID.get(0), str);

			String[][] table = new String[20][5];
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 5; j++) {
					table[i][j] = null;
				}
			}
			int num = Integer.parseInt(CurrentStudentInfo.studnetID.get(0));
			List<Map> cl = db.GetSchedule(num);
			cl = db.GetSchedule(num);
			table = return_table(table, cl);

			myList.removeAll(myList);

			Classtime.setCellValueFactory(cellData -> cellData.getValue().getclasstime());
			MON.setCellValueFactory(cellData -> cellData.getValue().monday());
			TUE.setCellValueFactory(cellData -> cellData.getValue().tuesday());
			WED.setCellValueFactory(cellData -> cellData.getValue().wednesday());
			THU.setCellValueFactory(cellData -> cellData.getValue().thursday());
			FRI.setCellValueFactory(cellData -> cellData.getValue().friday());
			ClassTableView.setItems(myList);

			for (int i = 0; i < 20; i++) {
				String j;
				if (i % 2 == 0) {
					j = Integer.toString(9 + i / 2);
				} else {
					j = null;
				}

				ClassTableView.getItems()
						.add(new Show_class(new SimpleStringProperty(j), new SimpleStringProperty(table[i][0]),
								new SimpleStringProperty(table[i][1]), new SimpleStringProperty(table[i][2]),
								new SimpleStringProperty(table[i][3]), new SimpleStringProperty(table[i][4])));
			}

		}
	}

	@FXML
	void select(ActionEvent event) {
		String classname = classname_label.getText();
		String classday = classday_label.getValue();
		String classtime = classtime_label.getText();
		String sql = String.format("select * from ClassInfo where building=310 order by room");

		if (classname.equals("") && (classday == null) && classtime.equals("")) {
			System.out.println("don't have search input");
		} else {
			if (!classname.equals("") && (classday == null) && classtime.equals("")) { // only classname
				sql = String.format("select * from ClassInfo where building=310 and className='%s' order by room",
						classname);
			} else if (classname.equals("") && !(classday == null) && classtime.equals("")) { // only day
				sql = String.format("select * from ClassInfo where building=310 and day='%s' order by room", classday);
			} else if (classname.equals("") && (classday == null) && !classtime.equals("")) { // only classtime
				sql = String.format("select * from ClassInfo where building=310 and starttime='%s' order by room",
						classtime);
			} else if (!classname.equals("") && !(classday == null) && classtime.equals("")) { // classname, classday
				sql = String.format(
						"select * from ClassInfo where building=310 and className='%s' and day='%s' order by room",
						classname, classday);
			} else if (!classname.equals("") && (classday == null) && !classtime.equals("")) { // classname, classtime
				sql = String.format(
						"select * from ClassInfo where building=310 and className='%s' and starttime='%s' order by room",
						classname, classtime);
			} else if (classname.equals("") && !(classday == null) && !classtime.equals("")) { // classday, classtime
				sql = String.format(
						"select * from ClassInfo where building=310 and day='%s' and starttime='%s' order by room",
						classday, classtime);
			} else if (!classname.equals("") && !(classday == null) && !classtime.equals("")) { // classname, classday,
																								// classtime
				sql = String.format(
						"select * from ClassInfo where building=310 and className='%s' and day='%s' and starttime='%s' order by room",
						classname, classday, classtime);
			}

			ClassInfoDB db = new ClassInfoDB();

			String str = "";
			List<Map> allClass = db.GetResultMap(sql);
			classlist_Box.getItems().clear();
			if (allClass.size() == 0) {
				classlist_Box.getItems().add("nothing");
			} else {
				Map<String, String> current = allClass.get(0);
				for (int i = 0; i < allClass.size(); i++) {
					current = allClass.get(i);
					for (String key : current.keySet()) {
						String value = current.get(key);
						// System.out.println(value + "\t\t");
						str = str + "\t" + value;
					}
					classlist_Box.getItems().add(str);
					str = "";
				}
			}
		}
	}

	@FXML
	void find_path_btn(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FindPath_GUI.fxml"));
			Parent root_ = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Find Path");
			stage.setScene(new Scene(root_));
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("cannot load new window");
		}
	}
	@FXML
	void emptyclass_btn(ActionEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmptyClass_GUI.fxml"));
			Parent root_ = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setTitle("Empty Class");
			stage.setScene(new Scene(root_));
			stage.show();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("cannot load new window");
		}
	}
}
