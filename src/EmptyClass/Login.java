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
		//Java GUI create
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		launch(args);
    }

	public void start(Stage primaryStage) throws Exception {
        /* 
         * Scene Builder �� �̿��Ͽ� ȭ���� ����� �ڹٿ� fxml ������ ��������ش�.
         * Parent Ŭ������ ���Ǵ� ������ ����.
         * The base class for all nodes that have children in the scene graph.
         * �� ��� ���̵�(������Ʈ��)�� �Ҽӵ� �θ�(Scene) ����?          
         */
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Class Program");
        primaryStage.setScene(scene);
        primaryStage.show();
        /*
         * Scene Builder �� Window -> Preference -> JavaFX �� ��ġ�� ����ϸ�
         * fxml ������ ��Ŭ�� �Ͽ� Open with Scene Builder �� �����ؼ� ���� �����ϴ�.
         */
    }

}
