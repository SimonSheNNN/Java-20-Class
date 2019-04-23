package hw2;

import java.util.Random;
import java.util.Scanner;


public class MatchCardGame {
	public final int n;
	private int count;
	private final char[] board;
	private char[] boardUser;
	private int[] order; 
	private boolean[] flip;
		
	
	public MatchCardGame(int n){
		this.n=n;
        board =new char [n];		//new board array with size n
        flip = new boolean [n];		//state of the cards
        boardUser= new char [n];
        order= new int [99999];
        count =0;
        
        
		System.out.println("There are " +n+" cards on the baord");
		
        char[] alphabet = new char[26]; // new array        
        for(char ch = 'a'; ch <= 'z'; ++ch)// fills alphabet array with the alphabet
        {
            alphabet[ch-'a']=ch;
        } 
                       
        
		for(int i = 0; i < n/4 ; i++ ){			//fills with alphabets, 0-3 is a 4-7 is b .....
			for (int j=0; j<4; j++){
				board[i*4+j]=alphabet[i];
				flip[i*4+j]=false;
			}//end of 2 for
		}//end of 1 for
		
		for (int k=0;k<n;k++){		//generate a board for users, start with all *
			boardUser[k]='*';
		}
		
		
	}//end of constructor
	
	
	public String boardToString (){
		for (int i =0;i<n;i++){			// if flipped, board for users of that position become visible
			if (flip[i]==true){
				boardUser[i]=board[i];
			}// end of if
		}//end of for
		
		
		char[] print=new char[2*n];    // insert spaces between values to be more readable
		for (int i=0;i<n;i++){
			print[2*i]=boardUser[i];
			print[2*i+1]=' ';
		}
		
		String boardS=new String(print);	//convert char array to string
		return(boardS);
	}
	
	
	public boolean flip(int i){
		if(i<0 || i >n || i != (int)i ){		//if a string, a negative, or too large, return false
			return false;
		}else if (flip[i]==true){			//if already flipped, return false
			return false;
		}else {
			flip[i]=true;		//flip
			order[count]=i;    	//keep track and match the index of card that is flipped with the order
			count++;			//count++ here so that later function has to use count-1 to represent the latest flip
			return true;
		}
		
	}

	public boolean wasMatch (){

		if(board[order[count-1]]==board[order[(count-2)]]){		//count-1 is the latest, count-2 is the second latest

			return true;
		}else{
			return false;
		}
		
		
	}
	
	public char previousFlipIdentity (){
		return board[order[count-1]];
		
	}
	
	public void flipMismatch (){
		flip[order[count-1]]=false;		//flip back the two unmatched ones
		flip[order[count-2]]=false;
		boardUser[order[count-1]]='*';	//put the user board back to '*' 
		boardUser[order[count-2]]='*';
		
	}
	
	public boolean gameOver (){
		for(int i =0; i<n; i++){
			if(flip[i]!=true){
				return false;
			}
		}
		return true;
	}
	
	public final int getFlips (){
		
		return count;
	}
	
	
	public void shuffleCards (){				//shuffle, problem 2

		for (int i=n-1; i>=0; i--){				//shuffle starts with all elements, decrease from end of array
			int j=(int)(Math.random()*i);		//generate a random index
			char temp= board[i];				//swap
			board[i]=board[j];
			board[j]=temp;
		}
	}
	
	
	
	
	
	
}
