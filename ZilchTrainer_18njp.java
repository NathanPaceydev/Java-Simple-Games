import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// THIS IS THE GOOD COPY***********
/* Nathan Pacey, ID: 18njp, student #: 20153310
 * 
 */
public class ZilchTrainer_18njp {

	// YOU CANNOT ADD ANY MORE ATTRIBUTES!
	// Seeding the generator ensures that a different sequence will be used
	// each time the game is played.  You can invoke .nextInt(n) from the
	// generator object to get a random integer between 0 and (n-1).
	public static Random generator = new Random(System.currentTimeMillis());
	public static final int GAME_LIMIT = 10000;
	private static int numToRoll = 6;
	private static boolean freeRoll = false;

	// WRITE YOUR METHODS HERE********
	
	//method to roll die and put values in an array
	public static int[] rollDice(int size) {
		
		int[] diceArray = new int[size];//initialize an array to hold the rolls 
		// setting the min and max size to calc random rolls
		int sizeMin = 1; 
		int sizeMax = 6;
		//iterating through the array and setting each element to a random number from 1-6
		for (int i = 0; i < size; i++) {
		diceArray[i] = (int)(Math.random()*(sizeMax-sizeMin+1)+sizeMin);
		}
		return diceArray; // return the array
	}//rollDice;
	
	//special case to only roll one dice
	public static int rollOneDice() {
		//setting the min and max for rand
		int sizeMin = 1; 
		int sizeMax = 6;
		//randomly assigning int from 1-6 for roll
		int oneDice = (int)(Math.random()*(sizeMax-sizeMin+1)+sizeMin);
		return oneDice;//return the roll
	}//rollOneDice;
	
	
	//method to score values ***** Change Var Name******************
	public static int scoreThrow(int[] dice) {
		
		// want to count the number of each dice value
		// we can put the number of each value in this array
		int[] diceVal = new int[6]; // array of 6 elements
        
        int score = 0; //start score at zero
        
        // loop through the array and add the number of each value
        for(int a = 0; a < dice.length; a++) { 
        	
        	//add 1 to the (index-1) for every value of dice
            diceVal[dice[a]-1] = diceVal[dice[a] - 1] + 1;
        }

        // Special Case for ones
        //For values of 1, update scores using switch cases
        switch(diceVal[0]) {
            case 1: //1 one add 100 to score
                score += 100;
                numToRoll--; //take one dice out
                break;
            case 2: // 2 ones add 200 to score
                score += 200;
                numToRoll-=2; //take out 2 dice
                break;
            case 3: // 3 ones add 1000 to score
                score += 1000;
                numToRoll-=3; // take out 3 dice
                break;
            case 4: //4 ones add 2000
                score += 2000; 
                numToRoll-=4; // take out 4 dice
                break;
            case 5: // 5 ones
                score += 4000;
                numToRoll-=5;
                break;
            case 6: // 6 ones
                score += 8000;
                numToRoll-=6;
                break;
        } // end of ones case

        //For all other values, start 1 to length
        //Need to iterate through a loop with a nest switch case to update points
        // check for duplicates
        for(int i = 1; i < diceVal.length; i++) {

            switch(diceVal[i]) {
                case 1: // one 5
                    if(i==4){
                        score += 50;
                        numToRoll-=1;
                    }
                    break;
                case 2: // 2 fives
                    if(i==4){
                        score += 100;
                        numToRoll-=2;
                    }
                    break;
                case 3: // for rolling 2's
                    score += (i+1)*100;
                    numToRoll-=3;
                    break;
                case 4: 
                    score += (i+1)*200;
                    numToRoll-=4;
                    break;
                case 5: 
                    score += (i+1)*400;
                    numToRoll-=5;
                    break;
                case 6: 
                    score += (i+1)*800;
                    numToRoll-=6;
                    break;

            }
        }//for values 2-6;


        // Special Cases !!

        int straight = 0; //check for straight
        int three_pairs = 0; // check for three pairs

        //iterate through the diceVal array and check for special cases
        for(int k=0; k < 6; k++) {

        	//all values 1 in array
            if(diceVal[k] == 1) {
                straight++; //increment by 1
            }
            // values of 2 in the array
            if(diceVal[k] == 2) {
                three_pairs++;
            }
        }

        // check for straight
        if(straight == 6) {
        	//update for special case
            score = 1500;
            freeRoll = true;
            numToRoll = 0;
        }
        //check for three pairs
        if(three_pairs == 3) {

            // update for special case
            score = 1500;
            freeRoll = true;
            numToRoll = 0;
        }
        
        //no need to have a special case for score 0 since it was inputted as zero
        
        return score;
    }//scoreThrow;

