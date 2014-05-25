package edu.chalmers.brawlbuddies.util;

import java.util.ArrayList;
import java.util.List;
/**
 * A class to copy of a list
 * @author David Gustafsson
 * @version 1.0
 *
 */
public class ListCopy {
	/**
	 * Copies the orignal list without copying the items in the list
	 * @param original - the orignal list
	 * @return List<E> - a copy of the list
	 */
	public static <E> List<E> simpleCopy(List<E> original){
		List<E> list = new ArrayList<E>();
		for(E element : original){
			list.add(element);
		}
		return list;
	}
}
