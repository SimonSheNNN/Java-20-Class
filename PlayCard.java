package hw2;
public class PlayCard {
	  public static void main(String[] args) {
	    //set up reader to take inputs
	    java.util.Scanner reader = new java.util.Scanner (System.in);
	    
	    int n = 16; //game size
	    MatchCardGame g1 = new MatchCardGame(n);
	    g1.shuffleCards();
	    
	    while(!g1.gameOver()) {
	      //print board status
	      System.out.println(g1.boardToString());
	      
	      //ask for a card to flip until we get a valid one
	      System.out.println("Which card to play?");
	      while(!g1.flip(reader.nextInt())) {
		  	System.out.println("Invalid spot, please choose a spot that is between 0 and "+n+" and not taken");
	  	  }

	      
	      //print board status
	      System.out.println(g1.boardToString());
	      
	      //ask for a card to flip until we get a valid one
	      while(!g1.flip(reader.nextInt())) {
	    	  System.out.println("Invalid spot, please choose a spot that is between 0 and "+n+" and not taken");
	      }
	      
	      //say whether the 2 cards were a match
	      if(g1.wasMatch()) {
	        System.out.println("Was a match!");
	      } else {
	        //print board to show mismatched cards
	        System.out.println(g1.boardToString());		
	        System.out.println("Was not a match.");
	        //flip back the mismatched cards
	        g1.flipMismatch();
	      }
	    }
	    
	    //Report the score
	    System.out.println("The game took " + g1.getFlips() + " flips.");

	    
	    
	    
	    //Using the AIs
	    int count;
	    MatchCardGame g2 = new MatchCardGame(n);
	    g2.shuffleCards();
	    count = playRandom(g2);
	    System.out.println("The bad AI took " + count + " flips.");
	    MatchCardGame g3 = new MatchCardGame(n);
	    g3.shuffleCards();
	    count = playGood(g3);
	    System.out.println("The good AI took " + count + " flips.");
	    
	    //Using MCs
	    int N = 1000;
	    System.out.println("The bad AI took " + randomMC(N) + " flips on average.");
	    System.out.println("The good AI took " + goodMC(N) + " flips on average.");
	  }
	    
	    
	  	//it will print out a lot of "spot is taken
	    public static int playRandom(MatchCardGame g){	 
    		int chosen;
	    	while(!g.gameOver()){
	    		//random generate an available index and flip
	    		do{				
	    			chosen=(int)(Math.random()*(g.n));
	    		}while(g.flip(chosen)==false);
	    		do{				//do it again
	    			chosen=(int)(Math.random()*(g.n));
	    		}while(g.flip(chosen)==false);
	    		

	    		if(g.wasMatch()==false) {
	  	      		g.flipMismatch();
	    		}
	    	}
	    	return g.getFlips();
	    }
	    
	    
	    public static int playGood(MatchCardGame g){
	    	char[] memory= new char[g.n];
	    	for(int i=0;i<g.n;i++){
	    		memory[i]='*';	    		
	    	}
	    	boolean flipped;
			int chosen;

	    	while(!g.gameOver()){
	    		
	    		//finds all possible pairs,and flip them all, since flip two at a time, after flip it's still even
	    		for(int i=0; i<g.n;i++){		//finding a match by cycle through all elements
	    			for(int j=0;j<g.n;j++){
	    				if(memory[i]==memory[j] && i!=j && memory[i]!='*'){
	   						//if found, flip both
	   						g.flip(i);			
	   						g.flip(j);
	   						memory[i]=memory[j]='*';
    					}//if
    				}//2 for
	   			}
	    		
		
	   			//random generate an available index and flip, this is after even number of flips
	    		do{				
	        		chosen=(int)(Math.random()*(g.n));
	   	   		}while(g.flip(chosen)==false);
	   			//memory records the random we took, count only increase when flipped once
	   			memory[chosen]=g.previousFlipIdentity();
	   			
	   			//now it's after odd number of flips
	   			flipped= false;    // an indicator to see if find any pairs or not
	   			for(int i =0;i<g.n;i++){
	   				if(memory[i]== g.previousFlipIdentity()&& i!=chosen&&memory[i]!='*'){
	   					g.flip(i);
    					memory[i]='*';
	    				memory[chosen]='*';
	    				flipped=true;
	    				break;
	    			}
	    		}
	   			
	   			
	   			if(flipped==false){ //if not found an available pair, flip a random
	    				
	   				//random generate an available index and flip
	   				do{				
	   	    			chosen=(int)(Math.random()*(g.n));
    	    		}while(g.flip(chosen)==false);
	    			//memory records the random we took
	    			memory[chosen]=g.previousFlipIdentity();

	    		}

	    		if(g.wasMatch()==false){
	    			g.flipMismatch();
	    		}
	    		
	    	}//while
	    	
	    	return g.getFlips();
	    }
	    
	    
	    
	    public static double randomMC(int N){
	    	MatchCardGame g = new MatchCardGame(32);
	    	   
	    	int total = 0;
	    	  
	    	for ( int i = 0; i <N; i++){			//gets the sum
	    		g.shuffleCards();
	    		total = total + playRandom(g);
	    	}
	    	  
	    	return total/N;  //return average
	    	}
	    
	    
	    
	    public static double goodMC(int N){
	    	int total = 0;
    		MatchCardGame g = new MatchCardGame(32);

	    	for (int i = 0; i <N; i++){				//do n times, gets the sum
	    		g.shuffleCards();
	    		total = total + playGood(g);
	    	}
	    	
	    	return total/N;   //return  average
	      
	    }
	  
}