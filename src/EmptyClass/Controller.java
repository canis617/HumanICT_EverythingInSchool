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
	private TableColumn<Show_class, Integer> Classtime;
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
	private TextField classday_label;
	@FXML
	private TextField classtime_label;
	@FXML
	private ChoiceBox<String> classlist_Box;

	@FXML
	private Button search;
	@FXML
	private Button add_btn;
	@FXML
	private Button del_btn;
	@FXML
	private Button find_path_btn;

	ObservableList<Show_class> myList = FXCollections.observableArrayList(
			new Show_class(new SimpleIntegerProperty(1), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(2), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(3), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(4), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(5), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(6), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(7), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(8), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)),
			new Show_class(new SimpleIntegerProperty(9), new SimpleStringProperty(null), new SimpleStringProperty(null),
					new SimpleStringProperty(null), new SimpleStringProperty(null), new SimpleStringProperty(null)));

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {

		ClassInfoDB db = new ClassInfoDB();

		//get student id from login
		if(CurrentStudentInfo.studnetID.isEmpty()){
			System.exit(-1);
		}
		int num = Integer.parseInt(CurrentStudentInfo.studnetID.get(0));

		// make init Table
		List<Map> cl = db.GetSchedule(num);
		Map<String, String> element = cl.get(0);
		String[][] table = new String[20][5];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				table[i][j] = null;
			}
		}

		for (int i = 0; i < cl.size(); i++) {
			element = cl.get(i);
			String className = element.get("className");
			String day = element.get("day");
			int starttime = (Integer.parseInt(element.get("starttime")) - 9);
			// 9:00 -> 0
			float lastingtime = Float.parseFloat(element.get("lastingtime"));
			switch (day) {
			case "mon":
				for (int j = 0; j < (lastingtime * 2 + starttime); j++) {
					table[j][0] = className;
				}
				break;
			case "tues":
				for (int j = 0; j < (lastingtime * 2 + starttime); j++) {
					table[j][1] = className;
				}
				break;
			case "wed":
				for (int j = 0; j < (lastingtime * 2 + starttime); j++) {
					table[j][2] = className;
				}
				break;
			case "thurs":
				for (int j = 0; j < (lastingtime * 2 + starttime); j++) {
					table[j][3] = className;
				}
				break;
			case "fri":
				for (int j = 0; j < (lastingtime * 2 + starttime); j++) {
					table[j][4] = className;
				}
				break;
			default:
				System.out.println("day search error");
				break;
			}
		}

		// ClassTableView.getItems().add(e);
		Classtime.setCellValueFactory(cellData -> cellData.getValue().getclasstime().asObject());
		MON.setCellValueFactory(cellData -> cellData.getValue().monday());
		TUE.setCellValueFactory(cellData -> cellData.getValue().tuesday());
		WED.setCellValueFactory(cellData -> cellData.getValue().wednesday());
		THU.setCellValueFactory(cellData -> cellData.getValue().thursday());
		FRI.setCellValueFactory(cellData -> cellData.getValue().friday());
		ClassTableView.setItems(myList);

		for (int i = 0; i < 20; i++) {
			
			ClassTableView.getItems().add(new Show_class(new SimpleIntegerProperty(i), new SimpleStringProperty(table[i][0]), new SimpleStringProperty(table[i][1]),
					new SimpleStringProperty(table[i][2]), new SimpleStringProperty(table[i][3]), new SimpleStringProperty(table[i][4])));
			
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
	}

	public void print123(ActionEvent event) {
		System.out.println(123);
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
}
