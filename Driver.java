import java.io.IOException;

/*
 * This program creates an elementary cellular automata and displays it in an easy
 * to understand visual way. This class is the driver class that conatins the main()
 * method
 */
public class Driver {

	public static void main(String[] args) throws IOException {
		
//		//Create a set of test cells (WORKS
//		Cell A = new Cell();
//		Cell B = new Cell("1");
//		Cell C = new Cell(new String("0"));
//		
//		//Test Cell printing(WORKS)
//		System.out.println(A + " " + B + " " + C);
//		
//		//Test getCellState(WORKS)
//		System.out.println(A.getCellState() + " " + B.getCellState() + " " + C.getCellState());
//		
//		//Change Value of middle cell(WORKS)
//		B.setCellValue("2");
//		System.out.println(A.getCellState() + " " + B.getCellState() + " " + C.getCellState());
//		System.out.println();
//		
//		//Create a blank Generation for testing purposes(WORKS)
//		Generation Gen1 = new Generation();
//		System.out.println(Gen1); 
//		
//		//Create a generation Based on a given state (WORKS)
//		String Gen2GivenState = "000000010000000";
//		Generation Gen2 = new Generation(Gen2GivenState);
//		System.out.println(Gen2);
//		System.out.println();
//		
//		//Calculate the wolfram code for a rule and print it (WORKS)
//		int givenRuleNum = 22;
//		Rule rule22 = new Rule(givenRuleNum);
//		System.out.println(rule22);
//		System.out.println("What rule should binary should look like: " + String.format("%08d", Integer.parseInt(Integer.toBinaryString(givenRuleNum))));
//		System.out.println();
//		
//		//Use a rule to find the next Value of a Cell (WORKS)
//		System.out.println(Gen2.getcellState(6) + " " + Gen2.getcellState(7) + " " + Gen2.getcellState(8));
//		System.out.println(rule22.calcCellNextEvolutionVal(Gen2.getcellState(6), Gen2.getcellState(7), Gen2.getcellState(8)));
//		System.out.println("Above should be 1");
//		
//		//Use a rule to construct a new generation based on a rule (WORKS)
//		Generation Gen2Evolution = Gen2.evolveGeneration(rule22);
//		System.out.println(Gen2Evolution);
//		System.out.println("Above should be: 000000111000000");
//		System.out.println(Gen2Evolution.evolveGeneration(rule22));
		
		//Below are some offline tests
		
		//Test1 is a default True False test of rule 22 with 15 cells and 7 evolutions (PASSED)
		Automaton test1 = new Automaton("rule22-15cells-input.txt");
		test1.evolve(7);
		
		test1.save("test1.txt");
		
		System.out.print(test1);
		System.out.println();
		System.out.println();
		
		//Print out the initital state of test1
		System.out.println(test1.getStateString(0));
		
		//Test2 is a rule 22 test where False = 0 and true = . with 31 evolutions and 63 cells (PASSED)
		Automaton test2 = new Automaton("rule22-63cells-input.txt");
		test2.evolve(31);
		
		test2.save("test2.txt");
		
		System.out.println(test2.getStateString(0));
		
		System.out.println(test2);
		System.out.println();
		System.out.println();
		
		//Test3 is a rule 30 test where False = , and True = @ with 79 evolutions and 79 cells (PASSED)
		Automaton test3 = new Automaton("rule30-79cells-input.txt");
		test3.evolve(79);
		
		test3.save("test3.txt");
		
		System.out.println(test3);
	}
}
