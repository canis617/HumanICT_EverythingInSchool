package EmptyClass;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Login extends Application {

    public Login() {
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        /*
         * Scene Builder 를 이용하여 화면을 만들고 자바에 fxml 파일을 연결시켜준다.
         * Parent 클래스의 정의는 다음과 같다.
         * The base class for all nodes that have children in the scene graph.
         * 즉 모든 아이들(컨포넌트들)이 소속될 부모(Scene) 정도?
         */
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
         * Scene Builder 는 Window -> Preference -> JavaFX 에 위치를 등록하면
         * fxml 파일을 우클릭 하여 Open with Scene Builder 를 선택해서 편집 가능하다.
         */
    }

}