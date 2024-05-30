package Phase2;


public class SNode {
	private Object key;
	private SNode next;

	public SNode(Object key) {
		this.key = key;
	}

	public Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public SNode getNext() {
		return next;
	}

	public void setNext(SNode next) {
		this.next = next;
	}
	
	public String toString() {
		if(key instanceof District) return ((District)key).getName();
		return ((Martyr)key).getName();
	}
	
	public boolean equals(Object o) {
		if(key instanceof District) return ((District)key).equals((District)o);
		else if(key instanceof Location) return ((Location)key).equals((Location)o);
		return ((Martyr)key).equals((Martyr)o);
	}
	
	public int compareTo(Object o) {
		if(key instanceof District) return ((District)key).compareTo((District)o);
		else if(key instanceof Location) return ((Location)key).compareTo((Location)o);
		return ((Martyr)key).compareTo((Martyr)o);
	}

}