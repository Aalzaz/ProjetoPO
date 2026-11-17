package erp.core;

import java.io.Serializable;

public class Purchase implements Serializable, Comparable<Purchase> {
	//TODO
	
	private int _id;

	public int id(){
		return _id;
	}

	@Override 
	public int compareTo(Purchase pur){
		return _id - pur.id();
	}
}
