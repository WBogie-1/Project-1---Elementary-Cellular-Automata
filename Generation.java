/**
 * This class is use as a building block for automaton and will be made up an array of cell objects
 * Each object represents a new generation of the ECA being constructed by the automaton
 * @author Carter Bogie
 * @version 2.3
 */
public class Generation {
	
	/**
	 * Array of cell objects that makes up the contents of a generation
	 */
	private Cell[] generationContents;
	
	/**
	 * Constructor for generation that given a number of cell which will be the length of the generation
	 * creates a generation full of default cells
	 * @param contentsLength int number of cells in the generation
	 */
	public Generation(int contentsLength) {
		this.generationContents = new Cell[contentsLength];
		for(int idx = 0; idx < contentsLength; ++idx) {
			generationContents[idx] = new Cell();
		}
	}
	
	/**
	 * Constructor given an initial state array of boolean values that creates a generation of the correct length
	 * and cells with the appropriate boolean values based on the given initial state
	 * @param initState boolean[] that represents the state of each cell in the generation
	 */
	public Generation(boolean[] initState) {
		this.generationContents = new Cell[initState.length];
		
		for(int idx = 0; idx < initState.length; ++idx) {
			generationContents[idx] = new Cell(initState[idx]);
		}
	}
	
	/**
	 * Constructor given a state where cell contents are not represented in boolean form
	 * Given the state and the true/false symbols build a new generation with correct boolean values
	 * @param givenState String that contains a set of true false symbol describing a generation state
	 * @param trueSymbol a char that represents true as cell state
	 * @param falseSymbol a char that represents fasle as cell state
	 */
	public Generation(String givenState, char trueSymbol, char falseSymbol) {
		//Create a new generation of correct length
		this.generationContents = new Cell[givenState.length()];
		//Create a new cell for each space in generation and assign correct boolean value
		//Based on given true false symbols
		for(int idx = 0; idx < generationContents.length; ++idx) {
			if(givenState.charAt(idx) == trueSymbol) {
				generationContents[idx] = new Cell(true);
			}
			else {
				generationContents[idx] = new Cell(false);
			}
		}
	}
	
	/**
	 * Calculate the values of each cell in a new generation after evolving a generation based on a given rule 
	 * @param rule Rule object that will dictate evolution of the generation
	 */
	public Generation evolveGeneration(Rule rule) {
		Generation nextGeneration = new Generation(generationContents.length);
		//For each cell try to calculate the value after evolution
		//If first or last cell is targeted OutOfBounds exception will be thrown, catch and handle appropriately
		//If last cell use last, second to last, and fist cell values
		//If first cell use first, second, and last cell values
		for(int idx = 0; idx < generationContents.length; ++idx) {
			try {
				nextGeneration.generationContents[idx].setCellState(rule.calcCellNextEvolutionVal(generationContents[idx - 1].getCellState(), generationContents[idx].getCellState(), generationContents[idx + 1].getCellState()));

			}
			catch(Exception e){
				if(idx == 0) {
					nextGeneration.generationContents[idx].setCellState(rule.calcCellNextEvolutionVal(generationContents[generationContents.length - 1].getCellState(), generationContents[idx].getCellState(), generationContents[idx + 1].getCellState()));
				}
				else{
					nextGeneration.generationContents[idx].setCellState(rule.calcCellNextEvolutionVal(generationContents[idx - 1].getCellState(), generationContents[idx].getCellState(), generationContents[0].getCellState()));
				}
			}
		}
		return nextGeneration;
	}
	
	/**
	 * Getter method that returns a copy of a cells state in the generation given an index
	 * @param idx index of the target cell in the generation
	 * @return copyResult boolean copy of the target cells state
	 * @see Cell#getCellState()
	 */
	public boolean getcellState(int idx) {
		boolean copyResult;
		boolean result = this.generationContents[idx].getCellState();
		copyResult = result;
		return copyResult;
	}
	
	/**
	 * Getter method that returns a copy of the boolean array of cell states in a target generation
	 * @return copyStateList a boolean[] copy of the state of each cell
	 * @see Cell#getCellState()
	 */
	public boolean[] getGenerationState() {
		boolean[] copyStateList = new boolean[generationContents.length];
		
		for(int idx = 0; idx < copyStateList.length; ++idx) {
			copyStateList[idx] = generationContents[idx].getCellState();
		}
		return copyStateList;
	}
	
	/**
	 * To string method returns the contents of the generation as a string (Used for testing)
	 */
	@Override
	public String toString() {
		String genValueString = "";
		for(int idx = 0; idx < generationContents.length; ++idx) {
			genValueString += String.valueOf(generationContents[idx].toString());
		}
		return genValueString;
	}
}
