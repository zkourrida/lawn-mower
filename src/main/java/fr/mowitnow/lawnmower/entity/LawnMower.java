package fr.mowitnow.lawnmower.entity;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.mowitnow.lawnmower.enumeration.Instruction;
import fr.mowitnow.lawnmower.enumeration.Orientation;

/**
 * Lawn mower entity
 * @author zkourrid
 *
 */
public class LawnMower {
	
	//--------------------------------------------------------------------------
	// Class members
	//--------------------------------------------------------------------------
	
	/** lawn mower settings */
	private LawnMowerSetting lawnMowerSetting;
	/** lawn area settings */
	private LawnAreaSetting lawnAreaSetting;
	/** lawn mower instructions */
	private List<Instruction> instructions;
	
	private static final Logger LOG = LogManager.getLogger();
	
	//--------------------------------------------------------------------------
	// Constructors
	//--------------------------------------------------------------------------
	
	/**
	 * LawnMower default constructor
	 */
	public LawnMower(){
		this.lawnMowerSetting = new LawnMowerSetting();
		this.lawnAreaSetting = new LawnAreaSetting();
	}
	
	/**
	 * LawnMower constructor
	 * @param lawnAreaSetting : lawn mower settings
	 * @param lawnMowerSetting : lawn area settings
	 * @param instructions : lawn mower instructions
	 */
	public LawnMower(LawnAreaSetting lawnAreaSetting, 
			LawnMowerSetting lawnMowerSetting, List<Instruction> instructions){
		this.lawnMowerSetting = lawnMowerSetting;
		this.lawnAreaSetting = lawnAreaSetting;
		this.instructions = instructions;
	}
	
	//--------------------------------------------------------------------------
	// Methods
	//--------------------------------------------------------------------------
	
	/**
	 * Run the lawn mower 
	 * @return LawnMowerSetting object to indicate its final position.
	 * <code>null</code>, in case one of class members is not set.  
	 */
	public LawnMowerSetting cutGrass() {
		if(this.lawnMowerSetting == null){
			LOG.error("This lawn mower cannot run without mower parameters"); 
			return null;
		}
		if(this.lawnAreaSetting == null){
			LOG.error("This lawn mower cannot run without area parameters"); 
			return null;
		}
		if(instructions == null || instructions.isEmpty()){
			LOG.error("This lawn mower cannot run without instructions"); 
			return null;
		}
		int nextX;
		int nextY;
		for(Instruction instruction : instructions){
			if(instruction != null){
				if(instruction != Instruction.A){
					try{
						this.lawnMowerSetting.setO(
							Orientation.getNextOrientation(
								this.lawnMowerSetting.getO(), instruction));
					} catch (IllegalArgumentException iae){
						LOG.error(
								"{} is not a correct orientation. Message : {}", 
								this.lawnMowerSetting.getO(), iae.getMessage());
						return null;
					}
				} else {
					nextX = this.lawnMowerSetting.getX();
					nextY = this.lawnMowerSetting.getY();
					switch (this.lawnMowerSetting.getO()){
					case N :
						nextY++;
						break;
					case E : 
						nextX++;
						break;
					case W : 
						nextX--;
						break;
					case S :
						nextY--;
						break;
					default:
						LOG.warn("{} is not a valid instruction", 
								instruction); 
					}
					if(canMoveForward(nextX, nextY)){
						this.lawnMowerSetting.setX(nextX);
						this.lawnMowerSetting.setY(nextY);
					}
				}
			}
		}
		return this.lawnMowerSetting;
	}
	
	/**
	 * Check if the lawn mower is still inside the lawn area
	 * @return <code>true</code> if still inside, <code>false</code> otherwise 
	 */
	private boolean canMoveForward(int nextX, int nextY){
		if(nextX>=this.lawnAreaSetting.getLdx() || 
				nextX<=this.lawnAreaSetting.getRtx() || 
		   nextY>=this.lawnAreaSetting.getLdy() || 
		   		nextY<=this.lawnAreaSetting.getRty()
		){
			return true;
		}
		return false;
	}
	
	//--------------------------------------------------------------------------
	// Setters and getters
	//--------------------------------------------------------------------------

	public LawnMowerSetting getLawnMowerSetting() {
		return lawnMowerSetting;
	}

	public void setLawnMowerSetting(LawnMowerSetting lawnMowerSetting) {
		this.lawnMowerSetting = lawnMowerSetting;
	}

	public LawnAreaSetting getLawnAreaSetting() {
		return lawnAreaSetting;
	}

	public void setLawnAreaSetting(LawnAreaSetting lawnAreaSetting) {
		this.lawnAreaSetting = lawnAreaSetting;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}
	
}
