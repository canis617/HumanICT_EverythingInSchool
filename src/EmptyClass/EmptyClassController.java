package EmptyClass;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

public class EmptyClassController implements Initializable{
    @FXML
    private ChoiceBox Day;
    @FXML
    private ChoiceBox Time;

    @FXML
    private ChoiceBox OrderBy;

    @FXML
    private TextArea EmptyClassList;

    @FXML
    private Button CloseBtn;
    @FXML
    private Button GetPathBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Day.getItems().add("Mon");
        Day.getItems().add("Tues");
        Day.getItems().add("Wed");
        Day.getItems().add("Thurs");
        Day.getItems().add("Fri");

        Time.getItems().add("09:00");
        Time.getItems().add("09:30");
        Time.getItems().add("10:00");
        Time.getItems().add("10:30");
        Time.getItems().add("11:00");
        Time.getItems().add("11:30");
        Time.getItems().add("12:00");
        Time.getItems().add("12:30");
        Time.getItems().add("13:00");
        Time.getItems().add("13:30");
        Time.getItems().add("14:00");
        Time.getItems().add("14:30");
        Time.getItems().add("15:00");
        Time.getItems().add("15:30");
        Time.getItems().add("16:00");
        Time.getItems().add("16:30");
        Time.getItems().add("17:00");
        Time.getItems().add("17:30");
        Time.getItems().add("18:00");
        Time.getItems().add("18:30");
    }


}

