/**
 * Rule class that can given a rule number produce a char[] that will be used to represent the rule
 * and calculate the values of a generation for automaton evolution.
 * Convert the rule number to binary and then plug the char representation of each value in the string
 * into the ruleCodeArray
 * @author Carter Bogie
 * @version 2.3
 */
public class Rule {
	
	/**
	 * Default size value for all ruleCodeArrays that contains 8-bit binary representation of the rule
	 */
	final private int RULE_CODE_DEFAULT_SIZE = 8;
	
	/**
	 * List of variables that makes up each rule array
	 * An int to represent the rule number
	 * A char[] to store the 8-bit representation of the rule
	 * A String to store the binary value of the rule before plugging into the array
	 */
	private int ruleNum;
	private char[] ruleCodeArray = new char[RULE_CODE_DEFAULT_SIZE];
	private String ruleBinaryValue;
	
	/**
	 * Rule constructor that given a rule number formats the rule into a string representation of 
	 * the binary value of the rule with a length of 8 then plugs the values into the array
	 * @param ruleNum an int rule number the rule object is based on
	 */
	public Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		
		ruleBinaryValue = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ruleNum))); 
		//See GitHub notes^^^
		
		for(int idx = 0; idx < RULE_CODE_DEFAULT_SIZE; ++idx) {
			ruleCodeArray[idx] = ruleBinaryValue.charAt(idx);
		}
	}
	
	/**
	 * Method of rule that calculates the value of a given cell given the 
	 * boolean values of the target cell and its neighboring cells
	 * @param leftCell boolean state of the cell to the left of the target
	 * @param targetCell boolean state of the target cell
	 * @param rightCell boolean state of the cell to the right of the target
	 * @return boolean state of the target cell in the next evolution
	 */
	public boolean calcCellNextEvolutionVal(boolean leftCell, boolean targetCell, boolean rightCell) {
		
		//If the cells are true represent them as a 1 in the calculation below
		int leftCellValue = (leftCell == true ? 1 : 0);
		int targetCellValue = (targetCell == true ? 1 : 0);
		int rightCellValue = (rightCell == true ? 1 : 0);
		
		//Calculate the cell in the ruleCodeArray that will apply to the target cell
		int rulesIdx = (4 * leftCellValue + 2 * targetCellValue + rightCellValue);
		if(ruleCodeArray[7 - rulesIdx] == '1') {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Getter method that returns a copy of the ruleNum 
	 * @return copyRuleNum a copy of the ruleNum
	 */
	public int getRuleNum() {
		int copyRuleNum = ruleNum;
		return copyRuleNum;
	}
	
	/**
	 * toString method that returns a string representation of a rules in the format below
	 * Format: "RuleNum: ruleNum WolframCode: ruleWolframCode
	 * @return resultString the formatted string representation of a rule
	 */
	@Override
	public String toString() {
		
		//Get a String representation of the rule number
		String ruleNumCopy = String.valueOf(ruleNum);
		//Create the result string in the given format
		String resultString = "Rule Number: " + ruleNumCopy + " Wolfram Code: ";
		
		//Add the wolfram code to the result string
		for(int idx = 0; idx < ruleCodeArray.length; ++idx) {
			resultString += String.valueOf(ruleCodeArray[idx]);
		}
		return resultString;
	}
}
