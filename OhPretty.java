/*
 * Date: March 9, 2022
 * Description: Displays a range of a particular symbol using the number of
 * lines and symbols per line.
 */
import java.util.Scanner;

public class OhPretty {
	public static void main(String[] args) {
		int lines; //number of lines
		int symPerLin; //symbols per line
		int totalSyms; //limit of symbols
		char symbol; //symbol to display
		
		int symsDisplayed = 0; //current number of symbols displayed
		int i; //exterior for loop line counter
		int w; //interior for loop symbol per line count
		
		//create scanner to ask for lines,
		//symbols per line, total number of symbols and the symbol to display.
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Give me a character: "); //ask for symbol
		symbol = scan.nextLine().charAt(0); //store symbol
		
		System.out.println("Enter the number of lines: "); //ask for lines
		
		while(!scan.hasNextInt()) {  //while it's not an int
			System.out.print("Please enter a number: ");
			scan.next();
		}
		lines = scan.nextInt(); //store lines
		
		System.out.println("Enter the number of symbols per line: "); //ask for symbols per line
		
		while(!scan.hasNextInt()) {  //while it's not an int
			System.out.print("Please enter a number: ");
			scan.next();
		}
		symPerLin = scan.nextInt(); //store symPerLin
		
		System.out.println("Enter the maximum number of symbols to display: "); //ask for total symbols
		
		while(!scan.hasNextInt()) {  //while it's not an int
			System.out.print("Please enter a number: ");
			scan.next();
		}
		totalSyms = scan.nextInt(); //store totalSyms
		
		for(i=0;i<lines;i++) {
			System.out.println(""); //separate linex of symbols
			
			for (w=0;w<symPerLin;w++) {
				System.out.print(symbol);
				symsDisplayed += 1;
				
				if (symsDisplayed == totalSyms) { //check if symbols limit reached
					break;
				}
			}
			if (symsDisplayed == totalSyms) { //break out of exterior loop if symbols limit reached
				break;
			}
		}
	}//end of main
}//end of class
