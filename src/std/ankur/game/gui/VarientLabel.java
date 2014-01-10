/**
 * @author Ankur Kumar Kumawat
 * 	Used for Chess variant image.
 */
package std.ankur.game.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class VarientLabel extends JLabel {
	File file;	
	public VarientLabel(File file){
		super();
		this.file = file;
	}	
	public void paintComponent(Graphics g){
		BufferedImage img = null;
		try{
			img = ImageIO.read(file);
			
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		g.drawImage(img,  5, 5, this.getWidth()-10, this.getHeight()-10, null);
	}
}
