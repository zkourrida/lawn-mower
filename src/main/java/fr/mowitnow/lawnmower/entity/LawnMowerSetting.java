package fr.mowitnow.lawnmower.entity;

import fr.mowitnow.lawnmower.enumeration.Orientation;
import fr.mowitnow.lawnmower.util.Utils;

/**
 * Lawn mower setting entity
 * @author zkourrid
 *
 */
public class LawnMowerSetting {

	//--------------------------------------------------------------------------
	// Class members
	//--------------------------------------------------------------------------

	/** Cartesian coordinates x-axis */
	private int x;
	/** Cartesian coordinates y-axis */
	private int y;
	/** Geographical orientation */
	private Orientation o;

	//--------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------

	/**
	 * Default LawnMowerSetting constructor
	 */
	public LawnMowerSetting(){
		this.x = 0;
		this.y = 0;
		this.o = Orientation.N;
	}

	/**
	 * LawnMowerSetting constructor
	 * @param x : Cartesian coordinates x-axis
	 * @param y : Cartesian coordinates y-axis
	 * @param o : Geographical orientation
	 */
	public LawnMowerSetting(int x, int y, Orientation o){
		this.x = x;
		this.y = y;
		this.o = o;
	}
	
	/**
	 * LawnMowerSetting constructor
	 * @param coordinates : Cartesian coordinates and orientation "x y O"
	 */
	public LawnMowerSetting(String coordinates){
		int[] xAndy = Utils.extractCoordonatesFromString(coordinates);
		this.x = xAndy[0];
		this.y = xAndy[1];
		this.o = Utils.extractOrientationFromString(coordinates);
	}

	//--------------------------------------------------------------------------
	// Setters and getters
	//--------------------------------------------------------------------------

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Orientation getO() {
		return o;
	}
	public void setO(Orientation o) {
		this.o = o;
	}
}
