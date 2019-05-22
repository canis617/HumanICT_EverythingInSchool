package EmptyClass;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {

	@FXML
	private Button JoinButton;

	@FXML
	private Button LoginButton;

	@FXML
	void loginbtn(ActionEvent event) { // LoginButton
		if (true) { // login check
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GUI.fxml"));
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.initStyle(StageStyle.DECORATED);
				stage.setTitle("Personal Scedule");
				stage.setScene(new Scene(root1));
				stage.show();
			} catch (Exception e) {
				System.out.println("cannot load new window");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
