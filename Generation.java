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
