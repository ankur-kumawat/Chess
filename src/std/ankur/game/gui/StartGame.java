/**
 * @author Ankur Kumar Kumawat
 *  Used for initializing the game
 */

package std.ankur.game.gui;

import java.awt.BorderLayout;
import java.io.File;

import std.ankur.game.movements.MovementListener;

public class StartGame {
	BoxPanel lay[][];
	MovementListener moveListner;
	BoxPanel black[][];
	BoxPanel white[][];
	public StartGame()
	{
		lay = new ChessGUI_Main("Chess 8x8").getBoardCells();
		black = new BoxPanel[2][8];
		white = new BoxPanel[2][8];
		initializeGame();
		moveListner = new MovementListener( black, white, lay);
		addEventListener();
	}
	
	public void initializeGame(){
		for(int i=0, k=0; i<lay.length; i++){
			for(int j=0, l=0; j<lay[i].length; j++){
				lay[i][j].setLayout(new BorderLayout());
				if(i==0){
					white[i][j] = lay[i][j];
					lay[i][j].setVacant(false);
					if(j==0 || j==7){
						setCell(i, j, "data/images/rookWhite.png");
						lay[i][j].setVarientName("rook");
						lay[i][j].setVarientTeam("white");
					}
					else if(j==1 || j==6){
						setCell(i, j, "data/images/knightWhite.png");
						lay[i][j].setVarientName("knight");
						lay[i][j].setVarientTeam("white");
					}
					else if(j==2 || j==5){
						setCell(i, j, "data/images/bishopWhite.png");
						lay[i][j].setVarientName("bishop");
						lay[i][j].setVarientTeam("white");
						if(j==2)
							lay[i][j].setAdditionalInfo("white");
						else
							lay[i][j].setAdditionalInfo("black");
					}
					else if(j==3){
						setCell(i, j, "data/images/queenWhite.png");
						lay[i][j].setVarientName("queen");
						lay[i][j].setVarientTeam("white");
					}
					else{
						setCell(i, j, "data/images/kingWhite.png");
						lay[i][j].setVarientName("king");
						lay[i][j].setVarientTeam("white");
						lay[i][j].setAdditionalInfo("nocheck");
					}
				}
				else{
					if(i==1){
						white[i][j] = lay[i][j];
						lay[i][j].setVacant(false);
						setCell(i, j, "data/images/pawnWhite.png");
						lay[i][j].setVarientName("pawn");
						lay[i][j].setVarientTeam("white");
						lay[i][j].setAdditionalInfo("first");
					}
					else if(i==6){
						black[k][l++] = lay[i][j];
						lay[i][j].setVacant(false);
						setCell(i, j, "data/images/pawnBlack.png");
						lay[i][j].setVarientName("pawn");
						lay[i][j].setVarientTeam("black");
						lay[i][j].setAdditionalInfo("first");
					}
					else if(i==7){
						k=1;
						black[k][l++]=lay[i][j];
						lay[i][j].setVacant(false);
						if(j==0 || j==7){
							setCell(i, j, "data/images/rookBlack.png");
							lay[i][j].setVarientName("rook");
							lay[i][j].setVarientTeam("black");
						}
						else if(j==1 || j==6){
							setCell(i, j, "data/images/knightBlack.png");
							lay[i][j].setVarientName("knight");
							lay[i][j].setVarientTeam("black");
						}
						else if(j==2 || j==5){
							setCell(i, j, "data/images/bishopBlack.png");
							lay[i][j].setVarientName("bishop");
							lay[i][j].setVarientTeam("black");
							if(j==2)
								lay[i][j].setAdditionalInfo("black");
							else
								lay[i][j].setAdditionalInfo("white");
						}
						else if(j==3){
							setCell(i, j, "data/images/queenBlack.png");
							lay[i][j].setVarientName("queen");
							lay[i][j].setVarientTeam("black");
						}
						else{
							setCell(i, j, "data/images/kingBlack.png");
							lay[i][j].setVarientName("king");
							lay[i][j].setVarientTeam("black");
							lay[i][j].setAdditionalInfo("nocheck");
						}
					}
				}
			}
		}
			
	}

	private void setCell(int i, int j, String path) {
		VarientLabel vLbl = new VarientLabel(new File(path));
		lay[i][j].add(vLbl);
	}
	
	private void addEventListener(){
		for(int i=0; i<white.length; i++)
			for(int j=0; j<white[i].length; j++)
				white[i][j].addMouseListener(moveListner);
	}
}
