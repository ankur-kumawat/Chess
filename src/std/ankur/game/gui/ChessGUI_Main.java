/**
 * @author Ankur Kumar Kumawat
 *  Used for creating cell matrix
 */
package std.ankur.game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ChessGUI_Main extends JFrame {
	private BoxPanel[][] lay;
	private boolean varientBlack=false;
	public ChessGUI_Main(String arg0) {
		super(arg0);
		setLayout(new GridLayout(8,8));
		lay = new BoxPanel[8][8];
		for(int i=0; i<lay.length; i++)
		{
			for(int j=0; j<lay[i].length; j++)
			{
				lay[i][j]=new BoxPanel();
				lay[i][j].setLayout(new BorderLayout());
				if(i==0 || i%2==0)
					if(j==0 || j%2==0)
						lay[i][j].setBackground(Color.black);
					else
						lay[i][j].setBackground(Color.white);
				else
					if(j==0 || j%2==0)
						lay[i][j].setBackground(Color.white);
					else
						lay[i][j].setBackground(Color.black);
				add(lay[i][j]);
			}
		}
		setBounds(new Rectangle(50,50,800,600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setResizable(false);
		setVisible(true);
	}
	public void setVarientBlack(boolean varientBlack){
		this.varientBlack=varientBlack;
	}
	public boolean getVarientBlack(){
		return varientBlack;
	}
	public BoxPanel[][] getBoardCells(){
		return lay;
	}	
}
