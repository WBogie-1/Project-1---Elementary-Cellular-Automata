/*
 * 
 */
public class Cell {

	private char cellValue;
		
	public Cell() {
		this.cellValue = '0';
	}
	
	public Cell(char value) {
		this.cellValue = value;
	}
	
	public void setCellValue(char value) {
		this.cellValue = value;
	}
	
	//Helper for getcell in Automaton Class generation class
	public char getCellState() {
		char copyValue = this.cellValue;
		return copyValue;
	}
	
	//ToString method for cell that returns a String representation of the contents of a given cell.
	@Override
	public String toString() {
		String cellStringValue = Character.toString(this.cellValue);
		return cellStringValue;
	}
}
