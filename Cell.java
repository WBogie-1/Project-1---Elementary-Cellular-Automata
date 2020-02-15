/*
 * 
 */
public class Cell {

	private boolean cellState;
		
	public Cell() {
		this.cellState = false;
	}
	
	public Cell(boolean state) {
		this.cellState = state;
		
	}
	
	public void setCellState(boolean state) {
		this.cellState = state;
	}
	
	//Helper for getcell in Automaton Class generation class
	public boolean getCellState() {
		boolean copyState = cellState;
		return copyState;
	}
	
	//ToString method for cell that returns a String representation of the contents of a given cell.
	@Override
	public String toString() {
		String cellStringValue = Boolean.toString(cellState);
		return cellStringValue;
	}
}
