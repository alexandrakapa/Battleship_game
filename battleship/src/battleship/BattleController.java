package battleship;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;




public class BattleController implements Initializable {
public TextField file = new TextField(); //to read the file id
public int [][] pla_placement = new int [5][4]; //to keep the placement from the player.txt
public int [][] comp_placement = new int [5][4]; //to keep the placement from the enemy.txt
public TextField row;	
public TextField column;
public Button shootButton;
public Text compact,plact,compon,plapon,comhit,plahit,msg,textmsg,moves;
Rectangle [][] playergrid = new Rectangle[11][11]; //we use it for making the grid of the player
Rectangle [][] computergrid = new Rectangle[11][11]; //we use it to make the grid of the computer
public Rectangle p00,p01,p02,p03,p04,p05,p06,p07,p08,p09,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,p41,p42,p43,p44,p45,p46,p47,p48,p49,p50,p51,p52,p53,p54,p55,p56,p57,p58,p59,p60,p61,p62,p63,p64,p65,p66,p67,p68,p69;
public Rectangle p70,p71,p72,p73,p74,p75,p76,p77,p78,p79,p80,p81,p82,p83,p84,p85,p86,p87,p88,p89,p90,p91,p92,p93,p94,p95,p96,p97,p98,p99;
public Rectangle c00,c01,c02,c03,c04,c05,c06,c07,c08,c09,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32,c33,c34,c35,c36,c37,c38,c39,c40,c41,c42,c43,c44,c45,c46,c47,c48,c49,c50,c51,c52,c53,c54,c55,c56,c57,c58,c59,c60,c61,c62,c63,c64,c65,c66,c67,c68,c69;
public Rectangle c70,c71,c72,c73,c74,c75,c76,c77,c78,c79,c80,c81,c82,c83,c84,c85,c86,c87,c88,c89,c90,c91,c92,c93,c94,c95,c96,c97,c98,c99;
int k = 0; //to know the computers moves
double com_successful = 0; //to help us count the success rate for computer
long r =  Math.round( Math.random() ); //we produce a random number (either 0 or 1) to see who plays first
long oldr = r;
int j = 0; //to know the players moves
int movesleft = 40;
double pla_successful = 0; //to help us count the success rate for player
double player_success_rate,computer_success_rate; //the percentages of successful hits
Label errorLabel = new Label("");
Label playerLabel = new Label("Player's last five shots:");
Label computerLabel = new Label("Computer's last five shots:");
Label enemyShipsLabel = new Label("Condition of enemy's ships:");

Label carrierCondition = new Label("Not sunk!");
Label battleshipCondition = new Label("Not sunk!");
Label cruiserCondition = new Label("Not sunk!");
Label submarineCondition = new Label("Not sunk!");
Label destroyerCondition = new Label("Not sunk!");

Label clarificationsRes = new Label("Hit : 1, Miss : 0");
Label clarifications0 = new Label("Type 0 : Empty spot");
Label clarifications1 = new Label("Type 1 : Carrier");
Label clarifications2 = new Label("Type 2 : Battleship");
Label clarifications3 = new Label("Type 3 : Cruiser");
Label clarifications4 = new Label("Type 4 : Submarine");
Label clarifications5 = new Label("Type 5 : Destroyer");
Label shot_five = new Label("5th shot:");
Label shot_four = new Label("4th shot:");
Label shot_three = new Label("3rd shot:");
Label shot_two = new Label("2nd shot:");
Label shot_one = new Label("1st shot:");

//for the player
Label pla_xy_five = new Label(""); //for the 5th coordinates of the player (i.e the last)
Label pla_res_five = new Label(""); //for the result or the player
Label pla_type_five = new Label(""); //for the type of the player 

Label pla_xy_four = new Label(""); //for the 4th coordinates of the player
Label pla_res_four = new Label(""); //for the result or the player
Label pla_type_four = new Label(""); //for the type of the player 

Label pla_xy_three = new Label(""); //for the 3d coordinates of the player
Label pla_res_three = new Label(""); //for the result or the player
Label pla_type_three = new Label(""); //for the type of the player 

Label pla_xy_two = new Label(""); //for the 2nd coordinates of the player
Label pla_res_two = new Label(""); //for the result or the player
Label pla_type_two = new Label(""); //for the type of the player 

Label pla_xy_one = new Label(""); //for the 1st coordinates of the player
Label pla_res_one = new Label(""); //for the result or the player
Label pla_type_one = new Label(""); //for the type of the player 

//for the computer
Label comp_xy_five = new Label(""); //for the 5th coordinates of the player (i.e the last)
Label comp_res_five = new Label(""); //for the result or the player
Label comp_type_five = new Label(""); //for the type of the computer 

Label comp_xy_four = new Label(""); //for the 4th coordinates of the computer
Label comp_res_four = new Label(""); //for the result or the computer
Label comp_type_four = new Label(""); //for the type of the computer 

Label comp_xy_three = new Label(""); //for the 3d coordinates of the computer
Label comp_res_three = new Label(""); //for the result or the computer
Label comp_type_three = new Label(""); //for the type of the computer 

Label comp_xy_two = new Label(""); //for the 2nd coordinates of the computer
Label comp_res_two = new Label(""); //for the result or the computer
Label comp_type_two = new Label(""); //for the type of the computer 

Label comp_xy_one = new Label(""); //for the 1st coordinates of the computer
Label comp_res_one = new Label(""); //for the result or the computer
Label comp_type_one = new Label(""); //for the type of the computer 

public Label [][] plaLastFive = new Label [5][3]; //for the last five player shots
public Label [][] compLastFive = new Label [5][3]; //for the last five computer shots

Grid playerGrid = new Grid(); //Initialize the two grids
Grid computerGrid = new Grid();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Initialize the grid:
		computerGridMap();
		playerGridMap();
		plaLastFiveMap(); //for the last five shoots
		compLastFiveMap();
		shootButton.setDisable(true); //we want them disabled at the start
		row.setDisable(true);
		column.setDisable(true);
		textmsg.setText("");
		msg.setText("Please load a file!");
		msg.setFill(Color.DARKGOLDENROD);
		msg.setLayoutX(330);
		moves.setText("Moves left: " + movesleft );
	}
	


	public void ShootButtonClicked() {
		if (r == 1) { //player plays first
			msg.setFill(Color.DODGERBLUE);
			msg.setLayoutX(208);
			String rowc = row.getText();
			String columnc = column.getText();
			int rowi = Integer.parseInt(rowc);
			int columni = Integer.parseInt(columnc);
			System.out.println("You have shot at (" + rowi + "," + columni + ")");
			computerGrid.shoot(rowi, columni); //the player shoots
			movesleft--;
			moves.setText("Moves left: " + movesleft );
			if (computerGrid.playerLastFive[j][2] == 1) //then player has a hit and must make the computerGrid cell red
			{
				computergrid[rowi][columni].setFill(Color.DARKRED);
				if (computerGrid.shipsSunk[computerGrid.ships[rowi][columni].getType() - 1] == true)
					{
					msg.setFill(Color.FORESTGREEN);
					msg.setText("You have sunken a ship,congratulations!");
					}
				else msg.setText("Your hit was succesful,congratulations!");
				pla_successful++; //we increment the counter of successful hits
			}
			else {	//we have a miss
				computergrid[rowi][columni].setFill(Color.BLACK);	
				msg.setFill(Color.MAROON);
				msg.setText("Your hit was unsuccesful,try again!");
			}
			row.setText("");
			column.setText("");
			playerGrid.computerShoot(); //the computer shoots
			
			
			if (playerGrid.computerLastFive[k][2] == 1) //then we have a hit and musts make the playerGrid cell red
			{
				playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.DARKRED);
				com_successful++; //we increment the counter of successful hits
			}	
			else //we have a miss
			{
				playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.BLACK);
			}
			plact.setText("Player's active Ships: " + playerGrid.aliveShips ); //set the text for alive ships
			compact.setText("Computer's active Ships: " + computerGrid.aliveShips);
			plapon.setText("Player's points: " + computerGrid.points); //set the text for points
			compon.setText("Computer's points: " + playerGrid.points);
			computer_success_rate = Math.round((com_successful/(k+1))*100); //the success rate for the computer
			player_success_rate = Math.round((pla_successful/(j+1))*100); //the success rate for the player
			comhit.setText("Computer's hit rate: " + computer_success_rate + "%");
			plahit.setText("Player's hit rate: " + player_success_rate + "%");
			System.out.println("-->Computer has : " + playerGrid.points + " points.Number of shots left : " + playerGrid.shots + ". Number of sunken ships: " + playerGrid.sunkenShips + ".Number of alive ships: " + playerGrid.aliveShips + ".Success rate: "+computer_success_rate);
			System.out.println("-->Player has : " + computerGrid.points + " points.Number of shots left : " + computerGrid.shots + ". Number of sunken ships: " + computerGrid.sunkenShips + ".Number of alive ships: " + computerGrid.aliveShips+ ".Success rate: "+player_success_rate);
			k++;
			j++;
		}
		
		else if (r==0) { //computer plays first
			msg.setFill(Color.DODGERBLUE);
			msg.setLayoutX(208);
		if(k!=40) { //because the computer has started playing before
			playerGrid.computerShoot(); //the computer shoots
			if (playerGrid.computerLastFive[k][2] == 1) //then we have a hit and musts make the playerGrid cell red
			{
				playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.DARKRED);
				com_successful++; //we increment the counter of successful hits
			}	
			else //we have a miss
			{
				playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.BLACK);
			}
			
		}
			String rowc = row.getText();
			String columnc = column.getText();
			int rowi = Integer.parseInt(rowc);
			int columni = Integer.parseInt(columnc);
			System.out.println("You have shot at (" + rowi + "," + columni + ")");
			computerGrid.shoot(rowi, columni); //the player shoots
			movesleft--;
			moves.setText("Moves left: " + movesleft );
			if (computerGrid.playerLastFive[j][2] == 1) //then we have a hit and musts make the computerGrid cell red
			{
				computergrid[rowi][columni].setFill(Color.DARKRED);
				if (computerGrid.shipsSunk[computerGrid.ships[rowi][columni].getType() - 1] == true)
					{
					msg.setFill(Color.FORESTGREEN);
					msg.setText("You have sunken a ship,congratulations!");
					}
				else 
					msg.setText("Your hit was succesful,congratulations!");
				pla_successful++; //we increment the counter of successful hits
			}
			else {	//we have a miss
				msg.setFill(Color.MAROON);
				computergrid[rowi][columni].setFill(Color.BLACK);	
				msg.setText("Your hit was unsuccesful,try again!");
			}
			row.setText("");
			column.setText("");
			plact.setText("Player's active Ships: " + playerGrid.aliveShips ); //set the text for alive ships
			compact.setText("Computer's active Ships: " + computerGrid.aliveShips);
			plapon.setText("Player's points: " + computerGrid.points); //set the text for point
			compon.setText("Computer's points: " + playerGrid.points);
			computer_success_rate = Math.round((com_successful/(k+1))*100); //the success rate for the computer
			player_success_rate = Math.round((pla_successful/(j+1))*100); //the success rate for the player
			comhit.setText("Computer's hit rate: " + computer_success_rate + "%");
			plahit.setText("Player's hit rate: " + player_success_rate + "%");
			System.out.println("-->Computer has : " + playerGrid.points + " points.Number of shots left : " + playerGrid.shots + ". Number of sunken ships: " + playerGrid.sunkenShips + ".Number of alive ships: " + playerGrid.aliveShips+ ".Success rate: "+ computer_success_rate);
			System.out.println("-->Player has : " + computerGrid.points + " points.Number of shots left : " + computerGrid.shots + ". Number of sunken ships: " + computerGrid.sunkenShips + ".Number of alive ships: " + computerGrid.aliveShips+ ".Success rate: "+ player_success_rate);
			k++;
			j++;
		}
		//for the player pop up
		if (j - 5 >= 0 ) //the player has done 5+ moves
		{
			for (int s = 0; s < 5; s++ ) {
				plaLastFive[s][0].setText("-> Coordinates: (" + computerGrid.playerLastFive[j-5+s][0] + "," + computerGrid.playerLastFive[j-5+s][1] + ")");
				plaLastFive[s][1].setText("-> Hit or miss: " + computerGrid.playerLastFive[j-5+s][2]);
				plaLastFive[s][2].setText("-> It was of type: " + computerGrid.playerLastFive[j-5+s][3]);
			}
		}
		else if (j - 4 == 0) //the player has done 4 moves
		{
			for (int s = 0; s < 4; s++) {
				plaLastFive[s][0].setText("-> Coordinates: (" + computerGrid.playerLastFive[j-4+s][0] + "," + computerGrid.playerLastFive[j-4+s][1] + ")");
				plaLastFive[s][1].setText("-> Hit or miss: " + computerGrid.playerLastFive[j-4+s][2]);
				plaLastFive[s][2].setText("-> It was of type: " + computerGrid.playerLastFive[j-4+s][3]);
			}
		}
		else if (j - 3 == 0) //the player has done 3 moves
		{
			for (int s = 0; s < 3; s++) {
				plaLastFive[s][0].setText("-> Coordinates: (" + computerGrid.playerLastFive[j-3+s][0] + "," + computerGrid.playerLastFive[j-3+s][1] + ")");
				plaLastFive[s][1].setText("-> Hit or miss: " + computerGrid.playerLastFive[j-3+s][2]);
				plaLastFive[s][2].setText("-> It was of type: " + computerGrid.playerLastFive[j-3+s][3]);
			}
		}
		else if (j - 2 == 0) //the player has done 2 moves
		{
			for (int s = 0; s < 2; s++) {
				plaLastFive[s][0].setText("-> Coordinates: (" + computerGrid.playerLastFive[j-2+s][0] + "," + computerGrid.playerLastFive[j-2+s][1] + ")");
				plaLastFive[s][1].setText("-> Hit or miss: " + computerGrid.playerLastFive[j-2+s][2]);
				plaLastFive[s][2].setText("-> It was of type: " + computerGrid.playerLastFive[j-2+s][3]);
			}
		}
		else if (j - 1 == 0) //the player has done 1 move
		{
				plaLastFive[0][0].setText("-> Coordinates: (" + computerGrid.playerLastFive[j-1][0] + "," + computerGrid.playerLastFive[j-1][1] + ")");
				plaLastFive[0][1].setText("-> Hit or miss: " + computerGrid.playerLastFive[j-1][2]);
				plaLastFive[0][2].setText("-> It was of type: " + computerGrid.playerLastFive[j-1][3]);
		}
		//for the computer pop up
		if (k - 5 >= 0 ) //the computer has done 5+ moves
		{
			for (int s = 0; s < 5; s++ ) {
				compLastFive[s][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-5+s][0] + "," + playerGrid.computerLastFive[k-5+s][1] + ")");
				compLastFive[s][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-5+s][2]);
				compLastFive[s][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-5+s][3]);
			}
		}
		else if (k - 4 == 0) //the computer has done 4 moves
		{
			for (int s = 0; s < 4; s++) {
				compLastFive[s][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-4+s][0] + "," + playerGrid.computerLastFive[k-4+s][1] + ")");
				compLastFive[s][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-4+s][2]);
				compLastFive[s][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-4+s][3]);
			}
		}
		else if (k - 3 == 0) //the computer has done 3 moves
		{
			for (int s = 0; s < 3; s++) {
				compLastFive[s][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-3+s][0] + "," + playerGrid.computerLastFive[k-3+s][1] + ")");
				compLastFive[s][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-3+s][2]);
				compLastFive[s][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-3+s][3]);
			}
		}
		else if (k - 2 == 0) //the computer has done 2 moves
		{
			for (int s = 0; s < 2; s++) {
				compLastFive[s][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-2+s][0] + "," + playerGrid.computerLastFive[k-2+s][1] + ")");
				compLastFive[s][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-2+s][2]);
				compLastFive[s][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-2+s][3]);
			}
		}
		else if (k - 1 == 0) //the computer has done 1 move
		{
				compLastFive[0][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-1][0] + "," + playerGrid.computerLastFive[k-1][1] + ")");
				compLastFive[0][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-1][2]);
				compLastFive[0][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-1][3]);
		}
		
		if (computerGrid.shots == 0  || playerGrid.sunkenShips == 5 || computerGrid.sunkenShips == 5)
		{
			shootButton.setDisable(true);
			row.setDisable(true);
			column.setDisable(true);
			System.out.println("The game has ended!");
			if (computerGrid.shots == 0 && playerGrid.shots == 0)
			{
				if (computerGrid.points > playerGrid.points) //the player has achieved more points on computers grid
					{
					System.out.println("The player has won with " + computerGrid.points + " points!");
					msg.setFill(Color.DARKSLATEBLUE);
					msg.setText("The player has won with " + computerGrid.points + " points!");
					}
				else {
					System.out.println("The computer has won with " + playerGrid.points + " points!");
					msg.setFill(Color.DARKSLATEBLUE);
					msg.setText("The computer has won with " + playerGrid.points + " points!");
				}
			}
			else 
			{
				if (playerGrid.sunkenShips == 5) //the computer has sunk players ships in players grid
					{
					System.out.println("The computer has won because he has sunken all player's ships!");
					msg.setFill(Color.DARKSLATEBLUE);
					msg.setLayoutX(20);
					msg.setText("The computer has won because he has sunken all player's ships!");
					}
				else if (computerGrid.sunkenShips == 5)
					{
					System.out.println("The player has won because she has sunken all computers ships!");
					msg.setFill(Color.DARKSLATEBLUE);
					msg.setText("The player has won because she has sunken all computers ships!");
					}
			}
		}
		
	}	
	

	public void StartButtonClicked() {
		System.out.println("Start button clicked");
		shootButton.setDisable(false); //we want them disabled at the start
		row.setDisable(false);
		column.setDisable(false);
		textmsg.setText("Please enter the coordinates of the place you want to shoot at:");
		if (r == 1) //player plays first
		{
		System.out.println("Player plays first");
		msg.setLayoutX(340);
		msg.setFill(Color.DARKBLUE);
		msg.setText("Player plays first!");
		}
	else if (r==0)  //computer plays first
	{
		System.out.println("Computer plays first");
		playerGrid.computerShoot(); //the computer shoots
		msg.setLayoutX(340);
		msg.setFill(Color.DARKBLUE);
		msg.setText("Computer plays first!");
		if (playerGrid.computerLastFive[k][2] == 1) //then computer has a hit and musts make the playerGrid cell red
		{
			playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.DARKRED);
			com_successful++; //we increment the counter of successful hits
		}	
		else //we have a miss
		{
			playergrid[playerGrid.computerLastFive[k][0]][playerGrid.computerLastFive[k][1]].setFill(Color.BLACK);
		}
		computer_success_rate = Math.round((com_successful/(k+1))*100); //the success rate for the computer
		comhit.setText("Computer's hit rate: " + computer_success_rate + "%");
		System.out.println("-->Computer has : " + playerGrid.points + " points.Number of shots left : " + playerGrid.shots + ". Number of sunken ships: " + playerGrid.sunkenShips + ".Number of alive ships: " + playerGrid.aliveShips + ".Success rate: "+computer_success_rate);
		k++;
		if (k - 1 == 0) //the computer has done 1 move
		{
				compLastFive[0][0].setText("-> Coordinates: (" + playerGrid.computerLastFive[k-1][0] + "," + playerGrid.computerLastFive[k-1][1] + ")");
				compLastFive[0][1].setText("-> Hit or miss: " + playerGrid.computerLastFive[k-1][2]);
				compLastFive[0][2].setText("-> It was of type: " + playerGrid.computerLastFive[k-1][3]);
		}
	}
	}
	
	public void LoadButtonClicked() {
	//	System.out.println("Load button clicked");
		cleanGrid();
		msg.setText("Please press start button!");
		msg.setFill(Color.DARKMAGENTA);
		msg.setLayoutX(300);
		file.setLayoutX(190);
		file.setLayoutY(140);
		file.setPrefWidth(50);
		Button searchButton = new Button("Upload File");
		searchButton.setLayoutX(160);
		searchButton.setLayoutY(210);
		searchButton.setPrefWidth(100);
		Label idmsg = new Label("Please enter the SCENARIO-D:");
		idmsg.setFont(Font.font(null, FontWeight.BOLD, 20));
		idmsg.setLayoutX(60);
		idmsg.setLayoutY(70);
		
		errorLabel.setTextFill(Color.RED);
		errorLabel.setLayoutX(30);
		errorLabel.setLayoutY(270);
		Pane root = new Pane(file,searchButton,idmsg,errorLabel);
		root.setPrefSize(450, 350);

		Parent content = root;
		searchButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                    	String x = file.getText();
                    	file.setText("");
                		try (BufferedReader reader = new BufferedReader(new FileReader(new File("player_"+x+".txt")))) { 

                	        String line;
                	        for (int j= 0;j<5;j++)
                	        {
                	        	line = reader.readLine();
                	        	String[] arrSplit = line.split(","); //to split the numbers
                		        for (int i=0; i < arrSplit.length; i++)
                		        {
                		          pla_placement[j][i]=Integer.parseInt(arrSplit[i]);
                		        }
                	        }

                	    } catch (IOException i) {
                	    	System.out.println("An error occured");
                	        i.printStackTrace();
                	    }
                		try (BufferedReader reader = new BufferedReader(new FileReader(new File("enemy_"+x+".txt")))) { //player_1.txt")))){//

                	        String line;
                	        for (int j= 0;j<5;j++)
                	        {
                	        	line = reader.readLine();
                	        	String[] arrSplit = line.split(","); //to split the numbers
                		        for (int i=0; i < arrSplit.length; i++)
                		        {
                		          comp_placement[j][i]=Integer.parseInt(arrSplit[i]);
                		        }
                	        }

                	    } catch (IOException i) {
                	    	System.out.println("An error occured");
                	        i.printStackTrace();
                	    }
                		placeShips();  //call function to place ships
                    } 
		});
		// create scene containing the content
		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Load");
		// make window visible
		window.show();
		
		
	}
	
	public void placeShips() {
		cleanGrid();
		errorLabel.setText("");
		try {   //place the players ships 
			for (int j = 0; j < 5; j++)
			{
				playerGrid.PlaceShip(pla_placement[j][0],pla_placement[j][1],pla_placement[j][2],pla_placement[j][3]);
				if (pla_placement[j][3] == 1) //horizontal
				{
					switch(pla_placement[j][0])
					{
					case 1: //so we have a carrier (blue)
						for (int i = 0;i < 5;i++)
							playergrid[pla_placement[j][1]][pla_placement[j][2]+i].setFill(Color.BLUE);	
					break;	
					case 2: //so we have a battleship (purple)
						for (int i = 0;i < 4;i++)
							playergrid[pla_placement[j][1]][pla_placement[j][2]+i].setFill(Color.VIOLET);
					break;
					case 3: //so we have a cruiser (orange)
						for (int i = 0;i < 3;i++)
							playergrid[pla_placement[j][1]][pla_placement[j][2]+i].setFill(Color.DARKORANGE);
					break;
					case 4: //so we have a submarine (yellow)
						for (int i = 0;i < 3;i++)
							playergrid[pla_placement[j][1]][pla_placement[j][2]+i].setFill(Color.YELLOW);
					break;
					case 5: //so we have a destroyer (green)
						for (int i = 0;i < 2;i++)
							playergrid[pla_placement[j][1]][pla_placement[j][2]+i].setFill(Color.GREEN);
					break;
					}
				}
				else if (pla_placement[j][3] == 2) //vertical
				{
					switch(pla_placement[j][0])
					{
					case 1: //so we have a carrier (blue)
						for (int i = 0;i < 5;i++)
							playergrid[pla_placement[j][1]+i][pla_placement[j][2]].setFill(Color.BLUE);	
					break;	
					case 2: //so we have a battleship (purple)
						for (int i = 0;i < 4;i++)
							playergrid[pla_placement[j][1]+i][pla_placement[j][2]].setFill(Color.VIOLET);
					break;
					case 3: //so we have a cruiser (orange)
						for (int i = 0;i < 3;i++)
							playergrid[pla_placement[j][1]+i][pla_placement[j][2]].setFill(Color.DARKORANGE);
					break;
					case 4: //so we have a submarine (yellow)
						for (int i = 0;i < 3;i++)
							playergrid[pla_placement[j][1]+i][pla_placement[j][2]].setFill(Color.YELLOW);
					break;
					case 5: //so we have a destroyer (green)
						for (int i = 0;i < 2;i++)
							playergrid[pla_placement[j][1]+i][pla_placement[j][2]].setFill(Color.GREEN);
							
					break;
					}
				}
			}
			
		}
			catch(OversizeException e)                       
			{
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
				errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
				cleanGrid();
			}
			catch(OverlapTilesException o)
			{
				System.out.println("There is another ship here,sorry!");
				errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
				cleanGrid();
			}		
			catch(AdjacentTilesException a)
			{
				System.out.println("You must keep a distance of a line -covid- here,sorry!");
				errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
				cleanGrid();
			}
			catch(InvalidCountException k)
			{
				System.out.println("pOoops!You have already placed a ship of this type!");
				errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
				cleanGrid();
			}
			catch(ArrayIndexOutOfBoundsException a)
			{
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
				errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
				cleanGrid();
			}
		System.out.println("Player has placed her ships!");
		try {   //place the computers ships 
			for (int j = 0; j < 5; j++)
			{
				computerGrid.PlaceShip(comp_placement[j][0],comp_placement[j][1],comp_placement[j][2],comp_placement[j][3]);
		/*		if (comp_placement[j][3] == 1) //horizontal
				{
					switch(comp_placement[j][0])
					{
					case 1: //so we have a carrier (blue)
						for (int i = 0;i < 5;i++)
							computergrid[comp_placement[j][1]][comp_placement[j][2]+i].setFill(Color.BLUE);	
					break;	
					case 2: //so we have a battleship (purple)
						for (int i = 0;i < 4;i++)
							computergrid[comp_placement[j][1]][comp_placement[j][2]+i].setFill(Color.VIOLET);
					break;
					case 3: //so we have a cruiser (orange)
						for (int i = 0;i < 3;i++)
							computergrid[comp_placement[j][1]][comp_placement[j][2]+i].setFill(Color.DARKORANGE);
					break;
					case 4: //so we have a submarine (yellow)
						for (int i = 0;i < 3;i++)
							computergrid[comp_placement[j][1]][comp_placement[j][2]+i].setFill(Color.YELLOW);
					break;
					case 5: //so we have a destroyer (green)
						for (int i = 0;i < 2;i++)
							computergrid[comp_placement[j][1]][comp_placement[j][2]+i].setFill(Color.GREEN);
					break;
					}
				}
				else if (comp_placement[j][3] == 2) //vertical
				{
					switch(comp_placement[j][0])
					{
					case 1: //so we have a carrier (blue)
						for (int i = 0;i < 5;i++)
							computergrid[comp_placement[j][1]+i][comp_placement[j][2]].setFill(Color.BLUE);	
					break;	
					case 2: //so we have a battleship (purple)
						for (int i = 0;i < 4;i++)
							computergrid[comp_placement[j][1]+i][comp_placement[j][2]].setFill(Color.VIOLET);
					break;
					case 3: //so we have a cruiser (orange)
						for (int i = 0;i < 3;i++)
							computergrid[comp_placement[j][1]+i][comp_placement[j][2]].setFill(Color.DARKORANGE);
					break;
					case 4: //so we have a submarine (yellow)
						for (int i = 0;i < 3;i++)
							computergrid[comp_placement[j][1]+i][comp_placement[j][2]].setFill(Color.YELLOW);
					break;
					case 5: //so we have a destroyer (green)
						for (int i = 0;i < 2;i++)
							computergrid[comp_placement[j][1]+i][comp_placement[j][2]].setFill(Color.GREEN);
							
					break;
					}
				}*/
			}
			
		}
		catch(OversizeException e)                       
		{
			System.out.println("Wrong placement!The grid is 10*10,sorry!");
			errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
			cleanGrid();
		}
		catch(OverlapTilesException o)
		{
			System.out.println("There is another ship here,sorry!");
			errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
			cleanGrid();
		}		
		catch(AdjacentTilesException a)
		{
			System.out.println("You must keep a distance of a line -covid- here,sorry!");
			errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
			cleanGrid();
		}
		catch(InvalidCountException k)
		{
			System.out.println("Ooops!You have already placed a ship of this type!");
			errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
			cleanGrid();
		}
		catch(ArrayIndexOutOfBoundsException a)
		{
			System.out.println("Wrong placement!The grid is 10*10,sorry!");
			errorLabel.setText("!The file you uploaded isn't valid,try again with another one.");
			cleanGrid();
		}
		System.out.println("Computer has placed his ships!");
		
	}
	
	public void cleanGrid()  //when we have an error we want the grid to be cleaned and load another file
	{
		playerGrid.emptyGrid();
		computerGrid.emptyGrid();
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0;j < 10; j++)
			{
				playergrid[i][j].setFill(Color.DODGERBLUE);
				computergrid[i][j].setFill(Color.DODGERBLUE);
			}
		}
		k = 0;
		j = 0;
		pla_successful = 0;
		com_successful = 0;
		player_success_rate = 0;
		computer_success_rate = 0;
		plact.setText("Player's active Ships: " + playerGrid.aliveShips ); //set the text for alive ships
		compact.setText("Computer's active Ships: " + computerGrid.aliveShips);
		plapon.setText("Player's points: " + computerGrid.points); //set the text for points
		compon.setText("Computer's points: " + playerGrid.points);
		comhit.setText("Computer's hit rate: " + computer_success_rate + "%");
		plahit.setText("Player's hit rate: " + player_success_rate + "%");
		movesleft=40;
		moves.setText("Moves left: " + movesleft );
		pla_xy_one.setText("");
		pla_xy_two.setText("");
		pla_xy_three.setText("");
		pla_xy_four.setText("");
		pla_xy_five.setText("");
		comp_xy_one.setText("");
		comp_xy_two.setText("");
		comp_xy_three.setText("");
		comp_xy_four.setText("");
		comp_xy_five.setText("");
		pla_res_one.setText("");
		pla_res_two.setText("");
		pla_res_three.setText("");
		pla_res_four.setText("");
		pla_res_five.setText("");
		comp_res_one.setText("");
		comp_res_two.setText("");
		comp_res_three.setText("");
		comp_res_four.setText("");
		comp_res_five.setText("");
		pla_type_one.setText("");
		pla_type_two.setText("");
		pla_type_three.setText("");
		pla_type_four.setText("");
		pla_type_five.setText("");
		comp_type_one.setText("");
		comp_type_two.setText("");
		comp_type_three.setText("");
		comp_type_four.setText("");
		comp_type_five.setText("");
		carrierCondition.setText("Not sunk!");
		battleshipCondition.setText("Not Sunk!");
		cruiserCondition.setText("Not Sunk!");
		submarineCondition.setText("Not Sunk!");
		destroyerCondition.setText("Not Sunk!");
		r = oldr;
		oldr = r;
	}
	
	public void ExitButtonClicked() {
		  final Stage dialog = new Stage();
	        dialog.setTitle("Exit");
	        Button yes = new Button("Yes");
	        Button no = new Button("No");

	        Label exitLabel = new Label("Are you sure you want to exit?");
	        exitLabel.setFont(Font.font(null, FontWeight.BOLD, 20));

	        dialog.initModality(Modality.NONE);
	        //dialog.initOwner((Stage) tableview.getScene().getWindow());

	        HBox dialogHbox = new HBox(20);
	        dialogHbox.setAlignment(Pos.CENTER);

	        VBox dialogVbox1 = new VBox(20);
	        dialogVbox1.setAlignment(Pos.CENTER_LEFT);

	        VBox dialogVbox2 = new VBox(20);
	        dialogVbox2.setAlignment(Pos.CENTER_RIGHT);

	        dialogHbox.getChildren().add(exitLabel);
	        dialogVbox1.getChildren().add(yes);
	        dialogVbox2.getChildren().add(no);

	        yes.setOnAction(e -> Platform.exit());
	        no.addEventHandler(MouseEvent.MOUSE_CLICKED,
	                new EventHandler<MouseEvent>() {
	                    @Override
	                    public void handle(MouseEvent e) {
	                        dialog.close();
	                    }
	                });

	        dialogHbox.getChildren().addAll(dialogVbox1, dialogVbox2);
	        Scene dialogScene = new Scene(dialogHbox, 500, 100);
	        dialog.setScene(dialogScene);
	        dialog.show();
	}

	public void EnemyShipsButtonClicked() {
		System.out.println("Enemy ships button clicked");
		enemyShipsLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
		enemyShipsLabel.setLayoutX(20);
	    enemyShipsLabel.setLayoutY(20);
	    
	    Label carrierLabel = new Label("Carrier: ");
	    carrierLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		carrierLabel.setLayoutX(20);
	    carrierLabel.setLayoutY(80);
	
	   
	    if (computerGrid.shipsSunk[0] == true)
	    	carrierCondition.setText("Sunk!");
		carrierCondition.setLayoutX(20);
	    carrierCondition.setLayoutY(110);
	    
	    Label battleshipLabel = new Label("Battleship: ");
	    battleshipLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		battleshipLabel.setLayoutX(20);
	    battleshipLabel.setLayoutY(150);
	    
	    
	    if (computerGrid.shipsSunk[1] == true)
	    	battleshipCondition.setText("Sunk!");
		battleshipCondition.setLayoutX(20);
	    battleshipCondition.setLayoutY(180);
	    
	    Label cruiserLabel = new Label("Cruiser: ");
	    cruiserLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		cruiserLabel.setLayoutX(20);
	    cruiserLabel.setLayoutY(220);
	    
	    
	    if (computerGrid.shipsSunk[2] == true)
	    	cruiserCondition.setText("Sunk!");
		cruiserCondition.setLayoutX(20);
	    cruiserCondition.setLayoutY(250);
	    
	    Label submarineLabel = new Label("Submarine: ");
	    submarineLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		submarineLabel.setLayoutX(20);
	    submarineLabel.setLayoutY(290);
	    
	    
	    if (computerGrid.shipsSunk[3] == true)
	    	submarineCondition.setText("Sunk!");
		submarineCondition.setLayoutX(20);
	    submarineCondition.setLayoutY(320);
	    
	    Label destroyerLabel = new Label("Destroyer: ");
	    destroyerLabel.setFont(Font.font(null, FontWeight.BOLD, 16));
		destroyerLabel.setLayoutX(20);
	    destroyerLabel.setLayoutY(360);
	    
	   
	    if (computerGrid.shipsSunk[4] == true)
	    	destroyerCondition.setText("Sunk!");
		destroyerCondition.setLayoutX(20);
	    destroyerCondition.setLayoutY(390);
	    
		Pane root = new Pane(enemyShipsLabel,carrierLabel,carrierCondition,battleshipCondition,cruiserCondition,submarineCondition,destroyerCondition,battleshipLabel,cruiserLabel,submarineLabel,destroyerLabel);
		root.setPrefSize(450, 450);

		Parent content = root;

		// create scene containing the content
		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Enemy Ships");
		// make window visible
		window.show();
	}

	
	
	public void PlayerShotsButtonClicked() {
		System.out.println("Player shots button clicked");
		
		playerLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
		playerLabel.setLayoutX(20);
	    playerLabel.setLayoutY(20);
	    
	    //some styling
	    shot_five.setLayoutX(20);
	    shot_five.setLayoutY(80);
	    shot_five.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    pla_xy_five.setLayoutX(20);
	    pla_xy_five.setLayoutY(110);
	    
	    pla_res_five.setLayoutX(20);
	    pla_res_five.setLayoutY(130);
	    
	    pla_type_five.setLayoutX(20);
	    pla_type_five.setLayoutY(150);
	    
	    shot_four.setLayoutX(250);
	    shot_four.setLayoutY(80);
	    shot_four.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    pla_xy_four.setLayoutX(250);
	    pla_xy_four.setLayoutY(110);
	    
	    pla_res_four.setLayoutX(250);
	    pla_res_four.setLayoutY(130);
	    
	    pla_type_four.setLayoutX(250);
	    pla_type_four.setLayoutY(150);
	    
	    shot_three.setLayoutX(20);
	    shot_three.setLayoutY(190);
	    shot_three.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    pla_xy_three.setLayoutX(20);
	    pla_xy_three.setLayoutY(220);
	    
	    pla_res_three.setLayoutX(20);
	    pla_res_three.setLayoutY(240);
	    
	    pla_type_three.setLayoutX(20);
	    pla_type_three.setLayoutY(260);
	    
	    shot_two.setLayoutX(250);
	    shot_two.setLayoutY(190);
	    shot_two.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    pla_xy_two.setLayoutX(250);
	    pla_xy_two.setLayoutY(220);
	    
	    pla_res_two.setLayoutX(250);
	    pla_res_two.setLayoutY(240);
	    
	    pla_type_two.setLayoutX(250);
	    pla_type_two.setLayoutY(260);
	    
	    shot_one.setLayoutX(20);
	    shot_one.setLayoutY(300);
	    shot_one.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    pla_xy_one.setLayoutX(20);
	    pla_xy_one.setLayoutY(330);
	    
	    pla_res_one.setLayoutX(20);
	    pla_res_one.setLayoutY(350);
	    
	    pla_type_one.setLayoutX(20);
	    pla_type_one.setLayoutY(370);
	    
	    clarificationsRes.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarificationsRes.setLayoutX(250);
	    clarificationsRes.setLayoutY(300);
	    clarifications0.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications0.setLayoutX(250);
	    clarifications0.setLayoutY(320);
	    clarifications1.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications1.setLayoutX(250);
	    clarifications1.setLayoutY(340);
	    clarifications2.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications2.setLayoutX(250);
	    clarifications2.setLayoutY(360);
	    clarifications3.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications3.setLayoutX(250);
	    clarifications3.setLayoutY(380);
	    clarifications4.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications4.setLayoutX(250);
	    clarifications4.setLayoutY(400);
	    clarifications5.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications5.setLayoutX(250);
	    clarifications5.setLayoutY(420);
	    
		Pane root = new Pane(playerLabel,clarificationsRes,clarifications0,clarifications1,clarifications2,clarifications3,clarifications4,clarifications5,shot_five,pla_xy_five,pla_res_five,pla_type_five,shot_four,pla_xy_four,pla_res_four,pla_type_four,shot_three,pla_xy_three,pla_res_three,pla_type_three,shot_two,pla_xy_two,pla_res_two,pla_type_two,shot_one,pla_xy_one,pla_res_one,pla_type_one);
		root.setPrefSize(450, 450);

		Parent content = root;

		// create scene containing the content
		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Player Shots");
		// make window visible
		window.show();
	}
	

	public void EnemyShotsButtonClicked() {
		System.out.println("Enemy shots button clicked");
		computerLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
		computerLabel.setLayoutX(20);
	    computerLabel.setLayoutY(20);
	    
	    //some styling
	    shot_five.setLayoutX(20);
	    shot_five.setLayoutY(80);
	    shot_five.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    comp_xy_five.setLayoutX(20);
	    comp_xy_five.setLayoutY(110);
	    
	    comp_res_five.setLayoutX(20);
	    comp_res_five.setLayoutY(130);
	    
	    comp_type_five.setLayoutX(20);
	    comp_type_five.setLayoutY(150);
	    
	    shot_four.setLayoutX(250);
	    shot_four.setLayoutY(80);
	    shot_four.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    comp_xy_four.setLayoutX(250);
	    comp_xy_four.setLayoutY(110);
	    
	    comp_res_four.setLayoutX(250);
	    comp_res_four.setLayoutY(130);
	    
	    comp_type_four.setLayoutX(250);
	    comp_type_four.setLayoutY(150);
	    
	    shot_three.setLayoutX(20);
	    shot_three.setLayoutY(190);
	    shot_three.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    comp_xy_three.setLayoutX(20);
	    comp_xy_three.setLayoutY(220);
	    
	    comp_res_three.setLayoutX(20);
	    comp_res_three.setLayoutY(240);
	    
	    comp_type_three.setLayoutX(20);
	    comp_type_three.setLayoutY(260);
	    
	    shot_two.setLayoutX(250);
	    shot_two.setLayoutY(190);
	    shot_two.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    comp_xy_two.setLayoutX(250);
	    comp_xy_two.setLayoutY(220);
	    
	    comp_res_two.setLayoutX(250);
	    comp_res_two.setLayoutY(240);
	    
	    comp_type_two.setLayoutX(250);
	    comp_type_two.setLayoutY(260);
	    
	    shot_one.setLayoutX(20);
	    shot_one.setLayoutY(300);
	    shot_one.setFont(Font.font(null, FontWeight.BOLD, 16));
	    
	    comp_xy_one.setLayoutX(20);
	    comp_xy_one.setLayoutY(330);
	    
	    comp_res_one.setLayoutX(20);
	    comp_res_one.setLayoutY(350);
	    
	    comp_type_one.setLayoutX(20);
	    comp_type_one.setLayoutY(370);
	    
	    clarificationsRes.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarificationsRes.setLayoutX(250);
	    clarificationsRes.setLayoutY(300);
	    clarifications0.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications0.setLayoutX(250);
	    clarifications0.setLayoutY(320);
	    clarifications1.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications1.setLayoutX(250);
	    clarifications1.setLayoutY(340);
	    clarifications2.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications2.setLayoutX(250);
	    clarifications2.setLayoutY(360);
	    clarifications3.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications3.setLayoutX(250);
	    clarifications3.setLayoutY(380);
	    clarifications4.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications4.setLayoutX(250);
	    clarifications4.setLayoutY(400);
	    clarifications5.setFont(Font.font(null, FontWeight.BOLD, 14));
	    clarifications5.setLayoutX(250);
	    clarifications5.setLayoutY(420);
	    
		Pane root = new Pane(computerLabel,clarificationsRes,clarifications0,clarifications1,clarifications2,clarifications3,clarifications4,clarifications5,shot_five,comp_xy_five,comp_res_five,comp_type_five,shot_four,comp_xy_four,comp_res_four,comp_type_four,shot_three,comp_xy_three,comp_res_three,comp_type_three,shot_two,comp_xy_two,comp_res_two,comp_type_two,shot_one,comp_xy_one,comp_res_one,comp_type_one);
		root.setPrefSize(450, 450);

		Parent content = root;

		// create scene containing the content
		Scene scene = new Scene(content);

		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Enemy Shots");
		// make window visible
		window.show();
	}
	
	void playerGridMap() {
		playergrid[0][0]=p00;
		playergrid[0][1]=p01;
		playergrid[0][2]=p02;
		playergrid[0][3]=p03;
		playergrid[0][4]=p04;
		playergrid[0][5]=p05;
		playergrid[0][6]=p06;
		playergrid[0][7]=p07;
		playergrid[0][8]=p08;
		playergrid[0][9]=p09;
		playergrid[1][0]=p10;
		playergrid[1][1]=p11;
		playergrid[1][2]=p12;
		playergrid[1][3]=p13;
		playergrid[1][4]=p14;
		playergrid[1][5]=p15;
		playergrid[1][6]=p16;
		playergrid[1][7]=p17;
		playergrid[1][8]=p18;
		playergrid[1][9]=p19;
		playergrid[2][0]=p20;
		playergrid[2][1]=p21;
		playergrid[2][2]=p22;
		playergrid[2][3]=p23;
		playergrid[2][4]=p24;
		playergrid[2][5]=p25;
		playergrid[2][6]=p26;
		playergrid[2][7]=p27;
		playergrid[2][8]=p28;
		playergrid[2][9]=p29;
		playergrid[3][0]=p30;
		playergrid[3][1]=p31;
		playergrid[3][2]=p32;
		playergrid[3][3]=p33;
		playergrid[3][4]=p34;
		playergrid[3][5]=p35;
		playergrid[3][6]=p36;
		playergrid[3][7]=p37;
		playergrid[3][8]=p38;
		playergrid[3][9]=p39;
		playergrid[4][0]=p40;
		playergrid[4][1]=p41;
		playergrid[4][2]=p42;
		playergrid[4][3]=p43;
		playergrid[4][4]=p44;
		playergrid[4][5]=p45;
		playergrid[4][6]=p46;
		playergrid[4][7]=p47;
		playergrid[4][8]=p48;
		playergrid[4][9]=p49;
		playergrid[5][0]=p50;
		playergrid[5][1]=p51;
		playergrid[5][2]=p52;
		playergrid[5][3]=p53;
		playergrid[5][4]=p54;
		playergrid[5][5]=p55;
		playergrid[5][6]=p56;
		playergrid[5][7]=p57;
		playergrid[5][8]=p58;
		playergrid[5][9]=p59;
		playergrid[6][0]=p60;
		playergrid[6][1]=p61;
		playergrid[6][2]=p62;
		playergrid[6][3]=p63;
		playergrid[6][4]=p64;
		playergrid[6][5]=p65;
		playergrid[6][6]=p66;
		playergrid[6][7]=p67;
		playergrid[6][8]=p68;
		playergrid[6][9]=p69;
		playergrid[7][0]=p70;
		playergrid[7][1]=p71;
		playergrid[7][2]=p72;
		playergrid[7][3]=p73;
		playergrid[7][4]=p74;
		playergrid[7][5]=p75;
		playergrid[7][6]=p76;
		playergrid[7][7]=p77;
		playergrid[7][8]=p78;
		playergrid[7][9]=p79;
		playergrid[8][0]=p80;
		playergrid[8][1]=p81;
		playergrid[8][2]=p82;
		playergrid[8][3]=p83;
		playergrid[8][4]=p84;
		playergrid[8][5]=p85;
		playergrid[8][6]=p86;
		playergrid[8][7]=p87;
		playergrid[8][8]=p88;
		playergrid[8][9]=p89;
		playergrid[9][0]=p90;
		playergrid[9][1]=p91;
		playergrid[9][2]=p92;
		playergrid[9][3]=p93;
		playergrid[9][4]=p94;
		playergrid[9][5]=p95;
		playergrid[9][6]=p96;
		playergrid[9][7]=p97;
		playergrid[9][8]=p98;
		playergrid[9][9]=p99;
	}
	void computerGridMap() {
		computergrid[0][0]=c00;
		computergrid[0][1]=c01;
		computergrid[0][2]=c02;
		computergrid[0][3]=c03;
		computergrid[0][4]=c04;
		computergrid[0][5]=c05;
		computergrid[0][6]=c06;
		computergrid[0][7]=c07;
		computergrid[0][8]=c08;
		computergrid[0][9]=c09;
		computergrid[1][0]=c10;
		computergrid[1][1]=c11;
		computergrid[1][2]=c12;
		computergrid[1][3]=c13;
		computergrid[1][4]=c14;
		computergrid[1][5]=c15;
		computergrid[1][6]=c16;
		computergrid[1][7]=c17;
		computergrid[1][8]=c18;
		computergrid[1][9]=c19;
		computergrid[2][0]=c20;
		computergrid[2][1]=c21;
		computergrid[2][2]=c22;
		computergrid[2][3]=c23;
		computergrid[2][4]=c24;
		computergrid[2][5]=c25;
		computergrid[2][6]=c26;
		computergrid[2][7]=c27;
		computergrid[2][8]=c28;
		computergrid[2][9]=c29;
		computergrid[3][0]=c30;
		computergrid[3][1]=c31;
		computergrid[3][2]=c32;
		computergrid[3][3]=c33;
		computergrid[3][4]=c34;
		computergrid[3][5]=c35;
		computergrid[3][6]=c36;
		computergrid[3][7]=c37;
		computergrid[3][8]=c38;
		computergrid[3][9]=c39;
		computergrid[4][0]=c40;
		computergrid[4][1]=c41;
		computergrid[4][2]=c42;
		computergrid[4][3]=c43;
		computergrid[4][4]=c44;
		computergrid[4][5]=c45;
		computergrid[4][6]=c46;
		computergrid[4][7]=c47;
		computergrid[4][8]=c48;
		computergrid[4][9]=c49;
		computergrid[5][0]=c50;
		computergrid[5][1]=c51;
		computergrid[5][2]=c52;
		computergrid[5][3]=c53;
		computergrid[5][4]=c54;
		computergrid[5][5]=c55;
		computergrid[5][6]=c56;
		computergrid[5][7]=c57;
		computergrid[5][8]=c58;
		computergrid[5][9]=c59;
		computergrid[6][0]=c60;
		computergrid[6][1]=c61;
		computergrid[6][2]=c62;
		computergrid[6][3]=c63;
		computergrid[6][4]=c64;
		computergrid[6][5]=c65;
		computergrid[6][6]=c66;
		computergrid[6][7]=c67;
		computergrid[6][8]=c68;
		computergrid[6][9]=c69;
		computergrid[7][0]=c70;
		computergrid[7][1]=c71;
		computergrid[7][2]=c72;
		computergrid[7][3]=c73;
		computergrid[7][4]=c74;
		computergrid[7][5]=c75;
		computergrid[7][6]=c76;
		computergrid[7][7]=c77;
		computergrid[7][8]=c78;
		computergrid[7][9]=c79;
		computergrid[8][0]=c80;
		computergrid[8][1]=c81;
		computergrid[8][2]=c82;
		computergrid[8][3]=c83;
		computergrid[8][4]=c84;
		computergrid[8][5]=c85;
		computergrid[8][6]=c86;
		computergrid[8][7]=c87;
		computergrid[8][8]=c88;
		computergrid[8][9]=c89;
		computergrid[9][0]=c90;
		computergrid[9][1]=c91;
		computergrid[9][2]=c92;
		computergrid[9][3]=c93;
		computergrid[9][4]=c94;
		computergrid[9][5]=c95;
		computergrid[9][6]=c96;
		computergrid[9][7]=c97;
		computergrid[9][8]=c98;
		computergrid[9][9]=c99;
	}
	
	void plaLastFiveMap() {
		plaLastFive[0][0] = pla_xy_one;
		plaLastFive[0][1] = pla_res_one;
		plaLastFive[0][2] = pla_type_one;
		plaLastFive[1][0] = pla_xy_two;
		plaLastFive[1][1] = pla_res_two;
		plaLastFive[1][2] = pla_type_two;
		plaLastFive[2][0] = pla_xy_three;
		plaLastFive[2][1] = pla_res_three;
		plaLastFive[2][2] = pla_type_three;
		plaLastFive[3][0] = pla_xy_four;
		plaLastFive[3][1] = pla_res_four;
		plaLastFive[3][2] = pla_type_four;
		plaLastFive[4][0] = pla_xy_five;
		plaLastFive[4][1] = pla_res_five;
		plaLastFive[4][2] = pla_type_five;
		
	}
	void compLastFiveMap() {
		compLastFive[0][0] = comp_xy_one;
		compLastFive[0][1] = comp_res_one;
		compLastFive[0][2] = comp_type_one;
		compLastFive[1][0] = comp_xy_two;
		compLastFive[1][1] = comp_res_two;
		compLastFive[1][2] = comp_type_two;
		compLastFive[2][0] = comp_xy_three;
		compLastFive[2][1] = comp_res_three;
		compLastFive[2][2] = comp_type_three;
		compLastFive[3][0] = comp_xy_four;
		compLastFive[3][1] = comp_res_four;
		compLastFive[3][2] = comp_type_four;
		compLastFive[4][0] = comp_xy_five;
		compLastFive[4][1] = comp_res_five;
		compLastFive[4][2] = comp_type_five;
		
	}
}
