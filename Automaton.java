import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is the superclass for the generation class and will be used to generate an Automaton based on a give rule
 * The class can also be used to change the symbols representing true/false in the automaton along with saving a loading
 * Automatons to and from a file.
 * @author Carter Bogie
 * @version 2.4
 */
public class Automaton {		
	
	/**
	 * Portion of Automaton that contains the values for true/false symbol, 
	 * the rule the automaton is constructed on and the list of generations
	 * that make up the Automaton
	 */
	private char falseSymbol = '0';
	private char trueSymbol = '1';
	private Rule rule;
	private ArrayList<Generation> generationList = new ArrayList<Generation>();
	
	/**
	 * Constructor that given a rule number and an initial state will construct a new automaton object
	 * @param ruleNum int representation of the rule number
	 * @param initState boolean[] that represents initial state
	 */
	public Automaton(int ruleNum, boolean[] initState) {
		//Create a new rule based on ruleNum and assign to Automaton
		rule = new Rule(ruleNum);
		//Create a new generation based on the initstate and add it to the automaton generation list	
		generationList.add(new Generation(initState));
	}
	
	/**
	 * Constructor that given a file name will read the file and create an automaton based on the file contents
	 * @param fileName String representation of the file name
	 * @throws IOException
	 */
	public Automaton(String fileName) throws IOException{
		//Create new buffered reader assigned to the given file name
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		//Create a new rule after reading rule num from file
		this.rule = new Rule(Integer.parseInt(br.readLine()));
		//Pull the True/False symbol from the file and save it to a string
		String TF = br.readLine();
		//Split the true/false string so the symbols are separated and save them to an array
		String[] trueFalseSplit = TF.split(" ", 2);
		falseSymbol = trueFalseSplit[0].charAt(0);
		trueSymbol = trueFalseSplit[1].charAt(0);
		
		//Create a new generation based on the info pulled from the file and add it to the generation list
		this.generationList.add(new Generation(br.readLine(), trueSymbol, falseSymbol));
		
		//Close the buffered reader
		br.close();
	}
		
	/**
	 * Getter method that returns the rule number with the help of Rule.getRuleNum()
	 * @return ruleNum
	 * @see Rule#getRuleNum()
	 */
	public int getRuleNum() {
		return rule.getRuleNum();
	}
		
	/**
	 * Method that evolves the Automaton a given number of times and adds each new generation to the generation list
	 * @param numSteps number of Automaton evolutions
	 */
	public void evolve(int numSteps) {
		//For each step evolve the automaton based on the last generation in the generation list
		for(int idx = 0; idx < numSteps; ++idx) {
			generationList.add(generationList.get(generationList.size() - 1).evolveGeneration(rule));
		}
	}
		
	/**
	 * Getter method that returns the total number of steps in an Automaton
	 * Use Generations number minus 1 because initial state is generation 0 not 1
	 * @return totalSteps int value of total number of steps in an automaton
	 */
	public int getTotalSteps() {
		return this.generationList.size() - 1;
	}
	
	/**
	 * Getter method that returns a copy of a state given a step number
	 * @param stepNum int value of targeted step
	 * @return copyState boolean[] that is a copy of the targeted state
	 */
	public boolean[] getState(int stepNum) {
		boolean[] copyState = generationList.get(stepNum).getGenerationState();
		return copyState;
	}
	
	/**
	 * Getter method that returns a string representation of the contents of the cell that makes up a generation
	 * after a given state using the defined true and false symbols
	 * @param stepNum int value of targeted step
	 * @return stateString a string representation of a state
	 */
	public String getStateString(int stepNum) {
		String stateString = "";
		//Pull the targeted step and replace all true cells with true symbol and false cells with false symbol
		for(boolean state : generationList.get(stepNum).getGenerationState()) {
			if(state == true) {
				stateString += trueSymbol;
			}
			else {
				stateString += falseSymbol;
			}
		}
		return stateString.trim();
	}
	
	/**
	 * Setter method that allows you to set the True Symbol
	 * @param symbol char that will replace true symbol
	 */
	public void setTrueSymbol(char symbol) {
		trueSymbol = symbol;
	}
	
	/**
	 * Setter method that allows you to set the False Symbol
	 * @param symbol char that will replace false symbol
	 */
	public void setFalseSymbol(char symbol) {
		falseSymbol = symbol;
	}
	
	/**
	 * Getter method that returns a copy of the true symbol
	 * @return copyTrueSymbol a char copy of the Automaton true symbol
	 */
	public char getTrueSymbol() {
		char copyTrueSymbol = trueSymbol;
		return copyTrueSymbol;
	}
	
	/**
	 * Getter method that returns a copy of the false symbol
	 * @return copyFalseSymbol a char copy of the Automation false symbol
	 */
	public char getFalseSymbol() {
		char copyFalseSymbol = falseSymbol;
		return copyFalseSymbol;
	}
	
	/**
	 * Save method that given a file name saves the output of the toString method to a file
	 * Overwrite the content of the file if it already exists create it if it doesn't
	 * @param filename String file name that contents will be saved to
	 * @throws IOException
	 */
	public void save(String filename) throws IOException{
		//Create a buffered reader assigned to the given file name
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		
		//Create a new file based on given file name
		File file = new File(filename);
		
		//Check to see if the file exists if not create it
		if(!file.exists()) {
			file.createNewFile();
		}
		//Write the data to the file using the toString method of Automaton
		bw.write(this.toString());
		
		//Flush and close the buffered reader
		bw.flush();
		bw.close();
	}
	
	/**
	 * toString method that returns a string representation of every generation in the Automaton
	 * Consists of the state string of each generation joined by newline characters
	 */
	@Override
	public String toString() {
		String ECAString = "";
		//Collect each generations state string and add a newline char to the end
		for(int idx = 0; idx < generationList.size(); ++idx) {
			ECAString += getStateString(idx) + "\n";
		}
		//Trim extra whitespace and return 
		return ECAString.trim();
	}
}
