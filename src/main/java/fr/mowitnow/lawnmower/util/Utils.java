package fr.mowitnow.lawnmower.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.mowitnow.lawnmower.entity.LawnAreaSetting;
import fr.mowitnow.lawnmower.entity.LawnMower;
import fr.mowitnow.lawnmower.entity.LawnMowerSetting;
import fr.mowitnow.lawnmower.enumeration.Instruction;
import fr.mowitnow.lawnmower.enumeration.Orientation;

/**
 * Utilities
 * @author zkourrid
 *
 */
public final class Utils {

	private static final Logger LOG = LogManager.getLogger();
	
	/**
	 * Extract two Cartesian coordinates x and y from the given string value
	 * @param coordinates : Cartesian coordinates "x y"
	 * @return list of two integers corresponding to the Cartesian coordinates 
	 * 		   x and y
	 */
	public static int[] extractCoordonatesFromString(String coordinates){
		if(coordinates != null && coordinates.contains(" ")){
			String[] slices = coordinates.split(" ");
			if (slices!= null && slices.length >= 2){
				int[] xAndy = new int[2];
				try{
					xAndy[0] = Integer.parseInt(slices[0]);
					xAndy[1] = Integer.parseInt(slices[1]);
				}catch(NumberFormatException nfe){
					LOG.error("{} are not correct coordinates. Message : {}", 
							coordinates, nfe.getMessage());
				}
				return xAndy;
			}
		}
		LOG.error("{} are not correct coordinates.", coordinates);
		return null;
	}
	
	/**
	 * Extract orientation from the given coordinates string value
	 * @param coordinates : Cartesian coordinates and orientation "x y O"
	 * @return the "O" value of the given coordinates "x y O" 
	 */
	public static Orientation extractOrientationFromString(String coordinates){
		if(coordinates != null && coordinates.contains(" ")){
			String[] slices = coordinates.split(" ");
			if (slices.length == 3){
				Orientation orientation = null;
				try{
					orientation = Orientation.fromString(slices[2]);
				}catch(IllegalArgumentException iae){
					LOG.error("{} is not a correct orientation. Message : {}", 
							slices[2], iae.getMessage());
				}
				return orientation;
			}
		}
		LOG.error("{} are not correct coordinates.", coordinates);
		return null;
	}
	
	/**
	 * Convert a string of multiple instructions to a list of instructions.
	 * @param instructions : concatenated instructions as a string value.
	 * @return list of instructions.
	 */
	public static List<Instruction> instructionsFromString(String instructions){
		List<Instruction> insts = new ArrayList<Instruction>();
		if(instructions != null && !instructions.contains(" ")){
			insts = new ArrayList<Instruction>();
			Instruction inst;
			for(int i=0; i<instructions.length(); i++){
				inst = Instruction.fromString(instructions.substring(i, i+1));
				if(inst != null){
					insts.add(inst);
				} else {
					LOG.warn("Null is not an instruction");
				}
			}
			return insts;
		}
		LOG.error("{} are not correct instructions.", instructions);
		return insts;
	}
	
	/**
	 * Load a setting file to extract lawn mowers settings
	 * @param instructionsFileName : the file name. 
	 * 	      this file is located in the application resources folder
	 * @return list of lawn mowers
	 */
	public static List<LawnMower> extractLawnMowers(
												String instructionsFileName){
		List<LawnMower> lawnMowers = new ArrayList<LawnMower>();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		if(cl == null){
			LOG.error("Cannot get the class loader");
			return lawnMowers;
		}
		URL url = cl.getResource(instructionsFileName);
		if(url == null){
			LOG.error("This file is not found {}", instructionsFileName);
			return lawnMowers;
		}
		File file = new File(url.getFile());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			LOG.error("Cannot find this file {}", instructionsFileName);
		}
		LawnAreaSetting lawnAreaSetting = null;
		LawnMowerSetting lawnMowerSetting = null;
		List<Instruction> instructions =  null;
		String line;
		String[] slices;
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			slices = null;
			if(line != null && !line.isEmpty()){
				if(line.contains(" ")){
					slices = line.split(" ");
					if(slices.length == 2){
						lawnAreaSetting = new LawnAreaSetting(line);
					} else if(slices.length == 3 && lawnAreaSetting != null){
						lawnMowerSetting = new LawnMowerSetting(line);
					}
				} else if(lawnAreaSetting != null && lawnMowerSetting != null){
					instructions = Utils.instructionsFromString(line);
					if(instructions != null){
						lawnMowers.add(new LawnMower(lawnAreaSetting, 
												 	 lawnMowerSetting, 
												 	 instructions));
					}
					lawnMowerSetting = null;
					instructions = null;
				}
			}
		}
		scanner.close();
		return lawnMowers;
	}
	
	/**
	 * Constructor never called
	 */
	private Utils() {
		//not called
	}

}
