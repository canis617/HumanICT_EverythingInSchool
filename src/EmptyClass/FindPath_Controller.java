package EmptyClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FindPath_Controller implements Initializable {
    @FXML
    private TextField start_label;

    @FXML
    private TextField arrive_label;

    @FXML
    private TextArea pathprint_area;

    @FXML
    private Button search_path_btn;

    @FXML          
    void search_path_btn(ActionEvent event) throws IOException {
    	print_find_path();
    }
    
    void enterAction(KeyEvent e) throws IOException {
    	if(e.getCode().equals(KeyCode.ENTER)) {
    		print_find_path();
    	}
    }
    
    public void print_find_path() throws IOException {
    	String start;
    	String arrive;
    	start = start_label.getText().toString();
    	arrive = arrive_label.getText().toString();
    	
    	///Main.java
    	Graph g = new Graph();
		String[] floorList = new String[13];
		//read index list
		try {
			BufferedReader br = new BufferedReader(new FileReader("data/floorindex.txt"));
			String line = null;
			int index = 0;
			while ((line = br.readLine()) != null) {
				floorList[index++] = new String(line);
			}
			br.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}

		for(int i=0; i<floorList.length;i++){
			g.add_floor(floorList[i]);
		}
		
		FindPath fp = new FindPath(g);
		Stack<Edge> result = fp.find_path(start, arrive);
		Edge tempEdge;
		for(int i = 0; !result.empty();i++){
			tempEdge = result.pop();
			pathprint_area.appendText(tempEdge.get_edge_start()+" -> "+tempEdge.get_edge_end()+" = "+tempEdge.get_time_weight() + "\n");
		}
    }
    
   
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
}
