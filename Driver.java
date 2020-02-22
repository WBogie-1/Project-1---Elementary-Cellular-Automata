import java.io.IOException;

/**
 * This class is used to test the Automation class, the Generation Class, the Rule class, and the Cell class
 * This was before we had learned Junit testing so I use lots of system outputs to make sure my methods are working
 * as intended
 * @author Carter Bogie
 * @version 2.1
 */
public class Driver {

	public static void main(String[] args) throws IOException {
		
		/**
		 * Calculate the wolfram code for a rule and print it based on a predetermined rule number
		 * WORKING!
		 */
		int givenRuleNum = 22;
		Rule rule22 = new Rule(givenRuleNum);
		System.out.println(rule22);
		System.out.println("What rule should binary should look like: " + String.format("%08d", Integer.parseInt(Integer.toBinaryString(givenRuleNum))));
		System.out.println();
		

		/**
		 * Use a rule to find the evolved value of a cell
		 * WORKING!
		 * @see Cell#calcCellNextEvolution()
		 */
		boolean[] testInitState = {false, false, false, false, false , false, true, false, false, false};
		Generation testGen = new Generation(testInitState);
		System.out.println(testGen);
		//Evolve the generation and make sure that the values are correct
		System.out.println(testGen.getcellState(6) + " " + testGen.getcellState(7) + " " + testGen.getcellState(8));
		System.out.println(rule22.calcCellNextEvolutionVal(testGen.getcellState(6), testGen.getcellState(7), testGen.getcellState(8)));
		System.out.println("Above should be true");
		

		/**
		 * Use a rule to evolve a generation and make sure the values are correct
		 * Also test the toString method of Automaton to make sure substitutions are correct
		 * WORKING!
		 * @see Generation#evoleGeneration()
		 */
		Generation Gen2Evolution = testGen.evolveGeneration(rule22);
		System.out.println(Gen2Evolution);
		System.out.println("Above should be: 000000111000000");
		System.out.println(Gen2Evolution.evolveGeneration(rule22));
		System.out.println();
		System.out.println();
		
		//Below are some offline tests
		System.out.println("OFFLINE TEST START HERE");
		
		/**
		 * Test1 is a default True/False test of rule 22 with 15 cells and 7 evolutions
		 * Use File "rule 22-15cells-input.txt" for Automaton construction
		 * WORKING!
		 */
		System.out.println("Test 1:");
		Automaton test1 = new Automaton("rule22-15cells-input.txt");
		//Evolve the automaton 7 times resulting in 8 total generations
		test1.evolve(7);
		//Save the evolved Automaton
		test1.save("test1.txt");
		
		System.out.print(test1);
		System.out.println();
		
		//Print out the initial state of test1
		System.out.println(test1.getStateString(0));
		System.out.println();
		System.out.println();
		
		/**
		 * Test2 is a rule 22 test where False=0 and True=. with 31 evolutions and 63 cells
		 * Use File "rule22-63cells-input.txt" for Automaton construction
		 * WORKING!
		 */
		System.out.println("Test 2:");
		Automaton test2 = new Automaton("rule22-63cells-input.txt");
		test2.evolve(31);
		
		test2.save("test2.txt");
		
		System.out.println(test2);
		System.out.println();
		System.out.println();
		
		/**
		 * Test3 is a rule 30 test where False=, and True=@ with 79 evolutions and 79 cells
		 * Use File "rule30-79cells-input.txt" for Automaton construction
		 * WORKING!
		 */
		System.out.println("Test 3:");
		Automaton test3 = new Automaton("rule30-79cells-input.txt");
		test3.evolve(79);
		
		test3.save("test3.txt");
		
		System.out.println(test3);
		System.out.println();
		System.out.println();
		
		/**
		 * Test4 is a rule 110 test where False=. and True=$ with 20 evolutions and 31 cells
		 * Use File "rule110-31cells-input.txt" for Automaton construction
		 * WORKING!
		 */
		System.out.println("Test 4:");
		Automaton test4 = new Automaton("rule110-31cells-input.txt");
		
		test4.evolve(20);
		
		System.out.println("Test4 Before Symbol Change: ");
		System.out.println(test4);
		System.out.println();
		
		test4.setFalseSymbol('0');
		test4.setTrueSymbol('1');
		System.out.println("Test 4 After Symbol Change: ");
		System.out.println(test4);
		
		test4.save("test4.txt");
		
		System.out.println(test4);
		System.out.println();
		System.out.println();
		
		/**
		 * Test5 is a rule 110 test where False=. and True=$ with 20 evolutions and 15 cells
		 * Created from a given init state not from reading a file
		 * Also used to test symbol change before and after evolution
		 * WORKING!
		 */
		boolean[] initStateTest5 = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, true};
		
		int ruleNumTest5 = 110;
		
		Automaton test5 = new Automaton(ruleNumTest5, initStateTest5);
		
		test5.evolve(20);
		
		System.out.println("Test 5: ");
		System.out.println("Test 5 Before Symbol Change: ");
		System.out.println(test5);
		System.out.println();
		
		test5.setFalseSymbol('.');
		test5.setTrueSymbol('$');
		
		System.out.println("Test 5 After Symbol Change: ");
		System.out.println(test5);
	}
}
