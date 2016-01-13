package fr.mowitnow.lawnmower.entity;

import fr.mowitnow.lawnmower.util.Utils;

/**
 * Lawn area setting entity
 * @author zkourrid
 *
 */
public class LawnAreaSetting {
	
	//--------------------------------------------------------------------------
	// Class members
	//--------------------------------------------------------------------------

	/** Default right donw corner coordinates */
	private static final String DEFAULT_LEFT_DOWN_CORNER = "0 0";
	
	/** Cartesian left down corner coordinates x-axis */
	private int ldx; 
	/** Cartesian left down corner coordinates y-axis */
	private int ldy;
	
	/** Cartesian right top corner coordinates x-axis */
	private int rtx; 
	/** Cartesian right top corner coordinates y-axis */
	private int rty;
	
	//--------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------

	/**
	 * Default LawnAreaSetting constructor
	 */
	public LawnAreaSetting() {
		setLeftDownCorner(DEFAULT_LEFT_DOWN_CORNER);
		setRightTopCorner(DEFAULT_LEFT_DOWN_CORNER);
	}
	
	/**
	 * 
	 * @param rightTopCorner : Cartesian right top corner coordinates "x y"
	 */
	public LawnAreaSetting(String rightTopCorner) {
		setLeftDownCorner(DEFAULT_LEFT_DOWN_CORNER);
		setRightTopCorner(rightTopCorner);
	}
	
	/**
	 * LawnAreaSetting constructor
	 * @param leftDownCorner : Cartesian left down corner coordinates "x y"
	 * @param rightTopCorner : Cartesian right top corner coordinates "x y"
	 */
	public LawnAreaSetting(String leftDownCorner, String rightTopCorner) {
		setLeftDownCorner(leftDownCorner);
		setRightTopCorner(rightTopCorner);
	}
	
	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------
	
	/**
	 * Set left down corner coordinates
	 * @param coordinates : Cartesian left down corner coordinates "x y"
	 */
	public void setLeftDownCorner(String coordinates){
		int[] xAndy = Utils.extractCoordonatesFromString(coordinates);
		this.ldx = xAndy[0];
		this.ldy = xAndy[1];
	}
	
	/**
	 * Set right top corner coordinates
	 * @param coordinates : Cartesian right top corner coordinates "x y"
	 */
	public void setRightTopCorner(String coordinates){
		int[] xAndy = Utils.extractCoordonatesFromString(coordinates);
		this.rtx = xAndy[0];
		this.rty = xAndy[1];
	}
	
	//--------------------------------------------------------------------------
	// Setters and getters
	//--------------------------------------------------------------------------
	
	public int getLdx() {
		return ldx;
	}
	public void setLdx(int ldx) {
		this.ldx = ldx;
	}
	public int getLdy() {
		return ldy;
	}
	public void setLdy(int ldy) {
		this.ldy = ldy;
	}
	public int getRtx() {
		return rtx;
	}
	public void setRtx(int rtx) {
		this.rtx = rtx;
	}
	public int getRty() {
		return rty;
	}
	public void setRty(int rty) {
		this.rty = rty;
	}
	
	
}
