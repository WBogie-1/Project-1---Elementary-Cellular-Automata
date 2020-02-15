/*
 * 
 */
public class Generation {
	
	final private int DEFAULT_GENERATION_SIZE = 15; //TODO This might need to change

	private Cell[] generationContents;
	
	//Default constuctor for Generation
	public Generation() {
		this.generationContents = new Cell[DEFAULT_GENERATION_SIZE];
		for(int idx = 0; idx < DEFAULT_GENERATION_SIZE; ++idx) {
			generationContents[idx] = new Cell();
		}
	}
	
	//Constructor for Generation given a number of cells which will be the length of the generationContents Array
	public Generation(int contentsLength) {
		this.generationContents = new Cell[contentsLength];
		for(int idx = 0; idx < contentsLength; ++idx) {
			generationContents[idx] = new Cell();
		}
	}
	
	//Generation constructor given an initial state array full of boolean values
	public Generation(boolean[] initState) {
		this.generationContents = new Cell[initState.length];
		
		for(int idx = 0; idx < initState.length; ++idx) {
			generationContents[idx] = new Cell(initState[idx]);
		}
	}
	
	//Generation constructor given a state that is not represented in boolean form
	public Generation(String givenState, char trueSymbol, char falseSymbol) {
		this.generationContents = new Cell[givenState.length()];
		for(int idx = 0; idx < generationContents.length; ++idx) {
			if(givenState.charAt(idx) == trueSymbol) {
				generationContents[idx] = new Cell(true);
			}
			else {
				generationContents[idx] = new Cell(false);
			}
		}
	}
	
	//Calculate next generation value based on a given rule number 
	public Generation evolveGeneration(Rule rule) {
		Generation nextGeneration = new Generation(generationContents.length);
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
	
	public boolean getcellState(int idx) {
		boolean copyResult;
		boolean result = this.generationContents[idx].getCellState();
		copyResult = result;
		return copyResult;
	}
	
	//returns a boolean[] representation of the state of every cell in a generation
	public boolean[] getGenerationState() {
		boolean[] copyStateList = new boolean[generationContents.length];
		
		for(int idx = 0; idx < copyStateList.length; ++idx) {
			copyStateList[idx] = generationContents[idx].getCellState();
		}
		return copyStateList;
	}
	
	//ToString method that prints out A generation in an easy to read format
	@Override
	public String toString() {
		String genValueString = "";
		for(int idx = 0; idx < generationContents.length; ++idx) {
			genValueString += String.valueOf(generationContents[idx].toString());
		}
		return genValueString;
	}
}
