package battleship;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

import javafx.scene.control.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;




public class BattleController implements Initializable{
public Button buttonid;
public TextField row;	
public TextField column;
public Button shootButton;
public Text compact,plact,compon,plapon,comhit,plahit,msg;
//public Rectangle p00,p01,p02;
//public GridPane pane;
Rectangle [][] playergrid = new Rectangle[11][11]; //we use it for making the grid of the player
Rectangle [][] computergrid = new Rectangle[11][11]; //we use it to make the grid of the computer
public Rectangle p00,p01,p02,p03,p04,p05,p06,p07,p08,p09,p10,p11,p12,p13,p14,p15,p16,p17,p18,p19,p20,p21,p22,p23,p24,p25,p26,p27,p28,p29,p30,p31,p32,p33,p34,p35,p36,p37,p38,p39,p40,p41,p42,p43,p44,p45,p46,p47,p48,p49,p50,p51,p52,p53,p54,p55,p56,p57,p58,p59,p60,p61,p62,p63,p64,p65,p66,p67,p68,p69;
public Rectangle p70,p71,p72,p73,p74,p75,p76,p77,p78,p79,p80,p81,p82,p83,p84,p85,p86,p87,p88,p89,p90,p91,p92,p93,p94,p95,p96,p97,p98,p99;
public Rectangle c00,c01,c02,c03,c04,c05,c06,c07,c08,c09,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21,c22,c23,c24,c25,c26,c27,c28,c29,c30,c31,c32,c33,c34,c35,c36,c37,c38,c39,c40,c41,c42,c43,c44,c45,c46,c47,c48,c49,c50,c51,c52,c53,c54,c55,c56,c57,c58,c59,c60,c61,c62,c63,c64,c65,c66,c67,c68,c69;
public Rectangle c70,c71,c72,c73,c74,c75,c76,c77,c78,c79,c80,c81,c82,c83,c84,c85,c86,c87,c88,c89,c90,c91,c92,c93,c94,c95,c96,c97,c98,c99;
int k = 0; //to know the computers moves
double com_successful = 0; //to help us count the success rate for computer
//long r =  Math.round( Math.random() ); //we produce a random number (either 0 or 1) to see who plays first
long r = 1;
int j = 0; //to know the players moves
double pla_successful = 0; //to help us count the success rate for player
double player_success_rate,computer_success_rate; //the percentages of successful hits

Grid playerGrid = new Grid(); //Initialize the two grids
Grid computerGrid = new Grid();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//System.out.println("Now it starts");
		//Initialize the grid:
		computerGridMap();
		playerGridMap();
		try {   //place the players ships 
			playerGrid.PlaceShip(1,7,0,1); //blue ship
			for (int i = 0;i < 5;i++)
				playergrid[7][i].setFill(Color.BLUE);
			playerGrid.PlaceShip(2,5,1,1); //purple
			for (int i = 0;i < 4;i++)
				playergrid[5][1+i].setFill(Color.VIOLET);
			playerGrid.PlaceShip(3,6,6,2); //red
			for (int i = 0;i < 3;i++)
				playergrid[6+i][6].setFill(Color.DARKORANGE);
			playerGrid.PlaceShip(4,1,1,1); //yellow  //we have initialized our board
			for (int i = 0;i < 3;i++)
				playergrid[1][1+i].setFill(Color.YELLOW);
			playerGrid.PlaceShip(5,3,8,2); //green
			for (int i = 0;i < 2;i++)
				playergrid[3+i][8].setFill(Color.GREEN);
			
		}
			catch(OversizeException e)                       
			{
			//	i=i+1; //in order to place again
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
			}
			catch(OverlapTilesException o)
			{
			//	i=i+1; //in order to place again
				System.out.println("There is another ship here,sorry!");
			}		
			catch(AdjacentTilesException a)
			{
			//	i=i+1; //in order to place again
				System.out.println("You must keep a distance of a line -covid- here,sorry!");
			}
			catch(InvalidCountException k)
			{
			//	i=i+1; //in order to place again
				System.out.println("Ooops!You have already placed a ship of this type!");
			}
			catch(ArrayIndexOutOfBoundsException a)
			{
				System.out.println("Wrong placement!The grid is 10*10,sorry!");
			//	i=i+1; //in order to place again
			}
		//}
		System.out.println("Player has placed her ships!");
		
		//computerGrid.computerPlaceShip();
		try {
			computerGrid.PlaceShip(1,8,4,1); //blue ship
			computerGrid.PlaceShip(2,4,2,2); //purple
			computerGrid.PlaceShip(3,3,8,2); //red
			computerGrid.PlaceShip(4,1,6,2); //yellow
			computerGrid.PlaceShip(5,4,4,2); //green
			
		}
		catch(OversizeException e)                       
		{}
		catch(OverlapTilesException o)
		{}
		catch(AdjacentTilesException a)
		{}
		catch(InvalidCountException i)
		{}
		catch(ArrayIndexOutOfBoundsException a)
		{
			System.out.println("Wrong placement!The grid is 10*10,sorry!");
		}
		
		System.out.println("Computer has placed his ships!");	
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
		}
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
			if (computerGrid.playerLastFive[j][2] == 1) //then player has a hit and musts make the computerGrid cell red
			{
				computergrid[rowi][columni].setFill(Color.DARKRED);
				msg.setText("Your hit was succesful,congratulations!");
				pla_successful++; //we increment the counter of successful hits
			}
			else {	//we have a miss
				computergrid[rowi][columni].setFill(Color.BLACK);	
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
		if(k!=5) { //because the computer has started playing before
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
			if (computerGrid.playerLastFive[j][2] == 1) //then we have a hit and musts make the computerGrid cell red
			{
				computergrid[rowi][columni].setFill(Color.DARKRED);
				msg.setText("Your hit was succesful,congratulations!");
				pla_successful++; //we increment the counter of successful hits
			}
			else {	//we have a miss
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
	}
	
	public void LoadButtonClicked() {
		System.out.println("Load button clicked");
	}
	
	public void ExitButtonClicked() {
		System.out.println("Exit button clicked");
	}

	public void EnemyShipsButtonClicked() {
		System.out.println("Enemy ships button clicked");
	}
	
	public void PlayerShotsButtonClicked() {
		System.out.println("Player shots button clicked");
	}

	public void EnemyShotsButtonClicked() {
		System.out.println("Enemy shots button clicked");
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

}
