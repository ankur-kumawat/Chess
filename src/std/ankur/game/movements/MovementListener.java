/**
 * @author Ankur Kumar Kumawat
 * This class is for finding and setting the possible moves of variants, 
 * Available moves for the king and victory of a side.
 *  Almost all most important things are done by this class.
 */
package std.ankur.game.movements;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.BorderFactory;

import std.ankur.game.gui.BoxPanel;
import std.ankur.game.gui.VarientLabel;

public class MovementListener implements MouseListener {
	BoxPanel[][] black, white, board;
	int r, c;
	int rT, cT;
	boolean blackActive=false;
	ConcurrentLinkedQueue<BoxPanel> moves;
	
	public MovementListener(BoxPanel[][] b, BoxPanel[][] w, BoxPanel[][] board){
		super();
		this.black = b;
		this.white = w;
		this.board = board;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//resetPrevious();
		board[r][c].setBorder(null);
		if(moves!=null)
			resetList();
		BoxPanel p = (BoxPanel)e.getComponent();
		setBorder(p);
		getIndexes(p);
		setMoves();
		setBorder();
		addMoveListener();
	}
	
	private void setBorder(BoxPanel p){
		p.setOpaque(true);
		p.setBorder(BorderFactory.createLineBorder(Color.blue, 3));
	}
	
