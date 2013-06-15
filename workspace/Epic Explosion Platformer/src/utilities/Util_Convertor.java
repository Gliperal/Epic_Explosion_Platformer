package utilities;

import java.util.ArrayList;

public class Util_Convertor<E> {
	public ArrayList<E> arrayToArrayList(E[] array) {
		ArrayList<E> arrayList = new ArrayList<E>();
		for(E e : array) {
			arrayList.add(e);
		}
		return arrayList;
	}
	
	public E[] ArrayListToArray(ArrayList<E> arrayList, E[] array) {
		for(int index = 0; index < arrayList.size(); index++) {
			array[index] = arrayList.get(index);
		}
		return array;
	}
}
