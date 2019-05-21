package EmptyClass;

import java.awt.Label;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	private TableColumn<Show_class, Integer> THU;
	@FXML
	private TableColumn<Show_class, Integer> FRI;

	ObservableList<Show_class> myList = FXCollections.observableArrayList(
		new Show_class(new SimpleIntegerProperty(1), new SimpleStringProperty("mon"), new SimpleStringProperty("tue"), new SimpleStringProperty("wed"), new SimpleIntegerProperty(3), new SimpleIntegerProperty(4)),
		new Show_class(new SimpleIntegerProperty(2), new SimpleStringProperty("mon"), new SimpleStringProperty("tue"), new SimpleStringProperty("wed"), new SimpleIntegerProperty(3), new SimpleIntegerProperty(4))
	);

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		Classtime.setCellValueFactory(cellData -> cellData.getValue().getclasstime().asObject());
		MON.setCellValueFactory(cellData -> cellData.getValue().monday());
		TUE.setCellValueFactory(cellData -> cellData.getValue().tuesday());
		WED.setCellValueFactory(cellData -> cellData.getValue().wednesday());
		THU.setCellValueFactory(cellData -> cellData.getValue().thursday().asObject());
		FRI.setCellValueFactory(cellData -> cellData.getValue().friday().asObject());
		ClassTableView.setItems(myList);
	}

	public void print123(ActionEvent event) {
		System.out.println(123);
	}

	

}
