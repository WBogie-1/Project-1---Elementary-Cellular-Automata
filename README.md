# Project-1---Elementary-Cellular-Automata
Version control repository for Project One CS 2334
## Notes and Ideas
#1.Function for rules could be something like below (might be able to make shorter).
```
//This tell you which index to look at in a given rule by returning the index based 
//on a calculation using the state of the 3 cells in consideration.
rules(int leftCell, int targetCell, int rightCell){
	int rulesIdx = (4 * leftCell + 2 * targetCell + rightCell);
	return ruleNumber[7 - rulesIdx]; //this may be named differently I have not defined RuleNumber yet!
	}
```
This works because you need an equation that given x + y + z = a number between zero and three where 0 <= variable <= 1 is true for all x y and z. You also know that this combination used in a form ax + by + cz <= 7 and >= 0. You also know that a + b = 6 when a(1) + b(1) + c(1) = 7. Assuming that c = 1 and that a != b the easiest solution to the system is (4,2), giving the multiple needed to complete the equation finding the targeted rule.
```
```
#2.When using String.toBinaryString() you have to use a formatter like below
```
//This is required because when outputting binary numbers as a string toBinaryString
// will ouput values shorter than the needed 8 length.
String.format("%08d", Integer.parseInt(Integer.toBinaryString(ruleNum)))
```
The other option would be too right a short padLeft method that would then be used in the formatter so that you are not having to convert types in the String.format() method call.
```
