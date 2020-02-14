/*
 * 
 */
public class Rule {
	
	final private int RULE_CODE_DEFAULT_SIZE = 8;
	
	private int ruleNum;
	private char[] ruleCodeArray = new char[RULE_CODE_DEFAULT_SIZE];
	private String ruleBinaryValue;
	
	//Rule constructor for rule given a rule number
	public Rule(int ruleNum) {
		this.ruleNum = ruleNum;
		
		ruleBinaryValue = String.format("%08d", Integer.parseInt(Integer.toBinaryString(ruleNum))); 
		//See GitHub notes^^^
		
		for(int idx = 0; idx < RULE_CODE_DEFAULT_SIZE; ++idx) {
			ruleCodeArray[idx] = ruleBinaryValue.charAt(idx);
		}
	}
	
	//Calculate cells state based on a rule index
	public char calcCellNextEvolutionVal(char leftCell, char targetCell, char rightCell) {
		
		int leftCellValue = (leftCell == Automaton.getTrueSymbol() ? 1 : 0);
		int targetCellValue = (targetCell == Automaton.getTrueSymbol() ? 1 : 0);
		int rightCellValue = (rightCell == Automaton.getTrueSymbol() ? 1 : 0);
		
		int rulesIdx = (4 * leftCellValue + 2 * targetCellValue + rightCellValue);
		return ruleCodeArray[7 - rulesIdx]; //this may be named differently I have not defined RuleNumber yet!
		}
	
	//Getter method that returns a copy of the ruleNum, helper for Automaton getRuleNum()
	public int getRuleNum() {
		int copyRuleNum = this.ruleNum;
		return copyRuleNum;
	}
	
	//toString method that return in format "RuleNum: rulenum WolframCode: ruleWolframCode
	@Override
	public String toString() {
		String ruleNumCopy = String.valueOf(this.ruleNum);
		String resultString = "Rule Number: " + ruleNumCopy + " Wolfram Code: ";
		
		//Add the wolfram code to the result string
		for(int idx = 0; idx < ruleCodeArray.length; ++idx) {
			resultString += String.valueOf(ruleCodeArray[idx]);
		}
		return resultString;
	}
}
