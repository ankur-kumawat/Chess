/**
 * @author Ankur Kumar Kumawat
 * Extends the JPanel to provide specific fields for use in Chess
 */
package std.ankur.game.gui;

import javax.swing.JPanel;

public class BoxPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private boolean vacant=true;
	private String varientName;
	private String varientTeam;
	private String additionalInfo;
	private boolean onCheck;
	
	public BoxPanel() {
		vacant = true;
		varientName = "";
		varientTeam = "";
		additionalInfo = "";
		onCheck=false;
	}
	/**
	 * @return the vacant
	 */
	public boolean isVacant() {
		return vacant;
	}
	/**
	 * @param vacant the vacant to set
	 */
	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}
	/**
	 * @return the varientTeam
	 */
	public String getVarientTeam() {
		return varientTeam;
	}
	/**
	 * @param varientTeam the varientTeam to set
	 */
	public void setVarientTeam(String varientTeam) {
		this.varientTeam = varientTeam;
	}
	/**
	 * @return the varientName
	 */
	public String getVarientName() {
		return varientName;
	}
	/**
	 * @param varientName the varientName to set
	 */
	public void setVarientName(String varientName) {
		this.varientName = varientName;
	}
	/**
	 * @return the additionalInfo
	 */
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	/**
	 * @param additionalInfo the additionalInfo to set
	 */
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	/**
	 * @return the onCheck
	 */
	public boolean isOnCheck() {
		return onCheck;
	}
	/**
	 * @param onCheck the onCheck to set
	 */
	public void setOnCheck(boolean onCheck) {
		this.onCheck = onCheck;
	}
}