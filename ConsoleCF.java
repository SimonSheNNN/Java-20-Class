package hw4;
import java.util.Random;


public class ConsoleCF extends  CFGame  {
	private CFPlayer player1;
	private CFPlayer player2;
	private boolean oneFirst;
	private boolean oneWin;
	private boolean twoWin;

	public ConsoleCF (CFPlayer ai){
		player1=new HumanPlayer();
		player2=ai;
		Random rand=new Random();
		int order = rand.nextInt(2);
		if(order==1){
			oneFirst=true;
		}else{
			oneFirst=false;
		}
	}
	
	public ConsoleCF (CFPlayer ai1,CFPlayer ai2){
		player1=ai1;
		player2=ai2;
		Random rand=new Random();
		int order = rand.nextInt(2);
		if(order==1){
			oneFirst=true;
		}else{
			oneFirst=false;
		}
	}
	
	public void playOut (){
		oneWin=false;
		twoWin=false;
		if(oneFirst){
			do{  
				//if first player moves and game ends, first player win, same logic for the reverse
				this.play(player1.nextMove(this));
				if(this.isGameOver()){
					oneWin = true;
					break;
				}
				this.play(player2.nextMove(this));
				if(this.isGameOver()){
					twoWin = true;
					break;
				}
			}while(!this.isGameOver());

		}else{
			do{
				this.play(player2.nextMove(this));
				if(this.isGameOver()){
					twoWin = true;
					break;
				}
				this.play(player1.nextMove(this));
				if(this.isGameOver()){
					oneWin = true;
					break;
				}
			}while(!this.isGameOver());


		}
		
		if(this.isGameOver()){
			if(this.winner()==1){
				if(oneFirst==true){
					//first one wins and first one is player one
					System.out.println(player1.getName()+ " win!");
				}else{
					//second one wins and first one is player two
					System.out.println( player2.getName()+ " win!");
				}
			}else if(this.winner()==-1){
				if(oneFirst==true){
					System.out.println(player1.getName()+ " win!");
				}else{
					System.out.println( player2.getName()+ " win!");
				}
			}else{
				System.out.println("game over, draw");
			}
		}
	}
		
	
	public String getWinner(){
		if(oneWin){
			return(player1.getName());
		}else if(twoWin){
			return(player2.getName());
		}else{
			return("draw");
		}
	}
	


	public void printGame(CFGame g){
		int[][] copy = g.getState();

		for(int j = 5; j > -1; j--){
			for(int i = 0; i<7;i++){
				System.out.print(copy[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
		
		
	}

	
	private class HumanPlayer implements CFPlayer {
		public int nextMove(CFGame g){
		    java.util.Scanner reader = new java.util.Scanner (System.in);
		    printGame(g);
		    System.out.println("enter the column you want to play");
		    int h = reader.nextInt();
		    boolean cont=true;
		    if(h<8&&h>0&&(int)h==h){
			    for(int j=0;j<6;j++){
					if(g.getState()[h-1][j]==0){
						cont=false;
					}
				}
		    }


		    while(cont){
		    	//if not valid, enter the while loop
		   		System.out.println("Invalid entry, please enter another one");
		   		h = reader.nextInt();
			    if(h<8&&h>0&&(int)h==h){
				    for(int j=0;j<6;j++){
						if(g.getState()[h-1][j]==0){
							cont=false;
						}
					}
			    }
		   	}
		   	return h;
		    

		}
		
		public String getName (){
			return "Human Player";
		}
	}
}

