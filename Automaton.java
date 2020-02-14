import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * 
 */
public class Automaton {

		private static String falseSymbol = "0";
		private static String trueSymbol = "1";
		private Rule rule;
		private ArrayList<Generation> generationList = new ArrayList<Generation>();
		
		public Automaton(int ruleNum, boolean[] initState) {
			String[] convertedTFArray = new String[initState.length];
			String initStateConverted = "";
			
			this.rule = new Rule(ruleNum);
			for(int idx = 0; idx < initState.length; ++idx) {
				convertedTFArray[idx] = (initState[idx] == true ? trueSymbol : falseSymbol);
				initStateConverted += convertedTFArray[idx];
			}
			
			this.generationList.add(new Generation(initStateConverted));
		}
		
		public Automaton(String fileName) throws IOException{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			this.rule = new Rule(Integer.parseInt(br.readLine())); 
			
			String[] trueFalseSplit = br.readLine().split(" ");
			falseSymbol = trueFalseSplit[0];
			trueSymbol = trueFalseSplit[1];
			
			this.generationList.add(new Generation(br.readLine()));
			
			br.close();
		}
		
		//getter method that returns ruleNum using helper method from rule
		public int getRuleNum() {
			return this.rule.getRuleNum();
		}
		
		//Evolve method that evolves the ECA a given number of steps
		public void evolve(int numSteps) {
			for(int idx = 0; idx < numSteps; ++idx) {
				this.generationList.add(generationList.get(generationList.size()).evolveGeneration(this.rule));
			}
		}
		
		//Getter method that returns the total number of steps of an ECA, which is generations minus 1 because initial state is gen 0
		public int getTotalSteps() {
			return this.generationList.size() - 1;
		}
		
		//Getter method that returns a copy of the states of a cell after a given step returned as an Array of ints
		public int[] getState(int stepNum) {
			int[] state = new int[this.generationList.get(stepNum + 1).toString().length()];
			
			for(int idx = 0; idx < state.length; ++idx) {
				state[idx] = Integer.parseInt(Character.toString(this.generationList.get(stepNum + 1).toString().charAt(idx)));
			}
			return state;
		}
		
		//Getter method that returns a string representation of the contents of the cell that make up a generation after a given state
		//using the defined true and false symbols
		public String getStateString(int stepNum) {
			return this.generationList.get(stepNum + 1).toString();
		}
		
		//Setter method that allows you to set the True Symbol
		public static void setTrueSymbol(char symbol) {
			trueSymbol = Character.toString(symbol);
		}
		
		//Setter method that allows you to set the False Symbol
		public static void setFalseSymbol(char symbol) {
			falseSymbol = Character.toString(symbol);
		}
		
		public static String getTrueSymbol() {
			String copyTrueSymbol = trueSymbol;
			return copyTrueSymbol;
		}
		
		public static String getFalseSymbol() {
			String copyFalseSymbol = falseSymbol;
			return copyFalseSymbol;
		}
		
		//Save method that saves the output of the toString method to a file with the given name.
		//Overwrite the content of the file if it already exists
		public void save(String filename) throws IOException{
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			
			File file = new File(filename);
			
			//Check to see if the file exists if not create it
			if(!file.exists()) {
				file.createNewFile();
			}
			
			for(int i = 0; i < this.generationList.size(); i++) {
				bw.write(this.toString());
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
		}
		
		//toString method return a string that represents the entire evolution of the ECA. 
		//Consists of the state string for every generation joined by newline characters
		@Override
		public String toString() {
			String ECAString = "";
			for(int idx = 0; idx < this.generationList.size(); ++idx) {
				ECAString += "\n" + this.getStateString(idx);
			}
			return ECAString;
		}
}
