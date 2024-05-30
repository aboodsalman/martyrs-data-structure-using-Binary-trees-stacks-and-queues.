package Phase2;

public class TNode implements Comparable<Object>{
	private TNode left, right;
	private Object data;
	
	public TNode(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public TNode getLeft() {
		return left;
	}

	public void setLeft(TNode left) {
		this.left = left;
	}

	public TNode getRight() {
		return right;
	}

	public void setRight(TNode right) {
		this.right = right;
	}

	@Override
	public int compareTo(Object o) {
		if(o instanceof District) return ((District)data).compareTo((District)o);
		else if(o instanceof Location) return ((Location)data).compareTo((Location)o);
		else if(o instanceof Martyr) return ((Martyr)data).compareTo((Martyr)o);
		return ((Date)data).compareTo((Date)o);
	}
	
	public boolean equals(Object o) {
		if(o instanceof District) return ((District)data).equals((District)o);
		else if(o instanceof Location) return ((Location)data).equals((Location)o);
		return ((Date)data).equals((Date)o);
	}
	public String toString() {
		if(data instanceof District) return ((District)data).toString();
		else if(data instanceof Location) return ((Location)data).toString();
		return ((Date)data).toString();
	}
	
	
}
