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

		private char falseSymbol = '0';
		private char trueSymbol = '1';
		private Rule rule;
		private ArrayList<Generation> generationList = new ArrayList<Generation>();
		
		public Automaton(int ruleNum, boolean[] initState) {
			rule = new Rule(ruleNum);
			
			generationList.add(new Generation(initState));
		}
		
		public Automaton(String fileName) throws IOException{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			this.rule = new Rule(Integer.parseInt(br.readLine()));
			String TF = br.readLine();
			String[] trueFalseSplit = TF.split(" ", 2);
			falseSymbol = trueFalseSplit[0].charAt(0);
			trueSymbol = trueFalseSplit[1].charAt(0);
			
			this.generationList.add(new Generation(br.readLine(), trueSymbol, falseSymbol));
			
			
			br.close();
		}
		
		//getter method that returns ruleNum using helper method from rule
		public int getRuleNum() {
			return rule.getRuleNum();
		}
		
		//Evolve method that evolves the ECA a given number of steps
		public void evolve(int numSteps) {
			for(int idx = 0; idx < numSteps; ++idx) {
				generationList.add(generationList.get(generationList.size() - 1).evolveGeneration(rule));
			}
		}
		
		//Getter method that returns the total number of steps of an ECA, which is generations minus 1 because initial state is gen 0
		public int getTotalSteps() {
			return this.generationList.size() - 1;
		}
		
		//Getter method that returns a copy of the states of the cells in a generation after a given step
		public boolean[] getState(int stepNum) {
			boolean[] copyState = generationList.get(stepNum).getGenerationState();
			return copyState;
		}
		
		//Getter method that returns a string representation of the contents of the cell that make up a generation after a given state
		//using the defined true and false symbols
		public String getStateString(int stepNum) {
			String stateString = "";
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
		
		//Setter method that allows you to set the True Symbol
		public void setTrueSymbol(char symbol) {
			trueSymbol = symbol;
		}
		
		//Setter method that allows you to set the False Symbol
		public void setFalseSymbol(char symbol) {
			falseSymbol = symbol;
		}
		
		public char getTrueSymbol() {
			char copyTrueSymbol = trueSymbol;
			return copyTrueSymbol;
		}
		
		public char getFalseSymbol() {
			char copyFalseSymbol = falseSymbol;
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
			
			bw.write(this.toString());
			
			bw.flush();
			bw.close();
		}
		
		//toString method return a string that represents the entire evolution of the ECA. 
		//Consists of the state string for every generation joined by newline characters
		@Override
		public String toString() {
			String ECAString = "";
			for(int idx = 0; idx < generationList.size(); ++idx) {
				ECAString += getStateString(idx) + "\n";
			}
			return ECAString.trim();
		}
}
