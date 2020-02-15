import java.io.IOException;

/*
 * This program creates an elementary cellular automata and displays it in an easy
 * to understand visual way. This class is the driver class that conatins the main()
 * method
 */
public class Driver {

	public static void main(String[] args) throws IOException {
		
		//Calculate the wolfram code for a rule and print it (WORKS)
		int givenRuleNum = 22;
		Rule rule22 = new Rule(givenRuleNum);
		System.out.println(rule22);
		System.out.println("What rule should binary should look like: " + String.format("%08d", Integer.parseInt(Integer.toBinaryString(givenRuleNum))));
		System.out.println();
		
		//Use a rule to find the next Value of a Cell (WORKS)
		boolean[] testInitState = {false, false, false, false, false , false, true, false, false, false};
		Generation testGen = new Generation(testInitState);
		System.out.println(testGen);

		
		
		System.out.println(testGen.getcellState(6) + " " + testGen.getcellState(7) + " " + testGen.getcellState(8));
		System.out.println(rule22.calcCellNextEvolutionVal(testGen.getcellState(6), testGen.getcellState(7), testGen.getcellState(8)));
		System.out.println("Above should be true");
		
		//Use a rule to construct a new generation (WORKS)
		Generation Gen2Evolution = testGen.evolveGeneration(rule22);
		System.out.println(Gen2Evolution);
		System.out.println("Above should be: 000000111000000");
		System.out.println(Gen2Evolution.evolveGeneration(rule22));
		System.out.println();
		System.out.println();
		
		//Below are some offline tests
		System.out.println("OFFLINE TEST START HERE");
		
		//Test1 is a default True False test of rule 22 with 15 cells and 7 evolutions (PASSED)
		System.out.println("Test 1:");
		Automaton test1 = new Automaton("rule22-15cells-input.txt");
		test1.evolve(7);
		
		test1.save("test1.txt");
		
		System.out.print(test1);
		System.out.println();
		
		//Print out the initital state of test1
		System.out.println(test1.getStateString(0));
		System.out.println();
		System.out.println();
		
		//Test2 is a rule 22 test where False = 0 and true = . with 31 evolutions and 63 cells (PASSED)
		System.out.println("Test 2:");
		Automaton test2 = new Automaton("rule22-63cells-input.txt");
		test2.evolve(31);
		
		test2.save("test2.txt");
		
		//System.out.println(test2.getStateString(0));
		
		System.out.println(test2);
		System.out.println();
		System.out.println();
		
		//Test3 is a rule 30 test where False = , and True = @ with 79 evolutions and 79 cells (PASSED)
		System.out.println("Test 3:");
		Automaton test3 = new Automaton("rule30-79cells-input.txt");
		test3.evolve(79);
		
		test3.save("test3.txt");
		
		System.out.println(test3);
		System.out.println();
		System.out.println();
		
		//Test4 is a rule 110 test where False = . and True = $ with 20 evolutions and 31 cells (PASSED)
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
		
		//Test5 is a rule 110 test where False = . and True = $ with 20 evolutions and 15 cells
		//Created not reading from a file
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
