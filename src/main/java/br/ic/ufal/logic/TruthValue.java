package br.ic.ufal.logic;

/**
 * Defines what is the representation that would be considered in the truth
 * table construction, if it's binary (O and 1) or true and false values (T and
 * F)
 * 
 * @author Anderson Santos
 * 
 */
public class TruthValue {
	public static int TRUE_FALSE = 0, ZERO_ONE = 1;

	/**
	 * 
	 * @param value
	 * @param displayMethod
	 * @return valueString
	 */
	public static String getTruthValueString(final boolean value,
			final int displayMethod) {

		String valueString;

		if (displayMethod == TRUE_FALSE) {
			valueString = (value == true) ? "V" : "F";
		} else {
			valueString = (value == true) ? "1" : "0";
		}
		return valueString;
	}
}