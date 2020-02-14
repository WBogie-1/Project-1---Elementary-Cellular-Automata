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
	
	//Generation constructor given a state
	public Generation(String givenState) {
		this.generationContents = new Cell[givenState.length()];
		for(int idx = 0; idx < generationContents.length; ++idx) {
			generationContents[idx] = new Cell(givenState.charAt(idx));
		}
	}
	
	//Calculate next generation value based on a given rule number //TODO
	public Generation evolveGeneration(Rule rule) {
		Generation nextGeneration = new Generation();
		for(int idx = 0; idx < this.generationContents.length; ++idx) {
			try {
				nextGeneration.generationContents[idx].setCellValue(rule.calcCellNextEvolutionVal(this.generationContents[idx - 1].getCellState(), this.generationContents[idx].getCellState(), this.generationContents[idx + 1].getCellState()));
			}
			catch(Exception e){
				if(idx == 0) {
					nextGeneration.generationContents[idx].setCellValue(rule.calcCellNextEvolutionVal(this.generationContents[generationContents.length - 1].getCellState(), this.generationContents[idx].getCellState(), this.generationContents[idx + 1].getCellState()));
				}
				else{
					nextGeneration.generationContents[idx].setCellValue(rule.calcCellNextEvolutionVal(this.generationContents[idx - 1].getCellState(), this.generationContents[idx].getCellState(), this.generationContents[0].getCellState()));
				}
			}
		}
		return nextGeneration;
	}
	
	public String getcellState(int idx) {
		String copyResult;
		String result = this.generationContents[idx].getCellState();
		copyResult = result;
		return copyResult;
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
