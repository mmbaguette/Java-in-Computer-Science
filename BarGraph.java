import java.util.*;

 /*
 * Date: March 21, 2022
 * Description: Creates a bar graph from the given values, assigned with a title
 */

public class BarGraph {
	public static void main(String[] args) {
		String[] keys; //initialize placeholder strings that will be parsed into a arrays
		String[] values;
		String graphName; //graph title
		
		List<String> valuesList;
		List<String> keysList;
		
		System.out.println("Enter multiple values by separating them with a comma (ex: \"4, 5, hi\").\n");
		
		Scanner scan = new Scanner(System.in); //keyboard to take input
		System.out.println("Type in the name for your bar graph: ");
		graphName = scan.nextLine(); //list of keys for bar graph
		
		System.out.println("Type in the keys for your bar graph: ");
		keys = scan.nextLine().split(",",0);
		keysList = new LinkedList<String>(Arrays.asList(keys)); //assign array list from String array of values
		keysList.removeAll(Collections.singleton("")); //remove all empty strings
		
		for(int i = 0; i<keysList.size(); i++) { //remove trailling spaces
			keysList.set(i, keysList.get(i).replaceFirst("^\\s*", ""));
		} 
		
		System.out.println("Type in the values for your bar graph: ");
		while(true) {
			//System.out.println("Next line: " + scan.nextLine());
			values = scan.nextLine().replace(" ", "").split(",",0); //for numbers, we don't want spaces
			valuesList = new LinkedList<String>(Arrays.asList(values)); //assign array list from String array of values
			valuesList.removeAll(Collections.singleton("")); //remove all empty strings
			boolean isInt = true;
			
			//make sure every value in valuesList is an integer!
			for (int i = 0; i < valuesList.size(); i++) { 
				try {
			        Integer.parseInt(valuesList.get(i)); 
			    } catch(NumberFormatException e) { //these errors mean it's not an int!
			    	isInt = false; //at least one of the numbers is not an int,
			    	break; 		   // so we don't need to check the rest in the list
			    } 
				catch(NullPointerException e) {
					isInt = false;
					break;
				}
			}
			
			//if any given num is not an int or keys and values list size do not match
			if (!isInt || keysList.size() != valuesList.size()) { 
	 			System.out.format("Please enter %d numbers: ", keysList.size());
			} else {
				break;
			}
		}
		
		System.out.println("\n"+graphName);
		System.out.println("--------------");
		int maxStr = keysList.stream().map(String::length).max(Integer::compareTo).get(); //length of longest string. 
		
		for (int i = 0; i < keysList.size(); i++) { //loop through each key
			//each string should take up as many spaces on the left as the longest for the padding is the same
			System.out.format("%-" + maxStr + "s ", keysList.get(i));
			
			for(int v = 0; v < Integer.parseInt(valuesList.get(i)); v++) { //display each hashtag
				System.out.print("#");
			}
			System.out.println(""); //new line
		}
		
		scan.close(); //close scanners to release input buffers
	} //end of main	
} //end of class
