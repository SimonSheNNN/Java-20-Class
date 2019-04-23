package hw6;


import java.util.HashMap;
import java.util.Map;

public class Business implements Comparable<Business>{
  String businessID;
  String businessName;
  String businessAddress;
  String reviews;
  int reviewCharCount;
  
  public String toString() {
    return "-------------------------------------------------------------------------------\n"
          + "Business ID: " + businessID + "\n"
          + "Business Name: " + businessName + "\n"
          + "Business Address: " + businessAddress + "\n"
          //+ "Reviews: " + reviews + "\n"
          + "Character Count: " + reviewCharCount;
  }
  
  public Business(String[] fields){
    businessID = fields[0];
    businessName = fields[1];
    businessAddress = fields[2];
    reviews = fields[3];
    reviewCharCount = reviews.length();
  }
  
  public int compareTo(Business second){
    if(this.reviewCharCount < second.reviewCharCount) return -1;
    else if(this.reviewCharCount > second.reviewCharCount) return 1;
    else return 0;
  }
  


  public void getTfidfScore(Map<String,Double> tfidfScores, 
		  Map<String,Integer> corpusDFCount,int lowest){
	  
      Map<String,Integer> freqmap = new HashMap<String,Integer>();
      String[] parsed = reviews.split(" ");

      for(String word : parsed){
          
    	  if(freqmap.containsKey(word)){
    		  freqmap.put(word,freqmap.get(word)+1);
    	  }
    	  else{
    		  freqmap.put(word, 1); 
    	  }
      }
      
	  for(String word: freqmap.keySet()){				//compute tfidfScore
		  if(corpusDFCount.get(word) >= lowest){
			  double score = (double)(freqmap.get(word))/(double)(corpusDFCount.get(word));
			  tfidfScores.put(word, score);
		  }
	  }
  }
  
  
  
}


    
   