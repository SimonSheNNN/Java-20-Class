package hw5;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MetaCollection<E> extends AbstractCollection<E> {
  //keep reference added collections in this list
  private final ArrayList<Collection<E>> collectionList=new ArrayList<Collection<E>>();
  
  public MetaCollection(Collection<E>... c_arr){

	  for(Collection<E> collE: c_arr){
		  collectionList.add(collE);
		  System.out.println(collectionList.get(0)==collE);
	  }
	
	  
  }
  
  public void printc(){
	  System.out.println(collectionList);
  }
  
  public void addCollection(Collection<E> coll) { 
	  collectionList.add(coll);
  }
  
  @Override
  public Iterator<E> iterator() {
    return new JoinedIter();
  }

  @Override
  public int size() {
	  //return combined.length;
	  ArrayList<E> result=new ArrayList<E>();
	  for(int i=0;i<collectionList.size();i++){
		  Stream<E> combined = Stream.concat(result.stream(),collectionList.get(i).stream());
		  result= (ArrayList<E>) combined.collect(Collectors.toList());
	  }

	  return result.size();
  }

  
  private class JoinedIter implements Iterator<E>{
	private int itrCounter=0;
	private int collNum=0;
	private int single=0;
	
	@Override
	public boolean hasNext() {
		return itrCounter<size();
	}

	@Override
	public E next() {
		if(single<collectionList.get(collNum).size()){
			itrCounter++;
			return (E)collectionList.get(collNum).toArray()[single++];
		}else{
			collNum++;
			itrCounter++;
			single=0;
			return (E)collectionList.get(collNum).toArray()[single++];	
		}
				
		
			

	}
	   
  }
}
