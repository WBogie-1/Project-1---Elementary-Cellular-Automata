/*
 * This program creates an elementary cellular automata and displays it in an easy
 * to understand visual way. This class is the driver class that conatins the main()
 * method
 */
public class Driver {

	public static void main(String[] args) {
		
		//Create a set of test cells (WORKS
		Cell A = new Cell();
		Cell B = new Cell("1");
		Cell C = new Cell(new String("0"));
		
		//Test Cell printing(WORKS)
		System.out.println(A + " " + B + " " + C);
		
		//Test getCellState(WORKS)
		System.out.println(A.getCellState() + " " + B.getCellState() + " " + C.getCellState());
		
		//Change Value of middle cell(WORKS)
		B.setCellValue("2");
		System.out.println(A.getCellState() + " " + B.getCellState() + " " + C.getCellState());
		
		//Create a blank Generation for testing purposes(WORKS)
		Generation Gen1 = new Generation();
		System.out.println(Gen1); 
		
		//Create a generation Based on a given state (WORKS)
		String Gen2GivenState = "101100010101100";
		Generation Gen2 = new Generation(Gen2GivenState);
		System.out.println(Gen2);
		
		//Calculate a the wolfram code for a rule and print it //TODO
		
		//Use a rule to find the next Value of a Cell //TODO
		
		//Use a rule to construct a new generation based on a rule //TODO
	}
}
