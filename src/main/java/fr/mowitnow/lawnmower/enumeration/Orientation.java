package fr.mowitnow.lawnmower.enumeration;

/**
 * Instruction Enum
 * @author zkourrid
 *
 */
public enum Orientation {

	//--------------------------------------------------------------------------
	// Enumeration values
	//--------------------------------------------------------------------------

	/** North */
	N, 
	/** East */
	E, 
	/** West */
	W, 
	/** South */
	S;

	// -------------------------------------------------------------------------
	// Utility methods
	// -------------------------------------------------------------------------

	/**
	 * Returns an orientation based on the given instruction.
	 * @param  orientation : the value of the enumeration to find.
	 * @param  instruction : the value of the enumeration to find.
	 * @return Orientation value.
	 * @throws IllegalArgumentException if the given orientation or instruction 
	 * 		  are not correct.
	 */
	public static Orientation getNextOrientation(Orientation orientation, 
			Instruction instruction){
		if(orientation != null && instruction != null) {
			switch(orientation) {
			case N : 
				switch(instruction) {
				case D : 
					return E;
				case G :
					return W;
				default:
					return orientation;
				}
			case E : 
				switch(instruction) {
				case D : 
					return S;
				case G :
					return N;
				default:
					return orientation;
				}
			case W : 
				switch(instruction) {
				case D : 
					return N;
				case G :
					return S;
				default:
					return orientation;
				}
			case S :
				switch(instruction) {
				case D : 
					return W;
				case G :
					return E;
				default:
					return orientation;
				}
			default:
				return orientation;
			}
		} else {
			throw new IllegalArgumentException(orientation.toString());
		}
	}

	/**
	 * Returns the enumeration value corresponding to the specified orientation.
	 * @param  orientation : the value of the enumeration to find.
	 * @return the enumeration.
	 * @throws IllegalArgumentException if the specified orientation can not
	 *         be matched against a known enumeration value.
	 */
	public static Orientation fromString(String orientation){
		Orientation result = null;
		if(orientation != null && !orientation.isEmpty()){
			for(Orientation o : Orientation.values()){
				if(o.toString().equals(orientation)){
					result = o;
					break;
				}
			}
		}
		if(result == null){
			throw new IllegalArgumentException(orientation);
		}
		return result;
	}
}
