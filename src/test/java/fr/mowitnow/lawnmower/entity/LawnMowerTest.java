package fr.mowitnow.lawnmower.entity;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fr.mowitnow.lawnmower.enumeration.Instruction;
import fr.mowitnow.lawnmower.enumeration.Orientation;
import fr.mowitnow.lawnmower.util.Utils;

public class LawnMowerTest {

	/**
	 * Set lawn area and two lawn mowers.
	 * Run the two lawn mowers.
	 * Check the returned values
	 */
	@Test
	public void cutGrassTest() {
		LawnAreaSetting lawnAreaSetting = new LawnAreaSetting("5 5");
		LawnMowerSetting lawnMowerSetting = new LawnMowerSetting("1 2 N");
		List<Instruction> instructions = 
				Utils.instructionsFromString("GAGAGAGAA");
		
		LawnMower lawnMower = new LawnMower(lawnAreaSetting, 
											lawnMowerSetting,
											instructions);
		
		LawnMowerSetting result = lawnMower.cutGrass();
		
		assertEquals(result.getX(), 1);
		assertEquals(result.getY(), 3);
		assertEquals(result.getO(), Orientation.fromString("N"));
		
		lawnMowerSetting = new LawnMowerSetting("3 3 E");
		instructions = Utils.instructionsFromString("AADAADADDA");
		
		lawnMower = new LawnMower(lawnAreaSetting, 
								  lawnMowerSetting,
								  instructions);
		
		result = lawnMower.cutGrass();
		
		assertEquals(result.getX(), 5);
		assertEquals(result.getY(), 1);
		assertEquals(result.getO(), Orientation.fromString("E"));
	}	
}