	private void getIndexes(BoxPanel p){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if( board[i][j] == p ){
					r = i;
					c = j;
				}
			}
		}
	}
	
	private void setMoves(){
		if(board[r][c].getVarientName().equals("king")){
			setupKingMoves();
		}
		else{
			if(board[r][c].getVarientName().equals("queen")){
				setupQueenMoves();
			}
			else{
				if(board[r][c].getVarientName().equals("rook")){
					setupRookMoves();
				}
				else{
					if(board[r][c].getVarientName().equals("knight")){
						setupKnightMoves();
					}
					else{
						if(board[r][c].getVarientName().equals("bishop")){
							setupBishopMoves();
						}
						else{
							setupPawnMoves();
						}
					}
				}
			}
		}
	}
	
	private void setupPawnMoves(){
		moves = new ConcurrentLinkedQueue<BoxPanel>();
		int x, y;
		if(board[r][c].getVarientTeam().equals("white")){
			x = r+1;
			y = r+2;
			System.out.println(x+" white "+y);
		}
		else{
			x = r-1;
			y = r-2;
			
			System.out.println(r+" black "+c);
		}
		if(x>=0 && board[x][c].isVacant()){
			moves.add(board[x][c]);
			if(board[r][c].getAdditionalInfo().equals("first"))
				if(board[y][c].isVacant()){
					moves.add(board[y][c]);
				}
		}
		if(c!=0 && x>=0)
			if(isEnemy(x, c-1)){
				moves.add(board[x][c-1]);
			}
		if(c!=7 && x>=0)
			if(isEnemy(x, c+1)){
				moves.add(board[x][c+1]);
			}
	}
	
	private boolean isEnemy(int x, int y){
		if(!board[x][y].isVacant())
			if(board[x][y].getVarientTeam().equals(board[r][c].getVarientTeam())){
				return false;
			}
			else{
			/*	System.out.println(board[r][c].getVarientTeam()+"rc");
				System.out.println(board[x][y].getVarientTeam()+"xy");
				board[x][y].setOpaque(true);
				board[x][y].setBorder(BorderFactory.createLineBorder(Color.red, 3));
			*/
				return true;
			}
		return false;
	}
	
	private void setBorder(){
		Iterator<BoxPanel> iter= moves.iterator();
		while(iter.hasNext()){
			BoxPanel p = iter.next();
			p.setOpaque(true);
			if(p.isVacant())
				p.setBorder(BorderFactory.createLineBorder(Color.green, 3));
			else
				p.setBorder(BorderFactory.createLineBorder(Color.red, 3));
		}
	}
	private void setupKingMoves(){
		setupQueenMoves();
		ConcurrentLinkedQueue<BoxPanel> temp=moves;
		int tR=r,tC=c;
		if((board[r][c].getVarientTeam()).equals("white"))
				confirmKingMoves(black,temp);
		else
				confirmKingMoves(white,temp);
		r=tR;
		c=tC;
		moves=temp;
	}
	
	private void confirmKingMoves(BoxPanel[][] t, ConcurrentLinkedQueue<BoxPanel> temp){
		for(int i=0; i<2; i++)
			for(int j=0; j<8; j++){
				if(t[i][j]!=null){
					if(t[i][j].getVarientName().equals("king"))
						continue;
					getIndexes(t[i][j]);
					System.out.println("R="+r+" C="+c);
					setMoves();
					compareMoves(temp);
				}
			}
	}
	private void compareMoves(ConcurrentLinkedQueue<BoxPanel> temp){
		Iterator<BoxPanel> tI;
		Iterator<BoxPanel> mI = moves.iterator();
		while(mI.hasNext()){
			tI=temp.iterator();
			BoxPanel pM=mI.next();
			while(tI.hasNext()){
				BoxPanel pT=tI.next();
				if(pT==pM)
					temp.remove(pT);
			}
		}
	}
	
	private void setupQueenMoves(){
		moves = new ConcurrentLinkedQueue<BoxPanel>();
		topMoves();
		rightMoves();
		downMoves();
		leftMoves();
		leftDMoves();
		rightDMoves();
		topDMoves();
		downDMoves();
	}
	private void setupRookMoves(){
		moves = new ConcurrentLinkedQueue<BoxPanel>(); 
		topMoves();
		rightMoves();
		downMoves();
		leftMoves();
	}
	
	private void topMoves(){
		int x=r, y=c;
		if(x>0)
			x-=1;
		while(board[x][y].isVacant()){
			moves.add(board[x--][y]);
			if(!(x>=0) || board[r][c].getVarientName().equals("king"))
				break;
		}
		if(x>=0){
			if(board[r][c].getVarientName().equals("king") && x+1!=r)
				return;
			if(isEnemy(x,y))
				moves.add(board[x][y]);
		}
	}
	private void rightMoves(){
		int x=r, y=c;
		if(y<7)
			y+=1;
		while(board[x][y].isVacant()){
			moves.add(board[x][y++]);
			if(!(y<=7) || board[r][c].getVarientName().equals("king"))
				break;
		}
		if(y<=7){
			if(board[r][c].getVarientName().equals("king") && y-1!=c)
				return;
			if(isEnemy(x,y))
				moves.add(board[x][y]);
		}
	}
	private void downMoves(){
		int x=r, y=c;
		if(x<7)
			x+=1;
		while(board[x][y].isVacant()){
			moves.add(board[x++][y]);
			if(!(x<=7) || board[r][c].getVarientName().equals("king"))
				break;
		}
		if(x<=7){
			if(board[r][c].getVarientName().equals("king") && x-1!=r)
				return;
			if(isEnemy(x,y))
				moves.add(board[x][y]);
		}
	}
	private void leftMoves(){
		int x=r, y=c;
		if(y>0)
			y-=1;
		while(board[x][y].isVacant()){
			moves.add(board[x][y--]);
			if(!(y>=0) || board[r][c].getVarientName().equals("king"))
				break;
		}
		if(y>=0){
			if(board[r][c].getVarientName().equals("king") && y+1!=c)
				return;
			if(isEnemy(x,y))
				moves.add(board[x][y]);
		}
	}
	
	private void setupKnightMoves(){
		moves = new ConcurrentLinkedQueue<BoxPanel>();
		if(c>1)
			leftAttack();
		if(c<6)
			rightAttack();
		if(r>1)
			topAttack();
		if(r<6)
			downAttack();
	}
	
	private void leftAttack(){
		lrAttack(r,c-2);
	}
	private void rightAttack(){
		lrAttack(r,c+2);
	}
	private void lrAttack(int x, int y){
		if(x!=7)
			if(board[x+1][y].isVacant())
				moves.add(board[x+1][y]);
			else
				if(isEnemy(x+1,y))
					moves.add(board[x+1][y]);
		if(x!=0)
			if(board[x-1][y].isVacant())
				moves.add(board[x-1][y]);
			else
				if(isEnemy(x-1,y))
					moves.add(board[x-1][y]);
	}
	private void topAttack(){
		tdAttack(r-2,c);
	}
	private void downAttack(){
		tdAttack(r+2,c);
	}
	private void tdAttack(int x, int y){
		if(y!=7){
			if(board[x][y+1].isVacant())
				moves.add(board[x][y+1]);
			else
				if(isEnemy(x,y+1))
					moves.add(board[x][y+1]);
		}
		if(y!=0)
			if(board[x][y-1].isVacant())
				moves.add(board[x][y-1]);
			else
				if(isEnemy(x,y-1))
					moves.add(board[x][y-1]);
			
	}
	
	private void setupBishopMoves(){
		moves = new ConcurrentLinkedQueue<BoxPanel>();
		leftDMoves();
		rightDMoves();
		topDMoves();
		downDMoves();
	}
	
	private void leftDMoves(){
		int x=r-1,y=c-1;
		while((x>=0 && y>=0)&& board[x][y].isVacant()){
			moves.add(board[x--][y--]);
			if(board[r][c].getVarientName().equals("king"))
				break;
		}
		if(!(x<0 || y<0) && isEnemy(x,y)){
			if(board[r][c].getVarientName().equals("king") && (x+1!=r && y+1!=c))
				return;
			moves.add(board[x][y]);
		}
	}
	private void rightDMoves(){
		int x=r+1, y=c+1;
		while((x<=7 && y<=7)&& board[x][y].isVacant()){
			moves.add(board[x++][y++]);
			if(board[r][c].getVarientName().equals("king"))
				break;
		}
		if(!(x>7 || y>7)&& isEnemy(x,y)){
			if(board[r][c].getVarientName().equals("king") && (x-1!=r && y-1!=c))
				return;
			moves.add(board[x][y]);
		}
	}
	private void topDMoves(){
		int x=r-1, y=c+1;
		while((x>=0 && y<=7)&& board[x][y].isVacant()){
			moves.add(board[x--][y++]);
			if(board[r][c].getVarientName().equals("king"))
				break;
		}
		if(!(x<0 || y>7)&& isEnemy(x,y)){
			if(board[r][c].getVarientName().equals("king") && (x+1!=r && y-1!=c))
				return;
			moves.add(board[x][y]);
		}
	}
	private void downDMoves(){
		int x=r+1,y=c-1;
		while((x<=7 && y>=0)&& board[x][y].isVacant()){
			moves.add(board[x++][y--]);
			if(board[r][c].getVarientName().equals("king"))
				break;
		}
		if(!(x>7 || y<0)&& isEnemy(x,y)){
			if(board[r][c].getVarientName().equals("king") && (x-1!=r && y+1!=c))
				return;
			moves.add(board[x][y]);
		}
	}
	
	private void addMoveListener(){
		for(BoxPanel m:moves){
			final BoxPanel p = m;
			p.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					System.out.println("r="+r+" c="+c);
					MouseListener s[]=board[r][c].getMouseListeners();
					VarientLabel vl=null;
					if(s.length!=0)
						System.out.println("XXXX"+s[0].toString());
					System.out.println("R="+r+"C="+c);
					vl =(VarientLabel) board[r][c].getComponent(0);
					board[r][c].removeAll();
					p.removeAll();
					p.add(vl);
					board[r][c].setBorder(null);
					board[r][c].repaint();
					p.repaint();
					p.setVarientName(board[r][c].getVarientName());
					p.setVarientTeam(board[r][c].getVarientTeam());
					p.setAdditionalInfo("");
				//	p.setVacant(false);
					
					board[r][c].setVacant(true);
					board[r][c].setVarientName("");
					board[r][c].setVarientTeam("");
					board[r][c].setAdditionalInfo("");
					board[r][c].removeMouseListener(MovementListener.this);
					if(p.getVarientTeam().equals("white")){
						setLocation(white);
						white[rT][cT] = p;
						if(!p.isVacant()){
							for(int i=0; i<black.length; i++)
								for(int j=0; j<black[i].length; j++){
									if(black[i][j]==p){
										black[i][j]=null;
									}
								}
						}
						toogleChance(black, white);
					}
					else{
						System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						setLocation(black);
						black[rT][cT] = p;
						if(!p.isVacant()){
							for(int i=0;i<white.length; i++)
								for(int j=0;j<white[i].length;j++){
									if(white[i][j]==p){
										white[i][j]=null;
									}
								}
						}
						toogleChance(white, black);
					}
					resetList();
					getIndexes(p);
					setMoves();					
					confirmCheck(p);
					resetList();
					p.setVacant(false);
					//resetList();
				//	p.removeMouseListener(MovementListener.this);
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {	
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
				}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {
				}
			});
		}
	}
	
	private void resetList(){
		for(BoxPanel mvs:moves){
			BoxPanel p = mvs;
			MouseListener[] m = p.getMouseListeners();
			if(m.length!=0)
				p.removeMouseListener(m[0]);
			p.setBorder(null);
			p.repaint();
		}
	}
	
	private void toogleChance(BoxPanel[][] teamA, BoxPanel[][] teamI){
		if(blackActive)
			blackActive=false;
		else
			blackActive=true;
		if(getKing(teamA).isOnCheck()){
		}	
		else
			for(int i=0; i< teamA.length; i++)
				for(int j=0; j< teamA[i].length; j++){
					if(teamA[i][j]!=null){
						teamA[i][j].addMouseListener(MovementListener.this);
						System.out.println(teamA[i][j].getVarientName());
					}
					if(teamI[i][j]!=null){
						teamI[i][j].removeMouseListener(MovementListener.this);
					}
				}
	}
	
	public void setLocation(BoxPanel[][] team){
		for(int i=0; i<team.length; i++)
			for(int j=0; j<team[i].length; j++){
				if(team[i][j] == board[r][c]){
					rT = i;
					cT = j;
				}
			}
	}
	private void confirmCheck(BoxPanel p){
		BoxPanel bDT[][]=null;
		BoxPanel bAT[][]=null;
		if(p.getVarientTeam().equals("white")){
			bDT=black;
			bAT=white;
		}
		else{
			bDT=white;
			bAT=black;
		}
		setCheck(getKing(bDT),bAT);
		setCheck(getKing(bAT),bDT);
	}
	private void setCheck(BoxPanel p, BoxPanel bT[][]){
		for(int i=0; i<bT.length;i++){
			for(int j=0;j<bT[i].length; j++){
				getIndexes(bT[i][j]);
				setMoves();
				Iterator<BoxPanel> iter = moves.iterator();
				while(iter.hasNext())
					if(p==iter.next()){
						p.setOnCheck(true);
						return;
					}
			}
		}	
		p.setOnCheck(false);
	}
	
	private BoxPanel getKing(BoxPanel [][]p){
		for(int i=0; i<p.length; i++)
			for(int j=0; j<p[i].length; j++)
				if(p[i][j]!=null && p[i][j].getVarientName().equals("king"))
					return p[i][j];
		return null;
	}	

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}