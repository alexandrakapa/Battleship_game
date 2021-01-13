package battleship;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MenuBar;

import java.util.Random;

//import application.AlertBoxes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class GUI extends Application {

	Stage window;
	BorderPane layout;
	Scene mainscene;//, enemyShipsScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	int rowNum = 10;
	int colNum = 10;
	int gridHeight = 10;
	int gridWidth = 10;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setTitle("MediaLab Battleship");

		
		//App menu
		Menu appMenu  = new Menu("Application");
		
		
		appMenu.getItems().add(new MenuItem("Start"));
		appMenu.getItems().add(new MenuItem("Load"));
		appMenu.getItems().add(new MenuItem("Exit"));
		
		//Details menu
		Menu detMenu  = new Menu("Details");
				
				//menu items
		
		detMenu.getItems().add(new MenuItem("Enemy Ships..."));
		detMenu.getItems().add(new MenuItem("Player Shots..."));
		detMenu.getItems().add(new MenuItem("Enemy Shots..."));
		
		//Main menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(appMenu,detMenu);
		
		layout = new BorderPane();
		layout.setTop(menuBar);
		mainscene = new Scene(layout, 400, 300);
		
		
		
		window.setScene(mainscene);
		window.show();
		
	}
	
}