package hw4;
import java.util.Random;


public class SimonAI implements CFPlayer {

	public int nextMove(CFGame g) {

		int[][] copy = g.getState();

		//check by setting every place to 1 and -1, if game wins, that is a 
		//game ending move, so block or move
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				if (copy[i][j] == 0) {
					copy[i][j] = 1;
					if (checkwin(copy) == 1) {
						return (i + 1);
					} 
					
					copy[i][j] = -1;
					if (checkwin(copy) == -1) {
						return (i + 1);
					} else {
						copy[i][j] = 0;
					}
				}
			}
		}


		int x;
		boolean cont=true;
		Random rand=new Random();
		do{
			x= rand.nextInt(7);
			if(g.getState()[x][5]==0){
				cont=false;
			}
			
		}while(cont);
		return (x+1);	
	}
	
	//do this becaue we cant change the arraylist in cfgame, so we need to make a 
	//copy and do the same kind of game ending check
	private int checkwin(int[][] a) {
		int[][] copy = a;
	
		//check for vertical
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 3; j++) {
				if (copy[i][j] == copy[i][j + 1] && copy[i][j + 1] ==
						copy[i][j + 2] && copy[i][j + 2] == copy[i][j + 3]) {
					if (copy[i][j] == 1) {
						return 1;
					} else if (copy[i][j] == -1) {
						return -1;
					}
				}
			}
		}
		
		//check for horizontal
		for (int j = 0; j < 6; j++) {
			for (int i = 0; i < 4; i++) {
				if (copy[i][j] == copy[i + 1][j] && copy[i + 1][j] 
						== copy[i + 2][j] && copy[i + 2][j] == copy[i + 3][j]) {
					if (copy[i][j] == 1) {		
						return 1;
					} else if (copy[i][j] == -1) {
						return -1;
					}
				}
			}
		}
		
		//check for diagonal (increasing)
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				if (copy[i][j] == copy[i + 1][j + 1] && copy[i + 1][j + 1] == 
						copy[i + 2][j + 2] && copy[i + 2][j + 2] == copy[i + 3][j + 3]) {
					if (copy[i][j] == 1) {
						return 1;
					} else if (copy[i][j] == -1) {
						return -1;
					}
				}
			}
		}
		
		//check for diagonal (decreasing)
		for (int i = 6; i > 2; i--) {
			for (int j = 0; j < 3; j++) {
				if (copy[i][j] == copy[i - 1][j + 1] && copy[i - 1][j + 1] ==
						copy[i - 2][j + 2] && copy[i - 2][j + 2] == copy[i - 3][j + 3]) {
					if (copy[i][j] == 1) {
						return 1;
					} else if (copy[i][j] == -1) {
						return -1;
					}
				}
			}	
		}

			return 0;
	}
		
	public String getName() {
		return "Simon AI";
	}
	
}
	
	
