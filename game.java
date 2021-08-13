import java.util.Scanner;
//This code runs a game called NIM
/* Game Rules: Random number of marbles in a bag 
Each turn you can take any number of marbles from 1 to half the marbles
Whoever takes the last marble loses
*/
public class game {
	
	public int pile() {
		int sizeMax = 100;//setting the max size of the pile
		int sizeMin = 10;//setting the min size of the pile
		//choosing the size at random
		int sizePile = (int)(Math.random()*(sizeMax-sizeMin+1)+sizeMin);
		System.out.println("Pile Size: "+sizePile+" marbles");
		
		return sizePile;
	}//method pile size;
	
	public int goFirst() {
		// 50/50 chance generator
		int chance = (int)(Math.random()*(2-1+1)+1);
		//output a print for who goes  first
		if(chance == 1) {
			System.out.println("Looks like the computer Goes First!");
		} else {
			System.out.println("Looks like you go first!");
		}
		return chance; // return chance
	}//goFirst;
	
	public int fiftyFifty(int sizePile) {
		//method to determine how the computer should play
		int chance = (int)(Math.random()*(2-1+1)+1);
		//now need to determine how the computer should play
		int numMarbles;
		//code to determine how the computer plays
		if (chance == 1) {
			//play random
			numMarbles = (int)(Math.random()*(sizePile/2-1+1)+1);
			System.out.println("\nComputer Choosing Random");
		} else {
			//Play Smart
			System.out.println("\nComputer Choosing Smart");
			//Smart alg
			int power = 6; 
			int numLeft = (int)Math.pow(2, power) - 1;
			while (numLeft > sizePile) {
				power = power - 1;
				numLeft = (int)Math.pow(2, power) - 1;
				}
			//update num
			numMarbles = sizePile - numLeft;
			//special case if
			if (numMarbles == 0) {
				numMarbles = (int)(Math.random()*(sizePile/2-1+1)+1);
			}
		}
		System.out.println("The computer is taking " + numMarbles + " from the pile");
		sizePile = sizePile - numMarbles;
		return sizePile;
	}//fiftyFifty
	
	public int user(int sizePile){
		int allowed = 1; //initalize var
		int userNum = 0;
		
		while(allowed == 1) {
			Scanner screen = new Scanner(System.in);
			System.out.println("Choose a number between 1 and " + (sizePile/2) + ":");
			userNum = screen.nextInt();
			//check user input
			if (userNum < sizePile/2+1 && userNum > 0) {
				System.out.println("You choose "+ userNum + " marbles from the pile");
				
				allowed = 0;
			} else {
				//false input case
				System.out.println("You need to choose a number from 1 to " + sizePile/2);
			}
		}
		sizePile = sizePile - userNum; //update size
		return sizePile;
	}//user
		

	public static void main(String[] args) {
		//initializing a new game
		System.out.println("Welcome to Nim! \nFirst we must generate a pile of marbles");
		game newgame = new game();
		int size = newgame.pile();//returning the pile size
		
		System.out.println("\nNow we must see which player goes first you or the computer!");
		int first = newgame.goFirst();//computer goes first if 1, you go first if 2
		
		//first iteration of the game
		if (first == 1) {
			size = newgame.fiftyFifty(size);
		} else {
			size = newgame.user(size);
		}
		System.out.println("\nThere is now " + size + " marbles in the pile");
		
		//loop of the game until last marble
		while(size>1) {
			if (first == 1) {
				size = newgame.user(size); //call user input
				first++; //change turn
			} else {
				size = newgame.fiftyFifty(size); // call computer
				first--; //change turn
			}
			System.out.println("\nThere is now " + size + " marbles in the pile");
		}//while;
		//size is now less than 1, need to determine who won
		if (first == 2) {
			System.out.println("You Win!!");
		} else {
			System.out.println("You Lose!");
		}
		
	}//main;
}//class;
@NathanPaceydev
 
