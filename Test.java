package hw4;


import java.util.Scanner;
import hw4.CFPlayer;
import hw4.RandomAI;
import hw4.SimonAI;
import hw4.ConsoleCF;
import hw4.GUICF;


public class Test {
  public static void main(String[] args) {
	System.out.print("which gamemode?");
    Scanner reader = new Scanner (System.in);
    int gameMode = reader.nextInt();
  //  int gameMode=1;
    if (gameMode==1) {
      GUICF game =new GUICF(new SimonAI());
    } else if (gameMode==2) {
      CFPlayer ai1 = new SimonAI();
      CFPlayer ai2 = new RandomAI();
      int n = 10000;
      int winCount = 0;
      for (int i =0; i < n ; i++) {
        ConsoleCF game = new ConsoleCF(ai1, ai2);
        game.playOut();
        if(game.getWinner() == ai1.getName())
          winCount++;
      }
      System.out.println(((double) winCount)/n);
    } else {
      ConsoleCF game = new ConsoleCF(new SimonAI());
      game.playOut();
      System.out.println(game.getWinner() + " has won.");
    } 
  }
	
//	public static void main(String[] args) {
//		CFPlayer ai1 = new SimonAI (); 
//		CFPlayer ai2 = new RandomAI (); 
//		int n = 10;
//		int winCount = 0;
//		for (int i=0; i<n; i++) {
//		ConsoleCF game = new ConsoleCF(ai1, ai2); 
//  	game.playOut();
//		if (game.getWinner()==ai1.getName())
//		winCount ++; }
//		System.out.print(ai1.getName() + " wins "); System.out.print(((double) winCount)/((double) n)*100 + "%"); System.out.print(" of the time.");
//		}
}
