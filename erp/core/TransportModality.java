package erp.core;

import java.io.Serializable;


class TransportModality implements Serializable {
	@java.io.Serial
	private static final long serialVersionUID = 202608261201L;

	private String _name;
	private int _price;
	private int _time;

	public TransportModality(String name, int price, int time) {
		_name  = name;
		_price = price;
		_time  = time;
	}

	public String name() { return _name; }

	public int price() { return _price; }

	public int time() { return _time; }
}