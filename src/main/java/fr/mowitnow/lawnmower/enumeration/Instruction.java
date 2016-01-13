package fr.mowitnow.lawnmower.enumeration;

/**
 * Orientation Enum
 * @author zkourrid
 *
 */
public enum Instruction {
	
	//--------------------------------------------------------------------------
    // Enumeration values
    //--------------------------------------------------------------------------
	
	/** Right */
	D, 
	/** Left */
	G, 
	/** Go forward */
	A;
	
	// -------------------------------------------------------------------------
    // Utility methods
    // -------------------------------------------------------------------------
	
	/**
    * Returns the enumeration value corresponding to the specified instruction.
    * @param  instruction : the value of the enumeration to find.
    * @return the enumeration.
    * @throws IllegalArgumentException if the specified instruction can not
    *         be matched against a known enumeration value.
    */
	public static Instruction fromString(String instruction){
		Instruction result = null;
		if(instruction != null && !instruction.isEmpty()){
			for(Instruction i : Instruction.values()){
				if(i.toString().equals(instruction)){
					result = i;
					break;
				}
			}
		}
		if(result == null){
			 throw new IllegalArgumentException(instruction);
		}
		return result;
	}
}
