package erp.core;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Purchase implements Serializable, Comparable<Purchase> {
	private Client 	_client;
	private Company _company;
	private int 	_date;
	private int 	_id;
	private List<PurchaseLine> _lines = new ArrayList<>();

	public int id(){
		return _id;
	}

	@Override 
	public int compareTo(Purchase pur){
		return _id - pur.id();
	}
}
