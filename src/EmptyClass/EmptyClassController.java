package EmptyClass;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmptyClassController implements Initializable{
    @FXML
    private ChoiceBox<String> Day;
    @FXML
    private TextField Time;

    @FXML
    private ChoiceBox<String> BuildingList;

    @FXML
    private ChoiceBox<String> OrderBy;

    @FXML
    private ListView<String> EmptyClassList;

    @FXML
    private Button SearchBtn;
    @FXML
    private Button CloseBtn;
    @FXML
    private Button GetPathBtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ClassInfoDB db = new ClassInfoDB();
        List<Map> buildingList = db.GetBuildingList();

        while(!buildingList.isEmpty()){
            String building = (String) buildingList.remove(0).get("building");
            BuildingList.getItems().add(building);
        }

        Day.getItems().add("mon");
        Day.getItems().add("tues");
        Day.getItems().add("wed");
        Day.getItems().add("thurs");
        Day.getItems().add("fri");

        OrderBy.getItems().add("ascending");
        OrderBy.getItems().add("descending");


        String currentDay = "";
        Calendar cal = Calendar.getInstance() ;
        int dayNum = cal.get(Calendar.DAY_OF_WEEK) ;
        switch(dayNum){
            case 1:
                currentDay = "mon";
                break ;
            case 2:
                currentDay = "tues";
                break ;
            case 3:
                currentDay = "wed";
                break ;
            case 4:
                currentDay = "thurs";
                break ;
            case 5:
                currentDay = "fri";
                break ;
            case 6:
                currentDay = "sat";
                break ;
            case 7:
                currentDay = "sun";
                break ;
        }

        System.out.println(cal.get(cal.HOUR_OF_DAY) + (double)cal.get(cal.MINUTE)/60.0);
        double currentTime = cal.get(cal.HOUR_OF_DAY) + (double)cal.get(cal.MINUTE) / 60.0;

        List<Map> emptyclassList = db.GetEmptyClass(310, currentDay, currentTime);

        EmptyClassList.setItems(FXCollections.observableArrayList());
        while(!emptyclassList.isEmpty()){
            EmptyClassList.getItems().add((String) emptyclassList.remove(0).get("room"));
        }
    }

    @FXML
    boolean search_path_btn(ActionEvent event) throws IOException {
        ClassInfoDB db = new ClassInfoDB();
        int building = Integer.parseInt(BuildingList.getValue());
        String day = Day.getValue();
        double time = Double.parseDouble(Time.getText());
        List<Map> emptyclassList = db.GetEmptyClass(building, day, time);

        EmptyClassList.getItems().clear();
        if(OrderBy.getValue() != null){
            if(OrderBy.getValue().equals("descending")){
                while(!emptyclassList.isEmpty()){
                    EmptyClassList.getItems().add((String) emptyclassList.remove(emptyclassList.size()-1).get("room") + "\n");
                }
                return true;
            }
        }
        while(!emptyclassList.isEmpty()){
            EmptyClassList.getItems().add((String) emptyclassList.remove(0).get("room") + "\n");
        }
        return true;
    }

    @FXML
    void close_btn(ActionEvent event) throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
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

        // get a handle to the stage
        Stage stage = (Stage) CloseBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}

