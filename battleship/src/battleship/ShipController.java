package battleship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.stage.Stage;

public class ShipController implements Initializable {
		public Text msgs;
	public TextField file;
	public Button btn,filebtns;
	public int [][] pla_placement = new int [5][4]; //to keep the placement from the player.txt
	
	public void uploadButtonClicked() {
	String x = file.getText();
	//System.out.println(x);
	try (BufferedReader reader = new BufferedReader(new FileReader(new File("player_1.txt")))){//player_"+x+".txt")))) {

        String line;
       // while ((line = reader.readLine()) != null)
        for (int j= 0;j<5;j++)
        {
        	line = reader.readLine();
        	//System.out.println(line);
        	String[] arrSplit = line.split(","); //to split the numbers
	        for (int i=0; i < arrSplit.length; i++)
	        {
	          System.out.println(arrSplit[i]);
	          pla_placement[j][i]=Integer.parseInt(arrSplit[i]);
	        }
	        System.out.println("-");
        }

    } catch (IOException e) {
    	System.out.println("An error occured");
        e.printStackTrace();
    }
	
	}
	
	
	
	public void btnClick() {
		
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("BattleFile.fxml"));
		//	FXMLLoader loader = new FXMLLoader(getClass().getResource("BattleFile.fxml"));
			//BattleController battleController = loader.getController();
			
		//	Parent root = loader.load();
			Stage newStage = new Stage();
			
			Scene scene = new Scene(root,880,900);
			
			newStage.setScene(scene);
			newStage.setTitle("MediaLab Battleship");
			newStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//msgs.setText("ok");
	}

   
}
