/*
 * 
 */
public class Cell {

	private String cellValue;
		
	public Cell() {
		this.cellValue = "0";
	}
	
	public Cell(String value) {
		this.cellValue = value;
	}
	
	public Cell(char value) {
		this.cellValue = String.valueOf(value);
	}
	
	public void setCellValue(String value) {
		this.cellValue = value;
	}
	
	//Helper for getcell in Automaton Class
	public String getCellState() {
		String copyValue = this.cellValue;
		return copyValue;
	}
	
	//ToString method for cell that returns a String representation of the contents of a given cell.
	@Override
	public String toString() {
		String cellStringValue = this.cellValue;
		return cellStringValue;
	}
}
