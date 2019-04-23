package hw6;


import java.io.*;
import java.util.*;

import hw6.Business;

public class YelpAnalysis{
	public static void main(String[] args) {
		Map<String,Integer> corpusDFCount = new HashMap<String,Integer>();
		PriorityQueue<Business> businessQueue =new PriorityQueue<Business>();
		Set<String> wordLibrary = new HashSet<String>();
		try{
	    	FileInputStream fis = new FileInputStream("/Users/Simon/Downloads/yelp_Dataset/yelpDatasetParsed_short.txt");
	    	BufferedInputStream bis = new BufferedInputStream(fis);
	        readBusiness(bis,businessQueue,corpusDFCount,wordLibrary);

	        bis.close();
	        fis.close();
	    }catch(IOException e){  // catch possible IOExceptions
	        System.out.println("File not found");
	    }
			

        for(int i=0;i<10;i++){
            Map<String,Double> tfidfScores = new HashMap<String,Double>();
            Business b = businessQueue.poll();

            b.getTfidfScore(tfidfScores,corpusDFCount,5);
            System.out.println(b);
            printTopWords(tfidfScores,30);
        }
	}
	
	
	public static void readBusiness(BufferedInputStream bis,PriorityQueue<Business> businessQueue,
            Map<String,Integer> corpusDFCount,Set<String> wordLibrary){  
		StringBuilder builder = new StringBuilder();	//record each field
		String[] properties = new String [4];			//the fields for business
		int count = 0;
		try{
			while(true){
				int b= bis.read();
				if(b==-1){
					break;
				}
				char c = (char) b;
				if(c != '{'&&c != '\n'){			//start
					if(c==','|| c =='}'){			//end of each field
						properties[count]=builder.toString();
						count++;

						if(count>3){
                          addDocumentCount(corpusDFCount,properties[3], wordLibrary);
                          businessQueue.add(new Business(properties));
                          if(businessQueue.size() > 10){
                        	  businessQueue.remove();	//if more than 10, remove the smallest
                        	  
                          }
                          count=0;
						}
						builder.setLength(0);
					}else{
						builder.append(c);
					}
				}//first if	
			}//while
		}catch(IOException e){
			System.out.println("IO Exception");
	    }
		
	}			

	
    private static void addDocumentCount(Map<String,Integer> corpusDFCount, 
    		String words,Set<String> wordLibrary){   
    	String[] parsed = words.split(" ");
    	
    	for(String word : parsed){				//split the review to words
    		if(!wordLibrary.contains(word)){
    			if(corpusDFCount.containsKey(word)){
    				corpusDFCount.put(word,corpusDFCount.get(word)+1); 
    			}else{
    				corpusDFCount.put(word, 1); 
    			}
    			wordLibrary.add(word);
    		}
    	}
    	wordLibrary.clear();
    	
	}
	

    public static <K,V> void printTopWords(Map<String,Double> scoreMap, int n){
    	
    	//sort the map to a treemap 
     	TreeMap<String, Double> sorted = new TreeMap<>(new Comparator<String>() {
    		@Override
    		public int compare(String o1, String o2) {
    			Double o1Value = scoreMap.get(o1);
    			Double o2Value = scoreMap.get(o2);
    			return o2Value.compareTo(o1Value);
    		}
    	});
    	sorted.putAll(scoreMap);  				
    	
        for(int i=0;i<n;i++){
           sorted.firstKey();        	
           System.out.print("("+sorted.firstKey()+","+sorted.get(sorted.firstKey())+") ");
           sorted.remove( sorted.firstKey());

        }
        System.out.println();
    }


    
	
}