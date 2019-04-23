package hw5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtil {
	 public static <E> ArrayList <E> merge(
			 Collection <? extends E> c1, Collection <? extends E> c2){

		 
		 Stream<? extends E> combined = Stream.concat(c1.stream(),c2.stream());
	     ArrayList<E> result = (ArrayList<E>) combined.collect(Collectors.toList());

		 return result;
	 }
	 
	 public static <E> ArrayList <E> select(
			 Collection <? extends E> coll , Predicate <? super E> pred){

		 ArrayList<E> result= new ArrayList<E>();
		 for(E e:coll){
			 if(pred.test(e)){
				 result.add(e);
			 }
		 }
		 return result;
	 }
}
