package hw4;
//final?????????????? are we changing ret_arr or state

public class CFGame {
	private final int[][] state;     
	private boolean isRedTurn;
	private boolean draw;
	
	{
	state = new int[7][6];
	for (int i=0; i<7; i++)
	  for (int j=0; j<6; j++)
	    state[i][j] = 0;
	isRedTurn = true; //red goes first
	
	draw=false;
	}
	
	public int[][] getState() {
	int[][] ret_arr = new int[7][6];
	for (int i=0; i<7; i++)
	  for (int j=0; j<6; j++)
	    ret_arr[i][j] = state[i][j];
	return ret_arr;
	}
	
	public boolean isRedTurn() {
	return isRedTurn;
	}
	

	public boolean play(int column) {
	  if(column<=7&&column>=1&& column == (int)column&&state[column-1][5]==0){
		  for( int i =0;i<6;i++){
			  if(state[column-1][i]==0){
				  if(isRedTurn){
					  state[column-1][i]=1;
					  isRedTurn=false;
					  break;
				  }else{
					  state[column-1][i]=-1;
					  isRedTurn=true;
					  break;
				  }
			  }
		  }
		  
		  return true;
	  }else{
		  return false;
	  }

}


	public int winner(){
		if(draw){
			return 0;
		}else{
			if(isRedTurn){
				return -1;
			}else{
				return 1;
			}
		}
	}

	public boolean isGameOver() {
		draw=false;
		  

		 //check for vertical first
		for (int i=0; i<7; i++){
		      for (int j=0; j<=2; j++){
		    	  if(state[i][j] == state[i][j+1] && state[i][j+2] ==state[i][j+1]
		    			  &&state[i][j+2] ==state[i][j+3] && state[i][j]!=0){
		    		  return true;
		    	  }
		      }
		}//first for
		  
		  //check for horizontal
		for (int i=0; i<=3; i++){
		      for (int j=0; j<6; j++){
		    	  if(state[i+1][j] == state[i][j] && state[i+2][j] ==state[i+1][j]
		    			  &&state[i+2][j] ==state[i+3][j] && state[i][j]!=0){
		    		  return true;
		    	  }
		      }
		}//first for
		  
		  //check for diagonal going right
		for (int i=0; i<=3; i++){
		      for (int j=0; j<=2; j++){
		    	  if(state[i][j]==state[i+1][j+1] && state[i+1][j+1] ==state[i+2][j+2]
		    			  &&state[i+2][j+2] ==state[i+3][j+3]&& state[i][j]!=0 ){
		    		  return true;
		    	  }
		      }
		}//first for
		  
		  //check for diagonal going left
		for (int i=3; i<=6; i++){
		      for (int j=0; j<=2; j++){
		    	  if(state[i][j]==state[i-1][j+1] &&state[i-1][j+1] ==state[i-2][j+2]
		    			  &&state[i-2][j+2] ==state[i-3][j+3]&& state[i][j]!=0 ){
		    		  return true;
		    	  }
		      }
		}//first for
		  
		boolean blank=false;
		for (int i=0; i<7; i++){
			for (int j=0; j<6; j++){
				if(state[i][j]==0){
					blank=true;
				}	
			}
		}
		if(!blank){
			draw= true;
			return true;
		}

		return false;

	}
}