    //End of student code********************
	
	
	// Returns the name of the dice roll as a word.
	private static String getNumberName(int roll) {
		String[] names = {"one", "two", "three", "four", "five", "six"};
		return names[roll - 1];
	} // end getNumberName

	// Obtains and returns a single character as provided by the user. If the user enters
	// more than one character the extra characters are ignored. If he or she does not 
	// provide any characters then the null character is returned.
	private static char getChar() {
		byte[] buffer = new byte[100];
		int numRead = 0;
		try {
			numRead = System.in.read(buffer);
		} catch (IOException e) {
		}
		if (numRead > 0)
			return (char)buffer[0];
		return '\0';
	} // end getChar

	// Returns true if the supplied target is in the supplied array, false otherwise.
	private static boolean inArray(char[] array, char target) {
		for (char elem : array)
			if (elem == target)
				return true;
		return false;
	} // end inArray

	// Prompts for, obtains and returns a single character from the user. If the
	// character is not legal, the user is prompted again.
	private static char playerPrompt(String prompt) {
		char response = '?';
		char[] legalResponses = {'r', 'R', 'b', 'B', 'q', 'Q'};
		while (true) {
			System.out.print(prompt);
			response = getChar();
			if (inArray(legalResponses, response))
				return response;
			else
				System.out.print("Illegal entry, please try again. ");
		}
	} // end playerPrompt

	// Displays instructions to the user.
	public static void displayIntro() {
		// A Text block is useful here!
	System.out.println("This training program will help you practice the game of Zilch - \nlearning when to roll again and when it is too risky.\nYou will roll against an AI player who banks or rolls at random.\nYou should be able to win easily if you are making better choices!\n\nPossible responses at a prompt are 'r' to roll again, 'b' to\nbank your points, just <enter> and 'q' to quit the trainer. Otherwise\nthe session will run until one player wins.");
	} // end displayIntro

	// Displays the players dice roll to the console.
	public static void displayRoll(int rollCount, int[] dice) {
		String out = "Roll " + rollCount + ": ";
		for (int die : dice)
			out += "*" + getNumberName(die) + "*";
		out += " Scoring:";
		System.out.print(out);
	} // end displayRoll

