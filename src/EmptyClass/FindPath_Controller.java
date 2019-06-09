package EmptyClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FindPath_Controller implements Initializable {
	@FXML
	private TextField start_label;

	@FXML
	private TextField arrive_label;

	@FXML
	private ListView<String> path_list;

	@FXML
	private Button search_path_btn;

	@FXML
	void search_path_btn(ActionEvent event) throws IOException {
		print_find_path();
	}

	void enterAction(KeyEvent e) throws IOException {
		if (e.getCode().equals(KeyCode.ENTER)) {
			print_find_path();
		}
	}

	ObservableList<String> myList = FXCollections.observableArrayList();
	
	public void print_find_path() throws IOException {
		String start;
		String arrive;
		start = start_label.getText().toString();
		arrive = arrive_label.getText().toString();
		if(!start.equals("") && !arrive.equals("")) {
			/// Main.java
			Graph g = new Graph();
			String[] floorList = new String[13];
			// read index list
			try {
				BufferedReader br = new BufferedReader(new FileReader("data/floorindex.txt"));
				String line = null;
				int index = 0;
				while ((line = br.readLine()) != null) {
					floorList[index++] = new String(line);
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			for (int i = 0; i < floorList.length; i++) {
				g.add_floor(floorList[i]);
			}
			
			FindPath fp = new FindPath(g);
			Stack<Edge> result = fp.find_path(start, arrive, false);
			Edge tempEdge;
			Replace replacestr = new Replace();
			int count = 0;
			boolean flag = false;
			String i_st = "";
			String i_end = "";
			
			path_list.setItems(myList);
			path_list.getItems().removeAll(myList);
			
			for (int i = 0; !result.empty(); i++) {
				tempEdge = result.pop();
				if (!flag && tempEdge.get_edge_end().substring(1, 2).equals("i")) {
					i_st = tempEdge.get_edge_start();
					flag = true;
				}
				if (flag == true && !tempEdge.get_edge_end().substring(1, 2).equals("i")) {
					i_end = tempEdge.get_edge_end();
					flag = false;
				}
				if (flag) {
					// pass
				} else {
					if (!i_end.equals("")) {
						path_list.getItems().add(replacestr.Replace(i_st) + " -> " + replacestr.Replace(i_end));
						i_st = "";
						i_end = "";
					} else {
						path_list.getItems().add(replacestr.Replace(tempEdge.get_edge_start()) + " -> "
								+ replacestr.Replace(tempEdge.get_edge_end()) + "\n");
					}
				}

				count += tempEdge.get_time_weight();
			}
			path_list.getItems().add("Estimated time :   " + count / 60 + "min   " + count % 60 + "sec");
		}else {
			System.out.println("please input start & arrive");
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
