package edu.chalmers.brawlbuddies.util;

import java.util.ArrayList;
import java.util.List;

public class ListCopy {

	public static <E> List<E> simpleCopy(List<E> original){
		List<E> list = new ArrayList<E>();		//TODO not always create arraylist
		for(E element : original){
			list.add(element);
		}
		return list;
	}
}
