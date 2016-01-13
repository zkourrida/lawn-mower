package fr.mowitnow.lawnmower;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.mowitnow.lawnmower.entity.LawnMower;
import fr.mowitnow.lawnmower.entity.LawnMowerSetting;
import fr.mowitnow.lawnmower.util.Utils;

/**
 * Lawn mower runner
 * @author zkourrid
 *
 */
public final class LawnMowerRunner {
	
	/** instructions file to load */
	private static final String INSTRUCTIONS_FILE_NAME = "instructions.txt";
	private static final Logger LOG = LogManager.getLogger();
	
	public static void main(String[] args) {
		List<LawnMower> lawnMowers = 
				Utils.extractLawnMowers(INSTRUCTIONS_FILE_NAME);
		LawnMowerSetting lawnMowerSetting;
		if(lawnMowers != null){
			for(LawnMower lawnMower : lawnMowers){
				if(lawnMower != null){
					lawnMowerSetting = lawnMower.cutGrass();
					if(lawnMowerSetting != null){
						LOG.info("Job done ! This is my position ({}, {}, {}).", 
								lawnMowerSetting.getX(), 
								lawnMowerSetting.getY(), 
								lawnMowerSetting.getO());
					}
				}
			}
		}
	}

	/**
	 * Constructor never called
	 */
	private LawnMowerRunner(){
		//not called
	}
}
