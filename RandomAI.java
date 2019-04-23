package hw4;
import java.util.Random;

public class RandomAI implements CFPlayer {
	public int nextMove(CFGame g){
		int x;
		boolean blank=true;
		Random rand=new Random();
		do{
			x= rand.nextInt(7);
			if(g.getState()[x][5]==0){
				blank=false;
			}

		}while(blank);
		return x;
			

	}
	
	public String getName (){
		return "Random Player";
	}

}
