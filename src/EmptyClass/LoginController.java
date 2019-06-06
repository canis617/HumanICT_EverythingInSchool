package EmptyClass;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

    @FXML
    private TextField StudentID;

	@FXML
	private Button JoinButton;

	@FXML
	private Button LoginButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        LoginButton.setOnAction(event->handlebtnMain(event));
	}
	@FXML
	public boolean handlebtnMain(ActionEvent event) { // LoginButton
		String inputID  = StudentID.getText();
		boolean valid = false;

		ClassInfoDB test = new ClassInfoDB();
		List<Map> studentList = test.GetStudentList();

		Map<String,String> current;
		for(int i=0; i<studentList.size();i++){
			current = studentList.get(i);
			String value = current.get("studentID");
			if(value.equals(inputID)){
				valid = true;
				break;
			}
		}

		if(valid) {
			try {
				CurrentStudentInfo.studnetID.add(inputID);


				Stage stage = new Stage();

				stage.setTitle("time table");
				URL fxmlPath = new File("src/EmptyClass/GUI.fxml").toURL();

				FXMLLoader loader = new FXMLLoader();
				Parent layout = (AnchorPane) loader.load(fxmlPath);
				Scene sc = new Scene(layout);
				stage.setScene(sc);
				stage.show();
				// 메인 창 닫아주기
				Stage main = (Stage) LoginButton.getScene().getWindow();
				main.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return true;
		}

		return false;

	}

}
