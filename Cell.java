/**
 * This class is used to create the objects that will act as the building blocks for each generation
 * Made up of a single boolean value that is used to identify the cells state
 * @author Carter Bogie
 * @version 2.3
 */
public class Cell {
	
	/**
	 * variable that contains the boolean representation of the cells state 
	 */
	private boolean cellState;
	
	/**
	 * Default constructor for Cell class that creates a cell with a false initial state
	 */
	public Cell() {
		this.cellState = false;
	}
	
	/**
	 * Cell constructor that creates a cell using a given boolean state
	 * @param state boolean value for cellState
	 */
	public Cell(boolean state) {
		this.cellState = state;
		
	}
	
	/**
	 * Setter method that set the state of a cell
	 * @param state boolean value that target cell's state will be set to
	 */
	public void setCellState(boolean state) {
		this.cellState = state;
	}
	
	/**
	 * Getter method that gets a copy of cell state that is being
	 * used as a helper method for getCell in Automaton
	 * @return copyState a copy of the boolean state of target cells
	 */
	public boolean getCellState() {
		boolean copyState = cellState;
		return copyState;
	}
	
	/**
	 * ToString method that returns a cells state given as a string
	 * @return cellStringValue the String representation of cell boolean state
	 */
	@Override
	public String toString() {
		String cellStringValue = Boolean.toString(cellState);
		return cellStringValue;
	}
}