	// Plays the game.
	public static void runTrainer() {
		boolean playRandom = false;
		int throwSum = 0;
		int turnSum = 0;
		int randomTurnSum = 0;
		int humanTurnSum = 0;
		int bankedSum = 0;
		int randomBankedSum = 0;
		int zilchCount = 0;
		int randomZilchCount = 0;
		int rollCount = 0;
		int randomRollCount = 0;
		int[] dice;
		char input;
		String prompt;
		System.out.print("Press <enter> to start trainer: ");
		input = getChar();
		// Loops until the human has had enough or one player has won.
		while (!(input == 'q' || input == 'Q') && bankedSum < GAME_LIMIT && randomBankedSum < GAME_LIMIT) {
			if (playRandom) {
				System.out.print("\n***RANDOM PLAYER Playing*** ");
				randomTurnSum = turnSum;
				humanTurnSum = 0;
			} else {
				System.out.print("\n***YOU Playing!*** ");
				humanTurnSum = turnSum;
				randomTurnSum = 0;
			}
			// Display current values for both players.
			System.out.println(numToRoll + " dice left.");
			System.out.print("RANDOM PLAYER Stats: ");
			System.out.print("\tTurn Sum: " + randomTurnSum + " \tBank: " + randomBankedSum +
					" \tZilch count: " + randomZilchCount + "\n");			
			System.out.print("YOUR Stats: ");
			System.out.println("\t\tTurn Sum: " + humanTurnSum + " \tBank: " + bankedSum +
					" \tZilch count: " + zilchCount + "\n");
			// Essentially pauses output so a human can read it..
			if (!playRandom) {
				System.out.print("Press <enter> to roll: ");
				input = getChar();
				if (input == 'q' || input == 'Q')
					break;
			}
			// Roll the dice and display the resulting values.
			dice = rollDice(numToRoll);
			if (playRandom) {
				randomRollCount++;
				System.out.print("Random ");
				displayRoll(randomRollCount, dice);
			} else {
				rollCount++;
				System.out.print("Your ");
				displayRoll(rollCount, dice);
			}
			// Score the roll and show the score and the free roll notice if obtained.
			throwSum = scoreThrow(dice);
			System.out.print(" " + throwSum + " points.");
			if (freeRoll || numToRoll == 0) {
				System.out.println(" You get a free roll!");
				freeRoll = false;
				numToRoll = 6;
			}
			// Check for a zilch.
			if (throwSum == 0) {
				System.out.print(" A zilch!!\n");
				if (playRandom)
					randomZilchCount++;
				else {
					zilchCount++;
					System.out.print("Press <enter> to continue: ");
					input = getChar();
					if (input == 'q' || input == 'Q')
						break;					
				}
				turnSum = 0;
				// Check zilch count.  If at three, penalize banked points and reset
				// zilch count.
				if (zilchCount == 3) {
					bankedSum -= 500;
					zilchCount = 0;
				}
				if (randomZilchCount == 3) {
					randomBankedSum -= 500;
					randomZilchCount = 0;
				}
				// Turn is over.
				playRandom = !playRandom;
			}
			else {
				// Add throw score to turn sum and report.
				turnSum += throwSum;
				System.out.println("\nTurn Sum: " + turnSum + " and " + numToRoll + " dice left to roll.");
				// If turn sum is greater than 300 you can choose to bank the turn
				// sum or roll again.
				if (turnSum >= 300 && numToRoll > 0) {
					if (playRandom) {
						// The AI player chooses.
						if (numToRoll == 6)
							System.out.println("Rolling again.");
						else if (rollOneDice() > numToRoll) {
							// Bank turn sum.
							System.out.println("Random choice to bank.");
							randomZilchCount = 0;
							freeRoll = false;
							randomBankedSum += turnSum;
							turnSum = 0;
							numToRoll = 6;
							// Turn is over after banking.
							playRandom = !playRandom;
						}
						else
							System.out.println("Random choice to roll.");
					} else {
						// The human chooses.
						prompt = "Do you want to (r)oll or (b)ank your turn sum? ";
						input = playerPrompt(prompt);
						if (input == 'q' || input == 'Q')
							break;
						if (input == 'b' || input == 'B') {
							// Bank turn sum.
							zilchCount = 0;
							freeRoll = false;
							bankedSum += turnSum;
							turnSum = 0;
							numToRoll = 6;
							// Turn is over after banking.
							playRandom = !playRandom;
						}
					}
				}
				else if (turnSum < 300 && numToRoll > 0)
					System.out.println("Less than 300 still, you must roll again.");
			}
		}
		// Game is over.  Report results.
		System.out.println("\nYour score: " + bankedSum + " in " + rollCount + " rolls.");
		System.out.println("Random choices score: " + randomBankedSum + " in " + randomRollCount + " rolls.");
		if (bankedSum > randomBankedSum)
			System.out.println("You won! You are making good choices.");
		else
			System.out.println("You lost! What? You are not making good choices!");
	} // end runTrainer

	// Displays the instructions and starts the game.
	public static void main(String[] args) {
		displayIntro();
		runTrainer();
	} // end main

} // end ZilchTrainer