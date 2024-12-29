import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

/*
 * Date: March 10, 2022
 * Description: Prompts the user to answer a number of math questions until they 
 * give up, then display their score.
 */
public class AnswerMeThis {
	public static void main(String[] args) {
		int maxTries = 3; //maximum number of tries before losing
		int totalTries = 0; //total tries in all questions that user took
		int correct = 0; //number of questions answered correctly
		int wrong = 0 ; //number of questions lost
		
		//number of attempts in the user took in the current question
		int triesThisQuestion = 0; 
		int triesLeft = maxTries - triesThisQuestion; //number of tries left before they lose the question
		double answer = 0; //answer to the question
		double usersAnswer; //the answer that the user entered
		
		double maxRandInt = 20; //max and min random numbers for math math problem
		double minRandInt = 2;
		
		int firstNum; //first and second number in the math question
		int secondNum;
		DecimalFormat df = new DecimalFormat("#.##");
		
		final char[] methods = {'*', '/', '+', '-'}; //random math operations
		int methodIndex = 0; //index of method selected
		Random random = new Random(); //initialize Random object
		
		boolean stillPlaying = true; //whether the user wants to continue playing
		
		//initialize variable for user's computer name on LAN
		String hostname;
		
		try { //getting hostname could fail because of bad DNS setup on LAN
			//retrieve this computer's hostname from its IP address
			hostname = InetAddress.getLocalHost().getHostName(); 
			
			//if the computer belongs to my teacher
			if (hostname.equals("PC-1-ICS") {
				maxRandInt = 2000; 
				System.out.format("Good luck, %s. You'll need it.\n", hostname);
			} else {
				System.out.format("Welcome, %s!\n", hostname);
			}
		} catch (UnknownHostException e) { 
			System.out.println("You're lucky this time."); //acknowledge defeat
		}
		
		Scanner scan = new Scanner(System.in); //scanner to ask for answer to questions
		System.out.println("Answer the following questions with maximum two decimal places."
				+ "\nEnter 0 when you're done, and to show your score.\n");
		
		//while the user still wants to play
		while(stillPlaying) { 
			firstNum = (int) ((Math.random() * maxRandInt) + minRandInt);
			secondNum = (int)((Math.random() * maxRandInt) + minRandInt);
			methodIndex = random.nextInt(methods.length);
			
			switch (Character.toString(methods[methodIndex])) {
				case "+":
					answer = firstNum + secondNum;
					break;
				case "-":
					answer = firstNum - secondNum;
					break;
				case "*":
					answer = firstNum * secondNum;
					break;
				case "/": //if result of division is a decimal, round to two places
					answer = Double.parseDouble(df.format((double)firstNum / (double)secondNum));
					break;
			}
			
			System.out.format("%d %c %d = ", firstNum, methods[methodIndex], secondNum);
			
			//while their total attempts at this question has not exceeded the limit
			while(triesThisQuestion < maxTries) { //while they still have enough chances for this question
				while(!scan.hasNextDouble()) { //while it's not a double
					System.out.print("Please enter a number: ");
					scan.next();
				}
				usersAnswer = Double.parseDouble(df.format(scan.nextDouble())); //store usersAnswer
				
				if (usersAnswer == answer) { //correct answer!
					System.out.println("Correct!");
					correct++; //user got another question right
					triesThisQuestion++; //user tried this question
					break;
				} else if (usersAnswer == 0.0) { //user wants to leave
					stillPlaying = false;
					break;
				} else {
					triesThisQuestion++; //user tried this question
					triesLeft = maxTries - triesThisQuestion; //reassign triesLeft
					
					if (triesLeft == 0) { //no more tries left
						System.out.println("You lost."); //lost the question
						wrong++; //user got another question wrong by running out of chances
						break;
					} else if (triesLeft == 1) {
						System.out.println("Try again. Last chance.");
					}
					else { //they still have tries left
						System.out.format("Try again. %d chances left.\n", triesLeft);
					}
				}
			}
			totalTries += triesThisQuestion;
			triesThisQuestion = 0; //reset attempt made this question
			System.out.format("The answer was %.2f\n\n", answer);
		}
		
		scan.close(); //close scanner buffer
		System.out.println("*******RESULTS*******");
		System.out.format("Tries: %d\n", totalTries);
		System.out.format("Correct: %d\n", correct);
		System.out.format("Wrong: %d\n", wrong);
		
		if (correct > wrong) {
			System.out.println("\nYOU ROCK!");
		} else {
			System.out.println("\nStay in school, kid.");
		}
	}//end of main
}//end of class
